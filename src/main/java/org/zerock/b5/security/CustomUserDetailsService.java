package org.zerock.b5.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2

// UserDetailsService: 사용자의 정보를 담는 인터페이스
// 

public class CustomUserDetailsService implements UserDetailsService{
  
  // loadUserByUsername: username이라는 회원 아이디와 같은 식별 값으로 회원 정보를 가져옴
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    log.info("loadUserByUsername" + username);

   UserDetails user = User.builder()
    .username(username)
    // 테스트 코드에서 1111을 복호화한 암호
    .password("$2a$10$qkE9NGrNP7hnYllZUCqZLu89O5bUl9Rj7yNlwIvSNl0LgYlJXyEvG")
    .authorities("ROLE_USER", "ROLE_G1")
    .build();

    return user;

  }


  
}
