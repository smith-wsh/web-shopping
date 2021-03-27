package com.wsh.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wsh.Implements.FinishBuyImplement;
import com.wsh.Implements.ShoppingCartImplement;
import com.wsh.bean.FinishBuy;
import com.wsh.factory.DBImplements;

public class EditFinishBuy implements FinishBuyImplement {
	
	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public EditFinishBuy(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception {
		pstmt = null;
		String sql = "insert into alreadybuy(uid,gid,number,aid,buytime)value(?,?,?,?,now());";
		ShoppingCartImplement Impl = DBImplements.getShoppingCartServiceInstance();
		String message = Impl.getDesignateGoodsMs(uid, gid);
		String aid = message.split("&")[0];
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		pstmt.setInt(3, number);
		pstmt.setString(4, aid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<FinishBuy> getAllBuyGoods(int uid) throws Exception {
		pstmt = null;
		ResultSet rs = null;
		List<FinishBuy> abList = null;
		String sql = "select * from alreadyBuy where uid=? order by buytime desc";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();
		FinishBuy ab;
		abList = new ArrayList<FinishBuy>();
		while (rs.next()) {
			ab = new FinishBuy();
			ab.setUid(uid);
			ab.setAid(rs.getString("aid"));
			ab.setGid(rs.getInt("gid"));
			ab.setNumber(rs.getInt("number"));
			String date = rs.getDate("buytime").toString();
			String time = rs.getTime("buytime").toString();
			ab.setBuyTime(date + " " + time);
			abList.add(ab);
		}
		return abList;
	}
}
