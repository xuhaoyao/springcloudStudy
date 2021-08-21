package com.scnu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 官网给出警告,我们自定义的规则配置不能放在@ComponentScan所扫描的范围内
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule iRule(){
        return new RandomRule();
    }

}
