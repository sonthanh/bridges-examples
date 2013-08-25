package org.stjs.bridge.example.angularjs.services;

import static org.stjs.javascript.Global.$or;

import org.stjs.bridge.example.angularjs.App;
import org.stjs.bridge.example.angularjs.extra.GlobalExtra;
import org.stjs.bridge.example.angularjs.extra.JSON;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.functions.Function0;

/**
 * Services that persists and retrieves TODOs from localStorage
 */
public class TodoStorage {
	private static final String STORAGE_ID = "todos-angularjs";

	public <T> T get() {
		return JSON.parse((String) $or(GlobalExtra.localStorage.getItem(STORAGE_ID), "[]"));
	}

	public void put(Object todos) {
		GlobalExtra.localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
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
