package com.gg.gop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService{
//	@Autowired
//	BCryptPasswordEncoder passwordincoder;
	@Autowired
	private MemberDao mDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto user=mDao.getMemberInfo(username);

		if(user==null) {
			throw new UsernameNotFoundException(username+"사용자를 찾을 수 없습니다");
		}
		return User.builder().username(username).password(user.getPassword())./*roles(user.getRole()).*/build();
	}

}
