package com.wsh.factory;

import com.wsh.Implements.FinishBuyImplement;
import com.wsh.Implements.GoodsImplement;
import com.wsh.Implements.ShoppingCartImplement;
import com.wsh.Implements.UserImplement;
import com.wsh.services.ServiceFinishBuy;
import com.wsh.services.ServiceGoods;
import com.wsh.services.ServiceShoppingCart;
import com.wsh.services.ServiceUser;

public class DBImplements {
	
	public static UserImplement getUserServiceInstance() throws Exception {
		return new ServiceUser();
	}

	public static GoodsImplement getGoodsServiceInstance() throws Exception {
		return new ServiceGoods();
	}

	public static ShoppingCartImplement getShoppingCartServiceInstance()
			throws Exception {
		return new ServiceShoppingCart();
	}

	public static FinishBuyImplement getAlreadyBuyServiceInstance() throws Exception {
		return new ServiceFinishBuy();
	}
}
