package com.chu.pattern.abstractFactory;

public class MailFactory implements provider {

	public Sender produce() {
		// TODO Auto-generated method stub
		return new Mail();
	}

}
