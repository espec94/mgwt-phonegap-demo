package com.googlecode.mgwt.examples.showcase.client.request;

import com.google.gwt.http.client.*;

public class ServerRequestUtil {

    public static void GetRequest(String url) {

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                    System.out.println(exception.toString());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        String headers= response.getHeadersAsString();
                        String statusText= response.getStatusText();
                        int statusCode= response.getStatusCode();
                        String toString= response.toString();


                        System.out.println("responseText: "+responseText);
                        System.out.println("headers: "+headers);
                        System.out.println("statusTest: "+statusText);
                        System.out.println("statusCode: "+statusCode);
                        System.out.println("toString: "+toString);

                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }

    }
}
