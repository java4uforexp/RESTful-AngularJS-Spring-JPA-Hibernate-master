package com.amq.cma.oauth.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.amq.cma.model.Company;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OAuth2Client {

	

	public static void main(String[] args) {

		Properties config = OAuthUtils.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);
		String resourceServerUrl = config.getProperty(OAuthConstants.RESOURCE_SERVER_URL);
		String username = config.getProperty(OAuthConstants.USERNAME);
		String password = config.getProperty(OAuthConstants.PASSWORD);
		String grantType = config.getProperty(OAuthConstants.GRANT_TYPE);
		String authenticationServerUrl = config
				.getProperty(OAuthConstants.AUTHENTICATION_SERVER_URL);
		
		if (!OAuthUtils.isValid(username) || !OAuthUtils.isValid(password)
				|| !OAuthUtils.isValid(authenticationServerUrl)
				|| !OAuthUtils.isValid(grantType)) {
			System.out
					.println("Please provide valid values for username, password, authentication server url and grant type");
			System.exit(0);
		}
		if (!OAuthUtils.isValid(resourceServerUrl)) {
			// Resource server url is not valid. Only retrieve the access token
			System.out.println("Retrieving Access Token");
			OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
			String accessToken = OAuthUtils.getAccessToken(oauthDetails);
			if(OAuthUtils.isValid(accessToken)){
			System.out
					.println("Successfully retrieved Access token for Password Grant: "
							+ accessToken);
			}
		} else {
			// Response from the resource server must be in Json or Urlencoded or xml
			System.out.println("Resource endpoint url: " + resourceServerUrl);
			System.out.println("Attempting to retrieve protected resource");
			String output = OAuthUtils.getProtectedResource(config);
			ObjectMapper mapper = new ObjectMapper();
			try {
				//TypeReference<List<Company>> mapType = new TypeReference<List<Company>>() {};
				//List<Company> companyList = mapper.readValue(output, mapType;);
				Company company = mapper.readValue(output, Company.class);
				System.out.println(company);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
