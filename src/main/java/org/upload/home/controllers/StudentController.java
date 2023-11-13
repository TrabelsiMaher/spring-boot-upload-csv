package org.upload.home.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.upload.home.entities.Student;
import org.upload.home.services.StudentServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/studens")
@RequiredArgsConstructor
public class StudentController {
	private final StudentServices studentServices;
@PostMapping(value="/uploadcsv",consumes = {"multipart/form-data"})
public ResponseEntity<Integer> uploadCSV(@RequestPart("file")MultipartFile file) throws IOException{
	
	return ResponseEntity.ok(studentServices.uploadStudents(file));
}

}
