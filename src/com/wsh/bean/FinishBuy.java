package com.wsh.bean;

/*
 * �Ѿ��������Ʒ
 */
public class FinishBuy {
	
	// ����
	private String aid;
	// �û�id
	private int uid;
	// ��Ʒid
	private int gid;
	// �������Ʒ����
	private int number;
	// ����ʱ��
	private String buyTime;

	/*
	 * ����  ����
	 */
	public String getAid() { return aid; }
	public void setAid(String aid) { this.aid = aid; }

	/*
	 * �û�id  ����
	 */
	public int getUid() { return uid; }
	public void setUid(int uid) { this.uid = uid; }

	/*
	 * ��Ʒid  ����
	 */
	public int getGid() { return gid; }
	public void setGid(int gid) { this.gid = gid; }

	/*
	 * ��Ʒ����  ����
	 */
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }

	/*
	 * ����ʱ��  ����
	 */
	public String getBuyTime() { return buyTime; }
	public void setBuyTime(String buyTime) { this.buyTime = buyTime; }

}
