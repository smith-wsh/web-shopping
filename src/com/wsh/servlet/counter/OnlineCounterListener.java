package com.wsh.servlet.counter;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class OnlineCounterListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		OnlineCounter.raise();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		OnlineCounter.reduce();

	}
}
