package wechat.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat.app.dao.UserLoginDao;
import wechat.app.service.IUserLoginService;

@Service
public class UserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public int login(JSONObject jsonObject) {
        return userLoginDao.login(jsonObject);

    }
}
