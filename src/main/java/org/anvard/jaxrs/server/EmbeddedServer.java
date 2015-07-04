package org.anvard.jaxrs.server;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public final class EmbeddedServer {

	private static final int SERVER_PORT = 8680;

	private EmbeddedServer() {
	}

	public static void main(String[] args) throws Exception {
		URI baseUri = UriBuilder.fromUri("http://localhost").port(SERVER_PORT)
				.build();
		ResourceConfig config = new ResourceConfig(Calculator.class);
		Server server = JettyHttpContainerFactory.createServer(baseUri, config,
				false);

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		resourceHandler.setResourceBase(".");
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[] { resourceHandler,
				server.getHandler(), new DefaultHandler() });
		server.setHandler(handlerCollection);
		server.start();
		server.join();
	}
}
