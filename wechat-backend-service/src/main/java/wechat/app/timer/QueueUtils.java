package wechat.app.timer;

import wechat.app.dao.entity.OrderInfo;

import java.util.concurrent.DelayQueue;

public class QueueUtils {

    //Thread-safe queue
    private static DelayQueue<OrderInfo> queue = new DelayQueue<>();

    private QueueUtils(){

    }

    public static void payStatusCheck(OrderInfo orderInfo){
        queue.add(orderInfo);
    }

    public static DelayQueue getQueue(){
        return queue;
    }
}
