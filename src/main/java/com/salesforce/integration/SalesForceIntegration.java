package com.salesforce.integration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;


public class SalesForceIntegration {

    public static void main(final String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://login.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9n_HvETGhr3BV1cTGocDP_PnIPJYkIMartY53LbDwtLkbSy4ifjki0Th36W8n.H67l8qXuFPA6Q43706T&client_secret=2C60333EA1C93F2A33D9156BAF587A94672C6773F4ECD90E5FACFE51E253DC6E&username=rinks@max.com&password=Rinku@1980EW6HR2ipkmUTXmCv6liAAmjb");


//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                System.out.println(instream.read());
            }
        }
    }
}
