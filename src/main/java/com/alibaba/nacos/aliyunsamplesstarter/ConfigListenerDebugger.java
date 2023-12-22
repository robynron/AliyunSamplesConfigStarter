package com.alibaba.nacos.aliyunsamplesstarter;

import com.alibaba.nacos.spring.context.event.config.NacosConfigReceivedEvent;
import com.alibaba.nacos.spring.core.env.NacosPropertySource;
import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Iterator;

/**
 * @author rong
 */
@Component
public class ConfigListenerDebugger implements EnvironmentAware, ApplicationListener<NacosConfigReceivedEvent> {
    
    Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigListenerDebugger.class);
    
    @Override
    public void onApplicationEvent(NacosConfigReceivedEvent event) {
        logger.info(
                "Listening on NacosConfigReceivedEvent -  dataId : {} , groupId : {} , "
                        + "content : {} ." ,
                event.getDataId(), event.getGroupId(), event.getContent());
    }
    
    @Override
    public void setEnvironment(Environment environment) {
        // Do nothing but to print some info
        if (environment instanceof StandardServletEnvironment) {
            Iterator<PropertySource<?>> iterator = ((StandardServletEnvironment) environment).getPropertySources()
                    .iterator();
            while (iterator.hasNext()) {
                PropertySource<?> it = iterator.next();
                if (! (it instanceof NacosPropertySource)) {
                    continue;
                }
                logger.info("NacosPropertySource - init source value. ");
                ((NacosPropertySource) it).getSource().forEach((k, v) -> {
                    logger.info("key: {}, value: {}.", k, v);
                });
            }
        }
    }
}
