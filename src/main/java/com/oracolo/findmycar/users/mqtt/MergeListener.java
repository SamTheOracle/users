package com.oracolo.findmycar.users.mqtt;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.mqtt.messages.TelegramUserMessage;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

@ApplicationScoped
public class MergeListener implements IMqttMessageListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Inject
	Event<TelegramUserMessage> telegramUserMessageEvent;

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		try {
			TelegramUserMessage telegramUserMessage = Json.decodeValue(Buffer.buffer(message.getPayload()), TelegramUserMessage.class);
			telegramUserMessageEvent.fire(telegramUserMessage);
		} catch (Exception e) {
			logger.error("Error decoding message.", e);
		}
	}
}
