package com.chu.pattern.factory;


/**
 * 工厂模式
 * @author Administrator
 *
 */
public class test {
	public static void main(String[] args) {
		Sender mail = SendFactory.getMail();
		mail.send();
		
		Sender sms = SendFactory.getSms();
		sms.send();
	}
}
