package wechat.app.server;

import com.alibaba.fastjson.JSONObject;

public interface IUserLogin {

    int login(JSONObject jsonObject);

}
