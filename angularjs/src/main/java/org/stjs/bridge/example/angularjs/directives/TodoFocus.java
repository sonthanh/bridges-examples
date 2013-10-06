package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.angularjs.Watcher;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.functions.Function1;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoFocus {

	public static void main(String[] args) {
		App.todomvc.directive("todoFocus", new Function1<Callback3<Object, Object, Object>, Object>() {
			@Override
			public Object $invoke(final Callback3<Object, Object, Object> $timeout) {
				return new Callback3<Scope, JQueryCore<JQueryCore<?>>, Attributes>() {
					public void $invoke(final Scope scope, final JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
						scope.$watch(attrs.$get("todoFocus"), new Watcher<Boolean>() {
							@Override
							public void $invoke(Boolean newVal, Boolean oldVal) {
								if (newVal) {
									$timeout.$invoke(new Function0<Boolean>() {
										@Override
										@Template("invoke")
										public Boolean $invoke() {
											elem.$get(0).focus();
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
package org.stjs.bridge.example.angularjs.directives;

import org.stjs.bridge.angularjs.Attributes;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.angularjs.Watcher;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.functions.Function1;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoFocus {
	private static Callback3<Object, Object, Object> $timeout;

	public TodoFocus(final Scope scope, final JQueryCore<JQueryCore<?>> elem, final Attributes attrs) {
		scope.$watch(attrs.$get("todoFocus"), new Watcher<Boolean>() {
			@Override
			public void $invoke(Boolean newVal, Boolean oldVal) {
				if (newVal) {
					$timeout.$invoke(new Function0<Boolean>() {
						@Override
						public Boolean $invoke() {
							elem.$get(0).focus();
							return true;
						}
					}, 0, false);
				}
			}
		});
	}

	public static void main(String[] args) {
		App.todomvc.directive("todoFocus", new Function1<Callback3<Object, Object, Object>, Object>() {
			@Override
			public Object $invoke(Callback3<Object, Object, Object> $timeout) {
				TodoFocus.$timeout = $timeout;
				return TodoFocus.class;
			}
		});
	}
}
