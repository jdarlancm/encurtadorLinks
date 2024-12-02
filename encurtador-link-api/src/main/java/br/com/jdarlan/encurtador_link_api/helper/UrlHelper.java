package br.com.jdarlan.encurtador_link_api.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlHelper {

    private static boolean isValidUrl(String url) {
        try {
            URI uri = new URI(url);
            uri.toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    private static boolean isReachableUrl(String url) {
        try {
            URI uri = new URI(url);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 400);
        } catch (IOException | URISyntaxException e) {
            return false;
        }
    }

    public static boolean isValidAndReachableUrl(String url) {
        return isValidUrl(url) && isReachableUrl(url);
    }

}
