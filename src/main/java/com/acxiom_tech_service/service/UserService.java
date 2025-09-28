package com.acxiom_tech_service.service;

import com.acxiom_tech_service.dto.UserDetailsDto;

public interface UserService {
	
	boolean login(String username, String password);
	
	boolean submitUserDetails(UserDetailsDto userDetailsDto);

}
