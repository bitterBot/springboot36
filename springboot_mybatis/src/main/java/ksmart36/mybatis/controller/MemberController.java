package ksmart36.mybatis.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart36.mybatis.dto.Member;
import ksmart36.mybatis.dto.memberLevel;
import ksmart36.mybatis.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired 
	private MemberService memberService;
	
	@GetMapping("/getLoginHistory")
	public String getLoginHistory(Model model
			,@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
		Map<String, Object> resultMap = memberService.getLoginHistory(currentPage);
		System.out.println("login 이력->" + resultMap.get("loginHistory").toString());
		
		model.addAttribute("title", "로그인이력조회");
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("loginHistory", resultMap.get("loginHistory"));
		model.addAttribute("currentPage", currentPage);
		
		return "login/loginHistory";
	}
	
	//@GetMapping("/")와 동일
	//@PostMapping("/") 포스트 방식
	@RequestMapping(value="/getMemberList", method = RequestMethod.GET)
	public String getMemberList(Model model) {
		List<Member> memberList = memberService.getMemberList();
		System.out.println("memberList->"+memberList);
		
		model.addAttribute("title", "회원정보조회");
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}
	
	@PostMapping("/getMemberList")
	public String searchMember(Model model
			,@RequestParam(value="cate", required = false) String cate
			,@RequestParam(value="flag", required = false) String flag) {
		System.out.println("받은카테고리 -> " + cate);
		System.out.println("받은검색어 -> " + flag);
		List<Member> memberList = memberService.searchMember(cate, flag);
		
		System.out.println("검색 결과 리스트 -> " + memberList);
		model.addAttribute("title", "회원정보조회");
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}
	
	@GetMapping("/addMember")
	public String addMember(Model model) {
		model.addAttribute("title", "회원추가");
		return "member/addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(Member member
			, @RequestParam(value="memberId", required = false) String memberId
			, @RequestParam(value="memberPw", required = false) String memberPw
			, @RequestParam(value="memberName", required = false) String memberName
			, @RequestParam(value="memberLevel", required = false) int memberLevel
			, @RequestParam(value="memberEmail", required = false) String memberEmail
			, @RequestParam(value="memberAddr", required = false) String memberAddr){
		System.out.println("회원아이디 -> " + memberId);
		System.out.println("회원비밀번호 -> " + memberPw);
		System.out.println("회원이름 -> " + memberName);
		System.out.println("회원권한 -> " + memberLevel);
		System.out.println("회원이메일 -> " + memberEmail);
		System.out.println("회원주소 -> " + memberAddr);
		int result = memberService.addMember(member);
		
		System.out.println("쿼리 실행 결과 -> " + result);
		
		return "redirect:/getMemberList";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(Model model
			, @RequestParam(value="memberId", required = false) String memberId) {
		Member m = memberService.modiSelMember(memberId);
		List<memberLevel> memberLevelList = memberService.getMemberLevelList();
		
		model.addAttribute("title", "회원수정");
		model.addAttribute("member", m);
		model.addAttribute("memberLevelList", memberLevelList);
		return "member/modifyMember";
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(Member member
			, @RequestParam(value="memberId", required = false) String memberId
			, @RequestParam(value="memberPw", required = false) String memberPw
			, @RequestParam(value="memberName", required = false) String memberName
			, @RequestParam(value="memberLevel", required = false) int memberLevel
			, @RequestParam(value="memberEmail", required = false) String memberEmail
			, @RequestParam(value="memberAddr", required = false) String memberAddr) {
		System.out.println("회원아이디 -> " + memberId);
		System.out.println("회원비밀번호 -> " + memberPw);
		System.out.println("회원이름 -> " + memberName);
		System.out.println("회원권한 -> " + memberLevel);
		System.out.println("회원이메일 -> " + memberEmail);
		System.out.println("회원주소 -> " + memberAddr);
		int result = memberService.modifyMember(member);
		
		System.out.println("쿼리 실행 결과 -> " + result);
		
		return "redirect:/getMemberList";
	}
	
	@GetMapping("/deleteMember")
	public String deleteMember(Model model
			, @RequestParam(value="memberId", required = false) String memberId) {
		
		model.addAttribute("title", "회원삭제확인");
		model.addAttribute("memberId", memberId);
		return "member/deleteMember";
	}
	
	@PostMapping("/deleteMember")
	public String deleteMember(@RequestParam(value="memberId", required = false) String memberId
			,@RequestParam(value="memberPw", required = false) String memberPw) {
		System.out.println("받은아이디 -> " + memberId);
		System.out.println("받은비밀번호 -> " + memberPw);
		int result = 0;
		if(memberPw != null && !memberPw.equals("")) {
			result = memberService.deleteMember(memberId, memberPw);
		}
		
		System.out.println("쿼리 실행 결과 -> " + result);
		return "redirect:/getMemberList";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "로그인화면");
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(Model model
			,@RequestParam(value="memberId", required=false) String memberId
			,@RequestParam(value="memberPw", required=false) String memberPw
			,HttpSession session) {
			Member member = memberService.modiSelMember(memberId);
			if(member.getMemberPw().equals(memberPw)) {
				session.setAttribute("SID", member.getMemberId());
				session.setAttribute("SNAME", member.getMemberName());
				session.setAttribute("SLEVEL", member.getMemberLevel());
			}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
