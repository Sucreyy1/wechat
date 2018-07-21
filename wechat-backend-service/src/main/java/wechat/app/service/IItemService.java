package wechat.app.service;

import com.alibaba.fastjson.JSONObject;

public interface IItemService {

    //添加购物车
    int addToCar(JSONObject jsonObject);
    //付款
    int purchase(JSONObject jsonObject);
    //取消订单
    int cancelOrder(JSONObject jsonObject);

}
