package com.wsh.services;

import java.sql.SQLException;
import java.util.List;

import com.wsh.Implements.FinishBuyImplement;
import com.wsh.bean.FinishBuy;
import com.wsh.db.DBConnection;
import com.wsh.db.EditFinishBuy;

public class ServiceFinishBuy implements FinishBuyImplement {

	private DBConnection dbconn = null;

	private FinishBuyImplement dao = null;

	public ServiceFinishBuy() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new EditFinishBuy(this.dbconn.getConnection());
	}

	@Override
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception {
		if (isInt(uid) && isInt(gid) && isInt(number)) {
			return this.dao.addBuyGoods(uid, gid, number);
		}
		return false;
	}

	@Override
	public List<FinishBuy> getAllBuyGoods(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.getAllBuyGoods(uid);
		}
		return null;
	}

	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}

}
