package com.wsh.services;

import com.wsh.Implements.UserImplement;
import com.wsh.bean.User;
import com.wsh.db.DBConnection;
import com.wsh.db.EditUser;

public class ServiceUser implements UserImplement {
	
	private DBConnection dbconn = null;

	private UserImplement dao = null;

	public ServiceUser() throws Exception {
		this.dbconn = new DBConnection();
		this.dao = new EditUser(this.dbconn.getConnection());
	}

	@Override
	public boolean addUser(User user) throws Exception {
		boolean flag = false;
		if (user != null && this.dao.queryByName(user.getUname()) == null
				&& this.dao.queryByEmail(user.getEmail()) == null) {
			flag = this.dao.addUser(user);
		}
		return flag;
	}

	@Override
	public boolean editEmail(int uid, String email) throws Exception {
		if (isInt(uid) && email != null) {
			return this.dao.editEmail(uid, email);
		}
		return false;
	}

	@Override
	public boolean editLoginTime(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.editLoginTime(uid);
		}
		return false;
	}

	@Override
	public boolean editPasswd(int uid, String passwd) throws Exception {
		if (isInt(uid) && passwd != null) {
			return this.dao.editPasswd(uid, passwd);
		}
		return false;
	}

	@Override
	public boolean deleteUser(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.deleteUser(uid);
		}
		return false;
	}

	@Override
	public User queryByName(String uname) throws Exception {
		if (uname != null) {
			return this.dao.queryByName(uname);
		}
		return null;
	}

	@Override
	public User queryByEmail(String email) throws Exception {
		if (email != null) {
			return this.dao.queryByEmail(email);
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
