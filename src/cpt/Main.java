package cpt;

public class Main{
    public static void Main(String[] args) throws Exception{
        String client_id = "8c2fedf8d39e4e4b831dde153af912ff"; // The app's client id
        String client_secret = "a7646a64433740338b2759e87edfad7d"; // The app's secret
        String redirect_uri = "http://127.0.0.1:5500/"; // Redirect URI
        String scopes = "user-read-private user-read-email"; // Scope that allows app to request access to the user's data
    }
}