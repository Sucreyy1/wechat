package wechat.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat.app.service.IUserLoginService;
import wechat.app.utils.JsonUtils;


@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private IUserLoginService userLogin;


    /**
     * 微信登陆后保存用户信息
     *
     * @param jsonObject 前端用户信息
     * @return jsonObject
     */
    @PostMapping("/login")
    public JSONObject test(@RequestBody JSONObject jsonObject) {
        logger.info("微信登陆信息:{}",JsonUtils.prettyJson(jsonObject.toJSONString()));
        if(userLogin.login(jsonObject) == 1){
            jsonObject.clear();
            jsonObject.put("resCode", 500);
            jsonObject.put("message","登陆失败");
            return jsonObject;
        }
        jsonObject.clear();
        jsonObject.put("resCode", 200);
        jsonObject.put("message","登陆成功");
        return jsonObject;
    }
}
