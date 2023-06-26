package org.zerock.b5.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 시큐리티와 관련된 설정은 config에!

@Configuration // 빈을 등록하기 위한 어노테이션?
@Log4j2
@EnableMethodSecurity // 시큐리티(권한)에 대한 설정 가능하게 하는 어노테이션
@RequiredArgsConstructor
public class CustomSecurityConfig {

  // PaswswordEncoder: Spring Security에서는 비밀번호를 안전하게 저장할 수 있도록
  // 비밀번호의 단방향 암호화를 지원하는 PasswordEncoder 인터페이스와 구현체를 제공
  // BCryptPasswordEncoder: PasswordEncoder 인터페이스의 구현체 중 하나
  // -> bcrypt 비밀번호 해시 알고리즘을 사용하여 비밀번호 암호화

  private final DataSource dataSource;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

	  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
      repo.setDataSource(dataSource);
      return repo;
  }

  // SecurityFilterChain: Spring Security의 필터 체인을 구성하는 객체
  // 필터 체인: HTTP 요청을 처리하고 보안 관련 작업을 수행하는 데 사용

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{

    log.info("filter chain------------------------------");

		// /login 경로 로그인 페이지 띄우기

    // 폼 로그인(사용자에게 아이디와 패스워드를 입력받아 인증을 처리하는 방식) 구성
    // Customizer.withDefaults(): 폼 로그인 설정을 적용하는 메소드
    //http.formLogin(Customizer.withDefaults());

 		http.formLogin(config -> {
			config.loginPage("/member/signin");
		});

		 http.rememberMe(config -> {
        config.tokenRepository(persistentTokenRepository());
        config.tokenValiditySeconds(60*60*24*7);
      }); 

		http.csrf(config -> {
			config.disable();
		});

    
    return http.build();
  }


  
}
