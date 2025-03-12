package com.example.esercizio_springBoot_testCRUD;

import com.example.esercizio_springBoot_testCRUD.controller.StudentController;
import com.example.esercizio_springBoot_testCRUD.entity.Student;
import com.example.esercizio_springBoot_testCRUD.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EsercizioSpringBootTestCrudApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;
	@MockitoBean
	private StudentService studentService;
	@Autowired
	private StudentController studentController;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	private Student student;

	@BeforeEach
	public void setUp() {

		student = new Student();

		student.setId(1L);
		student.setName("gigi");
		student.setSurname("gaga");
		student.setWorking(true);


	}

	@Test
	public void testCreateStudent() throws Exception {
		when(studentService.createStudent(any(Student.class))).thenReturn(student);
		mockMvc.perform(post("/student/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(student)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(student.getName()));

	}

	@Test
	public void testGetAllStudent() throws Exception {
		when(studentService.getAllStudents()).thenReturn(Collections.singletonList(student));

		mockMvc.perform(get("/student/select-all"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void testUpdateStudent() throws Exception {
		when(studentService.updateStudentId(anyLong(), any(Student.class))).thenReturn(Optional.of(student));

		mockMvc.perform(put("/student/" + student.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(student)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(student.getName()));

	}

	@Test
	public void testDeleteStudent() throws Exception {
		when(studentService.deleteStudent(any(Student.class))).thenReturn(student);

		mockMvc.perform(delete("/student/" + student.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(student)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(student.getName()));

	}

	@Test
	void testUpdateWorkingStatus() throws Exception {
		when(studentService.updateWorkingStatus(anyLong(), eq(true))).thenReturn(Optional.of(student));
		mockMvc.perform(patch("/student/1/working")
						.param("working", "true"))  // Nome corretto del parametro
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.working").value(true));
	}

	@Test
	public void testStudentById() throws Exception {
		when(studentService.getStudentById(anyLong())).thenReturn(Optional.of(student));
		mockMvc.perform(get("/student/" + student.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(student)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(student.getName()));
	}


	}


