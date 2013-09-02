package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.jquery.Event;
import org.stjs.javascript.jquery.EventHandler;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoBlur {

	public static void main(String[] args) {
		App.todomvc.directive("todoBlur", new Function0<Object>() {
			@Override
			public Object $invoke() {
				return new Callback3<Scope, JQueryCore<JQueryCore<?>>, Attributes>() {

					@Override
					public void $invoke(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
						elem.bind("blur", new EventHandler() {
							@Override
							public boolean onEvent(Event ev, Element THIS) {
								scope.$apply(attrs.$get("todoBlur"));
								return false;
							}
						});
					}
				};
			}
		});
	}

}
