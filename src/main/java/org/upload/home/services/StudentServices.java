package org.upload.home.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.upload.home.entities.Student;
import org.upload.home.entities.StudentCsvRepresentation;
import org.upload.home.repositories.StudentRepository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServices {

	
	private final StudentRepository studentRepository;

	public Integer uploadStudents(MultipartFile file) throws IOException {
		Set<Student> students=parseCSV(file);
		studentRepository.saveAll(students);
		return return students.size();
	}

	private Set<Student> parseCSV(MultipartFile file) throws IOException {
		try(Reader reader=new BufferedReader(new InputStreamReader(file.getInputStream()))){
			HeaderColumnNameMappingStrategy<StudentCsvRepresentation> columnNameMappingStrategy=new HeaderColumnNameMappingStrategy<>();
			columnNameMappingStrategy.setType(StudentCsvRepresentation.class);
			CsvToBean<StudentCsvRepresentation> csvToBean=new CsvToBeanBuilder<StudentCsvRepresentation>(reader)
					.withMappingStrategy(columnNameMappingStrategy)
					.withIgnoreEmptyLine(true)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
		return	csvToBean.parse()
						.stream()
						.map(csvLine->Student.builder()
										.name(csvLine.getFname())
										.lastname(csvLine.getLname())
										.age(Integer.parseInt(csvLine.getAge()))
										.build())
						.collect(Collectors.toSet());
		}
		
	}
}
