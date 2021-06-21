package com.oracolo.findmycar.users;

import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.client.RecordPublisherRestClient;
import com.oracolo.findmycar.users.client.dto.RecordDto;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class UsersMicroserviceLifecycle {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@ConfigProperty(name = "microservice.host")
	String host;

	@ConfigProperty(name = "microservice.port")
	String port;

	@ConfigProperty(name = "microservice.name")
	String name;

	@ConfigProperty(name = "microservice.root")
	String root;

	@ConfigProperty(name = "microservice.post_service")
	Boolean mustPost;

	@Inject
	@RestClient
	RecordPublisherRestClient recordPublisherRestClient;

	void onStart(@Observes StartupEvent ev) throws UnknownHostException {
		if(mustPost){
			logger.info("Users microservice started with configuration: {}, {}, {}, {}",port, name, host, root);
			RecordDto.LocationDto locationDto = new RecordDto.LocationDto();
			locationDto.port = Integer.parseInt(port);
			locationDto.root = root;
			locationDto.host = host;
			locationDto.ssl = false;
			locationDto.endpoint = "http://" + host + ":" + port + root;
			RecordDto recordDto = new RecordDto();
			recordDto.location = locationDto;
			recordDto.name = name;
			logger.info("Posting microservice {}", recordDto);
			try {
				Response response = recordPublisherRestClient.postRecord(recordDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
