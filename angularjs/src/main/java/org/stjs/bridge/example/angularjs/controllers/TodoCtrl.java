package org.stjs.bridge.example.angularjs.controllers;

import static org.stjs.bridge.angularjs.GlobalAngularJS.angular;

import org.stjs.bridge.angularjs.Location;
import org.stjs.bridge.angularjs.Watcher;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.bridge.example.angularjs.Todo;
import org.stjs.bridge.example.angularjs.extra.ArrayExtra;
import org.stjs.bridge.example.angularjs.services.TodoStorage;
import org.stjs.javascript.Array;
import org.stjs.javascript.functions.Callback0;
import org.stjs.javascript.functions.Callback1;
import org.stjs.javascript.functions.Function2;
import org.stjs.javascript.functions.Function3;

public class TodoCtrl {
	public TodoCtrl(final TodoScope $scope, Location $location, final TodoStorage todoStorage,
			final Function2<Array<Todo>, Todo, Array<Todo>> filterFilter) {
		$scope.todos = todoStorage.get();

		$scope.newTodo = "";
		$scope.editedTodo = null;
		$scope.$watch("todos", new Watcher<String>() {
			@Override
			public void $invoke(String newValue, String oldValue) {
				$scope.remainingCount = filterFilter.$invoke($scope.todos, new Todo() {
					{
						completed = false;
					}
				}).$length();
				$scope.completedCount = $scope.todos.$length() - $scope.remainingCount;
				$scope.allChecked = $scope.remainingCount == 0;
				if (newValue != oldValue) { // This prevents unneeded calls to the local storage
					todoStorage.put($scope.todos);
				}
			}
		}, true);

		if ($location.path() == "") {
			$location.path("/");
		}

		$scope.location = $location;

		$scope.$watch("location.path()", new Watcher<String>() {
			@Override
			public void $invoke(String path, String oldValue) {
				$scope.statusFilter = path == "/active" ? new Todo() {
					{
						completed = false;
					}
				} : path == "/completed" ? new Todo() {
					{
						completed = true;
					}
				} : null;
			}
		});

		$scope.addTodo = new Callback0() {
			@Override
			public void $invoke() {
				final String newTodo = $scope.newTodo.trim();
				if (newTodo.length() == 0) {
					return;
				}

				$scope.todos.push(new Todo() {
					{
						title = newTodo;
						completed = false;
					}
				});

				$scope.newTodo = "";
			}
		};

		$scope.editTodo = new Callback1<Todo>() {
			@Override
			public void $invoke(Todo todo) {
				$scope.editedTodo = todo;
				// Clone the original todo to restore it on demand.
				$scope.originalTodo = angular.extend(new Todo(), todo);
			}
		};

		$scope.doneEditing = new Callback1<Todo>() {
			@Override
			public void $invoke(Todo todo) {
				$scope.editedTodo = null;
				todo.title = todo.title.trim();

				if (todo.title == "") {
					$scope.removeTodo.$invoke(todo);
				}
			}
		};

		$scope.revertEditing = new Callback1<Todo>() {
			public void $invoke(Todo todo) {
				$scope.todos.$set($scope.todos.indexOf(todo), $scope.originalTodo);
				$scope.doneEditing.$invoke($scope.originalTodo);
			}
		};

		$scope.removeTodo = new Callback1<Todo>() {
			public void $invoke(Todo todo) {
				$scope.todos.splice($scope.todos.indexOf(todo), 1);
			}
		};

		$scope.clearCompletedTodos = new Callback0() {
			public void $invoke() {
				$scope.todos = $scope.todos.filter(new Function3<Todo, Integer, ArrayExtra<Todo>, Boolean>() {
					public Boolean $invoke(Todo todo, Integer index, ArrayExtra<Todo> array) {
						return !todo.completed;
					}
				});
			}
		};

		$scope.markAll = new Callback1<Boolean>() {
			public void $invoke(final Boolean completed) {
				$scope.todos.forEach(new Callback1<Todo>() {
					public void $invoke(Todo todo) {
						todo.completed = completed;
					}
				});
			}
		};
	}

	public static void main(String[] args) {

		/**
		 * The main controller for the app. The controller: - retrieves and persists the model via the todoStorage
		 * service - exposes the model to the template and provides event handlers
		 */
		App.todomvc.controller("TodoCtrl", TodoCtrl.class);
	}
}
