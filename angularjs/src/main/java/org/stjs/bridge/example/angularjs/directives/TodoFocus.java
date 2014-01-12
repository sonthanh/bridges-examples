package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.functions.Function1;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoFocus {
	private static Callback3<Function0<Boolean>, Object, Object> $timeout;

	public TodoFocus(final Scope scope, final JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		scope.<Boolean>$watch(attrs.$get("todoFocus"), (newVal, oldVal) -> {
			if (newVal) {
				$timeout.$invoke(() -> {
					elem.$get(0).focus();
					return true;
				}, 0, false);
			}
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoFocus", new Function1<Callback3<Function0<Boolean>, Object, Object>, Object>() {
			@Override
			public Object $invoke(Callback3<Function0<Boolean>, Object, Object> $timeout) {
				TodoFocus.$timeout = $timeout;
				return TodoFocus.class;
			}
		});
	}
}
