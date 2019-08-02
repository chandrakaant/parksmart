package com.ckp.parksmart.util;

import java.util.List;
import java.util.Map;

public interface MessageBundleResource {

    /**
     * gives you message value back depending upon the key passed
     *
     * @param key
     * @return
     */
    String getMessage(final String key);

    /**
     * gives you message value back depending upon the key passed with additional parameters
     *
     * @param key
     * @param objects
     * @return
     */
    String getMessage(final String key, Object[] objects);

    /**
     * Get message for set of keys
     *
     * @param keys
     * @return
     */
    Map<String, String> getMessages(final List<String> keys);
}

