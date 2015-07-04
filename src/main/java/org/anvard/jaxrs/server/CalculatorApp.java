package org.anvard.jaxrs.server;

import org.glassfish.jersey.server.ResourceConfig;

public class CalculatorApp extends ResourceConfig {

	public CalculatorApp() {
		packages("org.anvard.jaxrs");
	}
}
