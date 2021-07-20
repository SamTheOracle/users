package com.oracolo.findmycar.users.mqtt.messages;

import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RetrySyncMessage {

	public String messageId;

	public List<String> uniqueKeys;

	@Override
	public String toString() {
		return "RetrySyncMessage{" + "messageId='" + messageId + '\'' + ", uniqueKeys=" + uniqueKeys + '}';
	}
}
