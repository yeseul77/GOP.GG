package com.gg.gop.securety;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;

//MemberService 클래스에서 구핸해도 됨
@Component
@Slf4j
//UserDetailsService인테페이스의 loadUserByUsername메소드로 로그인 구현 
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDao memberDao;
	
	//UserDetails인터페이스: 시큐리티에서 회원정보를 담기위한 용도
	//User클래스: UserDetails인터페이스 구현체
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//로그인 성공시 User를  반환, 실패시 핸들러에 전달 
		MemberDto mb = memberDao.getMemberInfo(username);
		log.info("======member:{}",mb);
		if(mb==null) {
			//로그인실패시 예외를 로그인실패핸들러에 던짐
			throw new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다");
		}
		//User클래스: UserDetails의 구현체
		//필수:아이디,비밀번호, 권한, 선택: disabled(t/f(로그인안됨)), accountLocked(t/f(로그인안됨)),accountExpired(t/f) 
		return User.builder().username(username).password(mb.getPassword()).roles(mb.getRole()).build();
	}
}

