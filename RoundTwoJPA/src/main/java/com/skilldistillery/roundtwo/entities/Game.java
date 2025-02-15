package com.skilldistillery.roundtwo.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	@Column(name = "minimum_age")
	private int minimumAge;

	@Column(name = "website_url")
	private String websiteUrl;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "min_players")
	private int minPlayers;

	@Column(name = "max_players")
	private Integer maxPlayers;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "gathering_has_game", joinColumns = @JoinColumn(name = "gathering_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Gathering> gatherings;

	@ManyToMany(mappedBy = "games")
	private List<Category> categories;

	@JsonIgnore
	@OneToMany(mappedBy = "game")
	private List<InventoryItem> inventoryItems;

	@JsonIgnore
	@OneToMany(mappedBy = "game")
	private List<GameResource> resources;

	@JsonIgnore
	@ManyToMany(mappedBy = "favoriteGames")
	private List<User> favoritedUsers;

	@JsonIgnore
	@OneToMany(mappedBy = "game")
	private List<GameComment> gameComments;
	
	

	public Game() {
		super();
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public List<Gathering> getGatherings() {
		return gatherings;
	}

	public void setGatherings(List<Gathering> gatherings) {
		this.gatherings = gatherings;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public List<GameResource> getResources() {
		return resources;
	}

	public void setResources(List<GameResource> resources) {
		this.resources = resources;
	}

	public List<User> getFavoritedUsers() {
		return favoritedUsers;
	}

	public void setFavoritedUsers(List<User> favoritedUsers) {
		this.favoritedUsers = favoritedUsers;
	}

	public int getNumberOfFavoritedUsers() {
		return this.favoritedUsers.size();
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", description=" + description + ", minimumAge=" + minimumAge
				+ ", websiteUrl=" + websiteUrl + ", imageUrl=" + imageUrl + ", minPlayers=" + minPlayers
				+ ", maxPlayers=" + maxPlayers + "]";
	}

}
