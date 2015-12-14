/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Logger;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ksu
 */
public class AutoLoggingBeanPostProcessor implements BeanPostProcessor {

    Map<String, Class> classMap = new HashMap<>();
    private AutoLoggingController loggingController = new AutoLoggingController();
    final Logger logger = LoggerFactory.getLogger(AutoLogging.class);

    public AutoLoggingBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(loggingController, new ObjectName("autologging", "name", "autologger"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(AutoLogging.class)) {
            classMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = classMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (loggingController.isEnabled()) {
                        logger.debug("Call method " + method + " with args: " + Arrays.toString(args));
                        Object result;
                        try {
                            result = method.invoke(bean, args);
                            logger.debug("Method " + method + " returns " + result);
                            return result;
                        } catch (Exception e) {
                            logger.error("Method " + method + "threw Exception ", e);
                            throw e;
                        }
                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
