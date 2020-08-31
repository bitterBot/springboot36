package ksmart36.mybatis.dto;

public class memberLevel {
	private int levelNum;
	private String levelName;
	
	@Override
	public String toString() {
		return "memberLevel [levelNum=" + levelNum + ", levelName=" + levelName + "]";
	}
	public int getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
}
