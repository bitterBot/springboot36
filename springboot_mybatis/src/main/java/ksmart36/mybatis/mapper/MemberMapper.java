package ksmart36.mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import ksmart36.mybatis.dto.Member;
import ksmart36.mybatis.dto.memberLevel;

@Mapper
public interface MemberMapper {
	//회원 로그인이력 전체 행의 갯수
	public int getLoginCount();
	
	//회원 로그인 로그 조회
	public List<Map<String, Object>> getLoginHistory(Map<String, Object> currentPage);
	
	//회원 검색
	public List<Member> searchMember(String cate, String flag);
	
	//1. 회원 정보 삭제 전 로그인 로그 삭제
	public int deleteLogin(String memberId);
	
	//2-1. 상품테이블 통해 g_code 조회
	public List<Map<String, Object>> getGoodsCodeById(String memberId);
	
	//2-2. 주문테이블 삭제
	public int deleteOrder(List<Map<String, Object>> goodsCodeList);
	
	//3. 상품테이블 삭제
	public int deleteGoods(String memberId);
	
	//회원 정보 삭제
	public int deleteMember(String memberId);
	
	//회원 정보 수정
	public int modifyMember(Member member);
	
	//회원 목록 조회
	public List<Member> getMemberList();
	
	//권한 목록 조회
	public List<memberLevel> getMemberLevelList();
	
	//회원 정보 등록
	public int addMember(Member member);
	
	//회원 수정 한명 조회
	public Member modiSelMember(String memberId);
}
