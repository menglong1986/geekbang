package com.example.eight.config;

import com.example.eight.bean.Klass;
import com.example.eight.bean.School;
import com.example.eight.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass(School.class)
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.properties")
public class SchoolAutoConfiguration {


	@Autowired
	private SchoolProperties schoolProperties;

	@Bean
	public School getSchool() {
		List<Integer> studentIds = schoolProperties.getStudentIds();
		List<String> studentNames = schoolProperties.getStudentNames();
		List<Integer> classIds = schoolProperties.getMyClassIds();
		List<String> classNames = schoolProperties.getMyClassNames();
		List<Map<String, Integer>> studentOfClass = schoolProperties.getStudentOfClass();

		List<Student> students = new ArrayList<>(studentIds.size());
		for (int i = 0; i < studentIds.size(); i++) {
			students.add(new Student(studentIds.get(i), studentNames.get(i)));
		}

		List<Klass> klasses = new ArrayList<>();
		for (int i = 0; i < classIds.size(); i++) {
			klasses.add(new Klass(classIds.get(i), classNames.get(i)));
		}

		for (Map info : studentOfClass) {
			klasses.get((Integer) info.get("myClassId")).addStudent(students.get((Integer) info.get("studentId")));
		}

		System.out.println(studentIds.toString());
		System.out.println(studentNames.toString());
		System.out.println(classIds.toString());
		System.out.println(classNames.toString());
		System.out.println(studentOfClass.toString());


		return new School(klasses);
	}
}





