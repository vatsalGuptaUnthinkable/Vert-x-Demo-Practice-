package Unthinkable.pract;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        HttpServer server =vertx.createHttpServer();

        server.requestHandler(httpServerRequest -> {
             HttpServerResponse response = httpServerRequest.response();
             response.putHeader("content-type","text.plain");
             response.end("Please Let this work this time  :( ");
        });


        server.listen(8080);
    }
}
