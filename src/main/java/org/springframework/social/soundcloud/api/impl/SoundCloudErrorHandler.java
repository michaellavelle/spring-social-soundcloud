/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.soundcloud.api.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.ServerDownException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Subclass of {@link DefaultResponseErrorHandler} that handles errors from SoundCloud's
 * API, interpreting them into appropriate exceptions.
 * @author Michael Lavelle
 */
class SoundCloudErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		List<Map<String,String>> errorDetails = extractErrorDetailsFromResponse(response);
		
		if (errorDetails == null) {
			handleUncategorizedError(response, errorDetails);
		}

		handleSoundCloudError(response.getStatusCode(), errorDetails);
		
		// if not otherwise handled, do default handling and wrap with UncategorizedApiException
		handleUncategorizedError(response, errorDetails);			
	}
	
	@Override 
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if(super.hasError(response)) {
			return true;
		}
		// only bother checking the body for errors if we get past the default error check
			String content = readFully(response.getBody());		
			return content.startsWith("{\"errors\":");
		
	}
	
	
	private boolean isMessageContainingText(List<String> messages,String text)
	{
		for (String message :messages)
		{
			return message.contains(text);
		}
		return false;
	}
	
	private boolean isMessageStartsWithText(List<String> messages,String text)
	{
		for (String message :messages)
		{
			return message.startsWith(text);
		}
		return false;
	}
	
	private String constructMessage(List<String> messages)
	{
		return messages.toString();
	}

	/**
	 * Examines the error data returned from SoundCloud and throws the most applicable exception.
	 * @param errorDetails a Map containing an "error_message"
	 */
	void handleSoundCloudError(HttpStatus statusCode, List<Map<String, String>> errorDetailsList) {
		// Can't trust the type to be useful. It's often OAuthException, even for things not OAuth-related. 
		// Can rely only on the message (which itself isn't very consistent).
		List<String> messages = new ArrayList<String>();
		for (Map<String,String> errorDetails : errorDetailsList)
		{
			String message = errorDetails.get("error_message");
			messages.add(message);
		}
		String message = constructMessage(messages);
		
		if (statusCode == HttpStatus.OK) {
			
		} else if (statusCode == HttpStatus.BAD_REQUEST) {
				throw new ResourceNotFoundException("soundcloud", message);

		}
		else if (statusCode == HttpStatus.NOT_FOUND) {
			throw new ResourceNotFoundException("soundcloud", message);

		}
		else if (statusCode == HttpStatus.UNAUTHORIZED) {
			if (isMessageStartsWithText(messages,"invalid_token")) {
				handleInvalidAccessToken(message);
			}
			throw new NotAuthorizedException("soundcloud", message);
		} else if (statusCode == HttpStatus.FORBIDDEN) {
			
				throw new OperationNotPermittedException("soundcloud", message);
		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
				throw new InternalServerErrorException("soundcloud", message);
		}
		else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE) {
			throw new ServerDownException("soundcloud", message);
	}
	}

	private void handleInvalidAccessToken(String message) {
		if (message.contains("invalid_token")) {
			throw new InvalidAuthorizationException("soundcloud", "An invalid token was supplied");
		} 
		 else {
			 throw new InvalidAuthorizationException("soundcloud", message);				
		}
	}

	private void handleUncategorizedError(ClientHttpResponse response, List<Map<String, String>> errorDetails) {
		try {
			super.handleError(response);
		} catch (Exception e) {
			if (errorDetails != null) {
				String m = "";
				throw new UncategorizedApiException("soundcloud", m, e);
			} else {
				throw new UncategorizedApiException("soundcloud", "No error details from SoundCloud", e);
			}
		}
	}

	/*
	 * Attempts to extract SoundCloud error details from the response.
	 * Returns null if the response doesn't match the expected JSON error response.
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String,String>> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());

		List<String> authenticateHeaders = response.getHeaders().get("Www-Authenticate");
		String authenticateHeader = authenticateHeaders == null || authenticateHeaders.size() == 0 ? null : authenticateHeaders.get(0);
		String json = null;
		if (authenticateHeader != null)
		{
			json = "{" + authenticateHeader.replace('=', ':').replace("OAuth realm", "\"OAuth realm\"").replace("error", "\"error\"") + "}";
			try {
			    Map<String, String> responseMap = mapper.<Map<String, String>>readValue(json, new TypeReference<Map<String, String>>() {});
			    List<Map<String,String>> errorsList = new ArrayList<Map<String,String>>();
			    if (responseMap.containsKey("error")) {
			    	Map<String,String> errorMap = new HashMap<String,String>();
			    	errorMap.put("error_message", responseMap.get("error"));
			    	errorsList.add( errorMap);
			    	return errorsList;
			    }
			  
			} catch (JsonParseException e) {
				return null;
			}
		}
		else
		{
			json = readFully(response.getBody());
			try {
			    Map<String, Object> responseMap = mapper.<Map<String, Object>>readValue(json, new TypeReference<Map<String, Object>>() {});
			    if (responseMap.containsKey("errors")) {
			    	return (List<Map<String,String>>) responseMap.get("errors");
			    }
			    else
			    {
			    	return null;
			    }
			} catch (JsonParseException e) {
				return null;
			}
		}
		return null;
	
	}
	
	private String readFully(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		while (reader.ready()) {
			sb.append(reader.readLine());
		}
		return sb.toString();
	}
}
