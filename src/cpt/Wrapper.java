package com.wrapper.spotify;

// JavaFx library imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Spotify API library imports
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

// Main application
public class Wrapper extends Application{
    public static void main(String[] args) throws Exception{
        String client_id = "client"; // The app's client id
        String client_secret = "secret"; // The app's secret
        String redirect_uri = "uri"; // Redirect URI
        String scopes = "user-read-private user-read-email"; // Scope that allows app to request access to the user's data

        // Building the communication with the api that will retrieve thevlistening data of the user for us
        SpotifyApi.Builder()
        .setClientId(client_id)
        .setClientSecret(client_secret)
        .setRedirectUri(redirect_uri)
        .build();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }
}

