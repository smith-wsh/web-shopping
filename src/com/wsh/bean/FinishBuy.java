package com.wsh.bean;

/*
 * 已经购买的商品
 */
public class FinishBuy {
	
	// 主键
	private String aid;
	// 用户id
	private int uid;
	// 商品id
	private int gid;
	// 购买的商品数量
	private int number;
	// 购买时间
	private String buyTime;

	/*
	 * 主键  操作
	 */
	public String getAid() { return aid; }
	public void setAid(String aid) { this.aid = aid; }

	/*
	 * 用户id  操作
	 */
	public int getUid() { return uid; }
	public void setUid(int uid) { this.uid = uid; }

	/*
	 * 商品id  操作
	 */
	public int getGid() { return gid; }
	public void setGid(int gid) { this.gid = gid; }

	/*
	 * 商品数量  操作
	 */
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }

	/*
	 * 购买时间  操作
	 */
	public String getBuyTime() { return buyTime; }
	public void setBuyTime(String buyTime) { this.buyTime = buyTime; }

}
