package org.stjs.bridge.example.angularjs.controllers;

import org.stjs.bridge.angularjs.Location;
import org.stjs.bridge.angularjs.Scope;
import org.stjs.bridge.example.angularjs.Todo;
import org.stjs.bridge.example.angularjs.extra.ArrayExtra;
import org.stjs.javascript.functions.Callback0;
import org.stjs.javascript.functions.Callback1;

public class TodoScope extends Scope {
	public ArrayExtra<Todo> todos;
	public String newTodo;
	public Todo editedTodo;
	public Todo originalTodo;

	public Todo statusFilter;

	public int remainingCount;
	public int completedCount;
	public boolean allChecked;

	public Location location;

	public Callback0 addTodo;
	public Callback1<Todo> editTodo;

	public Callback1<Todo> doneEditing;

	public Callback1<Todo> revertEditing;

	public Callback1<Todo> removeTodo;

	public Callback0 clearCompletedTodos;

	public Callback1<Boolean> markAll;
}
