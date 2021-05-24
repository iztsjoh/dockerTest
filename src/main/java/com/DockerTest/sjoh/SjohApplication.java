package com.DockerTest.sjoh;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class SjohApplication {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(SjohApplication.class);
	private static final int DEFAULT_PORT = 8081;

	public static void main(String[] args) {
		//SpringApplication.run(SjohApplication.class, args);

		try(ServerSocket listener =  new ServerSocket(DEFAULT_PORT))
		{

			System.out.println("Http Server started at "+DEFAULT_PORT+" port");
			Socket socket;
			while ((socket = listener.accept())  != null) {
				ServerHandler serverHandler = new ServerHandler(socket);
				serverHandler.start();
			}
                /*
                while (true) {
                    Socket socket = listener.accept();

                }

                 */
		}catch(Exception e){
			log.info(e.getMessage());
		}


	}

}


