package com.daou.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User {

//	private static final Logger log = LoggerFactory.getLogger(User.class);
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String userId;

	private String password;
	private String name;
	private String email;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public void update(User updateUser) {
		this.name = updateUser.name;
		this.email = updateUser.email; 
		
	}

	public boolean matchPassword(String password) {
		if(password == null) {
			return false;
		}
		return password.equals(this.password);
	}

    /*
         objects == checks to see if the variables refer to the same object reference.
         To compare the value of the objects you should use the equals() method E.g.
     */
	public boolean matchID(Long id) {
		if(id == null) {
			return false;
		}
//		log.info(" this.id : " + this.id + ", id : " + id);
		return this.id.equals(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return userId != null ? userId.equals(user.userId) : user.userId == null;
	}

	@Override
	public int hashCode() {
		return userId != null ? userId.hashCode() : 0;
	}
}
