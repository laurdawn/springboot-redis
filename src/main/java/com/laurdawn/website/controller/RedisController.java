package com.laurdawn.website.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laurdawn.website.enums.EResultType;

import redis.clients.jedis.exceptions.JedisDataException;

/** 
* @author  laurdawn 
* @version 2019年1月11日 下午4:31:24 
*/
@RestController
public class RedisController extends BaseController{
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 字符串设置
	 * @param key
	 * @param value
	 * @return
	 */
    @RequestMapping("/str/set")
    private String strSet(String key, String value) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	if(value == null || value.equals("")) {
    		return retResultData(EResultType.VALUE_NOT_FOUND);
    	}
    	if(redisTemplate.hasKey(key)) {
    		return retResultData(EResultType.REPEAT_KEY);
    	}
    	redisTemplate.opsForValue().set(key, value);
    	return retResultData(EResultType.SUCCESS);	
    }
    
    /**
     * 字符串获取
     * @param key
     * @return
     */
    @RequestMapping("/str/get")
    private String strGet(String key) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	String value = redisTemplate.opsForValue().get(key);
    	return retResultData(EResultType.SUCCESS, value);
    }

    /**
     * 列表设置
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/list/push")
    private String listPush(String key, String value) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	if(value == null || value.equals("")) {
    		return retResultData(EResultType.VALUE_NOT_FOUND);
    	}
    	if(redisTemplate.hasKey(key)) {
    		try {
				redisTemplate.opsForList().size(key);
			} catch (Exception e) {
				return retResultData(EResultType.REPEAT_KEY);
			}
    	}
    	redisTemplate.opsForList().leftPush(key, value);
    	return retResultData(EResultType.SUCCESS);	
    }
    
    /**
     * 获取列表第一个元素删除并返回值
     * @param key
     * @return
     */
    @RequestMapping("/list/pop")
    private String listPop(String key) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	String value = redisTemplate.opsForList().leftPop(key);
    	return retResultData(EResultType.SUCCESS, value);
    }
    
    /**
     * 获取list固定位置之间的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/list/range")
    private String listRange(String key, Long start, Long end) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	if(start == null) {
    		return retResultData(-1, "start [数据不存在 或者 数据为空]");
    	}
    	if(end == null) {
    		return retResultData(-1, "end [数据不存在 或者 数据为空]");
    	}
    	List<String> value = redisTemplate.opsForList().range(key, start, end);
    	return retResultData(EResultType.SUCCESS, value);
    }

    /**
     * set设置元素
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set/add")
    private String setAdd(String key, String... value) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	if(value == null || value.equals("")) {
    		return retResultData(EResultType.VALUE_NOT_FOUND);
    	}
    	if(redisTemplate.hasKey(key)) {
    		try {
				redisTemplate.opsForSet().size(key);
			} catch (Exception e) {
				return retResultData(EResultType.REPEAT_KEY);
			}
    	}
    	redisTemplate.opsForSet().add(key, value);
    	return retResultData(EResultType.SUCCESS);	
    }
    
    /**
     * set
     * 移除一个元素并返回数量
     * @param key
     * @return
     */
    @RequestMapping("/set/del")
    private String delRandom(String key, String... value) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	if(value == null || value.equals("")) {
    		return retResultData(EResultType.VALUE_NOT_FOUND);
    	}
    	Long num = redisTemplate.opsForSet().remove(key, value);
    	return retResultData(EResultType.SUCCESS, num);
    }
    
    /**
     * 获取set中key的所有元素
     * @param key
     * @return
     */
    @RequestMapping("/set/getAll")
    private String setGet(String key) {
    	if(key == null || key.equals("")) {
    		return retResultData(EResultType.KEY_NOT_FOUND);
    	}
    	Set<String> value = redisTemplate.opsForSet().members(key);
    	return retResultData(EResultType.SUCCESS, value);
    }
	
}
