package com.googlecode.mgwt.examples.showcase.client.common;

import com.google.gwt.http.client.URL;

public class UrlBuilder {

    public static String buildURL(String... urls) {

        StringBuilder builder = new StringBuilder();

        for (String current : urls) {
            builder.append(current).append("/");
        }

        return URL.encode(builder.toString());
    }

}
