package com.nagp.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

/**
 * Springboot Application launcher class
 * 
 * @author santoshkumar02
 *
 */
@SpringBootApplication
public class OrderServiceApplication {

	/**
	 * Launches the application
	 * 
	 * @param args - Application startup arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/**
	 * Gives an object of Tracer for open tracing of service
	 * 
	 * @return Tracer object
	 */
	@Bean
	public Tracer getTracer() {
		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
				.withType(ConstSampler.TYPE).withParam(1);
		Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
				.withLogSpans(true);
		Configuration config = new Configuration("order-service").withSampler(samplerConfig)
				.withReporter(reporterConfig);
		return config.getTracer();
	}

}
