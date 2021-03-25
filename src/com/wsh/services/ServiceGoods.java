package com.wsh.services;

import java.sql.SQLException;
import java.util.List;

import com.wsh.Implements.GoodsImplement;
import com.wsh.bean.Goods;
import com.wsh.db.DBConnection;
import com.wsh.db.EditGoods;

public class ServiceGoods implements GoodsImplement {
	
	private DBConnection dbconn = null;

	private GoodsImplement dao = null;

	public ServiceGoods() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new EditGoods(this.dbconn.getConnection());
	}

	@Override
	public boolean addGoods(Goods goods) throws Exception {
		if (goods != null) {
			return this.dao.addGoods(goods);
		}
		return false;
	}

	@Override
	public boolean editInfo(Goods goods) throws Exception {
		if (goods != null) {
			return this.dao.editInfo(goods);
		}
		return false;
	}

	@Override
	public boolean deleteGoods(int gid) throws Exception {
		if (this.dao.queryById(gid) != null & isInt(gid)) {
			return this.dao.deleteGoods(gid);
		}
		return false;
	}

	@Override
	public Goods queryById(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryById(gid);
		}
		return null;
	}

	@Override
	public int queryNumberById(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryNumberById(gid);
		}
		return 0;
	}

	@Override
	public List<Goods> getAllGoods() throws Exception {
		return this.dao.getAllGoods();
	}

	@Override
	public List<Goods> getLatestGoods(int gid, String type) throws Exception {
		if (isInt(gid) & type != null) {
			return this.dao.getLatestGoods(gid, type);
		}
		return null;
	}

	@Override
	public String[] queryTypes() throws Exception {
		return this.dao.queryTypes();
	}

	@Override
	public List<Goods> getTypeGoodsList(String type) throws Exception {
		if (type != null) {
			return this.dao.getTypeGoodsList(type);
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
