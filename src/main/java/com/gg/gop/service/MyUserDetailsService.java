package com.gg.gop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

//MemberService 클래스에서 구핸해도 됨
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberDao mDao;
    
    private HttpSession s;
    
    @Override
    public UserDetails loadUserByUsername(String u_id) throws UsernameNotFoundException {
        MemberDto mb = mDao.getMemberInfo(u_id);

        if (mb == null) {
            throw new UsernameNotFoundException(u_id + " 사용자를 찾을 수 없습니다");
        }
        
//        s.setAttribute("username", mb.getUsername());
        
        // Security Session에 인증된 사용자 정보를 설정합니다.
        UserDetails userDetails = User.builder()
                .username(mb.getUsername())
                .password(mb.getPassword())
                .build();

        // SecurityContext를 사용하여 Security Session에 사용자 정보를 설정합니다.
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return userDetails;
    }
}
