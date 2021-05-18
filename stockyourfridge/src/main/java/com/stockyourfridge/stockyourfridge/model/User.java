package com.stockyourfridge.stockyourfridge.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(unique = true)
	@NotBlank(message = "User name should not be blank")
	private String userName;
	
	@NotBlank(message = "Password should not be blank")
	private String password;
	
	@Column(unique = true)
	@Email(message = "Invalid email format")
	private String email;
	private boolean isEnabled;
	
	@ManyToMany(mappedBy = "users")
	List<Fridge> subscribedFridges;
	
	@OneToMany(mappedBy = "owner")
	List<Fridge> ownedFridges;
}
