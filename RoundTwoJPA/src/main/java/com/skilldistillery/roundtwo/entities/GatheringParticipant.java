package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gathering_participant")
public class GatheringParticipant {

	@EmbeddedId
	private GatheringParticipantId id;

	@Column(name = "participant_rating")
	private int participantRating;
	@Column(name = "participant_notes")
	private String participantNotes;
	@Column(name = "host_rating")
	private int hostRating;
	@Column(name = "host_notes")
	private String hostNotes;
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;

	public GatheringParticipant() {
	}

	public GatheringParticipantId getId() {
		return id;
	}

	public void setId(GatheringParticipantId id) {
		this.id = id;
	}

	public int getParticipantRating() {
		return participantRating;
	}

	public void setParticipantRating(int participantRating) {
		this.participantRating = participantRating;
	}

	public String getParticipantNotes() {
		return participantNotes;
	}

	public void setParticipantNotes(String participantNotes) {
		this.participantNotes = participantNotes;
	}

	public int getHostRating() {
		return hostRating;
	}

	public void setHostRating(int hostRating) {
		this.hostRating = hostRating;
	}

	public String getHostNotes() {
		return hostNotes;
	}

	public void setHostNotes(String hostNotes) {
		this.hostNotes = hostNotes;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GatheringParticipant other = (GatheringParticipant) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "GatheringParticipant [id=" + id + ", participantRating=" + participantRating + ", participantNotes="
				+ participantNotes + ", hostRating=" + hostRating + ", hostNotes=" + hostNotes + ", createDate="
				+ createDate + "]";
	}

}
