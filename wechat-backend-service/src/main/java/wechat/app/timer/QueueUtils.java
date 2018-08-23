package wechat.app.timer;

import wechat.app.dao.entity.OrderInfo;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueueUtils {

    //Thread-safe queue
    private static DelayQueue<OrderInfo> queue = new DelayQueue<>();

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private QueueUtils() {

    }

    public static void payStatusCheck(OrderInfo orderInfo) {
        if (queue.add(orderInfo)) {
            condition.signal();
        }
    }


    public void check() {
        new Thread(() -> {
            try {
                for (;;) {
                    OrderInfo orderInfo = queue.poll();
                    if (orderInfo != null) {
                        //超时将订单置为无效
                        orderInfo.setStatus("");
                        orderInfo.setUpdateTime(new Date());
                    } else {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
