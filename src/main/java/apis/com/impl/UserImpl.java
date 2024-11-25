package apis.com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apis.com.dto.LoginUserDto;
import apis.com.dto.UserDto;
import apis.com.entities.User;
import apis.com.enums.Role;
import apis.com.repository.UserRepository;
import apis.com.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public User signup(UserDto user) {
		User user1 = new User().
				setFullName(user.getFullName()).
				setEmail(user.getEmail()).
				setRole(user.getRole()).
				setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user1);
	}

	@Override
	public User updateUser(long id, UserDto userDto) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID " + id));
		existingUser.setFullName(userDto.getFullName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setRole(userDto.getRole());
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		return userRepository.save(existingUser);
	}

	@Override
	public User authenticate(LoginUserDto input) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}

	@Override
	public void deleteUser(long id) {
		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User with ID " + id + " does not exist");
		}
		userRepository.deleteById(id);
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID " + id));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findByRoleNot(Role.ADMIN);
	}

}
