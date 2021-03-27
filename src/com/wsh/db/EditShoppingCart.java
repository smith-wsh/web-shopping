package com.wsh.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.wsh.Implements.FinishBuyImplement;
import com.wsh.Implements.GoodsImplement;
import com.wsh.Implements.ShoppingCartImplement;
import com.wsh.bean.Goods;
import com.wsh.bean.ShoppingCart;
import com.wsh.factory.DBImplements;

public class EditShoppingCart implements ShoppingCartImplement {
	
	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public EditShoppingCart(Connection conn) {
		this.conn = conn;
	}
	
	public String getOrderNumber() {
        Random random = new Random();
//        ��������� ���ɶ��ƣ�����9λ�����
        Integer r = random.nextInt(900000000) + 100000000;

//        ����  13λʱ��
//        Long timeMillis = System.currentTimeMillis();

//        ����  17λʱ��
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStr = sdf.format(new Date());

//        13λ����+9λ�����
//        return  timeMillis + String.valueOf(r);
//        17λʱ��+9λ�����
        return  timeStr + r;
	}
	
	//���ﳵ�����Ʒ
	@Override
	public boolean addGoods(int uid, int gid, int number) throws Exception {
		pstmt = null;
		int result = 0;
		String message = this.getDesignateGoodsMs(uid, gid);
//		int sid = Integer.valueOf(message.split("&")[0]);
		if (!"".equals(message)) {
			String sid = message.split("&")[0];
			int goodsCount = Integer.valueOf(message.split("&")[1]);
			String sql = "update shoppingcart set number=?,sdate=now() where sid=?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, goodsCount + number);
			pstmt.setString(2, sid);
		} else {
			String sql = "insert into shoppingcart(sid,uid,gid,number,sdate)value(?,?,?,?,now());";
			pstmt = this.conn.prepareStatement(sql);
			String sid = this.getOrderNumber();
			pstmt.setString(1, sid);
			pstmt.setInt(2, uid);
			pstmt.setInt(3, gid);
			pstmt.setInt(4, number);
		}
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	//ɾ�����ﳵ�е���Ʒ
	@Override
	public boolean deleteGoods(int uid, int gid, int number) throws Exception {
		String message = this.getDesignateGoodsMs(uid, gid);
		int result = 0;
		if (!"".equals(message)) {
			String sid = message.split("&")[0];
			int goodsCount = Integer.valueOf(message.split("&")[1]);
			if (goodsCount < number) {
				return false;
			} else if (goodsCount == number) {
				String sql = "delete from shoppingcart where sid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, sid);
			} else {
				String sql = "update shoppingcart set number=? where sid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, goodsCount - number);
				pstmt.setString(2, sid);
			}
			result = pstmt.executeUpdate();
			pstmt.close();
		}
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	//��ȡ���ﳵ�е�������Ʒ
	@Override
	public List<ShoppingCart> getAllGoods(int uid) throws Exception {
		pstmt = null;
		ResultSet rs = null;
		List<ShoppingCart> scList = null;
		String sql = "select * from shoppingcart where uid=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();
		ShoppingCart sc;
		scList = new ArrayList<ShoppingCart>();
		while (rs.next()) {
			sc = new ShoppingCart();
			sc.setSid(rs.getString("sid"));
			sc.setUid(rs.getInt("uid"));
			sc.setGid(rs.getInt("gid"));
			sc.setNumber(rs.getInt("number"));
			String date = rs.getDate("sdate").toString();
			String time = rs.getTime("sdate").toString();
			sc.setSdate(date + " " + time);
			scList.add(sc);
		}
		return scList;
	}
	
	//���ָ���û����ﳵ���Ƿ���ָ����Ʒ��������򷵻ع��ﳵid����Ʒ���������򷵻ؿ�
	@Override
	public String getDesignateGoodsMs(int uid, int gid) throws Exception {
		ResultSet rs = null;
		String sql = "select * from shoppingcart where uid =? and gid=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString("sid") + "&" + rs.getInt("number");
		}
		return "";
	}

	//֧��ָ����Ʒ
	@Override
	public boolean payGoods(int uid, int gid, int number) throws Exception {
		boolean flag = false;
		GoodsImplement dao = DBImplements.getGoodsServiceInstance();
		int extantGoods = dao.queryNumberById(gid);
		conn.setAutoCommit(false);
		if (extantGoods >= number) {
			if (this.deleteGoods(uid, gid, number)) {
				Goods goods = dao.queryById(gid);
				goods.setNumber(extantGoods - number);
				FinishBuyImplement ab = DBImplements.getAlreadyBuyServiceInstance();
				flag = (ab.addBuyGoods(uid, gid, number) & dao.editInfo(goods));
			}
		}
		conn.commit();
		conn.setAutoCommit(true);
		return flag;
	}

	//֧��������Ʒ
	@Override
	public boolean payAllGoods(int uid) throws Exception {
		List<ShoppingCart> scList = this.getAllGoods(uid);
		if (scList != null & scList.size() > 0) {
			int gid;
			int number;
			ShoppingCart sc;
			for (int i = 0; i < scList.size(); i++) {
				sc = scList.get(i);
				gid = sc.getGid();
				number = sc.getNumber();
				this.payGoods(uid, gid, number);
			}
			return true;
		}
		return false;
	}
}
