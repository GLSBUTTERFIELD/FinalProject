package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "gathering_participant")
public class GatheringParticipant {

	@EmbeddedId
	private GatheringParticipantId id;

	@Column(name = "participant_rating")
	private Integer participantRating;
	@Column(name = "participant_notes")
	private String participantNotes;
	@Column(name = "host_rating")
	private Integer hostRating;
	@Column(name = "host_notes")
	private String hostNotes;
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;
	
	@JsonIgnoreProperties({"participants"})
	@ManyToOne
	@JoinColumn(name = "gathering_id")
	@MapsId(value = "gatheringId")
	private Gathering gathering;

	public GatheringParticipant() {
	}

	public GatheringParticipantId getId() {
		return id;
	}

	public void setId(GatheringParticipantId id) {
		this.id = id;
	}

	public Integer getParticipantRating() {
		return participantRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gathering getGathering() {
		return gathering;
	}

	public void setGathering(Gathering gathering) {
		this.gathering = gathering;
	}

	public void setParticipantRating(Integer participantRating) {
		this.participantRating = participantRating;
	}

	public String getParticipantNotes() {
		return participantNotes;
	}

	public void setParticipantNotes(String participantNotes) {
		this.participantNotes = participantNotes;
	}

	public Integer getHostRating() {
		return hostRating;
	}

	public void setHostRating(Integer hostRating) {
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
	
	@JsonProperty
	public String getParticipantName() {
		return this.user.getFirstName()+ " "+ this.user.getLastName();
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
