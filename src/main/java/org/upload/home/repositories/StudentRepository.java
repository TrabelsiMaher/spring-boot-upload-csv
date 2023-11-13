package org.upload.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.upload.home.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
