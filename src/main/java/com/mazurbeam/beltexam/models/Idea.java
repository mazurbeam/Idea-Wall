package com.mazurbeam.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.mazurbeam.beltexam.models.User;

@Entity
@Table(name="ideas")
public class Idea {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name="idea_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> liked;
	
	private Date createdAt;
    private Date updatedAt;
    
    public Idea() {
    	
    }
    
    public Idea(Long id, User user, String text, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	
	public void addLike(User user) {
		this.liked.add(user);
	}

	@PrePersist
    protected void onCreate(){
    this.setCreatedAt(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
    this.setUpdatedAt(new Date());
    }
}
