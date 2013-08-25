package org.stjs.bridge.example.angularjs.directives;

import static org.stjs.bridge.angularjs.GlobalAngularJS.$timeout;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoFocus {

	public static void main(String[] args) {
		App.todomvc.directive("todoFocus", new Function0<Object>() {
			@Override
			public Object $invoke() {
				return new Callback3<Scope, JQueryCore<JQueryCore<?>>, Attributes>() {
					public void $invoke(final Scope scope, final JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
						scope.$watch(attrs.$get("todoFocus"), new Callback2<Boolean, Boolean>() {
							@Override
							public void $invoke(Boolean newVal, Boolean oldVal) {
								if (newVal) {
									$timeout(new Function0<Boolean>() {
										@Override
										@Template("invoke")
										public Boolean $invoke() {
											elem.focus();
											return true;
										}
									}, 0, false);
								}
							}
						});
					}

				};
			}
		});
	}
}
