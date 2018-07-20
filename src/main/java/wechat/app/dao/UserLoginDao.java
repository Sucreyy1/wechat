package wechat.app.dao;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wechat.app.dao.entity.UserInfo;
import wechat.app.repository.UserInfoRepository;

@Component
@Transactional(rollbackFor = Exception.class)
public class UserLoginDao {

    private static Logger logger = LoggerFactory.getLogger(UserLoginDao.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    public int login(JSONObject jsonObject) {
        try {
            JSONObject info = jsonObject.getJSONObject("userinfo");
            //获取用户信息
            JSONObject userInfo = info.getJSONObject("userInfo");
            //获取微信授权信息
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

            //如果用户注册过,则不操作数据库
            String openid = authInfo.getString("openid");
            UserInfo userInfo1 = userInfoRepository.findByOpenId(openid);
            if (null != userInfo1 && userInfo1.equals(userinfo)){
                logger.info("客户: {} 已注册.",userinfo.getUserName());
                return 0;
            }
            userInfoRepository.save(userinfo);
        } catch (Exception e) {
            logger.error("客户注册失败...");
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
