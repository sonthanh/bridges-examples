package org.stjs.bridge.example.angularjs;

import static org.stjs.bridge.angularjs.GlobalAngularJS.angular;

import org.stjs.bridge.angularjs.Module;
import org.stjs.javascript.annotation.GlobalScope;

@GlobalScope
public class App {
	public static Module todomvc;

	public static void main(String[] args) {
		todomvc = angular.module("todomvc");
	}
}
