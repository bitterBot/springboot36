package ksmart36.mybatis.service;

import java.text.DecimalFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart36.mybatis.dto.Goods;
import ksmart36.mybatis.mapper.GoodsMapper;

@Service
@Transactional
public class GoodsService {
	
	@Autowired 
	private GoodsMapper goodsMapper;
	
	public List<Goods> searchGoods(String cate, String flag) {
		List<Goods> goodsList = goodsMapper.searchGoods(cate, flag);
		DecimalFormat dc = new DecimalFormat("###,###");
		String ch = null;
		Goods goods = null;
		for(int i=0;i<goodsList.size();i++) {
			goods = goodsList.get(i);
			ch = dc.format(goods.getGoodsPrice());
			goods.setGoodsPriceComma(ch);
		}
		return goodsList;
	}
	
	public int deleteGoods(String gcode) {
		int result = 0;
		int result1 = goodsMapper.deleteOrder(gcode);
		int result2 = goodsMapper.deleteGoods(gcode);
		if(result1 != 0 && result2 != 0) {
			result = 1;
		}
		return result;
	}
	
	public int updateGoods(Goods goods) {
		int result = goodsMapper.updateGoods(goods);
		return result;
	}
	
	public Goods getGoodsInfo(String gcode) {
		Goods goods = goodsMapper.getGoodsInfo(gcode);
		return goods;
	}
	
	public int addGoods(Goods goods) {
		int result = goodsMapper.addGoods(goods);
		return result;
	}
	
	public List<Goods> getGoodsList() {
		List<Goods> goodsList = goodsMapper.getGoodsList();
		DecimalFormat dc = new DecimalFormat("###,###");
		String ch = null;
		Goods goods = null;
		for(int i=0;i<goodsList.size();i++) {
			goods = goodsList.get(i);
			ch = dc.format(goods.getGoodsPrice());
			goods.setGoodsPriceComma(ch);
		}
		return goodsList;
	}
}
