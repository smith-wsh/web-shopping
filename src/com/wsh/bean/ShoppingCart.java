package com.wsh.bean;

/*
 * ���ﳵ
 */
public class ShoppingCart {

	// ����
	private String sid;
	// �û�id
	private int uid;
	// ��Ʒid
	private int gid;
	// ���ﳵ��Ʒ����
	private int number;
	// ���빺�ﳵ��ʱ��
	private String sdate;
	
	public String getSid() { return sid; }
	public void setSid(String sid) { this.sid = sid; }
	
	public int getUid() { return uid; }
	public void setUid(int uid) { this.uid = uid; }

	public int getGid() { return gid; }
	public void setGid(int gid) { this.gid = gid; }
	
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }
	
	public String getSdate() { return sdate; }
	public void setSdate(String sdate) { this.sdate = sdate; }

}
