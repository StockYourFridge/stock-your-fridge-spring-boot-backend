package com.stockyourfridge.stockyourfridge.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Required;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
	private long userId;
	
	@NotBlank(message = "User name should not be blank")
	private String userName;
	
	@NotBlank(message = "Password should not be blank")
	private String password;
	
	@Email(message = "Invalid email format")
	private String email;
	private boolean isEnabled;
}
