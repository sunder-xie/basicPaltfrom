package com.kintiger.platform.framework.content.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author
 */
public class CountListener implements HttpSessionListener {

	/**
	 * 
	 */
	public final void sessionCreated(final HttpSessionEvent arg0) {
		init();
		OnlineCounter.raise();
	}

	/**
	 * 
	 */
	public final void sessionDestroyed(final HttpSessionEvent arg0) {
		if (OnlineCounter.getOnline() > 0) {
			OnlineCounter.reduce();
		}
	}

	private void init() {
	}
}