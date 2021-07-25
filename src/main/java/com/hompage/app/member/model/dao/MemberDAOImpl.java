package com.hompage.app.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hompage.app.member.model.dto.MemberVO;


@Repository
public class MemberDAOImpl implements MemberDAO{

	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
		// 의존관계 주입(Dependency Injection, DI)
		// 느스한 결함
		// IoC(Inversion of Control, 제어의 역전)
		@Inject
		SqlSession sqlSession;
		
		// 01. 전체 회원 목록 조회
		@Override
		public List<MemberVO> memberList() {
			return sqlSession.selectList("member.memberList");
		}
		// 02. 회원 등록
		@Override
		public void insertMember(MemberVO vo) {
			sqlSession.insert("member.insertMember",vo);
			
		}
		// 03. 회원 정보 상세 조회
		@Override
		public MemberVO viewMember(String userId) {
			return sqlSession.selectOne("member.viewMember",userId);
		}
		// 04.회원 정보 삭제 처리
		@Override
		public void deleteMember(String userId) {
			sqlSession.delete("member.deleteMember",userId);
		}
		// 05.  회원 정보 수정 처리
		@Override
		public void updateMember(MemberVO vo) {
			sqlSession.update("member.updateMember",vo);
			

		}
		@Override
		public boolean checkPw(String userId, String userPw) {
			boolean result = false;
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("userPw", userPw);
			int count = sqlSession.selectOne("member.checkPw", map);
			if(count == 1) result= true;
			return result;
		}


}
