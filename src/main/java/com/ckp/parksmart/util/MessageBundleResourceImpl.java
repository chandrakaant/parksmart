package com.ckp.parksmart.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class MessageBundleResourceImpl implements MessageBundleResource {

    @Autowired
    private MessageSource messageSource;

    /**
     * gives you message value back depending upon the key passed
     *
     * @param key
     * @return
     */
    @Override
    public String getMessage(final String key) {
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * gives you message value back depending upon the key passed with additional parameters
     *
     * @param key
     * @param objects
     * @return
     */
    @Override
    public String getMessage(final String key, Object[] objects) {
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, objects, locale);
    }

    /**
     * Gives a map of key value pairs of messages
     *
     * @param keys
     * @return
     */
    @Override
    public Map<String, String> getMessages(List<String> keys) {
        Map<String, String> messages = new HashMap<>();
        for (String key : keys) {
            String value = getMessage(key);
            messages.put(key, value);
        }

        return messages;
    }
}

