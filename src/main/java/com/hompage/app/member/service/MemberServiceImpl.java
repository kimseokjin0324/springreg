package com.hompage.app.member.service;

import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Service;
import com.hompage.app.member.model.dao.MemberDAOImpl;
import com.hompage.app.member.model.dto.MemberVO;


@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAOImpl memberDao;

	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	
	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}
	// 03. 회원 정보 상세 조회 
	@Override
	public MemberVO viewMember(String userId) {
		return memberDao.viewMember(userId);
	}
	// 04.회원 정보 삭제
	@Override
	public void deleteMember(String userId) {
		 memberDao.deleteMember(userId);
		
	}
	// 05.  회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}

	@Override
	public boolean checkPw(String userId, String userPw) {
		return memberDao.checkPw(userId, userPw);
	}



}
