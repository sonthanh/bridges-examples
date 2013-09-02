package org.stjs.bridge.example.angularjs;

import static org.stjs.bridge.angularjs.GlobalAngularJS.angular;
import static org.stjs.javascript.JSCollections.$array;

import org.stjs.bridge.angularjs.Module;
import org.stjs.javascript.annotation.GlobalScope;

@GlobalScope
public class App {
	public static Module todomvc = angular.module("todomvc", $array());
}
