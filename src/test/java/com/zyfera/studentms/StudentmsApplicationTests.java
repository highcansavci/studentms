package com.zyfera.studentms;

import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("It should handle the integration tests successfully.")
class StudentmsApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl;
	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	@Autowired
	private StudentRepository studentRepository;

	private Student firstStudent;
	private Student nullNameStudent;
	private Student blankNameStudent;
	private Student exceedCharNameStudent;
	private Student nullSurnameStudent;
	private Student blankSurnameStudent;
	private Student exceedCharSurnameStudent;
	private Student nullNumberStudent;
	private Student blankNumberStudent;
	private Student exceedCharNumberStudent;

	private List<Grade> grades;
    private List<Grade> nullCodeGrades;
	private List<Grade> blankCodeGrades;
	private List<Grade> exceedCharCodeGrades;
	private List<Grade> nullValueGrades;
	private List<Grade> lessThanZeroValueGrades;
	private List<Grade> greaterThanOneHundredValueGrades;

	@BeforeEach
	@DisplayName("Setting up the data for the test cases.")
	void init() {
		baseUrl = "http://localhost:" + port + "/students";

        grades = new ArrayList<>();
		Grade firstGrade = new Grade(null, "MT101", 90, null);
		Grade secondGrade = new Grade(null, "PH101", 75, null);
		Grade thirdGrade = new Grade(null, "CH101", 60, null);
		Grade fourthGrade = new Grade(null, "MT101", 70, null);
		Grade fifthGrade = new Grade(null, "HS101", 65, null);
		grades.add(firstGrade);
		grades.add(secondGrade);
		grades.add(thirdGrade);
		grades.add(fourthGrade);
		grades.add(fifthGrade);

		nullCodeGrades = new ArrayList<>();
		Grade nullCodeFirstGrade = new Grade(1L, null, 90, null);
		Grade nullCodeSecondGrade = new Grade(2L, null, 75, null);
		nullCodeGrades.add(nullCodeFirstGrade);
		nullCodeGrades.add(nullCodeSecondGrade);
		nullCodeGrades.add(thirdGrade);
		nullCodeGrades.add(fourthGrade);
		nullCodeGrades.add(fifthGrade);

		blankCodeGrades = new ArrayList<>();
		Grade blankCodeFirstGrade = new Grade(1L, "", 90, null);
		Grade blankCodeSecondGrade = new Grade(2L, "", 75, null);
		blankCodeGrades.add(blankCodeFirstGrade);
		blankCodeGrades.add(blankCodeSecondGrade);
		blankCodeGrades.add(thirdGrade);
		blankCodeGrades.add(fourthGrade);
		blankCodeGrades.add(fifthGrade);

		exceedCharCodeGrades = new ArrayList<>();
		Grade exceedCharCodeFirstGrade = new Grade(1L, "QWERTYUIOPASDFGHKJLZCCVBBNMQWERTYUIO", 90, null);
		Grade exceedCharCodeSecondGrade = new Grade(2L, "ASDFGHJKLQWERTUYIOPZXCCVBNMASDGGAQW", 75, null);
		exceedCharCodeGrades.add(exceedCharCodeFirstGrade);
		exceedCharCodeGrades.add(exceedCharCodeSecondGrade);
		exceedCharCodeGrades.add(thirdGrade);
		exceedCharCodeGrades.add(fourthGrade);
		exceedCharCodeGrades.add(fifthGrade);

		nullValueGrades = new ArrayList<>();
		Grade nullValueFirstGrade = new Grade(1L, "MT101", null, null);
		Grade nullValueSecondGrade = new Grade(2L, "PH101", null, null);
		nullValueGrades.add(nullValueFirstGrade);
		nullValueGrades.add(nullValueSecondGrade);
		nullValueGrades.add(thirdGrade);
		nullValueGrades.add(fourthGrade);
		nullValueGrades.add(fifthGrade);

		lessThanZeroValueGrades = new ArrayList<>();
		Grade lessThanZeroValueFirstGrade = new Grade(1L, "MT101", -1, null);
		Grade lessThanZeroValueSecondGrade = new Grade(2L, "PH101", -10, null);
		lessThanZeroValueGrades.add(lessThanZeroValueFirstGrade);
		lessThanZeroValueGrades.add(lessThanZeroValueSecondGrade);
		lessThanZeroValueGrades.add(thirdGrade);
		lessThanZeroValueGrades.add(fourthGrade);
		lessThanZeroValueGrades.add(fifthGrade);

		greaterThanOneHundredValueGrades = new ArrayList<>();
		Grade greaterThanOneHundredValueFirstGrade = new Grade(1L, "MT101", 101, null);
		Grade greaterThanOneHundredValueSecondGrade = new Grade(2L, "PH101", 1001, null);
		greaterThanOneHundredValueGrades.add(greaterThanOneHundredValueFirstGrade);
		greaterThanOneHundredValueGrades.add(greaterThanOneHundredValueSecondGrade);
		greaterThanOneHundredValueGrades.add(thirdGrade);
		greaterThanOneHundredValueGrades.add(fourthGrade);
		greaterThanOneHundredValueGrades.add(fifthGrade);

		firstStudent = new Student();
		firstStudent.setId(null);
		firstStudent.setName("Ali");
		firstStudent.setSurname("Yilmaz");
		firstStudent.setStdNumber("B012X00012");
		firstStudent.setGrades(grades);

		nullNameStudent = new Student();
		nullNameStudent.setId(1L);
		nullNameStudent.setName(null);
		nullNameStudent.setSurname("Yilmaz");
		nullNameStudent.setStdNumber("B012X00012");
		nullNameStudent.setGrades(grades);

		blankNameStudent = new Student();
		blankNameStudent.setId(1L);
		blankNameStudent.setName("");
		blankNameStudent.setSurname("Yilmaz");
		blankNameStudent.setStdNumber("B012X00012");
		blankNameStudent.setGrades(grades);

		exceedCharNameStudent = new Student();
		exceedCharNameStudent.setId(1L);
		exceedCharNameStudent.setName("Aliqweasdrewfgasdgfdqeqwefsfadsqweqweqfasdjdgjjewrıı");
		exceedCharNameStudent.setSurname("Yilmaz");
		exceedCharNameStudent.setStdNumber("B012X00012");
		exceedCharNameStudent.setGrades(grades);

		nullSurnameStudent = new Student();
		nullSurnameStudent.setId(1L);
		nullSurnameStudent.setName("Ali");
		nullSurnameStudent.setSurname(null);
		nullSurnameStudent.setStdNumber("B012X00012");
		nullSurnameStudent.setGrades(grades);

		blankSurnameStudent = new Student();
		blankSurnameStudent.setId(1L);
		blankSurnameStudent.setName("Ali");
		blankSurnameStudent.setSurname("");
		blankSurnameStudent.setStdNumber("B012X00012");
		blankSurnameStudent.setGrades(grades);

		exceedCharSurnameStudent = new Student();
		exceedCharSurnameStudent.setId(1L);
		exceedCharSurnameStudent.setName("Ali");
		exceedCharSurnameStudent.setSurname("Yilmazqweqadasfdfkqjejhqwıoheklqdasjdklakjdasakdjaskl");
		exceedCharSurnameStudent.setStdNumber("B012X00012");
		exceedCharSurnameStudent.setGrades(grades);

		nullNumberStudent = new Student();
		nullNumberStudent.setId(1L);
		nullNumberStudent.setName("Ali");
		nullNumberStudent.setSurname("Yilmaz");
		nullNumberStudent.setStdNumber(null);
		nullNumberStudent.setGrades(grades);

		blankNumberStudent = new Student();
		blankNumberStudent.setId(1L);
		blankNumberStudent.setName("Ali");
		blankNumberStudent.setSurname("Yilmaz");
		blankNumberStudent.setStdNumber("");
		blankNumberStudent.setGrades(grades);

		exceedCharNumberStudent = new Student();
		exceedCharNumberStudent.setId(1L);
		exceedCharNumberStudent.setName("Ali");
		exceedCharNumberStudent.setSurname("Yilmaz");
		exceedCharNumberStudent.setStdNumber("B012X00012123456789AVCEADLEQOLAC");
		exceedCharNumberStudent.setGrades(grades);
	}

	@AfterEach
	public void deleteEntities() {
		studentRepository.deleteAll();
	}

	@Test
	@DisplayName("It should create the student with completely filled attributes successfully!")
	void createStudent() {
		grades.forEach(grade -> grade.setStudent(firstStudent));
		String response = REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class);
		Assertions.assertAll("Successful Student Creation",
				() -> Assertions.assertNotNull(response),
				() -> Assertions.assertEquals(response, "The student is created successfully!"));
	}

	@Test
	@DisplayName("It should not create the student with null student name!")
	void createNullStudentName() {
		grades.forEach(grade -> grade.setStudent(nullNameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, nullNameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid name, student name should not be blank.")),
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid name, student name should not be null.")));
	}

	@Test
	@DisplayName("It should not create the student with blank student name!")
	void createBlankStudentName() {
		grades.forEach(grade -> grade.setStudent(blankNameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, blankNameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid name, student name should not be blank.")));
	}

	@Test
	@DisplayName("It should not create the student with the name more than 20 characters!")
	void createExceedCharStudentName() {
		grades.forEach(grade -> grade.setStudent(exceedCharNameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, exceedCharNameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Student name must be at most 20 characters.")));
	}

	@Test
	@DisplayName("It should not create the student with null student surname!")
	void createNullStudentSurname() {
		grades.forEach(grade -> grade.setStudent(nullSurnameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, nullSurnameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid surname, student surname should not be blank.")),
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid surname, student surname should not be null.")));
	}

	@Test
	@DisplayName("It should not create the student with blank student surname!")
	void createBlankStudentSurname() {
		grades.forEach(grade -> grade.setStudent(blankSurnameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, blankSurnameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid surname, student surname should not be blank.")));
	}

	@Test
	@DisplayName("It should not create the student with the surname more than 20 characters!")
	void createExceedCharStudentSurname() {
		grades.forEach(grade -> grade.setStudent(exceedCharSurnameStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, exceedCharSurnameStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Student surname must be at most 20 characters.")));
	}

	@Test
	@DisplayName("It should not create the student with null student number!")
	void createNullStudentNumber() {
		grades.forEach(grade -> grade.setStudent(nullNumberStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, nullNumberStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid student number, student number should not be blank.")),
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid student number, student number should not be null.")));
	}

	@Test
	@DisplayName("It should not create the student with blank student number!")
	void createBlankStudentNumber() {
		grades.forEach(grade -> grade.setStudent(blankNumberStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, blankNumberStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid student number, student number should not be blank.")));
	}

	@Test
	@DisplayName("It should not create the student with the surname more than 10 characters!")
	void createExceedCharStudentNumber() {
		grades.forEach(grade -> grade.setStudent(exceedCharNumberStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, exceedCharNumberStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Student number must be at most 10 characters.")));
	}

	@Test
	@DisplayName("It should not create the student with null grade code!")
	void createNullGradeCode() {
		firstStudent.setGrades(nullCodeGrades);
		nullCodeGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid grade code, grade code should not be null.")),
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid grade code, grade code should not be blank.")));
	}

	@Test
	@DisplayName("It should not create the student with blank grade code!")
	void createBlankGradeCode() {
		firstStudent.setGrades(blankCodeGrades);
		blankCodeGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid grade code, grade code should not be blank.")));
	}

	@Test
	@DisplayName("It should not create the student with the grade code more than 10 characters!")
	void createExceedCharGradeCode() {
		firstStudent.setGrades(exceedCharCodeGrades);
		exceedCharCodeGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Grade code must be at most 10 characters.")));
	}

	@Test
	@DisplayName("It should not create the student with null grade!")
	void createNullGradeValue() {
		firstStudent.setGrades(nullValueGrades);
		nullValueGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("Please provide a valid grade, student grade should not be null.")));
	}

	@Test
	@DisplayName("It should not create the student with grade less than 0!")
	void createInvalidLessGrade() {
		firstStudent.setGrades(lessThanZeroValueGrades);
		lessThanZeroValueGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("The grade should be greater than or equal to 0.")));
	}

	@Test
	@DisplayName("It should not create the student with the grade greater than 100!")
	void createInvalidGreaterGrade() {
		firstStudent.setGrades(greaterThanOneHundredValueGrades);
		greaterThanOneHundredValueGrades.forEach(grade -> grade.setStudent(firstStudent));
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> REST_TEMPLATE.postForObject(baseUrl, firstStudent, String.class));
		Assertions.assertAll("Failed Student Creation",
				() -> Assertions.assertTrue(exception.getLocalizedMessage().contains("The grade should be less than or equal to 100.")));
	}
}
