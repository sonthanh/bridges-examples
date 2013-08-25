package org.stjs.bridge.example.angularjs.extra;

import org.stjs.javascript.Array;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.functions.Function3;

@STJSBridge
public interface ArrayExtra<T> extends Array<T> {
	public ArrayExtra<T> filter(Function3<T, Integer, ArrayExtra<T>, Boolean> fn);

	public ArrayExtra<T> filter(Function3<T, Integer, ArrayExtra<T>, Boolean> fn, Object context);
}
