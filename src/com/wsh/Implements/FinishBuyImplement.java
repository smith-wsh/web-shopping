package com.wsh.Implements;

import java.util.List;
import com.wsh.bean.*;

public interface FinishBuyImplement {
	
	// 向购物车添加商品
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception;

	// 获取指定用户的购物车内的所有商品
	public List<FinishBuy> getAllBuyGoods(int uid) throws Exception;

}
