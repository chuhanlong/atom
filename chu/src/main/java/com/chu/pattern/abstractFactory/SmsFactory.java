package com.chu.pattern.abstractFactory;

public class SmsFactory implements provider {

	public Sender produce() {
		// TODO Auto-generated method stub
		return new Sms();
	}

}
