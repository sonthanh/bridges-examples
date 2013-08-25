package org.stjs.bridge.example.angularjs.extra;

import org.stjs.javascript.annotation.STJSBridge;

@STJSBridge
public abstract class LocalStorage {
	public abstract <T> T getItem(String key);

	public abstract void setItem(String key, Object data);
}
