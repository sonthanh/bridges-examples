package org.stjs.bridge.example.emberjs;

import static org.stjs.bridge.emberjs.GlobalEmberJS.DS;
import static org.stjs.bridge.emberjs.GlobalEmberJS.Ember;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.emberjs.Application;
import org.stjs.bridge.emberjs.adapter.Adapter;
import org.stjs.bridge.emberjs.control.EmberTextField;
import org.stjs.bridge.emberjs.controller.EmberController;
import org.stjs.bridge.emberjs.system.EmberRoute;
import org.stjs.bridge.emberjs.system.EmberRouter;
import org.stjs.bridge.example.emberjs.models.Todo;

public class App extends Application {
	public static App Todos = Ember.Application.create();
	public Adapter ApplicationAdapter;
	public EmberController TodosController;
	public EmberController TodoController;
	public EmberRouter Router;
	public Todo Todo;
	public EmberRoute TodosRoute;
	public EmberRoute TodosActiveRoute;
	public EmberRoute TodosCompletedRoute;

	public EmberTextField EditTodoView;

	public static void main(String[] args) {
		//		todos.ApplicationAdapter = (Adapter) DS.extend(DS.LSAdapter, $map("namespace", "todos-emberjs"));
		//		$.extend(todos.ApplicationAdapter, DS.FixtureAdapter);
		Todos.ApplicationAdapter = DS.LSAdapter.extend($map("namespace", "todos-emberjs"));
		//		Todos.ApplicationAdapter = DS.FixtureAdapter.extend();

	}
}
