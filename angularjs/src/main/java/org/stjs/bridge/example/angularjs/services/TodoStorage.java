package org.stjs.bridge.example.angularjs.services;

import org.stjs.bridge.example.angularjs.App;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Function0;

/**
 * Services that persists and retrieves TODOs from localStorage
 */
public class TodoStorage {
	private static final String STORAGE_ID = "todos-angularjs";

	public Object get() {
		return JSON.parse(localStorage.getItem(STORAGE_ID) || "[]");
	}

	public void put(Object todos) {
		localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
	}

	public static void main(String[] args) {
		App.todomvc.factory("todoStorage", new Function0<Object>() {
			@Override
			@Template("invoke")
			public Object $invoke() {
				return new TodoStorage();
			}
		});
	}
}
