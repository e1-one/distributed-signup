package com.eone.signup.controller;

import com.eone.signup.model.SignupDto;
import com.eone.signup.model.UuidDto;
import com.eone.signup.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/signup")
public class SignUpController {

	@Autowired
	private SignupService signupService;

	@PostMapping
	public UuidDto signup(@RequestBody SignupDto signupDto) {
		String uuid = signupService.process(signupDto);
		return new UuidDto(uuid);
	}

}
