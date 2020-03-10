import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SpotifyAPI {
  private static final String clientId = "a3e77200de3a4e1285188b9e4c9f4f46";
  private static final String clientSecret = "7ca121d99ed443aebefe81f1bc9dc125";

  public static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
  private static final ClientCredentialsRequest clientCredentialsRequest =
      spotifyApi.clientCredentials().build();

  public SpotifyAPI() {
    
  }

  public static String clientCredentials_Sync() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      //System.out.println(clientCredentials.getAccessToken());
      //System.out.println("Expires in: " + clientCredentials.getExpiresIn());
      return clientCredentials.getAccessToken();
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
  }

  public static void clientCredentials_Async() {
    try {
      final CompletableFuture<ClientCredentials> clientCredentialsFuture =
          clientCredentialsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final ClientCredentials clientCredentials = clientCredentialsFuture.join();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

}
