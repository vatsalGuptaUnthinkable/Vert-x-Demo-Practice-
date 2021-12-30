package Unthinkable.pract;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        HttpServer server =vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/").handler(httpServerRequest -> {
            // This handler will be called /  request
             HttpServerResponse response = httpServerRequest.response();
             response.putHeader("content-type","text.plain");
             response.end("BAsic home.  ");
        });

        router.route("/hello").handler(request -> {
            // This handler will be called /hello  request
            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");
            response.end("Hello World from Vatsal");
        });
        server.requestHandler(router).listen(8080);
    }
}
