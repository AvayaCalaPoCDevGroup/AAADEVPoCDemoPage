/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.AAADEVPoCDemoPage.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.avaya.collaboration.ssl.util.SSLProtocolType;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.avaya.collaboration.ssl.util.SSLUtilityFactory;
import com.avaya.collaboration.util.logger.Logger;

import service.AAADEVPoCDemoPage.Beans.UserBean;
import service.AAADEVPoCDemoPage.Util.Constants;

public class HttpTokenRequest {
	
	private final SSLProtocolType protocolTypeAssistant;
	private final SSLContext sslContextAssistant;
	private final Logger logger = Logger.getLogger(getClass());
	public HttpTokenRequest() throws SSLUtilityException {
		super();
		this.protocolTypeAssistant = SSLProtocolType.TLSv1_2;
		this.sslContextAssistant = SSLUtilityFactory.createSSLContext(this.protocolTypeAssistant);
	}

	public JSONObject accessRequest(UserBean userBean, String password, String country, String _cliente)
			throws UnsupportedOperationException, IOException {
		final String URI = Constants.TOKEN_END_POINT;
		logger.info("HttpTokenRequest Inicio");
		
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		logger.info("URI : " + URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();  
		StringBody userName = new StringBody(userBean.getUserName(), ContentType.TEXT_PLAIN);
		StringBody passwordBody = new StringBody(password, ContentType.TEXT_PLAIN);
		StringBody pais = new StringBody(country, ContentType.TEXT_PLAIN);
		StringBody cliente = new StringBody(_cliente, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_ONE, ContentType.TEXT_PLAIN);
		
		reqEntity.addPart(Constants.USER_PART, userName);
		reqEntity.addPart(Constants.PASSWORD_PART, passwordBody);
		reqEntity.addPart(Constants.COUNTRY_PART, pais);
		reqEntity.addPart(Constants.CLIENT_PART, cliente);
		reqEntity.addPart(Constants.GRANT_TYPE , grantType);
        HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        logger.info("HttpTokenRequest Pre Execute");
        final HttpResponse response = client.execute(postMethod);
        logger.info("HttpTokenRequest Post Execute");
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        logger.info("HttpTokenRequest result Access  " + result.toString());
        JSONObject resp;
        try {
        	resp = new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
        } catch (Exception e) {
        	resp = new JSONObject().put("status", "error")
        			.put("message", result.toString())
        			.put("code", 666);
        	logger.info("HttpTokenRequest result catch error:  " + result.toString());
        }
        return resp;
	}

	public JSONObject refreshAccessRequest(String refreshToken) throws UnsupportedOperationException, IOException {
		final String URI = Constants.TOKEN_END_POINT;
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();  
		StringBody refreshTokenPart = new StringBody(refreshToken, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_TWO, ContentType.TEXT_PLAIN);
		reqEntity.addPart(Constants.REFRESH_TOKEN, refreshTokenPart);
		reqEntity.addPart(Constants.GRANT_TYPE, grantType);
		HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        logger.info("Refresh  " + result.toString());
        return new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
	}

	public JSONObject validateAccessToken(String accessToken) throws ClientProtocolException, IOException {
		final String URI = Constants.TOKEN_END_POINT;
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
		StringBody accessTokenPart = new StringBody(accessToken, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_THREE, ContentType.TEXT_PLAIN);
		reqEntity.addPart(Constants.ACCESS_TOKEN, accessTokenPart);
		reqEntity.addPart(Constants.GRANT_TYPE, grantType);
		HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        logger.info("Validate  " + result.toString());
        return new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
	}
}
