package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_role_permission")
public class RolePermission implements java.io.Serializable {

	private static final long serialVersionUID = 3067132252199116449L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "C_ID", unique = true, nullable = false, length = 32)
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="N_ROLEID")
	private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name="N_PERMISSIONID")
	private Permission permission;
	
	public String getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public void setId(String id) {
		this.id = id;
	}

}