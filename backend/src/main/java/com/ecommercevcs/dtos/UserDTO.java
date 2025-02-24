package com.ecommercevcs.dtos;

import com.ecommercevcs.entities.UserEntity;

public class UserDTO {
	
	private String name;

	public static UserDTO UserToDTO(UserEntity user) {
		UserDTO userdto = new UserDTO();
		userdto.setName(user.getName());
		return userdto;
	}
	
	public UserDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
