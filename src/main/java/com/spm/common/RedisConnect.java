package com.spm.common;

import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by he on 2017/10/14.
 */
@Component
public final class RedisConnect {
    //Redis服务器IP
    private static String[] ADDRESS =RedisConfig.REDIS_URLS;

    //Redis的端口号
    private static int PORT = RedisConfig.REDIS_PORT;

    private static boolean ISCLUSTER=RedisConfig.REDIS_ISCLUSTER;

    private static String REDIS_PWD=RedisConfig.REDIS_PWD;

    //访问密码
    private static String AUTH = "";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    private static JedisCluster jedisCluster = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            if(ISCLUSTER)
            {
                Set<HostAndPort> jedisClusterNodes = new HashSet<>();
                for (String url : ADDRESS) {
                    jedisClusterNodes.add(new HostAndPort(url.split(":")[0], Integer.parseInt(url.split(":")[1])));
                }
                // 根据节点集创集群链接对象
                //JedisCluster jedisCluster = newJedisCluster(jedisClusterNodes);
                // 节点，超时时间，最多重定向次数，链接池
                jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100, config);
            }
            else {
                if(!REDIS_PWD.equals("")) {
                    jedisPool = new JedisPool(config, ADDRESS[0], PORT, TIMEOUT, REDIS_PWD, 0);
                }
                else{
                    jedisPool = new JedisPool(config, ADDRESS[0], PORT, TIMEOUT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static JedisCommands getJedis() {
        try {
            if(ISCLUSTER)
            {
                if (jedisCluster != null) {
                    return jedisCluster;
                } else {
                    return null;
                }
            }
            else {
                if (jedisPool != null) {
                    Jedis resource = jedisPool.getResource();
                    return resource;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final JedisCommands jedis) {
        if (jedis != null) {
            if(!ISCLUSTER) {
                Jedis temp = (Jedis) jedis;
                temp.close();
            }
        }
    }
}
