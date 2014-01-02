package org.stjs.bridge.example.angularjs.extra;

import org.stjs.javascript.Array;
import org.stjs.javascript.annotation.STJSBridge;
import org.stjs.javascript.functions.Function3;

@STJSBridge
public class ArrayExtra<T> extends Array<T> {
	public native ArrayExtra<T> filter(Function3<T, Integer, ArrayExtra<T>, Boolean> fn);

	public native ArrayExtra<T> filter(Function3<T, Integer, ArrayExtra<T>, Boolean> fn, Object context);
}
