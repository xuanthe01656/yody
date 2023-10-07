package library;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.spire.ms.System.Security.Cryptography.X509Certificates.X509Certificate;

import model.bean.GoogleUser;

public class GoogleLb {
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String code = request.getParameter("code");
//		String accessToken = getToken(code);
//		GoogleUser user = getUserInfo(accessToken);
//		System.out.println(user);
//	}

	public static String getToken(String code) throws ClientProtocolException, IOException {
		// call api to get token
//		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
//				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
//						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
//						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
//						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
//				.execute().returnContent().asString();
//
//		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
//		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
//		System.out.println("access is:"+accessToken);
		String accessToken = "";
		try {

            TrustStrategy trustStrategy = new TrustStrategy(){
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

				@Override
				public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					return false;
				}
            };
            SSLConnectionSocketFactory sslsf = null;
            try {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(trustStrategy);
                sslsf = new SSLConnectionSocketFactory(
                        builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
                        sslsf).build();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {

            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            Unirest.setHttpClient(client);
    		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
			.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
					.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
					.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
					.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
			.execute().returnContent().asString();
            HttpResponse<String> response1 = Unirest.post("https://472038623800-qq9ac8me6to17ur356s14e3due4sqpmi.apps.googleusercontent.com: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@"+code+"?grant_type=client_credentials")
                    .header("cache-control", "no-cache")
                    .asString();

            JsonElement je = new JsonParser().parse(response1.getBody());
            accessToken = je.getAsJsonObject().get("access_token").getAsString().toString();
            return  accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


		//return accessToken;
	}

	public static GoogleUser getUserInfo(String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		GoogleUser googlePojo = new Gson().fromJson(response, GoogleUser.class);

		return googlePojo;
	}
}
