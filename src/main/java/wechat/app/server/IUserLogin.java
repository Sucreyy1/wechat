package wechat.app.server;

import com.alibaba.fastjson.JSONObject;

public interface IUserLogin {
    void login(JSONObject jsonObject);
}
