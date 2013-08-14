package org.stjs.bridge.example.backbonejs;

import static org.stjs.bridge.underscorejs.GlobalUnderscoreJS._;
import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.JSObjectAdapter.$get;

import org.stjs.bridge.backbonejs.Backbone.View;
import org.stjs.bridge.backbonejs.ViewOptions;
import org.stjs.bridge.underscorejs.TemplateOptions;
import org.stjs.javascript.Array;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.annotation.Template;
import org.stjs.javascript.dom.Input;
import org.stjs.javascript.functions.Callback3;
import org.stjs.javascript.functions.Function2;
import org.stjs.javascript.jquery.Event;
import org.stjs.javascript.jquery.GlobalJQuery;
import org.stjs.javascript.jquery.JQueryCore;

public class AppView extends View<TodoModel> {
	private JQueryCore<JQueryCore<?>> input;
	private JQueryCore<JQueryCore<?>> footer;
	private JQueryCore<JQueryCore<?>> main;
	private Input allCheckbox;
	private Function2<Object, TemplateOptions, String> statsTemplate;

	@Override
	protected void _ensureElement() {
		el = GlobalJQuery.$("#todoapp").get(0);
		super._ensureElement();
	}

	@Override
	protected void delegateEvents() {
		events = $map("keypress #new-todo", "createOnEnter",//
				"click #clear-completed", "clearCompleted",//
				"click #toggle-all", "toggleAllComplete"//
		);
		super.delegateEvents();
	}

	@Override
	protected void initialize() {
		this.input = this.$("#new-todo");
		this.allCheckbox = (Input) this.$("#toggle-all").get(0);

		this.listenTo(Todo.Todos, "add", JSObjectAdapter.$get(this, "addOne"));
		this.listenTo(Todo.Todos, "reset", JSObjectAdapter.$get(this, "addAll"));
		this.listenTo(Todo.Todos, "all", JSObjectAdapter.$get(this, "render"));

		this.footer = this.$("footer");
		this.main = GlobalJQuery.$("#main");

		statsTemplate = _.template(GlobalJQuery.$("#stats-template").html());

		Todo.Todos.fetch();

	}

	@Override
	public AppView render() {
		int done = Todo.Todos.done().$length();
		int remaining = Todo.Todos.remaining().$length();

		if (Todo.Todos.length > 0) {
			this.main.show();
			this.footer.show();
			this.footer.html(statsTemplate.$invoke($map("done", done, "remaining", remaining), null));
		} else {
			this.main.hide();
			this.footer.hide();
		}

		this.allCheckbox.checked = remaining == 0;
		return this;
	}

	public void addOne(final TodoModel todo) {
		TodoView view = new TodoView(new ViewOptions<TodoModel>() {
			{
				model = todo;
			}
		});
		this.$("#todo-list").append(view.render().el);
	}

	@SuppressWarnings("unchecked")
	public void addAll() {
		Todo.Todos.each((Callback3<TodoModel, Integer, Array<TodoModel>>) $get(this, "addOne"), this);
	}

	public void createOnEnter(Event e) {
		if (e.keyCode != 13) {
			return;
		}
		if (this.input.val() == null) {
			return;
		}

		Todo.Todos.create($map("title", this.input.val()));
		this.input.val("");
	}

	public boolean clearCompleted() {
		_.invoke(Todo.Todos.done(), "destroy");
		return false;
	}

	public void toggleAllComplete() {
		final boolean done = this.allCheckbox.checked;
		Todo.Todos.each(new Callback3<TodoModel, Integer, Array<TodoModel>>() {
			@Override
			@Template("invoke")
			public void $invoke(TodoModel todo, Integer index, Array<TodoModel> list) {
				todo.save($map("done", done));
			}
		});
	}

}
