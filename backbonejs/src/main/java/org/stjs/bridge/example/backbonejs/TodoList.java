package org.stjs.bridge.example.backbonejs;

import org.stjs.bridge.backbonejs.Backbone.Collection;
import org.stjs.bridge.backbonejs.Backbone.LocalStorage;
import org.stjs.javascript.Array;

public class TodoList extends Collection<TodoModel> {
	public LocalStorage localStorage;

	public TodoList() {
		super();
		model = TodoModel.class;
		comparator = "order";
		localStorage = new LocalStorage("todos-backbone");
	}

	public Array<TodoModel> done() {
		return this.where(new TodoModel(null, null) {
			{
				done = true;
			}
		});
	}

	public Array<TodoModel> remaining() {
		return this.without(this.done());
	}

	public int nextOrder() {
		if (this.length == 0) {
			return 1;
		}
		return (Integer) this.last().get("order") + 1;
	}

}
