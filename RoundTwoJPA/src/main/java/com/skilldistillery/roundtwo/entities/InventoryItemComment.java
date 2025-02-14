package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_item_comment")
public class InventoryItemComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	private boolean enabled;
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem inventoryItem;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public InventoryItemComment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		InventoryItemComment other = (InventoryItemComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "InventoryItemComment [id=" + id + ", comment=" + comment + ", enabled=" + enabled + ", createDate="
				+ createDate + ", lastUpdate=" + lastUpdate + "]";
	}

}
