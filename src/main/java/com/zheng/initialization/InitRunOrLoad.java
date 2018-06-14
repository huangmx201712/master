package com.zheng.initialization;

import com.zheng.kafka.KafkaConsumerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitRunOrLoad {

    private Logger logger= LoggerFactory.getLogger(InitRunOrLoad.class);
    /**
     * 注意：需要在系统启动的时候就执行某些方法的时候可以这样调用
     * Constructor >> @resource(@Autowired) >> @PostConstruct
     *@PostConstruct不是spring的标签跟@resource一样
     * @PostConstruct将在依赖注入完成后被自动调用当前方法
     */
    @PostConstruct
    void doInit(){
        logger.info(">>>>>>>>>>>>123456789>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>123456789>>>>>>>>>>>>>>>>>>");
    }

}
