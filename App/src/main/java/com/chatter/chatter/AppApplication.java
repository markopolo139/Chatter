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
//		Storing user login and password (used to login) (1)
//		User Friends (1)
//		User Pending friend request (1)
//		Groups (?) (Group name and group admin as PK)
//	WebSocket:
//		Messaging (to user and groups)
//		Sending files (etc PDF, photos) (?)
//	Application:
//		setting Profile photo (1) (check if photo use content type)
//		Sending files (etc PDF, photos)
//		Friends Requests (add or decline) (if 'add' add user to user friends and reverse)(later delete pending request) (1)
//		Search for friend (1)
//		Creating groups (1/?)
//		Creating message (1)

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
