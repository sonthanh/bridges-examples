package org.stjs.bridge.example.angularjs.services;

import static org.stjs.javascript.Global.localStorage;
import static org.stjs.javascript.JSGlobal.$or;
import static org.stjs.javascript.JSGlobal.JSON;

import org.stjs.bridge.example.angularjs.App;

/**
 * Services that persists and retrieves TODOs from localStorage
 */
public class TodoStorage {
	private static final String STORAGE_ID = "todos-angularjs";

	public void put(Object todos) {
		localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
	}

	@SuppressWarnings("unchecked")
	public <T> T get() {
		return (T) JSON.parse($or((String) localStorage.getItem(STORAGE_ID), "[]"));
	}

	public static void main(String[] args) {
		App.todomvc.factory("todoStorage", () -> new TodoStorage());
	}
}
