package wechat.app.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单信息
 * 实现Delayer接口用于加入延迟队列进行订单状态轮询
 */
public class OrderInfo implements Serializable,Delayed {

    private String status;

    private Date createTime;

    private Date updateTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.createTime.getTime() - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o)
            return 0;
        if (o instanceof OrderInfo) {
            OrderInfo o1 = (OrderInfo)o;
            return (int)(this.getCreateTime().getTime() - o1.getCreateTime().getTime());
        }
        return -1;
    }
}
