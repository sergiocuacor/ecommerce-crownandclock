package com.ecommercevcs.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.ecommercevcs.entities.embeddable.Address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class UserEntity {

//	id
//	nombre
//	apellidos
//	email
//	password
//	direccion
//	telefono
//	fechaRegistro
//	lista de pedidos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String firstName; 
	
	private String lastName;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password; //Encriptada
	
	private String phoneNumber;
	
	
	@Embedded // Para incrustar las propiedades de address en la tabla users.
	private Address address;

	@JsonManagedReference(value="user-order") // JsonManaged nos permitirá ver la orderList en el JSON cuando hagamos peticiones GET
	@OneToMany(mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY) // para que solo cargue orders cuando accedamos a ellos (mejora el rendimiento).
	private List<OrderEntity> orderList = new ArrayList<OrderEntity>();
	
	 //Esto sirve para el many to many que no se cree el bucle infinito MITICO
	@JsonIgnoreProperties({"users"}) //Esto sirve para el many to many que no se cree el bucle infinito MITICO
	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"),
			uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})}
	)
	private List<RoleEntity> roles;
	
	private boolean enabled;
	
	@PrePersist
    protected void prePersist() {
        enabled = true;
    }
	
	@Transient  // No se maneja en bddd
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean admin;
	
	
	
	
	
	
	// addOrder y removeOrder son helpers methods que ayudan a mantener la consistencia de la relación
	public void addOrder(OrderEntity order) {
		orderList.add(order);// Actualiza el lado del User
		order.setUser(this);// Actualiza el lado del Order
	}
	public void removeOrder(OrderEntity order) {
		orderList.remove(order);// Actualiza el lado del User
		order.setUser(null);// Actualiza el lado del Order
	}
	public UserEntity(Long id, String name, String firstName, String lastName, String email, String phoneNumber,
			Address address, List<OrderEntity> orderList) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.orderList = orderList;
	}
	public UserEntity() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<OrderEntity> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderEntity> orderList) {
		this.orderList = orderList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, admin, email, enabled, firstName, id, lastName, name, orderList, password,
				phoneNumber, roles);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(address, other.address) && admin == other.admin && Objects.equals(email, other.email)
				&& enabled == other.enabled && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(name, other.name) && Objects.equals(orderList, other.orderList)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(roles, other.roles);
	}
	
	
	
	
	
//	private UserRole role; // Administrador, cliente..
	
	
	
	
	
}
