package org.stjs.bridge.example.emberjs.views;

import static org.stjs.bridge.emberjs.GlobalEmberJS.Ember;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.control.EmberTextField;
import org.stjs.bridge.example.emberjs.App;
import org.stjs.javascript.functions.Callback1;

public class EditTodoView {
	public static void main(String[] args) {
		App.Todos.EditTodoView = Ember.TextField.extend($map("didInsertElement", new Callback1<EmberTextField>() {

			@Override
			public void $invoke(EmberTextField THIS) {
				THIS.$().focus();

			}
		}));

		Ember.Handlebars.helper("edit-todo", App.Todos.EditTodoView);
	}
}
