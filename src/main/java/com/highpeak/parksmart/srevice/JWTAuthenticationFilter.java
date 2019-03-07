package com.highpeak.parksmart.srevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.highpeak.idp.datastore.model.UserModel;
import com.highpeak.idp.datastore.repositories.UserRepository;
import com.highpeak.idp.pojo.LoginBean;
import com.highpeak.idp.pojo.LoginResponseBean;
import com.highpeak.idp.util.NullEmptyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 
 * @author Sushmitha.K
 *
 */

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;
    private static String email;
    private ApplicationContextProvider appContext = new ApplicationContextProvider();
    private UserRepository userRepository = appContext.getApplicationContext()
            .getBean(UserRepository.class);
    //    private UserProfileToRefreshTokenRepository userProfileToRefreshTokenRepository = appContext.getApplicationContext()
    //            .getBean(UserProfileToRefreshTokenRepository.class);

    public JWTAuthenticationFilter( String loginUrl, AuthenticationManager authenticationManager )
    {
        super(new AntPathRequestMatcher(loginUrl));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response )
    {
        try
        {
            LoginBean credentials = new ObjectMapper().readValue(request.getInputStream(), LoginBean.class);
            email = credentials.getEmail();
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                    credentials.getPassword(), new ArrayList<>()));
        }
        catch( IOException | AuthenticationException e )
        {
            e.printStackTrace();
            LOGGER.error("Exception", "Invalid Phone Number/Password");
            System.out.println(e.getMessage());
            throw new BadCredentialsException("Invalid Phone Number/password");

        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth ) throws IOException, ServletException
    {

        LOGGER.info("User Successfully authenticated\nGenerating auth token............");
        SecurityContextHolder.getContext().setAuthentication(auth);
        String role = auth.getAuthorities().iterator().next().toString();
        Optional<UserModel> userModel = userRepository.findByEmailAndIsActiveTrue(auth.getName());
        // check if the user is verified with otp or not
        if( userModel.isPresent() )
        {
            //            Optional<UserProfileToRefreshToken> userProfileToUserRefreshToken = userProfileToRefreshTokenRepository
            //                    .findByUserProfileId(userProfile.get());
            if( NullEmptyUtils.isNullorEmpty(userModel.get().getRefreshToken()) )
            {
                userModel.get().setRefreshToken(createRefreshToken(auth.getName(), role, userModel.get().getId()));
                userRepository.save(userModel.get());

            }
            response.addHeader("RefreshToken", userModel.get().getRefreshToken());
            response.addHeader(TokenConstant.HEADER_STRING,
                    TokenConstant.TOKEN_PREFIX + createAccessToken(auth.getName(), role, userModel.get().getId()));
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Content-Type", "application/json");
            try
            {
                response.getWriter().print(new JSONObject()
                        .put("entity",
                                new JSONObject(new Gson().toJson(new LoginResponseBean(userModel.get().getId(),
                                        userModel.get().getEmail(), role))))
                        .put("status", HttpServletResponse.SC_OK).put("message", "You are successfully logged in"));
            }
            catch( JSONException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed ) throws IOException, ServletException
    {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        UserModel userModel = null;
        if( email != null && !email.isEmpty() )
        {
            userModel = userRepository.findByEmail(email);
        }
        JSONObject jsonObject = new JSONObject();
        String message = "Invalid Email/Password";
        try
        {
            Integer messageCode = 401;
            if( userModel!= null && !NullEmptyUtils.isNull(userModel)
                    && NullEmptyUtils.isNull(userModel.isActive()) )
            {

                message = "Please Reset Password Before Login.";

                messageCode = 601;
                response.setStatus(601);
            }
            jsonObject.put("entity", JSONObject.NULL).put("status", messageCode).put("message", message)
                    .put("messageCode", messageCode).put("stackTrace", JSONObject.NULL);
        }
        catch( JSONException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.addHeader("Content-Type", "application/json");
        response.getWriter().print(jsonObject);

    }

    private String createAccessToken( String email, String role, Integer userId )
    {
        return Jwts.builder().claim("role", role).claim("userId", userId).setSubject((email))
                .setExpiration(new Date(System.currentTimeMillis() + TokenConstant.ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, TokenConstant.SECRET).compact();
    }

    private String createRefreshToken( String email, String role, Integer userId )
    {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("scopes", Arrays.asList());
        return Jwts.builder().setClaims(claims).claim("role", role).claim("userId", userId).setIssuer(null)
                .setId(UUID.randomUUID().toString()).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET).compact();
    }
}
