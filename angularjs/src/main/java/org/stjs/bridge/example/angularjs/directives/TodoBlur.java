package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.jquery.Event;
import org.stjs.javascript.jquery.EventHandler;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoBlur implements Callback3<Scope, JQueryCore<JQueryCore<?>>, Attributes> {

	@Override
	@Template("invoke")
	public void $invoke(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		elem.bind("blur", new EventHandler() {
			@Override
			public boolean onEvent(Event ev, Element THIS) {
				scope.$apply(attrs.$get("todoBlur"));
				return false;
			}
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoEscape", new Function0<Object>() {
			@Override
			@Template("invoke")
			public Object $invoke() {
				return new TodoBlur();
			}
		});
	}
}
