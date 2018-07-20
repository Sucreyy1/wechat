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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(newDate);
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
