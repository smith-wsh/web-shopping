package com.wsh.Implements;

import java.util.List;
import com.wsh.bean.*;

public interface FinishBuyImplement {
	
	// ���ﳵ�����Ʒ
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception;

	// ��ȡָ���û��Ĺ��ﳵ�ڵ�������Ʒ
	public List<FinishBuy> getAllBuyGoods(int uid) throws Exception;

}
