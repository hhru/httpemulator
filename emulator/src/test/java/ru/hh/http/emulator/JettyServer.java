package ru.hh.http.emulator;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {

	private static final AtomicInteger PORT = new AtomicInteger(8080);
	
	private final int serverPort = PORT.incrementAndGet();
	
	private final Server server = new Server(serverPort);
	
	public int start() throws Exception {
        
        final WebAppContext context = new WebAppContext();
        context.setDescriptor("WEB-INF\\web.xml");
        context.setResourceBase("src\\main\\webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        server.setHandler(context);
 
		server.start();
		return serverPort;
	}

	public void stop() throws Exception{
		server.stop();
	}
	
	public int getServerPort() {
		return serverPort;
	}

	
}
