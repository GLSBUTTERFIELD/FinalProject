package com.skilldistillery.roundtwo.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="item_condition")
public class ItemCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@Column(name="image_url")
	private String image_Url;
	public ItemCondition() {
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
	public String getImage_Url() {
		return image_Url;
	}
	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
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
		ItemCondition other = (ItemCondition) obj;
		return id == other.id;
	}
	
	
	@Override
	public String toString() {
		return "ItemCondition [id=" + id + ", name=" + name + ", description=" + description + ", image_Url="
				+ image_Url + "]";
	}
	
	
}
