package com.rest.automateapi.steps;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;

/**
 * For testing this project, XAMPP Apache was set to listen to port 89.
 * 
 * API automated testing
 * 
 * Port can be changed via terminal run: 
 * mvn test -Dserver.port=8080 -Dserver.host=http://localhost
 */
public class ServerDetails {
	
	public static final Logger logger = LoggerFactory.getLogger(ServerDetails.class);
	
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(89);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/qa-exercise/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
        
        logger.info("[WEB API TEST]");
    }
    
}
