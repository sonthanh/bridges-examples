package org.stjs.bridge.example.backbonejs;

import org.stjs.javascript.annotation.GlobalScope;

@GlobalScope
public class Todo {
	public static TodoList Todos = new TodoList();

	public static void main(String[] args) {

		// The collection of todos is backed by localStorage instead of a remote server.

		// Finally, we kick things off by creating the App.
		AppView App = new AppView();
	}
}
