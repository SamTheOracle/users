package com.oracolo.findmycar.users.mqtt;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

@ApplicationScoped
public class SyncListener implements IMqttMessageListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Inject
	Event<KeyChatValuesMessage> event;

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		try {
			KeyChatValuesMessage keyChatValuesMessage = Json.decodeValue(Buffer.buffer(message.getPayload()), KeyChatValuesMessage.class);
			event.fire(keyChatValuesMessage);
		} catch (Exception e) {
			logger.error("Error decoding message.", e);
		}
	}
}
