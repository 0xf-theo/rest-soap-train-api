package soap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Rest {

    public static String callRestService(String url, String requestBody, String method) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseBody = null;

        try {
            if ("GET".equals(method)) {
                HttpGet httpGet = new HttpGet(url);
                response = httpClient.execute(httpGet);
            } else if ("POST".equals(method)) {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new StringEntity(requestBody));
                httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
                response = httpClient.execute(httpPost);
            } else {
                throw new UnsupportedOperationException("Méthode HTTP non supportée");
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseBody;
    }

	
}
