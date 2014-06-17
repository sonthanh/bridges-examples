package org.stjs.bridge.example.emberjs;

import static org.stjs.bridge.emberjs.GlobalEmberJS.Ember;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.controller.EmberController;
import org.stjs.bridge.emberjs.data.Model;
import org.stjs.bridge.emberjs.system.EmberRoute;
import org.stjs.bridge.emberjs.system.EmberRouter;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Callback2;
import org.stjs.javascript.functions.Function1;

public class Router {

	public static void main(String[] args) {
		App.Todos.Router.map(new Callback1<EmberRouter>() {

			@Override
			public void $invoke(EmberRouter THIS) {
				THIS.resource("todos", $map("path", "/"), new Callback1<EmberRouter>() {

					@Override
					public void $invoke(EmberRouter THIS) {
						THIS.route("active");
						THIS.route("completed");
					}
				});

			}
		});
		App.Todos.TodosRoute = Ember.Route.extend($map("model", new Function1<EmberRoute, Object>() {

			@Override
			public Object $invoke(EmberRoute THIS) {
				return THIS.store.find("todo");
			}
		}));

		App.Todos.TodosActiveRoute = Ember.Route.extend($map( //
				"model", new Function1<EmberRoute, Object>() {
					@Override
					public Object $invoke(EmberRoute THIS) {
						return THIS.store.filter("todo", new Function1<Model, Boolean>() {

							@Override
							public Boolean $invoke(Model todo) {
								return !(Boolean) todo.get("isCompleted");
							}
						});
					}
				}, //
				"renderTemplate", new Callback2<EmberRoute, EmberController>() {
					@Override
					public void $invoke(EmberRoute THIS, EmberController controller) {
						THIS.render("todos/index", $map("controller", controller));
					}
				} //

				));

		App.Todos.TodosCompletedRoute = Ember.Route.extend($map( //
				"model", new Function1<EmberRoute, Object>() {
					@Override
					public Object $invoke(EmberRoute THIS) {
						return THIS.store.filter("todo", new Function1<Model, Boolean>() {

							@Override
							public Boolean $invoke(Model todo) {
								return (Boolean) todo.get("isCompleted");
							}
						});
					}
				}, //
				"renderTemplate", new Callback2<EmberRoute, EmberController>() {
					@Override
					public void $invoke(EmberRoute THIS, EmberController controller) {
						THIS.render("todos/index", $map("controller", controller));
					}
				} //

				));
	}
}
