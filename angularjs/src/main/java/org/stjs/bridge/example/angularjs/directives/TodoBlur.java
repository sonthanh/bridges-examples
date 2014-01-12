package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoBlur {

	public TodoBlur(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		elem.bind("blur", (ev, THIS) -> {
			scope.$apply(attrs.$get("todoBlur"));
			return false;
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoBlur", () -> TodoBlur.class);
	}
}
