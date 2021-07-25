package com.hompage.app.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hompage.app.HomeController;
import com.hompage.app.member.model.dto.MemberVO;
import com.hompage.app.member.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger=LoggerFactory.getLogger(HomeController.class);
	@Inject
	MemberService memberService;
	//회원목록
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		//controller->service->dao요청
		List<MemberVO> list=memberService.memberList();
		model.addAttribute("list",list);
		return "member/member_list";

	}
	//회원 등록페이지 이동
	@RequestMapping("member/write.do")
	public String memberWrite() {
		return "member/member_write";
	}
	//회원 등록후 ->목록으로 리다이렉트
	@RequestMapping("member/insert.do")
	public String MemberInsert(@ModelAttribute MemberVO vo) {
		memberService.insertMember(vo);
		return "redirect:/member/list.do";
	}
	// 회원 상세정보 조회
	@RequestMapping("member/view.do")
	public String memberView(String userId, Model model) {
		//회원 정보를 model에 저장
		model.addAttribute("dto",memberService.viewMember(userId));
		logger.info("클릭한 아이디 :"+userId);
		return "member/member_view";
	}
	@RequestMapping("member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO vo) {
		memberService.updateMember(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("member/delete.do")
	public String memberDelete(@RequestParam String userId,@RequestParam String userPw,Model model) {
		boolean result =memberService.checkPw(userId,userPw);
		if(result) {
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		}else {
			model.addAttribute("message","비밀번호 불일치");
			model.addAttribute("dto",memberService.viewMember(userId));
			return "member/member_view";
		}
	}
	

}
