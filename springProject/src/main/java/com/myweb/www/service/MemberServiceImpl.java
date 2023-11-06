package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public int register(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOK = mdao.register(mvo);
		return isOK *= mdao.insertAuthInit(mvo.getEmail());
	}
	
}
