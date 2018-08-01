package com.spm.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    /**
     * 设置键的值为字符串，且字符串大小不超过1073741824字节（1 GB）
     *
     * @param key   键
     * @param value 字符串值
     * @return 状态代码
     */
    String set(String key, String value);

    /**
     * 设置键的值为字符串，且字符串大小不超过1073741824字节（1 GB）
     *
     * @param key   键
     * @param value 字符串值
     * @param nxORxx NX|XX NX-仅键不存在时才起作用 XX-仅键存在时才起作用
     * @return 状态代码
     */
    String set(String key, String value, String nxORxx);

    /**
     * 设置键的值为字符串，且字符串大小不超过1073741824字节（1 GB）
     *
     * @param key   键
     * @param value 字符串值
     * @param nxORxx NX|XX NX-仅键不存在时才起作用 XX-仅键存在时才起作用
     * @param exORpx EX/PX 到期时间的单位：EX=秒，PX=毫秒
     * @param time <code>exORpx</code>单位下的到期时间
     * @return 状态代码
     */
    String set(String key, String value, String nxORxx, String exORpx, long time);

    /**
     * 仅当键不存在时设置键的值
     * @param key 键
     * @param value 值
     * @return 1值已设置 0 值未设置
     */
    Long setnx(String key, String value);

    /**
     * 设置键的值并设置到期时间
     * @param key 键
     * @param seconds 秒数
     * @param value 值
     * @return 状态代码
     */
    String setex(String key, int seconds, String value);

    /**
     * 设置键的值并设置到期时间
     * @param key 键
     * @param milliseconds 毫秒数
     * @param value 值
     * @return 状态代码
     */
    String psetex(final String key, final long milliseconds, final String value);

    /**
     * 如果键已经存在且是一个字符串，该命令将提供的值追加到那个字符串的结尾。
     * 如果键不存在将创建它，并且设置为空字符串，因此APPEND是非常类似于SET的一个特例。
     *
     * @param key   键
     * @param value 新值
     * @return 追加后的字符串值的长度
     */
    Long append(String key, String value);

    /**
     * 根据键(key)删除
     *
     * @param key 键
     * @return 删除的键数
     */
    Long del(String key);

    /**
     * 给定的键(key)是否存在
     *
     * @param key 键
     * @return true存在，false不存在
     */
    Boolean exists(String key);

    /**
     * 撤销超时key并转换为普通key
     * @param key 键
     * @return 键还继续留存返回1，键未继续留存返回0（仅发生在键未设置的情况下）
     */
    Long persist(String key);

    /**
     * 以字符串的形式返回key存储的值的类型。类型可以是“none”，“string”，“list”，“set”，“zset”，“hash”。
     * 如果键不存在返回“none”
     * @param key 键
     * @return 状态代码，具体是：“none”，“string”，“list”，“set”，“zset”，“hash”
     */
    String type(String key);

    /**
     * 指定的秒数(seconds)到之后，删除该键(key)
     * @param key 键
     * @param seconds 秒数
     * @return 1：超时设置成功 0：超时设置失败，因为该键已经关联了超时（这个会发生在Redis版本小于等于2.1.3，大于2.1.3会更新超时）或者键不存在。
     */
    Long expire(String key, int seconds);

    /**
     * 指定的毫秒数(milliseconds)到之后，删除该键(key)
     * @param key 键
     * @param milliseconds 毫秒数
     * @return 1：超时设置成功 0：超时设置失败，因为该键已经关联了超时（这个会发生在Redis版本小于等于2.1.3，大于2.1.3会更新超时）或者键不存在。
     */
    Long pexpire(String key, long milliseconds);

    /**
     * 指定的时间(unix时间，1970年1月1日算起的秒数)到之后，删除该键
     * @param key 键
     * @param unixTime 超时时间
     * @return 1：超时设置成功 0：超时设置失败，因为该键已经关联了超时（这个会发生在Redis版本小于等于2.1.3，大于2.1.3会更新超时）或者键不存在。
     */
    Long expireAt(String key, long unixTime);

    /**
     * 指定的时间(unix时间，1970年1月1日算起的毫秒数)到之后，删除该键
     * @param key 键
     * @param millisecondsTimestamp 毫秒级的时间戳
     * @return 1：超时设置成功 0：超时设置失败，因为该键已经关联了超时（这个会发生在Redis版本小于等于2.1.3，大于2.1.3会更新超时）或者键不存在。
     */
    Long pexpireAt(String key, long millisecondsTimestamp);

    /**
     * 返回键设置的超时时间还剩余多长(秒)
     * @param key 键
     * @return 键设置的超时时间还剩余的秒数。在Redis2.6或更旧的版本中，如果键不存在或未设置超时时间将返回-1。在Redis2.8或更新版本中，如果未设置超时时间返回-1，如果键不存在返回-2
     */
    Long ttl(String key);

    /**
     * 返回键设置的超时时间还剩余多长(毫秒)
     * @param key 键
     * @return 键设置的超时时间还剩余的毫秒数。在Redis2.6或更旧的版本中，如果键不存在或未设置超时时间将返回-1。在Redis2.8或更新版本中，如果未设置超时时间返回-1，如果键不存在返回-2
     */
    Long pttl(final String key);

    /**
     * 获取指定键的值。如果键不存在，将返回null。如果值存储的不是字符串，将返回错误，因为get仅能处理字符串值
     * @param key 键
     * @return 字符串值
     */
    String get(String key);

    /**
     * GETSET是一个设置新值并返回旧值的原子操作
     * @param key 键
     * @param value 新值
     * @return 旧值
     */
    String getSet(String key, String value);

    /**
     * 返回从start到end的子字符串（start和end都包含在内）
     * @param key 键
     * @param start 开始索引
     * @param end 结束索引
     * @return 从start到end的子字符串（start和end都包含在内）
     */
    String substr(String key, int start, int end);

    /**
     * 删除指定键存储的hash中的字段
     * @param key 键
     * @param field 字段
     * @return 如果指定键(key)存储的hash中存在指定的字段(field)，删除并返回1；否则返回0，不做任何操作。
     */
    Long hdel(String key, String... field);

    /**
     * 给定的字段是否在键存储的hash中存在
     * @param key 键
     * @param field 字段
     * @return true存在，false不存在(键或字段不存在)
     */
    Boolean hexists(String key, String field);

    /**
     * 如果键存储了hash列表，则返回字段关联的值。如果键或字段不存在，将返回特殊的零(nil)值
     * @param key 键
     * @param field 字段
     * @return 字符串值
     */
    String hget(String key, String field);

    /**
     * 返回键存储的hash列表的所有字段和值
     * @param key 键
     * @return hash列表的所有字段和值
     */
    Map<String, String> hgetAll(String key);

    /**
     * 返回键存储的hash列表的所有字段
     * @param key 键
     * @return hash列表的所有字段
     */
    Set<String> hkeys(String key);

    /**
     * 返回键存储的hash列表的长度
     * @param key 键
     * @return hash列表的长度
     */
    Long hlen(String key);

    /**
     * 返回指定字段(fields)关联的值
     * @param key 键
     * @param fields 字段
     * @return 指定字段关联的值
     */
    List<String> hmget(String key, String... fields);

    /**
     * 设置各自字段的各自值。HMSET可使用新值替换旧值。
     * 如果键不存在，将创建一个来存储hash列表。
     * @param key 键
     * @param hash hash列表
     * @return 返回OK或异常（如果hash是空的）
     */
    String hmset(String key, Map<String, String> hash);

    /**
     * 为键(key)存储的hash列表中指定的字段(field)设置值(value)
     * @param key 键
     * @param field 字段
     * @param value 值
     * @return 如果字段存在，且更新了新值，就返回0，如果字段不存在，就创建一个字段来存储并返回1。
     */
    Long hset(String key, String field, String value);

    /**
     * 为键(key)存储的hash列表中指定的字段(field)设置值(value)。键不存在时起作用
     * @param key 键
     * @param field 字段
     * @param value 值
     * @return 如果键存在，就返回0，如果不存在，就创建并返回1。
     */
    Long hsetnx(String key, String field, String value);

    /**
     * 返回键(key)存储的hash列表中的所有值
     * @param key 键
     * @return 键(key)存储的hash列表中的所有值
     */
    List<String> hvals(String key);

    /**
     * 在键存储的list的头部添加字符串值
     * 如果键不存在，在push操作之前会先创建；如果键存在，但存储的不是list，将返回一个错误
     * @param key 键
     * @param string 字符串值
     * @return push操作后list中的元素数
     */
    Long lpush(String key, String... string);

    /**
     * 在键存储的list的尾部添加字符串值
     * 如果键不存在，在push操作之前会先创建；如果键存在，但存储的不是list，将返回一个错误
     * @param key 键
     * @param string 字符串值
     * @return push操作后list中的元素数
     */
    Long rpush(String key, String... string);

    /**
     * 返回键存储的list的长度，如果键不存在将返回0；如果键存储的不是list，将返回一个错误
     * @param key 键
     * @return 键存储的list的长度
     */
    Long llen(String key);

    /**
     * 返回键存储的list的指定范围的元素。start和end是基于0的索引，0是list的第一个元素，1是第二个元素等等。
     * 例如lrange("foobar",0,2)将返回foobar键存储的列表的前三个元素
     * start和end也可以是负数，表示list结尾的偏移量。例如-1是列表的最后一个元素，-2是倒数第二个元素
     * 超出范围的索引不会产生错误：如果start超过列表的end（或者start&gt;end），返回空列表。
     * @param key 键
     * @param start 开始索引
     * @param end 结束索引
     * @return 键存储的list的指定范围的元素
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     COUNT 的值可以是以下几种：
     count &gt; 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     count &lt; 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param key 键
     * @param count 计数
     * @param value 值
     * @return 删除的元素数
     */
    Long lrem(String key, long count, String value);

    /**
     * 将键存储的list裁剪为只剩下指定范围里的元素
     * ltrim一般和lpush或rpush结合使用，例如
     * <p>
     * {@code lpush("mylist", "someelement"); ltrim("mylist", 0, 99); * }
     * </p>
     * <p>
     * 上面两个命令将压元素到list中，但list不会无限增长，这对于使用redis来存储日志非常有用
     * </p>
     * @param key 键
     * @param start 开始索引
     * @param end 结束索引
     * @return 状态代码
     */
    String ltrim(String key, long start, long end);

    /**
     * 返回键存储的list的指定索引位置的元素，0第一个，1第二个，支持负数，-1是最后一个，-2是倒数第二个
     * 如果键存储的值不是list，将返回一个错误，如果超出索引范围将返回零值(nil)
     * @param key 键
     * @param index 索引
     * @return 键存储的list的指定索引位置的元素
     */
    String lindex(String key, long index);

    /**
     * 在键存储的list的指定索引位置的元素设置新值。超出索引将产生一个错误。也支持负数索引。
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return 状态代码
     */
    String lset(String key, long index, String value);

    /**
     * 返回list的第一个元素并从list中删除的原子操作
     * 如果键不存在或list已经是空的将返回零值(nil)
     * @param key 键
     * @return list的第一个元素
     */
    String lpop(String key);

    /**
     * 返回list的最后一个元素并从list中删除的原子操作
     * 如果键不存在或list已经是空的将返回零值(nil)
     * @param key 键
     * @return list的最后一个元素
     */
    String rpop(String key);

    /**
     * 设置给定键的集合成员，如果成员值在集合中已存在，则不执行任何操作。
     * 如果键不存在，将创建一个新键来存储用该成员值填充的集合。
     * 如果键存在，但不能存储集合，则返回错误。
     * @param key 键
     * @param member 成员值
     * @return 返回整型，1要添加的成员被成功添加，0要添加的成员值已是集合成员
     */
    Long sadd(String key, String... member);

    /**
     * 返回给定键的所有集合成员。
     * @param key 键
     * @return 所有集合成员
     */
    Set<String> smembers(String key);

    /**
     * 从给定键存储的集合中移除指定成员。
     * 如果指定成员已不在集合中，则不执行任何操作。
     * 如果键存储的不是集合，则返回错误。
     * @param key
     * @param member
     * @return
     */
    Long srem(String key, String... member);

    /**
     * 从给定键存储的集合中随机移除一个成员并作为返回值返回。
     * 如果集合为空或键不存在，则返回一个空对象
     * {@link #srandmember(String)}命令可以执行相似的工作，但只返回元素并从集合中不移除。
     * @param key 键
     * @return
     */
    String spop(String key);

    /**
     * 从给定键存储的集合中随机移除指定数的成员并作为返回值返回。
     * @param key 键
     * @param count 指定数
     * @return 指定数的成员子集
     */
    Set<String> spop(String key, long count);

    /**
     * 获取给定键存储的集合中的元素数量，如果键不存在，和空集合一样返回0。
     * @param key 键
     * @return 给定键存储的集合中的元素数量
     */
    Long scard(String key);

    /**
     * 判断给定成员值是否是给定键存储的集合的成员，返回1则是，返回0则不是
     * @param key
     * @param member
     * @return 返回整型，1给定成员值是给定键存储的集合的成员，0给定成员值不是给定键存储的集合的成员或者键不存在。
     */
    Boolean sismember(String key, String member);

    /**
     * 从给定键存储的集合中随机返回一个成员。
     * @param key 键
     * @return 随机返回的成员值
     */
    String srandmember(String key);

    /**
     * 从给定键存储的集合中随机返回指定数量的成员。
     * @param key 键
     * @param count 指定数量
     * @return 指定数量的成员值列表
     */
    List<String> srandmember(String key, int count);

    /**
     * 获取给定键存储的值的长度
     * 当键不存在时返回0
     * @param key 键
     * @return 返回整型，给定键存储的值的长度
     */
    Long strlen(String key);

    /**
     * 排序给定键存储的一个集合或一个列表
     * @param key 键
     * @return 假定键存储的集合/列表包含一个数字列表，返回值将是从最小到最大的顺序排列的数字列表。
     */
    List<String> sort(String key);

}
