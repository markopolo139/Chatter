package com.chatter.chatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO:
// Security:
//		Login (1)
//		Registration (Profile photo) (1)
//		Forgot Password (recovery) (1)
//		(Future) Security for message, users in base and sending files
//		Maybe use JWT (tokens)
// Base:
//		Group entity (group name with admin and their users) (1)
//	WebSocket:
//		Messaging (to user and groups)
//		Sending files (etc PDF, photos) (?)
//	Application:
//		setting Profile photo (1) (check if photo use content type)
//		Sending files (etc PDF, photos)
//		Friends Requests (add or decline) (if 'add' add user to user friends and reverse)(later delete pending request) (1)
//		Search for friend (1)
//		Creating groups (1/?) (must be unique admin or group (may be both))
//		add (must be in admin friends) or delete (must be in group) user to/from group (1)
//		Creating message (1)
//	WEB:
//	model for user (without friends requests and friends, and password) (1)
//	users friends and requests will be in separate screens (1)
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
