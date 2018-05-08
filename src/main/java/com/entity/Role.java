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
@Table(name = "t_role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = -8972112646648474051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID")
	private Integer id;
	
	@Column(name = "C_NAME", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy="role",cascade=CascadeType.REFRESH) 
	private List<RolePermission> rolePermissionList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RolePermission> getRolePermissionList() {
		return rolePermissionList;
	}

	public void setRolePermissionList(List<RolePermission> rolePermissionList) {
		this.rolePermissionList = rolePermissionList;
	}

}