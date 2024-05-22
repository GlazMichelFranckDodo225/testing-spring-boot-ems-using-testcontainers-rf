package com.dgmf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Loads Full ApplicationContext (All the Spring Beans Available into Application)
// Will Invoke Automatically the "Main" Entry Point Class (that is Deployed with
// Embedded Tomcat Server
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
