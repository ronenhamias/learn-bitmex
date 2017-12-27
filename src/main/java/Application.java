import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class Application {

  public static void main(String[] args) throws Exception {

    Vertx vertx = Vertx.vertx();

    HttpClientOptions options = new HttpClientOptions()
        .setDefaultHost("www.bitmex.com")
        .setDefaultPort(443)
        .setSsl(true);
            
    HttpClient client = vertx.createHttpClient(options);

    client.websocket("/realtime", websocket -> {
      websocket.handler(data->{
        System.out.println(data.toString());
      });
      System.out.println("Connected!");
      websocket.writeTextMessage("{\"op\": \"subscribe\", \"args\": [\"quoteBin1m\"]}");
    });
  
  
    System.out.println("done");
  }

  
  
  
  
  
}
