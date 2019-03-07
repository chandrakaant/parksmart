
package com.highpeak.parksmart.srevice;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Sushmitha.K
 *
 */
@Service
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public ApplicationContext getApplicationContext()
    {
        return context;
    }

    @Override
    public void setApplicationContext( ApplicationContext ac ) throws BeansException
    {
        context = ac;
    }
}
