package apis.com.service;

import java.util.List; 

import apis.com.dto.LoginUserDto;
import apis.com.dto.UserDto;
import apis.com.entities.User;

public interface UserService { 
	
	public User signup(UserDto user);
	public User updateUser(long id, UserDto userDto);
	public User authenticate(LoginUserDto data);
	public void deleteUser(long id); 
	public User getUserById(long id);
	public List<User> getAllUsers(); 

} 