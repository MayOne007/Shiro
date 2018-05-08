package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)*/
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 3863128404839411936L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "C_USERNAME", nullable = false, length = 50)
	private String username;
	
	@Column(name = "C_PASSWORD", nullable = false, length = 255)
	private String password;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.REFRESH) 
	private List<UserRole> userRoleList;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}
	
}