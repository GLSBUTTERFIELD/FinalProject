package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="gathering_comment")
public class GatheringComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	
	@Column(name="create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "gathering_id")
	private Gathering gathering;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private GatheringComment parentComment;
	
	
	@OneToMany(mappedBy = "parentComment")
	private List<GatheringComment> subComment;
	
	private boolean enabled;
	

	public GatheringComment() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public GatheringComment getParentComment() {
		return parentComment;
	}

	public void setParentComment(GatheringComment parentComment) {
		this.parentComment = parentComment;
	}

	public List<GatheringComment> getSubComment() {
		return subComment;
	}

	public void setSubComment(List<GatheringComment> subComment) {
		this.subComment = subComment;
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

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		GatheringComment other = (GatheringComment) obj;
		return id == other.id;
	}



	@Override
	public String toString() {
		return "GatheringComment [id=" + id + ", comment=" + comment + ", createDate=" + createDate + ", enabled="
				+ enabled + "]";
	}
	
}
