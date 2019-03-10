package com.highpeak.parksmart.util;

import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.MailBean;
import com.highpeak.parksmart.service.CacheService;
import com.highpeak.parksmart.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpGeneration {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageBundleResource messageBundle;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CacheService cacheService;

    @Value("${mail.sender.subject}")
    private String subject;
    @Value("${mail.sender.precontent}")
    private String preContent;
    @Value("${mail.sender.postcontent}")
    private String postContent;


    public OtpGeneration() {

    }

    public OtpGeneration(String userName, String emailId) {
    }

    // Otp generation


    private static final Logger logger = LoggerFactory.getLogger(OtpGeneration.class);

    /**
     * Methid to generate OTP for login
     * @param emailId
     * @throws DataException
     * */
    public void generateOTP(String emailId, int userId) throws DataException
    {


        String numbers = "0123456789";

        // Creating 6 digit otp

        Random random = new Random();
        int otpLength = 4;
        char[] otp = new char[otpLength];
        logger.info("generating otp");
        for (int i = 0; i < otpLength; i++) {

            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }

        String generatedOTP = new String(otp);

        MailBean mailBean = new MailBean(emailId,
                subject,
                preContent + generatedOTP + " " + postContent);

        try {

            // Sending otp to user's mail
            logger.info("mail has been sent to user ");
            emailService.sendEmail(mailBean);
            cacheService.createCache(userId, generatedOTP);
            logger.info("otp cached");
        } catch (Exception e) {
            //logger.error("otp generation or mail sending failed");
            logger.error(e.getMessage());
            throw new DataException(Constants.EXCEPTION,
                    messageBundle.getMessage("UNABLE TO SEND OTP"), HttpStatus.BAD_REQUEST);
        }
    }
}
