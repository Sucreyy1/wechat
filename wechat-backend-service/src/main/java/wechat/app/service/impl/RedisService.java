package wechat.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wechat.app.service.IRedisService;

@Service
public class RedisService implements IRedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    private JedisPool jedisPool;

    @Override
    public boolean set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setWithTime(String key, String value, long time) {
        try(Jedis jedis = jedisPool.getResource()){
            jedis.set(key,value,"NX","EX",time);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean del(String key) {
        try(Jedis jedis = jedisPool.getResource()){
            if (jedis.exists(key)) {
                jedis.del(key);
            }else {
                logger.warn("key: {} 不存在.",key);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String get(String key) {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(String key) {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
