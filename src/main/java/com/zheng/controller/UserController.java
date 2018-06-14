package com.zheng.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


import com.alibaba.dubbo.config.ServiceConfig;
import com.zheng.log.LogRecord;
import com.zheng.service.IDubboService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zheng.model.User;
import com.zheng.service.UserService;

/**
 * 测试类
 *
 * @author hmx
 */
@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Resource
    private UserService userService;

    /**
     * mybatis 的测试
     * @return
     */
    @RequestMapping("/showUserInfo")
    @ResponseBody
    public ModelAndView getIndex() {
        logger.info("查询用户");
        ModelAndView mav = new ModelAndView("index");
        User user = userService.selectUserById(0);
        mav.addObject("user", user);
        return mav;
    }

    /**
     * 自定义注解的测试 logRecord
     * @return
     */
    @LogRecord(requestUrl = "/index")
    @RequestMapping("/getHome")
    public String getHome() {
        return "index";
    }


    /**
     * 该方法用于测试事务回滚
     */
    @RequestMapping("/addUserInfo")
    public void addUserInfo() {
        try {
            userService.addUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池启动线程
     */
    @RequestMapping("/startMyThread")
    public void startMyThread() {
        userService.startMyThread();
    }

    /**
     * kafka发送消息到主题中
     */
    @RequestMapping("/sendMessage")
    public void sendMessage() {
        userService.sendMessageBykafka();
    }

    /**
     * dubbo远程方法的调用
     */
    @RequestMapping("/doTest")
    public void doTest() {
       // String name = dubboService.findName();
        logger.info("正在dubbo进行远程实例的调用");
        userService.doTestDubboConsumer();

      //  ServiceConfig //暴露服务的一个类，可以研究一下
    }

 /*   @RequestMapping("/")
    public String tiIndex() {
      return "redirect:/showUserInfo.do";
    }*/
}
