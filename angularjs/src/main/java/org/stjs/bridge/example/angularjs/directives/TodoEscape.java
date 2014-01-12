package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoEscape {

	public TodoEscape(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		final int ESCAPE_KEY = 27;
		elem.bind("keydown", (ev, THIS) -> {
			if (ev.keyCode == ESCAPE_KEY) {
				scope.$apply(attrs.$get("todoEscape"));
			}
			return false;
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoEscape", () -> TodoEscape.class);
	}
}
