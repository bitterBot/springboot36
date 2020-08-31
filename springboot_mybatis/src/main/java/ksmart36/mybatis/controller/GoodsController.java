package ksmart36.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart36.mybatis.dto.Goods;
import ksmart36.mybatis.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired 
	private GoodsService goodsService;
	
	@GetMapping("/getGoodsList")
	public String getGoodsList(Model model) {
		List<Goods> goodsList = goodsService.getGoodsList();
		
		model.addAttribute("title", "회원정보조회");
		model.addAttribute("goodsList", goodsList);
		return "goods/goodsList";
	}
	
	@PostMapping("/getGoodsList")
	public String searchGoods(Model model
			,@RequestParam(value="cate", required = false) String cate
			,@RequestParam(value="flag", required = false) String flag) {
		System.out.println("받은카테고리 -> " + cate);
		System.out.println("받은검색어 -> " + flag);
		List<Goods> goodsList = goodsService.searchGoods(cate, flag);
		
		System.out.println("검색 결과 리스트 -> " + goodsList);
		model.addAttribute("title", "회원정보조회");
		model.addAttribute("goodsList", goodsList);
		return "goods/goodsList";
	}
	
	@GetMapping("/deleteGoods")
	public String deleteGoods(Model model
				,@RequestParam(value = "goodsCode", required=false) String goodsCode) {
		int result = goodsService.deleteGoods(goodsCode);
		System.out.println("쿼리 실행 결과 -> " + result);
		return "redirect:/getGoodsList";
	}
	
	@GetMapping("/modifyGoods")
	public String updateGoods(Model model
			,@RequestParam(value = "goodsCode", required=false) String goodsCode) {
		Goods goods = goodsService.getGoodsInfo(goodsCode);
		model.addAttribute("goods", goods);
		model.addAttribute("title", "상품수정");
		return "goods/updateGoods";
	}
	
	@PostMapping("/modifyGoods")
	public String updateGoods(@RequestParam(value="goodsName", required=false) String goodsName
			,@RequestParam(value="goodsPrice", required=false) int goodsPrice
			,Goods goods) {
		System.out.println("수정할 상품명 -> " + goodsName);
		System.out.println("수정할 상품가격 -> " + goodsPrice);
		int result = goodsService.updateGoods(goods);
		
		System.out.println("쿼리 실행 결과 -> " + result);
		
		return "redirect:/getGoodsList";
	}
	
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		model.addAttribute("title", "상품추가");
		return "goods/addGoods";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(@RequestParam(value="goodsName", required=false) String goodsName
			,@RequestParam(value="goodsPrice", required=false) int goodsPrice
			,Goods goods) {
		System.out.println("상품명 -> " + goodsName);
		System.out.println("상품가격 -> " + goodsPrice);
		int result = goodsService.addGoods(goods);
		
		System.out.println("쿼리 실행 결과 -> " + result);
		
		return "redirect:/getGoodsList";
	}
}
