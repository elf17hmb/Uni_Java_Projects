package com.fhzwickau.jwt.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzwickau.jwt.sec.domain.User;
import com.fhzwickau.jwt.sec.domain.UserRepository;

@Service
public class UserService
{

	private UserRepository userRepository;
	private TokenService tokenService;

	@Autowired
	UserService(UserRepository userRepository, TokenService tokenService)
	{
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	public User getUser(Long userId)
	{
		return userRepository.findById(userId).get();
	}

	public String saveUser(User user)
	{
		if (!userRepository.findByName(user.getName()).isPresent())
		{
			User savedUser = userRepository.save(user);
//			savedUser.setFirstName(firstName);
			String token = tokenService.createToken(savedUser.getId());
			System.out.println(token);
			return token;
		}
		return null;
	}

	public String loginUser(User user)
	{
		Optional<User> savedUserOptional = userRepository.findByName(user.getName());
		if (savedUserOptional.isPresent())
		{
			User savedUser = savedUserOptional.get();
			if (savedUser.getPassword().equals(user.getPassword()))
			{
				String token = tokenService.createToken(savedUser.getId());
				return token;
			}
		}
		return null;
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
}