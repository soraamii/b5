package org.zerock.b5.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

// user는 Userdetails 타입임
@Data
public class MemberDTO extends User{

  private String mname;

  public MemberDTO(String email, String mpw, String mname, List<String> roleNames) {

    super(email, mpw, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));

    this.mname = mname;

  }


  
}
