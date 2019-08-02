package com.ckp.parksmart.service;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    /**
     * Creates Cache
     *
     * @param userId
     * @param otp
     */
    public void createCache( int userId, String otp )
    {

        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("OTPcache");
        cache.put(new Element(String.valueOf(userId), otp));
        logger.info("cache created");

    }

    /**
     * Get Cache
     *
     * @param userId
     */
    public String gettingCache( int userId )
    {

        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("OTPcache");
        Element element = cache.get(String.valueOf(userId));
        if(element==null){
            logger.info("otp expired");
            return "Otp expired";
        }
        logger.info("fetching otp from cache.");
        return (String) element.getObjectValue();
    }

    /**
     * Create password cache
     *
     * @param username
     * @param password
     */
    public void createPasswordCache(String username,String password){

        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("PasswodCache");
        cache.put(new Element(username, password));
    }

    /**
     * Get Password cache
     *
     * @param username
     */
    public String gettingPasswordCache( String username )
    {

        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("PasswodCache");
        Element element = cache.get(username);
        return (String) element.getObjectValue();
    }

}
