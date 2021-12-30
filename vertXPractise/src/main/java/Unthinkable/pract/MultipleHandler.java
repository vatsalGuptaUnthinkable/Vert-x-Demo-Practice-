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
        route.handler(ctx -> {

            HttpServerResponse response = ctx.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route1\n");

            // Call the next matching route after a 5 second delay
            ctx.vertx().setTimer(5000, tid -> ctx.next());
        });

        route.handler(ctx -> {

            HttpServerResponse response = ctx.response();
            response.write("route2\n");

            // Call the next matching route after a 5 second delay
            ctx.vertx().setTimer(5000, tid -> ctx.next());
        });

        route.handler(ctx -> {

            HttpServerResponse response = ctx.response();
            response.write("route3");

            // Now end the response
            ctx.response().end();
        });
        server.requestHandler(router).listen(8081);
    }
}
