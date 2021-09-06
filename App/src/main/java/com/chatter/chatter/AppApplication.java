package com.chatter.chatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO:
//	WebSocket:
//		Messaging (to user and groups)
//		Sending files (etc PDF, photos) (?)
// Security:
//		(Future) Security for message, users in base and sending files
//		Maybe use JWT (tokens)
//	Application:
//		setting Profile photo (1) (check if photo use content type)
//		Sending files (etc PDF, photos)
//		Friends Requests (add or decline) (if 'add' add user to user friends and reverse)(later delete pending request) (1)
//		Search for friend (1)
//		Creating groups (1/?) (must be unique admin or group (may be both))
//		add (must be in admin friends) or delete (must be in group) user to/from group (1)
//		Creating message (1)
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
