package com.midzie.WeatherOnRoute.model.manager;
import org.json.JSONObject;
import java.net.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NetManager {

	public String getStringFromURL(String url) {
		Client client = ClientBuilder.newClient();

		URI rootTargetURI;
		try {
			rootTargetURI = new URI(url);
			WebTarget rootWebTarget = client.target(rootTargetURI);
			Invocation.Builder invocationBuilder = rootWebTarget.request(MediaType.TEXT_PLAIN_TYPE);

			Response response = invocationBuilder.get();
			return response.readEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return null;
	}

	public JSONObject getJSONFromURL(String url) {
		String content = getStringFromURL(url);		
		return new JSONObject(content);
	}

}
