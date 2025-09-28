package com.acxiom_tech_service.serviceimpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acxiom_tech_service.dto.UserDetailsDto;
import com.acxiom_tech_service.entity.UserEntity;
import com.acxiom_tech_service.repository.UserRepository;
import com.acxiom_tech_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String username, String password) {
        Optional<UserEntity> userOpt = userRepository.findByUserName(username);
        if (userOpt.isPresent()) {
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            return encodedPassword.equals(userOpt.get().getPassword());
        }
        return false;
    }
    
    
    @Override
    public boolean submitUserDetails(UserDetailsDto dto) {
    	
    	boolean isUserSaved = false;
        UserEntity user = new UserEntity();
        
        user.setUserName(dto.getUserName());
        user.setMobileNo(dto.getMobileNo());
        user.setAddress(dto.getAddress());
        user.setSkills(dto.getSkills());
        user.setHobbies(dto.getHobbies());
        user.setPhotoPath(dto.getPhotoPath());
        
        if(userRepository.save(user) != null) {
        	isUserSaved = true;
        }

        return isUserSaved;
    }

}
