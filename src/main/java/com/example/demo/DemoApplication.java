package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Override
//	public void run(String... args) throws Exception {
//		// Khi chương trình chạy
//		// Insert vào csdl một user.
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword(passwordEncoder.encode("admin"));
//		userRepository.save(user);
//		System.out.println(user);
//	}
}