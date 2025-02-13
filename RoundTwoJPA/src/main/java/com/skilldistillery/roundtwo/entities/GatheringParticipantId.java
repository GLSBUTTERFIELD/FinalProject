package com.skilldistillery.roundtwo.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GatheringParticipantId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "gathering_id")
	private int gatheringId;
	@Column(name = "user_id")
	private int userId;

	public GatheringParticipantId() {
	}

	public GatheringParticipantId(int gatheringId, int userId) {
		super();
		this.gatheringId = gatheringId;
		this.userId = userId;
	}

	public int getGatheringId() {
		return gatheringId;
	}

	public void setGatheringId(int gatheringId) {
		this.gatheringId = gatheringId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gatheringId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GatheringParticipantId other = (GatheringParticipantId) obj;
		return gatheringId == other.gatheringId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "GatheringParticipantId [gatheringId=" + gatheringId + ", userId=" + userId + "]";
	}

}
