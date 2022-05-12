package by.mytest.main.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6498432027237586332L;

	public User() {
	}

	public User(String login, String password, String name, String surName, String eMail, String telephone,
			String address, boolean enabled) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surName = surName;
		this.eMail = eMail;
		this.telephone = telephone;
		this.address = address;
		this.enabled = enabled;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_Login"), inverseJoinColumns = @JoinColumn(name = "role_idrole"))
	private Set<Role> roles;
	@Id
	@Column(name = "Login")
	private String login;

	@Column(name = "Password")
	private String password;

	@Column(name = "Name")
	private String name;

	@Column(name = "SurName")
	private String surName;

	@Column(name = "eMail")
	private String eMail;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "address")
	private String address;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Transient
	private String confirmPassword;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="user",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Schedule> schedule;
	
	

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(login, other.login);
	}

	@Override
	public String toString() {
		return "User [roles=" + roles.toString() + ", login=" + login + ", password=" + password + ", name=" + name + ", surName="
				+ surName + ", eMail=" + eMail + ", telephone=" + telephone + ", address=" + address + ", enabled="
				+ enabled + "]";
	}

}
