package com.fhzwickau.jwt.sec.boundary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhzwickau.jwt.sec.domain.User;
import com.fhzwickau.jwt.sec.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{

	private UserService userService;

	@Autowired
	UserController(UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody User user)
	{
		String token = userService.saveUser(user);
		System.out.println("REGISTERING " + token + " and Username: " + user.getName());
		return token;
	}

	@GetMapping("/get")
	public User getUser(HttpServletRequest request)
	{
		Long userId = (Long) request.getAttribute("userId");
		return userService.getUser(userId);
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody User user)
	{
		String token = userService.loginUser(user);
		System.out.println("Logging in with token : " + token + " and Username: " + user.getName());
		return token;
	}

	@GetMapping("/getAll")
	public List<User> getAllUsers()
	{
		List<User> allUsers = userService.getAllUsers();
		System.out.println("ALL USERS: " + allUsers);
		return allUsers;
	}
}
