package com.spm.service.impl;

import com.spm.common.RedisConnect;
import com.spm.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

        public String set(String key, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.set(key, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String set(String key, String value, String nxORxx) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.set(key, value, nxORxx);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String set(String key, String value, String nxxx, String expx, long time) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.set(key, value, nxxx, expx, time);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long setnx(String key, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.setnx(key, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String setex(String key, int seconds, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.setex(key, seconds, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String psetex(String key, long milliseconds, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.psetex(key, milliseconds, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long append(String key, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.append(key, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long del(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.del(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Boolean exists(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.exists(key);
            }
            catch (Exception ex){
                return null;
            }
            finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long persist(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.persist(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String type(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.type(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long expire(String key, int seconds) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.expire(key, seconds);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long pexpire(String key, long milliseconds) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.pexpire(key, milliseconds);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long expireAt(String key, long unixTime) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.expireAt(key, unixTime);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long pexpireAt(String key, long millisecondsTimestamp) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.pexpireAt(key, millisecondsTimestamp);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long ttl(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.ttl(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long pttl(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.pttl(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String get(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.get(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String getSet(String key, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.getSet(key, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String substr(String key, int start, int end) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.substr(key, start, end);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long hdel(String key, String... field) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hdel(key, field);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Boolean hexists(String key, String field) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hexists(key, field);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String hget(String key, String field) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hget(key, field);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Map<String, String> hgetAll(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hgetAll(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Set<String> hkeys(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hkeys(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long hlen(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hlen(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public List<String> hmget(String key, String... fields) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hmget(key, fields);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String hmset(String key, Map<String, String> hash) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hmset(key, hash);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long hset(String key, String field, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hset(key, field, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long hsetnx(String key, String field, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hsetnx(key, field, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public List<String> hvals(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.hvals(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long lpush(String key, String... string) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lpush(key, string);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long rpush(String key, String... string) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.rpush(key, string);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long llen(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.llen(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public List<String> lrange(String key, long start, long end) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lrange(key, start, end);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long lrem(String key, long count, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lrem(key, count, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String ltrim(String key, long start, long end) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.ltrim(key, start, end);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String lindex(String key, long index) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lindex(key, index);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String lset(String key, long index, String value) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lset(key, index, value);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String lpop(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.lpop(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String rpop(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.rpop(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long sadd(String key, String... member) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.sadd(key, member);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Set<String> smembers(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.smembers(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long srem(String key, String... member) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.srem(key, member);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String spop(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.spop(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Set<String> spop(String key, long count) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.spop(key, count);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long scard(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.scard(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Boolean sismember(String key, String member) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.sismember(key, member);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public String srandmember(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.srandmember(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public List<String> srandmember(String key, int count) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.srandmember(key, count);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public Long strlen(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.strlen(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

        public List<String> sort(String key) {
            JedisCommands jedisCommands = RedisConnect.getJedis();
            try {
                return jedisCommands.sort(key);
            } finally {
                RedisConnect.returnResource(jedisCommands);
            }
        }

    }
