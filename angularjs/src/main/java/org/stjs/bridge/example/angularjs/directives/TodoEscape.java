package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.jquery.Event;
import org.stjs.javascript.jquery.EventHandler;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoEscape {

	public TodoEscape(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		final int ESCAPE_KEY = 27;
		elem.bind("keydown", new EventHandler() {
			@Override
			public boolean onEvent(Event ev, Element THIS) {
				if (ev.keyCode == ESCAPE_KEY) {
					scope.$apply(attrs.$get("todoEscape"));
				}
				return false;
			}
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoEscape", new Function0<Object>() {
			@Override
			public Object $invoke() {
				return TodoEscape.class;
			}
		});
	}
}
