package com.deloitte.fitnesstracker.service.impl;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USERNAME_NOT_FOUND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deloitte.fitnesstracker.dto.UserProfileDTO;
import com.deloitte.fitnesstracker.repository.UserRepository;
import com.deloitte.fitnesstracker.vo.Role;
import com.deloitte.fitnesstracker.vo.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


	@Autowired
    private UserRepository userRepository;


    public User findUserByEmail(String email) {
    	logger.info("UserDetailsServiceImpl : findUserByEmail : {} ", email);
        return userRepository.findByEmailId(email);
    }

    public void saveUser(User user) {
    	logger.info("UserDetailsServiceImpl : saveUser : {} ", user);
        Role userRole = new Role(USER);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    	logger.info("UserDetailsServiceImpl : saveUser completed");

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
    	logger.info("UserDetailsServiceImpl : loadUserByUsername email : {} ", email);
        User user = userRepository.findByEmailId(email);
        
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        	logger.info("UserDetailsServiceImpl : loadUserByUsername authorities : {} ", authorities);

            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND);
        }
        
    }
    
    public UserProfileDTO loadUserProfileByUsername(String email) throws UsernameNotFoundException {
    	logger.info("UserDetailsServiceImpl : loadUserProfileByUsername email : {} ", email);

        User user = userRepository.findByEmailId(email);
        if(user != null) {
            UserProfileDTO data = new UserProfileDTO();
            BeanUtils.copyProperties(user, data);
            return data;
        } else {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND);
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorities);
    }
}
