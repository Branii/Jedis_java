package com.pivo.utility;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Utility {

    private static Jedis jedis;

    public Utility() {
        this.jedis =  new Jedis();
    }

    public static String pushToCacheString(String key, String message) { 
        jedis.set(key, message);
        jedis.close();
        return "Message pushed to cache";
    } // push string to cache

    public static String pushToCacheList(String key, String message) {
        jedis.rpush(key, message);
        jedis.close();
        return "Message pushed to cache";
    } // push list to cache

    public static String pullFromCacheString(String key) {
        String message = jedis.get(key);
        jedis.close();
        return message;
    }

    public static String pullFromCacheList(String key) {
        List<String> messages = jedis.lrange(key, 0, -1);
        jedis.close();
        return messages.toString();
    } // pull list from cache


    
}
