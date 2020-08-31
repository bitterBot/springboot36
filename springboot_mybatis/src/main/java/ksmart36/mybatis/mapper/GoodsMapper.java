package ksmart36.mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import ksmart36.mybatis.dto.Goods;

@Mapper
public interface GoodsMapper {
	//상품 검색
	public List<Goods> searchGoods(String cate, String flag);
	//상품 정보삭제 전 주문정보 삭제(외래키 삭제)
	public int deleteOrder(String gcode);
	//상품 정보 삭제
	public int deleteGoods(String gcode);
	//상품 정보 수정
	public int updateGoods(Goods goods);
	//상품 정보 등록
	public int addGoods(Goods goods);
	//상품 수정 전 한가지 조회
	public Goods getGoodsInfo(String gcode);
	//상품 정보 조회
	public List<Goods> getGoodsList();
}
