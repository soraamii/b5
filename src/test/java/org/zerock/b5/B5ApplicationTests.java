package org.zerock.b5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class B5ApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	public void testEncoder() {

		String pw = "1111";

		String enPw = passwordEncoder.encode(pw);

		log.info("================" + enPw);

		// 암호 복호화 (테스트할 때마다 다른 값이 나옴)

		boolean result = passwordEncoder.matches("1111", enPw);

		log.info(result);

	}

}
