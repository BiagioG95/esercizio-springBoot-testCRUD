package com.example.esercizio_springBoot_testCRUD.repository;

import com.example.esercizio_springBoot_testCRUD.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
