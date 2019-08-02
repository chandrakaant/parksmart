package com.ckp.parksmart.security;

/**
 * 
 * @author Sushmitha.K
 *
 */
public class TokenConstant {

    private TokenConstant()
    {
    }

    public static final String SECRET = "4[)|>53(3|27";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 2592000000l; // 30 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 2592000000l; // 30 days
}
