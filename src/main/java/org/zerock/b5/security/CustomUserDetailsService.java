package org.zerock.b5.security;

import java.util.List;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b5.dto.MemberDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2

// UserDetailsService: 사용자의 정보를 담는 인터페이스

public class CustomUserDetailsService implements UserDetailsService{

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  // loadUserByUsername: username이라는 회원 아이디와 같은 식별 값으로 회원 정보를 가져옴
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    
    log.info("loadUserByUsername" + username);

    // SimpleGrantedAuthority: 문자열로 권한을 부여
    // DB에는 ROLE_ 를 뺏지만, 권한 부여할 때는 ROLE_부여해야 함
    MemberDTO memberDTO = new MemberDTO(
      username, 
      passwordEncoder.encode("1111"), 
      "문화면",
      List.of("ROLE_USER")
      );

    memberDTO.setMname("문화면");

  //  UserDetails user = User.builder()
  //   .username(username)
  //   // 테스트 코드에서 1111을 복호화한 암호
  //   .password(passwordEncoder.encode("1111"))
  //   .authorities("ROLE_USER", "ROLE_G1")
  //   .build();

    return memberDTO;

  }


  
}
