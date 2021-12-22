package com.example.eight;

import com.example.eight.bean.School;
import com.example.eight.config.SchoolAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SchoolAutoConfiguration.class)
class EightApplicationTests {

	@Autowired
	private School school;

	@Test
	public void school_starter_test() {
		System.out.println(school.toString());
	}

}
