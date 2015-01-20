package com.chu.pattern.abstractFactory;


/**
 * 抽象工厂模式
 * @author Administrator
 *
 */
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MailFactory factory = new MailFactory();
		Sender send = factory.produce();
		send.send();
	}

}
