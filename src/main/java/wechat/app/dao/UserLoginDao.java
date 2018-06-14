package wechat.app.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wechat.app.dao.entity.UserInfo;
import wechat.app.repository.UserInfoRepository;

@Component
public class UserLoginDao {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public void login(JSONObject jsonObject) {
        JSONObject info = jsonObject.getJSONObject("userinfo");
        JSONObject userInfo = info.getJSONObject("userInfo");
        JSONObject authInfo = info.getJSONObject("authInfo").getJSONObject("data");
        UserInfo userinfo = new UserInfo();
        userinfo.setPhone(info.getString("phone"));
        userinfo.setSessionId(authInfo.getString("session_key"));
        userinfo.setOpenId(authInfo.getString("openid"));
        userinfo.setUserName(userInfo.getString("nickName"));
        userinfo.setGender(Integer.parseInt(userInfo.getString("gender")));
        userinfo.setCity(userInfo.getString("city"));
        userinfo.setProvince(userInfo.getString("province"));
        userinfo.setCountry(userInfo.getString("country"));
        userinfo.setAvatarUrl(userInfo.getString("avatarUrl"));
        userInfoRepository.save(userinfo);
    }
}
