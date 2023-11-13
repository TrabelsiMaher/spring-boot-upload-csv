package org.upload.home.services;

import org.springframework.stereotype.Service;
import org.upload.home.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServices {

	
	private final StudentRepository studentRepository;
}
