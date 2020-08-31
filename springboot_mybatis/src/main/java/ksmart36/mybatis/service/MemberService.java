package ksmart36.mybatis.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart36.mybatis.dto.Member;
import ksmart36.mybatis.dto.memberLevel;
import ksmart36.mybatis.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	
	@Autowired 
	private MemberMapper memberMapper;
	
	//회원로그인이력조회
	public Map<String, Object> getLoginHistory(int currentPage){
		
		final int ROW_PER_PAGE = 10;
		
		int startRow = 0;
		
		startRow = (currentPage - 1) * ROW_PER_PAGE;
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startRow",  startRow);
		parameterMap.put("rowPerPage",  ROW_PER_PAGE);
		
		double totalRowCount = memberMapper.getLoginCount();
		
		int lastPage = (int) Math.ceil((totalRowCount / ROW_PER_PAGE));
		
		List<Map<String, Object>> loginHistory = memberMapper.getLoginHistory(parameterMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("loginHistory", loginHistory);
		resultMap.put("lastPage", lastPage);
		return resultMap;
	}
	
	public List<Member> searchMember(String cate, String flag){
		List<Member> memberList = memberMapper.searchMember(cate, flag);
		Member m = null;
		for(int i=0;i<memberList.size();i++) {
			m = memberList.get(i);
			if(m.getMemberLevel() == 1) {
				m.setMemberLevelName("관리자");
			} else if(m.getMemberLevel() == 2) {
				m.setMemberLevelName("판매자");
			} else {
				m.setMemberLevelName("구매자");
			}
		}
		return memberList;
	}
	
	public int deleteMember(String memberId, String memberPw) {
		Member m = memberMapper.modiSelMember(memberId);
		int result = 0;
		if(m.getMemberPw().equals(memberPw)) {
			memberMapper.deleteLogin(memberId);
			List<Map<String, Object>> gcodeList = memberMapper.getGoodsCodeById(memberId);
			memberMapper.deleteOrder(gcodeList);
			memberMapper.deleteGoods(memberId);
			memberMapper.deleteMember(memberId);
			result = 1;
		}
		return result;
	}
	
	public int modifyMember(Member member) {
		int result = memberMapper.modifyMember(member);
		return result;
	}
	
	public Member modiSelMember(String memberId) {
		Member m = memberMapper.modiSelMember(memberId);
		return m;
	}
	
	public int addMember(Member member) {
		int result = memberMapper.addMember(member);
		return result;
	}
	
	public List<memberLevel> getMemberLevelList(){
		List<memberLevel> memberLevelList = memberMapper.getMemberLevelList();
		return memberLevelList;
	}
	
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		Member m = null;
		for(int i=0;i<memberList.size();i++) {
			m = memberList.get(i);
			if(m.getMemberLevel() == 1) {
				m.setMemberLevelName("관리자");
			} else if(m.getMemberLevel() == 2) {
				m.setMemberLevelName("판매자");
			} else {
				m.setMemberLevelName("구매자");
			}
		}
		return memberList;
	}
}
