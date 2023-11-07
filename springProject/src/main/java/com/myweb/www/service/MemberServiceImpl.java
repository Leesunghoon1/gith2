package com.myweb.www.service;

import java.util.List;

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

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return mdao.getMemberList();
	}

	@Override
	public MemberVO getMember(String email) {
		// TODO Auto-generated method stub
		return mdao.getMember(email);
	}

	@Override
	public int modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		return mdao.remove(email);
	}
	
}
