package com.wsh.Implements;

import java.util.List;
import com.wsh.bean.Goods;

public interface GoodsImplement {
	
	// �����Ʒ
	public boolean addGoods(Goods goods) throws Exception;

	// ����ָ�����͵������ӵ��ļ���Ʒ
	public List<Goods> getLatestGoods(int gid, String type) throws Exception;

	// ����������Ʒ
	public List<Goods> getAllGoods() throws Exception;

	// �༭��Ʒ��Ϣ
	public boolean editInfo(Goods goods) throws Exception;

	// ɾ����Ʒ
	public boolean deleteGoods(int gid) throws Exception;

	// ����ָ��id����Ʒ
	public Goods queryById(int gid) throws Exception;

	// ��ѯָ��id����Ʒ������
	public int queryNumberById(int gid) throws Exception;

	// ����������Ʒ������
	public String[] queryTypes() throws Exception;

	// ����ָ�����͵�������Ʒ
	public List<Goods> getTypeGoodsList(String type) throws Exception;

}
