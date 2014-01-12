package org.stjs.bridge.example.angularjs.controllers;

import static org.stjs.bridge.angularjs.GlobalAngularJS.angular;

import org.stjs.bridge.angularjs.Location;
import org.stjs.bridge.example.angularjs.App;
import org.stjs.bridge.example.angularjs.Todo;
import org.stjs.bridge.example.angularjs.services.TodoStorage;
import org.stjs.javascript.Array;
import org.stjs.javascript.functions.Function2;

public class TodoCtrl {
	public TodoCtrl(final TodoScope $scope, Location $location, final TodoStorage todoStorage,
			final Function2<Array<Todo>, Todo, Array<Todo>> filterFilter) {
		$scope.todos = todoStorage.get();

		$scope.newTodo = "";
		$scope.editedTodo = null;
		$scope.$watch("todos", (newValue, oldValue) -> {
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
			}, true);

		if ($location.path() == "") {
			$location.path("/");
		}

		$scope.location = $location;

		$scope.$watch("location.path()", (path, oldValue) -> {
			$scope.statusFilter = path == "/active" ? new Todo() {
				{
					completed = false;
				}
			} : path == "/completed" ? new Todo() {
				{
					completed = true;
				}
			} : null;
		});

		$scope.addTodo = () -> {
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
		};

		$scope.editTodo = (todo) -> {
			$scope.editedTodo = todo;
			// Clone the original todo to restore it on demand.
			$scope.originalTodo = angular.extend(new Todo(), todo);
		};

		$scope.doneEditing = (todo) -> {
			$scope.editedTodo = null;
			todo.title = todo.title.trim();

			if (todo.title == "") {
				$scope.removeTodo.$invoke(todo);
			}
		};

		$scope.revertEditing = (todo) -> {
			$scope.todos.$set($scope.todos.indexOf(todo), $scope.originalTodo);
			$scope.doneEditing.$invoke($scope.originalTodo);
		};

		$scope.removeTodo = (todo) -> {
			$scope.todos.splice($scope.todos.indexOf(todo), 1);
		};

		$scope.clearCompletedTodos = () -> {
			$scope.todos = $scope.todos.filter((todo, index, array) -> !todo.completed);
		};

		$scope.markAll = (completed) -> {
			$scope.todos.forEach((Todo todo) -> {
				todo.completed = completed;
			});
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
