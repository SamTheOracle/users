package com.oracolo.findmycar.users.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Metadata {

	@Column(name = "insert_date", nullable = false)
	private LocalDateTime insertDate;

	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Metadata))
			return false;
		Metadata metadata = (Metadata) o;
		return Objects.equals(insertDate, metadata.insertDate) && Objects.equals(lastUpdate, metadata.lastUpdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(insertDate, lastUpdate);
	}

	@Override
	public String toString() {
		return "Metadata{" + "insertDate=" + insertDate + ", lastUpdate=" + lastUpdate + '}';
	}
}
