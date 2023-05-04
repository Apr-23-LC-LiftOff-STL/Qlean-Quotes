package org.launchcode.qleanquotes.handlers;

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.api.OAuthApi;
import com.squareup.square.SquareClient;
import com.squareup.square.models.ObtainTokenRequest;
import com.squareup.square.models.ObtainTokenResponse;
import com.squareup.square.Environment;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.LinkedList;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class OAuthHandler {

    // Options are Environment.SANDBOX, Environment.PRODUCTION
    private static final Environment ENVIRONMENT = Environment.SANDBOX;

    // Your application's ID and secret, available from the OAuth tab in the
    // Developer Dashboard
    // If you are testing the OAuth flow in the sandbox, use your sandbox
    // application
    // ID and secret. If you are testing in production, use the production
    // application ID and secret.
    private static final String APPLICATION_ID = "REPLACE ME";
    private static final String APPLICATION_SECRET = "REPLACE ME";
    // Modify this list as needed
    private static final String[] SCOPES = { "MERCHANT_PROFILE_READ", "PAYMENTS_WRITE_ADDITIONAL_RECIPIENTS",
            "PAYMENTS_WRITE", "PAYMENTS_READ" };
    // Serves the authorize link

    static class AuthorizeHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {

            String connect_host;
            // For testing in sandbox, the base url is
            // "https://connect.squareupsandbox.com",
            // and https://connect.squareup.com for production mode
            if (ENVIRONMENT == Environment.SANDBOX) {
                connect_host = "https://connect.squareupsandbox.com";
            } else {
                connect_host = "https://connect.squareup.com";
            }

            // Reject non-GET requests
            if (!t.getRequestMethod().equals("GET")) {
                t.sendResponseHeaders(405, 0);
                t.getResponseBody().close();
            }

            String authorizeURL = String.format(
                    "<a href=\"%s/oauth2/authorize?client_id=%s&scope=%s\">Click here</a> ",
                    connect_host,
                    APPLICATION_ID,
                    String.join("+", SCOPES))
                    + "to authorize the application.";

            System.out.println(authorizeURL);

            final byte[] out = (authorizeURL).getBytes("UTF-8");
            t.sendResponseHeaders(200, out.length);
            t.getResponseBody().write(out);
            t.getResponseBody().close();
        }
    }

    // Serves requests from Square to your application's redirect URL
    // Note that you need to set your application's Redirect URL to
    // http://localhost:8000/callback from your application dashboard
    static class CallbackHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {

            System.out.println("Request received");

            if (!t.getRequestMethod().equals("GET")) {
                t.sendResponseHeaders(405, 0);
                t.getResponseBody().close();
            }

            // Extract the returned authorization code from the URL
            URI requestUri = t.getRequestURI();
            List<NameValuePair> queryParameters = URLEncodedUtils.parse(requestUri, "UTF-8");
            String authorizationCode = null;
            for (NameValuePair param : queryParameters) {
                System.out.println(param.getName());
                System.out.println(param.getValue());
                if (param.getName().equals("code")) {
                    authorizationCode = param.getValue();
                    break;
                }
            }

            if (authorizationCode == null) {

                // The request to the Redirect URL did not include an authorization code.
                // Something went wrong.
                System.out.println("Authorization failed!");
                t.sendResponseHeaders(200, 0);
                t.getResponseBody().close();
                return;
            }

            SquareClient client = new SquareClient.Builder()
                    .environment(ENVIRONMENT)
                    .userAgentDetail("sample_app_oauth_java") // Remove or replace this detail when building your own app
                    .build();

            List<String> bodyScopes = new LinkedList<>();
            for (String scope : SCOPES) {
                bodyScopes.add(scope);
            }

            // Create obtain token request body
//            ObtainTokenRequest body = new ObtainTokenRequest.Builder(
//                    APPLICATION_ID,
//                    APPLICATION_SECRET,
//                    "authorization_code")
//                    .code(authorizationCode)
//                    .scopes(bodyScopes)
////                    .build();
//
//            OAuthApi oAuthApi = client.getOAuthApi();
//
//            // Call obtain token API and print the results on success
//            // In production, you should never write tokens to the page.
//            // You should encrypt the tokens and handle them securely.
//            oAuthApi.obtainTokenAsync(body).thenAccept(result -> {
//                if (result != null) {
//                    System.out.println("Access token: " + result.getAccessToken());
//                    System.out.println("Refresh token: " + result.getRefreshToken());
//                    System.out.println("Merchant id: " + result.getMerchantId());
//                    System.out.println("Authorization succeeded!");
//                    try {
//                        t.sendResponseHeaders(200, 0);
//                        t.getResponseBody().close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).exceptionally(exception -> {
//                try {
//                    t.sendResponseHeaders(405, 0);
//                    t.getResponseBody().close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            });
        }
    }

    // Start up the server, listening on port 8000
    public static void main(String[] args) {

        try {
            int portNumber = 8000;
            HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
            server.createContext("/callback", new CallbackHandler());
            server.createContext("/", new AuthorizeHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Listening on port " + portNumber);
        } catch (IOException e) {
            System.out.println("Server startup failed. Exiting.");
            System.exit(1);
        }
    }
}

