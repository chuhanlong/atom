package com.chu.pattern.factory;

public class SendFactory {

	public static Mail getMail(){
		return new Mail();
	}
	
	public static Sms getSms(){
		return new Sms();
	}
}
