package wechat.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat.app.server.IUserLogin;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private IUserLogin userLogin;


    /**
     * 微信登陆后保存用户信息
     * @param jsonObject 前端用户信息
     * @return 可有可无
     */
    @PostMapping("/login")
    @Transactional(rollbackOn = Exception.class)
    public JSONObject test(@RequestBody JSONObject jsonObject){
        logger.info(jsonObject.toJSONString());
        userLogin.login(jsonObject);
        jsonObject.clear();
        jsonObject.put("resCode",200);
        return jsonObject;
    }
}
