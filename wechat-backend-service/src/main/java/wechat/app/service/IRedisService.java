package wechat.app.service;

public interface IRedisService {

    boolean set(String key,String value);

    boolean setWithTime(String key,String value,long time);

    boolean del(String key);

    String get(String key);

    boolean exists(String key);
}
