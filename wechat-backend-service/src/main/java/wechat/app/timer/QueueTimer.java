package wechat.app.timer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import wechat.app.dao.entity.OrderInfo;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class QueueTimer {

    //Thread-safe queue
    private static DelayQueue<OrderInfo> queue = new DelayQueue<>();

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private QueueTimer() {

    }

    @Async
    public void payStatusCheck(OrderInfo orderInfo) {
        try {
            lock.lock();
            if (queue.add(orderInfo)) {
                condition.signal();
            }
        } finally {
            lock.unlock();
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
        }).start();
    }

}
