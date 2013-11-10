package edu.drexel;

import org.vertx.java.core.json.JsonObject;

public class Config {

	// http server settings
    public final String serverAddress;
    public final int serverPort;
    
    public Config(JsonObject config) {

        serverAddress = config.getString("serverAddress", "0.0.0.0");
        serverPort = (Integer)config.getNumber("serverPort", 8080);
        
    }
}
