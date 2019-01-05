package com.babkiewicz.artur.BackEnd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailIgnoreCase(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.",username));
		} else {
			return JwtUserFactory.create(user);
		}
	}
}
