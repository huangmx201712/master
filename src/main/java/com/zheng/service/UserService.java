package com.zheng.service;

import com.zheng.model.User;

public interface UserService {
	/**
	 * 查询用户
	 * @param userId
	 * @return
	 */
	User selectUserById(Integer userId);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	void addUser() throws Exception ;

	/**
	 * 启动用户自定线程
	 */
	void startMyThread();

	/**
	 * 利用kafka来发送消息
	 */
	void sendMessageBykafka();

	/**
	 * 开启注解事务来添加用户
	 * @exception Exception
	 */
	void insertUser() throws Exception;

	/**
	 * 测试客户调用dubbo远程服务器
	 */
	void doTestDubboConsumer();



}
