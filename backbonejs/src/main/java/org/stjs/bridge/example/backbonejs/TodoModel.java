package org.stjs.bridge.example.backbonejs;

import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.backbonejs.Backbone.Model;
import org.stjs.bridge.backbonejs.ModelOptions;
import org.stjs.javascript.Map;

public class TodoModel extends Model {
	public boolean done;

	public TodoModel(Map<String, ? extends Object> attributes, ModelOptions options) {
		super(attributes, options);
	}

	// Default attributes for the todo item.
	@Override
	public Map<String, ? extends Object> defaults() {
		return $map("title", "empty todo...", //
				"order", Todo.Todos.nextOrder(), //
				"done", false);
	}

	// Toggle the done state of this todo item.
	public void toggle() {
		this.save($map("done", !(Boolean) this.get("done")));
	}
}
