package com.oracolo.findmycar.users.mqtt;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@ApplicationScoped
public class SyncListener implements IMqttMessageListener {
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {

	}
}
