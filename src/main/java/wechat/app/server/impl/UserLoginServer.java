package wechat.app.server.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat.app.dao.UserLoginDao;
import wechat.app.server.IUserLogin;

@Service
public class UserLoginServer implements IUserLogin{

    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public int login(JSONObject jsonObject) {
        return userLoginDao.login(jsonObject);

    }
}
