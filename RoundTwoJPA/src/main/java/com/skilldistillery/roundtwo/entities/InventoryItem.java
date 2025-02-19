package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class InventoryItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String notes;
	
	private boolean available;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private boolean enabled;
	
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name="condition_id")
	private ItemCondition condition;
	
	@JsonIgnoreProperties({"inventoryItems"})
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	
	@JsonIgnoreProperties({"gatheringsAttended","inventoryItems"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnoreProperties({"inventoryItem"})
	@OneToMany(mappedBy="inventoryItem")
	private List<InventoryItemComment> comments;
	
	public InventoryItem() {
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean avaliable) {
		this.available = avaliable;
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

	public ItemCondition getCondition() {
		return condition;
	}

	public void setCondition(ItemCondition condition) {
		this.condition = condition;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<InventoryItemComment> getComments() {
		return comments;
	}

	public void setComments(List<InventoryItemComment> comments) {
		this.comments = comments;
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
		InventoryItem other = (InventoryItem) obj;
		return id == other.id;
	}

	public InventoryItem(int id, String name, String notes, boolean avaliable, String imageUrl, boolean enabled,
			LocalDateTime createDate, LocalDateTime lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.notes = notes;
		this.available = avaliable;
		this.imageUrl = imageUrl;
		this.enabled = enabled;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", name=" + name + ", notes=" + notes + ", available=" + available
				+ ", imageUrl=" + imageUrl + ", enabled=" + enabled + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + "]";
	}
	

}
