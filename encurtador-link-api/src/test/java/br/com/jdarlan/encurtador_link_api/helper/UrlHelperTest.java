package br.com.jdarlan.encurtador_link_api.helper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class UrlHelperTest {

    @Test
    void testIsValidUrl_ValidUrl() {
        assertTrue(UrlHelper.isValidAndReachableUrl("https://www.google.com.br"));
    }

    @Test
    void testIsValidUrl_InvalidUrl() {
        assertFalse(UrlHelper.isValidAndReachableUrl("ht!tp://invalid-url"));
    }

    @Test
    void testeIsReachableUrl_ReachableUrl() throws Exception {
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getResponseCode()).thenReturn(200);

        try(MockedStatic<URL> mockedUrl = Mockito.mockStatic(URL.class)) {
            URL mockUrl = mock(URL.class);
            when(mockUrl.openConnection()).thenReturn(mockConnection);
            mockedUrl.when(() -> new URI("https://reachable-url.com").toURL()).thenReturn(mockUrl);
            assertTrue(UrlHelper.isValidAndReachableUrl("https://reachable-url.com"));
        }
    }

    @Test
    void testIsReachableUrl_UnreachableUrl() throws Exception {
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getResponseCode()).thenThrow(IOException.class);

        try(MockedStatic<URL> mockedUrl = Mockito.mockStatic(URL.class)) {
            URL mockUrl = mock(URL.class);
            when(mockUrl.openConnection()).thenThrow(IOException.class);
            mockedUrl.when(() -> new URI("https://unreachable-url.com").toURL()).thenReturn(mockUrl);
            assertFalse(UrlHelper.isValidAndReachableUrl("https://unreachable-url.com"));
        }
    }
}
