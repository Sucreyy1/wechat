package wechat.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat.app.service.IItemService;
import wechat.app.service.IUserLoginService;
import wechat.app.utils.JsonUtils;


@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private IUserLoginService userLogin;
    @Autowired
    private IItemService itemService;


    /**
     * 微信登陆后保存用户信息
     *
     * @param jsonObject 前端用户信息
     * @return jsonObject
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject jsonObject) {
        logger.info("微信登陆信息:{}",JsonUtils.prettyJson(jsonObject));
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

    /**
     * 购买商品
     * @param jsonObject 订单信息
     * @return jsonObject
     */
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject jsonObject){
        //todo 前端返回信息封装
        logger.info("商品信息:{}",JsonUtils.prettyJson(jsonObject));
        int result = itemService.addToCar(jsonObject);
        jsonObject.clear();
        jsonObject.put("resCode",200);
        jsonObject.put("message","添加成功");
        return jsonObject;
    }
}
