package wechat.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat.app.entity.UserInfo;
import wechat.app.repository.UserInfoRepository;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private UserInfoRepository userInfoRepository;
    /**
     * 微信登陆后保存用户信息
     * @param jsonObject 前端用户信息
     * @return 可有可无
     */
    @PostMapping("/login")
    @Transactional(rollbackOn = Exception.class)
    public JSONObject test(@RequestBody JSONObject jsonObject){
        logger.info(jsonObject.toJSONString());
        JSONObject info = jsonObject.getJSONObject("userinfo");
        JSONObject userInfo = info.getJSONObject("userInfo");
        JSONObject authInfo = info.getJSONObject("authInfo").getJSONObject("data");
        String phone = info.getString("phone");
        UserInfo userinfo = new UserInfo();
        userinfo.setPhone(phone);
        userinfo.setSessionId(authInfo.getString("session_key"));
        userinfo.setOpenId(authInfo.getString("openid"));
        userinfo.setUserName(userInfo.getString("nickName"));
        userinfo.setGender(Integer.parseInt(userInfo.getString("gender")));
        userinfo.setCity(userInfo.getString("city"));
        userinfo.setProvince(userInfo.getString("province"));
        userinfo.setCountry(userInfo.getString("country"));
        userinfo.setAvatarUrl(userInfo.getString("avatarUrl"));
        userInfoRepository.save(userinfo);
        jsonObject.clear();
        jsonObject.put("resCode",200);
        return jsonObject;
    }
}
