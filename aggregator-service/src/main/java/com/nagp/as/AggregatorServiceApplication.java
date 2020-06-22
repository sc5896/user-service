package com.nagp.as;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
public class AggregatorServiceApplication {

	/**
	 * Launches the application
	 * 
	 * @param args - Application startup arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AggregatorServiceApplication.class, args);
	}

	/**
	 * This method gives an object of restTemplate.
	 * 
	 * @return RestTemplate object
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
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
		Configuration config = new Configuration("aggregator-service").withSampler(samplerConfig)
				.withReporter(reporterConfig);
		return config.getTracer();
	}
}
