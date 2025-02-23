package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String email;
	@Column(name="image_url")
	private String imageUrl;
	private String biography;
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	@UpdateTimestamp
	@Column(name="last_update")
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnoreProperties({"host"})
	@OneToMany(mappedBy = "user")
	private List<GatheringParticipant> gatheringsAttended;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<GatheringComment> gatheringComments;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<GameResource> resources;
	
	@JsonIgnoreProperties({"user","comments"})
	@OneToMany(mappedBy="user")
	private List<InventoryItem> inventoryItems;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<InventoryItemComment> inventoryItemComments;
	
	@JsonIgnore
	@OneToMany(mappedBy="host")
	private List<Gathering> gatheringsHosted;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "favorite_game", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> favoriteGames;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <GameComment> gameComments;
	
	
	
	public User() {
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<GatheringComment> getGatheringComments() {
		return gatheringComments;
	}
	public void setGatheringComments(List<GatheringComment> gatheringComments) {
		this.gatheringComments = gatheringComments;
	}
	public List<GatheringParticipant> getGatheringsAttended() {
		return gatheringsAttended;
	}
	public void setGatheringsAttended(List<GatheringParticipant> gatherings) {
		this.gatheringsAttended = gatherings;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
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
	public List<GameResource> getResources() {
		return resources;
	}
	public void setResources(List<GameResource> resources) {
		this.resources = resources;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}
	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	public List<InventoryItemComment> getInventoryItemComments() {
		return inventoryItemComments;
	}
	public void setInventoryItemComments(List<InventoryItemComment> inventoryItemComments) {
		this.inventoryItemComments = inventoryItemComments;
	}
	public List<Gathering> getGatheringsHosted() {
		return gatheringsHosted;
	}
	public void setGatheringsHosted(List<Gathering> gatheringsHosted) {
		this.gatheringsHosted = gatheringsHosted;
	}
	public List<Game> getFavoriteGames() {
		return favoriteGames;
	}
	public void setFavoriteGames(List<Game> favoriteGames) {
		this.favoriteGames = favoriteGames;
	}
	public List<GameComment> getGameComments() {
		return gameComments;
	}
	public void setGameComments(List<GameComment> gameComments) {
		this.gameComments = gameComments;
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
		User other = (User) obj;
		return id == other.id;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", enabled="
				+ enabled + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", imageUrl="
				+ imageUrl + ", biography=" + biography + ", createDate=" + createDate + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	
}
