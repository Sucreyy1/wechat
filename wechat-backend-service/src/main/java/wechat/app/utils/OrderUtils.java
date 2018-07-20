package wechat.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by 61674 on 2018/7/20.
 */
public class OrderUtils {
    /**
     * 获取订单号
     *
     * @return
     */
    public static String getOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        String newDate = sdf.format(new Date());
        Random random = new Random();
        StringBuilder sb = new StringBuilder(newDate).append((int)((random.nextDouble()*9+1)*1000));
        return sb.toString();
    }
}
