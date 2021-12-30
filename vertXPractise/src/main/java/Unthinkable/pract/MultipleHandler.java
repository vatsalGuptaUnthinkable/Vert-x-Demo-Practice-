package Unthinkable.pract;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MultipleHandler {
    public static void main(String args[]){
        Vertx vertx = Vertx.vertx();
        HttpServer server =vertx.createHttpServer();
        Router router = Router.router(vertx);
        Route route = router.route("/multiple");
        route.handler(req -> {

            HttpServerResponse response = req.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("Response from 1st five second \n");

            // Call the next matching route after a 5 second delay
            req.vertx().setTimer(5000, tid -> req.next());
        });

        route.handler(req -> {

            HttpServerResponse response = req.response();
            response.write("Response from 2nd five second \n");

            // Call the next matching route after a 5 second delay
            req.vertx().setTimer(5000, tid -> req.next());
        });

        route.handler(req -> {

            HttpServerResponse response = req.response();
            response.write("Response from 3rd five second ");

            // Now end the response
            req.response().end();
        });
        server.requestHandler(router).listen(8081);
    }
}
