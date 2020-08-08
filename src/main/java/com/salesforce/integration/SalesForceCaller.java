package com.salesforce.integration;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class SalesForceCaller {

    public static void main(final String[] args) throws IOException {
        URL urlForGetRequest = new URL("https://login.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9n_HvETGhr3BV1cTGocDP_PnIPJYkIMartY53LbDwtLkbSy4ifjki0Th36W8n.H67l8qXuFPA6Q43706T&client_secret=2C60333EA1C93F2A33D9156BAF587A94672C6773F4ECD90E5FACFE51E253DC6E&username=rinks@max.com&password=Rinku@1980EW6HR2ipkmUTXmCv6liAAmjb");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("POST");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            System.out.println("JSON String Result " + response.toString());
            final String resp = response.toString();
            final String token = resp.split("\\{\"access_token\":\"")[1].split("\"")[0];
            System.out.println("token " + token);
            getSubsequentResponse(token);
        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    private static void getSubsequentResponse(final String token) throws IOException {
        URL url = new URL("https://ap16.salesforce.com/services/apexrest/showAccountDetails/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization","Bearer "+token);
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        System.out.println("Response:-" + response.toString());
       // System.out.println(new JSONObject(response.toString().split("\\[")[1].split("]")));
    }
}
