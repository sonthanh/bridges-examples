package org.stjs.bridge.example.emberjs.controllers;

import static org.stjs.bridge.emberjs.GlobalEmberJS.Ember;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.ComputedProperty;
import org.stjs.bridge.emberjs.controller.EmberArrayController;
import org.stjs.bridge.emberjs.data.Model;
import org.stjs.bridge.emberjs.mixin.ArrayExtend;
import org.stjs.bridge.example.emberjs.App;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Function1;
import org.stjs.javascript.functions.Function3;

public class TodosController {

	public static void main(String[] args) {
		App.Todos.TodosController = Ember.ArrayController.extend( //
				$map("actions", //
						$map("createTodo", new Callback1<EmberArrayController>() {
							@Override
							public void $invoke(EmberArrayController THIS) {
								String title = (String) THIS.get("newTitle");
								if (title.trim().length() == 0) {
									return;
								}

								// Create the new Todo model
								Model todo = THIS.store.createRecord("todo", $map("title", title, "isCompleted", false));

								// Clear the "New Todo" text field
								THIS.set("newTitle", "");

								todo.save();
							}
						}, "clearCompleted", new Callback1<EmberArrayController>() {
							@Override
							public void $invoke(EmberArrayController THIS) {
								ArrayExtend completed = THIS.filterBy("isCompleted", true);
								completed.invoke("deleteRecord");
								completed.invoke("save");
							}
						})),

				$map("hasCompleted", ((ComputedProperty) (Object) new Function1<EmberArrayController, Boolean>() {

					@Override
					public Boolean $invoke(EmberArrayController THIS) {
						return ((Integer) THIS.get("completed")) > 0;
					}
				}).property("completed")),

				$map("completed", ((ComputedProperty) (Object) new Function1<EmberArrayController, Object>() {

					@Override
					public Object $invoke(EmberArrayController THIS) {
						return THIS.filterBy("isCompleted", true).$get("length");
					}
				}).property("@each.isCompleted")),

				$map("remaining", ((ComputedProperty) (Object) new Function1<EmberArrayController, Object>() {

					@Override
					public Object $invoke(EmberArrayController THIS) {
						return THIS.filterBy("isCompleted", false).$get("length");
					}
				}).property("@each.isCompleted")),

				$map("allAreDone", ((ComputedProperty) (Object) new Function3<EmberArrayController, String, Boolean, Boolean>() {

					@Override
					public Boolean $invoke(EmberArrayController THIS, String key, Boolean value) {
						if (value == null) {
							Object length = THIS.get("length");
							return length != null && (Integer) length > 0 && THIS.isEvery("isCompleted");
						} else {
							THIS.setEach("isCompleted", value);
							THIS.invoke("save");
							return value;
						}
					}
				}).property("@each.isCompleted")),

				$map("inflection", ((ComputedProperty) (Object) new Function1<EmberArrayController, Object>() {

					@Override
					public Object $invoke(EmberArrayController THIS) {
						Integer remaining = (Integer) THIS.get("remaining");
						return remaining == 1 ? "todo" : "todos";
					}
				}).property("remaining")));
	}
}
