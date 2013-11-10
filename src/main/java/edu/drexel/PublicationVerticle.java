package edu.drexel;

import org.vertx.java.core.Handler;
import org.vertx.java.platform.Verticle;
import com.jetdrone.vertx.yoke.middleware.*;
import com.jetdrone.vertx.yoke.Yoke;

import edu.drexel.model.DbMock;
import edu.drexel.services.PublicationService;
import edu.drexel.services.PublicationServiceImpl;
import edu.drexel.util.MappingJackson2StringMarshaller;

/*
This is a Java verticle
 */
public class PublicationVerticle extends Verticle {

	  public void start() {

		  final Config config = new Config(container.config());
	      final PublicationService pubService = new PublicationServiceImpl(new DbMock());
	      final MappingJackson2StringMarshaller marshaller = new MappingJackson2StringMarshaller().setPrettyPrint(true);
	      
	      final Yoke yoke = new Yoke(vertx)
	      .use(new Router()
	      	.get("/publications", new Handler<YokeRequest>() {
	          	@Override
	          	public void handle(YokeRequest request) {
	          		request.response()
	          			.setContentType("text/javascript", "UTF-8")
	          			.end(marshaller.marshall(pubService.getAllPubs()));
	          	}
	        })
	        .get("/publications/bar", new Handler<YokeRequest>() {
	        	@Override
	        	public void handle(YokeRequest request) {
	        		request.response().end("Hello world2!");
	        	}
	        })
	        .get("/publications/:id", new Handler<YokeRequest>() {
	        	@Override
	        	public void handle(YokeRequest request) {
	        		Integer id = Integer.valueOf(request.params().get("id"));
	        		request.response()
	        			.setContentType("text/javascript", "UTF-8")
	        			.end(marshaller.marshall(pubService.getPubsById(id)));
	        	}
	        }))
	        .listen(config.serverPort, config.serverAddress);
	      container.logger().info("Vert.x Server listening on " + config.serverAddress + ":" + config.serverPort);
	  }
}
