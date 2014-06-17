package org.stjs.bridge.example.emberjs.controllers;

import static org.stjs.bridge.emberjs.GlobalEmberJS.Ember;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.ComputedProperty;
import org.stjs.bridge.emberjs.controller.EmberController;
import org.stjs.bridge.emberjs.controller.EmberObjectController;
import org.stjs.bridge.emberjs.data.Model;
import org.stjs.bridge.example.emberjs.App;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Function3;

public class TodoController {
	public static void main(String[] args) {
		App.Todos.TodoController =
				Ember.ObjectController.extend(
						$map("isCompleted", ((ComputedProperty) (Object) new Function3<EmberController, String, Object, Object>() {
							@Override
							public Object $invoke(EmberController THIS, String key, Object value) {
								Model model = (Model) THIS.get("model");

								if (value == null) {
									// property being used as a getter
									return model.get("isCompleted");
								} else {
									// property being used as a setter
									model.set("isCompleted", value);
									model.save();
									return value;
								}
							}
						}).property("model.isCompleted")), // 
						$map("actions", // 
								$map("editTodo", new Callback1<EmberObjectController>() {
									@Override
									public void $invoke(EmberObjectController THIS) {
										THIS.set("isEditing", true);

									}
								}, "acceptChanges", new Callback1<EmberObjectController>() {
									@Override
									public void $invoke(EmberObjectController THIS) {
										THIS.set("isEditing", false);

										if (Ember.isEmpty(THIS.get("model.title"))) {
											THIS.send("removeTodo");
										} else {
											((Model) THIS.get("model")).save();
										}
									}
								}, "removeTodo", new Callback1<EmberObjectController>() {
									@Override
									public void $invoke(EmberObjectController THIS) {
										Model todo = (Model) THIS.get("model");
										todo.deleteRecord();
										todo.save();
									}
								}

								)), //
						$map("isEditing", false)

				);
	}
}
