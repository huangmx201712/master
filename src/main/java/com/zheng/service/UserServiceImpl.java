package com.zheng.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zheng.dao.UserDao;
import com.zheng.model.User;
import com.zheng.thread.MyThread;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * dubbo测试   远程对象实例
     */
    @Autowired
    private IDubboService dubboService;

    //@Resource
   // private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    public final static int USER_LENG = 5;


    @Override
    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public void addUser() throws Exception {
        int i = 0;
        int userLeng = 5;
        for (; i < USER_LENG; i++) {
            if (i == 4) {
                throw new Exception("报出异常，事务回滚");
            }
            User user = new User();
            user.setUserId(i + 3);
            user.setUserName("张三" + i);
            user.setUserPassword("123456");
            userDao.addUser(user);
        }
    }


    @Override
    public void startMyThread() {
        Thread thread=new MyThread();
        taskExecutor.execute(thread);
    }

    /**
     * kafka生产者
     */
    @Override
    public void sendMessageBykafka() {
/*        for(int i=0;i<10;i++){
            ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send("testtopic",i+"","g"+i);
            //添加回调函数成功和失败
            ListenableFutureCallback callback=new KafkaSendCallBack();
            sendResult.addCallback(callback);
        }*/
        System.out.println("消息发送成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser() throws Exception {
        int i = 0;
        int userLeng = 5;
        for (; i < USER_LENG; i++) {
            if (i == 4) {
                throw new Exception("报出异常，事务回滚：insertUser");
            }
            User user = new User();
            user.setUserId(i + 100);
            user.setUserName("李四" + i);
            user.setUserPassword("123456");
            userDao.addUser(user);
        }
    }


    @Override
    public void doTestDubboConsumer() {
        System.out.println( dubboService.findName());
    }
}
