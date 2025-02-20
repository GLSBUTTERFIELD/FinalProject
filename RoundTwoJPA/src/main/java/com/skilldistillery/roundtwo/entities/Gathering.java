package com.skilldistillery.roundtwo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Gathering {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;

	@Column(name = "registration_fee_usd")
	private Double fee;

	@Column(name = "min_participants")
	private int minParticipants;

	@Column(name = "max_participants")
	private int maxParticipants;

	@Column(name = "image_url")
	private String imageUrl;

	private boolean enabled;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "last_update")
	@UpdateTimestamp
	private LocalDateTime lastUpdate;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnoreProperties({"gathering"})
	@OneToMany(mappedBy = "gathering")
	private List<GatheringParticipant> participants;

	@JsonIgnoreProperties({"gathering", "subComment", "parentComment"})
	@OneToMany(mappedBy = "gathering")
	private List<GatheringComment> comments;

	@JsonIgnoreProperties({"gatherings"})
	@ManyToMany(mappedBy = "gatherings")
	private List<Game> games;
	
	@JsonIgnoreProperties({"gatheringsAttended", "inventoryItems", "password", "biography", "createDate", "lastUpdate"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User host;

	public Gathering() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<GatheringComment> getComments() {
		return comments;
	}

	public void setComments(List<GatheringComment> comments) {
		this.comments = comments;
	}

	public List<GatheringParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<GatheringParticipant> participants) {
		this.participants = participants;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public int getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
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
		Gathering other = (Gathering) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Gathering [id=" + id + ", name=" + name + ", description=" + description + ", fee=" + fee
				+ ", minParticipants=" + minParticipants + ", maxParticipants=" + maxParticipants + ", imageUrl="
				+ imageUrl + ", enabled=" + enabled + ", createDate=" + createDate + ", lastUpdate=" + lastUpdate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
