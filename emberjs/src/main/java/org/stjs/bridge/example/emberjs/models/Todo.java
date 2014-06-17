package org.stjs.bridge.example.emberjs.models;

import static org.stjs.bridge.emberjs.GlobalEmberJS.DS;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.data.Model;
import org.stjs.bridge.example.emberjs.App;
import org.stjs.javascript.Array;
import org.stjs.javascript.Map;

public class Todo extends Model {
	public String title;
	public boolean completed;
	public Array<Map<String, ? extends Object>> FIXTURES;

	public static void main(String[] args) {
		App.Todos.Todo = DS.Model.extend($map("title", DS.attr("string"), "isCompleted", DS.attr("boolean")));
		App.Todos.Todo.FIXTURES = new Array<Map<String, ? extends Object>>(3);
		App.Todos.Todo.FIXTURES.$set(0, $map("id", 1, "title", "Learn Ember.js", "isCompleted", true));
		App.Todos.Todo.FIXTURES.$set(1, $map("id", 2, "title", "...", "isCompleted", false));
		App.Todos.Todo.FIXTURES.$set(2, $map("id", 3, "title", "Profit!", "isCompleted", false));
	}
}
