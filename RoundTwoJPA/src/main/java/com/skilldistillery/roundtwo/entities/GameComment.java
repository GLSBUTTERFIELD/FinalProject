package com.skilldistillery.roundtwo.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="game_comment")
public class GameComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	private boolean enabled;
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private GameComment parentComment;
	
	@OneToMany(mappedBy = "parentComment")
	private List<GameComment> subComment;
	
	
	
	public GameComment() {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public GameComment getParentComment() {
		return parentComment;
	}
	public void setParentComment(GameComment parentComment) {
		this.parentComment = parentComment;
	}
	public List<GameComment> getSubComment() {
		return subComment;
	}
	public void setSubComment(List<GameComment> subComment) {
		this.subComment = subComment;
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
		GameComment other = (GameComment) obj;
		return id == other.id;
	}

	
	
	@Override
	public String toString() {
		return "GameComment [id=" + id + ", comment=" + comment + ", enabled=" + enabled + ", createDate=" + createDate
				+ "]";
	}
	
	
}
