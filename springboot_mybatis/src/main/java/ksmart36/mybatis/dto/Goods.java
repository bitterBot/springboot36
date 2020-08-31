package ksmart36.mybatis.dto;

public class Goods {
	private String goodsCode;
	private String goodsName;
	private int goodsPrice;
	private String goodsPriceComma;
	private String goodsSellerId;
	private String goodsSellerName;
	private String goodsRegDate;
	
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsSellerId=" + goodsSellerId + ", goodsRegDate=" + goodsRegDate + "]";
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsSellerName() {
		return goodsSellerName;
	}
	public void setGoodsSellerName(String goodsSellerName) {
		this.goodsSellerName = goodsSellerName;
	}
	public String getGoodsSellerId() {
		return goodsSellerId;
	}
	public void setGoodsSellerId(String goodsSellerId) {
		this.goodsSellerId = goodsSellerId;
	}
	public String getGoodsRegDate() {
		return goodsRegDate;
	}
	public void setGoodsRegDate(String goodsRegDate) {
		this.goodsRegDate = goodsRegDate;
	}
	public String getGoodsPriceComma() {
		return goodsPriceComma;
	}
	public void setGoodsPriceComma(String goodsPriceComma) {
		this.goodsPriceComma = goodsPriceComma;
	}
	
}
