package wechat.app.dao;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wechat.app.constant.ConstantCode;
import wechat.app.dao.entity.UserInfo;
import wechat.app.repository.UserInfoRepository;

import java.util.Date;

@Component
@Transactional(rollbackFor = Exception.class)
public class UserLoginDao {

    private static Logger logger = LoggerFactory.getLogger(UserLoginDao.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 保存用户信息
     *
     * @param authInfo 微信授权信息
     * @param userInfo 用户信息
     * @return 保存是否成功
     */
    public String login(JSONObject authInfo, JSONObject userInfo) {
        try {
            //获取微信授权信息
            authInfo = authInfo.getJSONObject("data");

            UserInfo userinfo = new UserInfo();
            userinfo.setPhone(userInfo.getString("phone"));
            userinfo.setSessionId(authInfo.getString("session_key"));
            userinfo.setOpenId(authInfo.getString("openid"));
            userinfo.setUserName(userInfo.getString("nickName"));
            userinfo.setGender(Integer.parseInt(userInfo.getString("gender")));
            userinfo.setCity(userInfo.getString("city"));
            userinfo.setProvince(userInfo.getString("province"));
            userinfo.setCountry(userInfo.getString("country"));
            userinfo.setAvatarUrl(userInfo.getString("avatarUrl"));

            //查看用户是否注册,如果注册且信息有更新则更新客户,否则跳过
            String openid = authInfo.getString("openid");
            UserInfo userInfo1 = userInfoRepository.findByOpenId(openid);
            if (null != userInfo1 && userInfo1.equals(userinfo)) {
                logger.info("客户: {} 已注册.", userinfo.getUserName());
                return ConstantCode.SUCCESS;
            }
            if (null == userInfo1) {
                userinfo.setCreateTime(new Date());
            }
            userinfo.setUpdateTime(new Date());
            userInfoRepository.save(userinfo);
        } catch (Exception e) {
            logger.error("客户注册失败...");
            e.printStackTrace();
            return ConstantCode.FAIL;
        }
        return ConstantCode.SUCCESS;
    }
}
