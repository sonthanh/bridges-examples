package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoFocus {
	public TodoFocus(final Scope scope, JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		scope.$watch((String)attrs.$get("todoFocus"), new Callback2<Boolean, Boolean> () {
				@Override
				@Template("invoke")
				public void $invoke(Boolean newVal, Boolean oldVal) {
					if (newVal) {
						$timeout(function () {
							elem[0].focus();
					
				}}, 0, false);
			}
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoFocus", new Function0<Class<TodoFocus>>() {
			@Override
			@Template("invoke")
			public TodoFocus<TodoBlur> $invoke() {
				return TodoFocus.class;
			}
		});
	}
}
