package wechat.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat.app.constant.ConstantCode;
import wechat.app.constant.ConstantEnum;
import wechat.app.service.IItemService;
import wechat.app.service.IRedisService;
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
    @Autowired
    private IRedisService redisService;


    /**
     * 微信登陆后保存用户信息
     *
     * @param jsonObject 前端用户信息
     * @return jsonObject
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject jsonObject) {
        logger.info("微信登陆信息:{}", JsonUtils.prettyJson(jsonObject));
        if (ConstantCode.FAIL.equals(userLogin.login(jsonObject))) {
            jsonObject.clear();
            jsonObject.put("resCode", ConstantEnum.FAIL.getCode());
            jsonObject.put("message", ConstantEnum.FAIL.getMessage());
            return jsonObject;
        }
        jsonObject.clear();
        jsonObject.put("resCode", ConstantEnum.SUCCESS.getCode());
        jsonObject.put("message", ConstantEnum.SUCCESS.getMessage());
        return jsonObject;
    }

    /**
     * 生成订单
     *
     * @param jsonObject 订单信息
     * @return jsonObject
     */
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject jsonObject) {
        //todo 暂时还没写订单逻辑,只是做测试
        logger.info("商品信息:{}", JsonUtils.prettyJson(jsonObject));
        int result = itemService.addToCar(jsonObject);
        jsonObject.clear();
        jsonObject.put("resCode", ConstantEnum.SUCCESS.getCode());
        jsonObject.put("message", ConstantEnum.SUCCESS.getMessage());
        return jsonObject;
    }

    /**
     * 缓存购物车
     * @param jsonObject
     * @return
     */
    @PostMapping("/toRedis")
    public JSONObject toRedis(JSONObject jsonObject){
        //用户信息
        String userId = jsonObject.getString("user_id");
        //购物车信息
        JSONObject itemList = jsonObject.getJSONObject("itemList");
        //todo 通过md5加密算法，计算redis缓存使用的key
        String key = null;
        redisService.set(key,itemList.toJSONString());
        jsonObject.clear();
        jsonObject.put("resCode", ConstantEnum.SUCCESS.getCode());
        jsonObject.put("message", ConstantEnum.SUCCESS.getMessage());
        return jsonObject;
    }

    /**
     * 取出缓存中的信息
     * @param userId
     * @return
     */
    @PostMapping("/getItem")
    public JSONObject getItem(@RequestParam(name = "userId") String userId){
        //todo md5加密后的key
        String key=null;
        String s = redisService.get(key);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resCode", ConstantEnum.SUCCESS.getCode());
        jsonObject.put("message", ConstantEnum.SUCCESS.getMessage());
        jsonObject.put("data",s);
        return jsonObject;
    }
}
