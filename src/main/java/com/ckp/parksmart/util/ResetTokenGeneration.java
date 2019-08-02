package com.ckp.parksmart.util;


import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.datastore.repository.UserRepository;
import com.ckp.parksmart.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetTokenGeneration {

/*
    @Value("${mail.sender.token.subject}")
    private String subject;

    @Value("${mail.sender.token.reset.content}")
    private String contentForResetPassword;

    @Value("${mail.sender.token.set.content}")
    private String contentForSetPassword;

    @Value("${url.reset.password}")
    private String url;

    @Value("${url.protocol}")
    private String protocol;

    @Value("${url.domain}")
    private String domain;

    @Value("${url.base}")
    private String baseUserUrl;

    @Value("${url.id}")
    private String idQueryParam;
*/


    @Autowired
    private UserRepository userRepository;

    @Autowired
    MessageBundleResource messageBundleResource;

/*
    @Autowired
    private NotificationService notificationService;
*/

    private static final Logger logger = LoggerFactory.getLogger(ResetTokenGeneration.class);

    /**
     * token generation
     *
     * @param userModel user model
     * @return Password reset token
     * @throws DataException data exeption
     */
    //change the method name
    public UserModel generateAndSaveToken(UserModel userModel) throws DataException
    {
        String token = UUID.randomUUID().toString();

        Optional<UserModel> userModelOptional = userRepository.findByEmailAndIsActiveTrue(userModel.getEmail());
        if (!userModelOptional.isPresent())
        {
            throw new DataException(Constants.EXCEPTION, messageBundleResource.getMessage(Constants.USER_DOES_NOT_EXIST)
                    , HttpStatus.BAD_REQUEST);
        }

        UserModel userDetails = userModelOptional.get();

        // setting token
//        userDetails.setPasswordVerificationToken(token);
        userDetails.setTokenExpiryDate(new Timestamp(DateUtil.convertToUTC(DateUtil.futureTimeMillis(1))));
        userRepository.save(userDetails);
        logger.info("random token generated and saved in database");
        return userDetails;
    }

/*
    */
/**
     * sending mail for Reset password
     *
     * @param userModel user model
     * @throws DataException data exception
     *//*

    public void sendForgotPasswordMail(UserModel userModel) throws DataException
    {
        userModel = generateAndSaveToken(userModel);
        String content = "Hi " + userModel.getUserName() + "\n" + contentForResetPassword + ": \n"
                + urlGeneration(protocol, domain, url, userModel.getPasswordVerificationToken(), idQueryParam,
                userModel.getId());

        MailBean mailBean = new MailBean(userModel.getEmail(), subject, content);

        //sending setting password token in mail
        notificationService.sendEmail(mailBean);
        logger.info(Constants.FORGOT_PASSWORD_URL_IS_SENT);
    }

    */
/**
     * sending mail for set password
     *
     * @param userModel user model
     * @throws DataException data exception
     *//*

    public String sendSetPasswordMail(UserModel userModel) throws DataException
    {
        String token = UUID.randomUUID().toString();
        String content = "Hi " + userModel.getUserName() + "\n" + contentForSetPassword + ": \n"
                + urlGeneration(protocol, domain, url, token, idQueryParam,
                userModel.getId());
        //mail isnt sent
        MailBean mailBean = new MailBean(userModel.getEmail(), subject, content);

        //sending setting password token in mail
        notificationService.sendEmail(mailBean);
        logger.info(Constants.PASSWORD_RESET_URL_HAS_BEEN_SENT);
        return token;
    }
*/


    /**
     * generating url
     *
     * @param protocol protocol
     * @param domain   domain
     * @param url      url
     * @param param    parameters
     * @param <T>      param
     * @return object
     */
    public static <T> String urlGeneration(T protocol, T domain, T url, String param, T urlId, int id)
    {
        return protocol.toString() + domain.toString() + url.toString() + param + urlId + id;
    }
}
