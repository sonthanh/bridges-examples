package org.stjs.bridge.example.backbonejs;

import static org.stjs.bridge.underscorejs.GlobalUnderscoreJS._;
import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.jquery.GlobalJQuery.$;

import org.stjs.bridge.backbonejs.Backbone.View;
import org.stjs.bridge.backbonejs.ViewOptions;
import org.stjs.bridge.underscorejs.TemplateOptions;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.functions.Function2;
import org.stjs.javascript.jquery.Event;
import org.stjs.javascript.jquery.JQueryCore;

public class TodoView extends View<TodoModel> {

	private JQueryCore<JQueryCore<?>> input;
	private Function2<Object, TemplateOptions, String> template;

	public TodoView(ViewOptions<TodoModel> options) {
		super(options);
		tagName = "li";
		template = _.template($("#item-template").html());
		// The DOM events specific to an item.
		on($map("click .toggle", "toggleDone", //
				"dblclick .view", "edit",//
				"click a.destroy", "clear",//
				"keypress .edit", "updateOnEnter",//
				"blur .edit", "close"));
	}

	public void initialize() {
		this.listenTo(this.model, "change", JSObjectAdapter.$get(this, "render"));
		this.listenTo(this.model, "destroy", JSObjectAdapter.$get(this, "remove"));
	}

	@Override
	public TodoView render() {
		this.$el.html(this.template.$invoke(this.model.toJSON(), null));
		this.$el.toggleClass("done", (Boolean) this.model.get("done"));
		this.input = $(".edit");
		return this;
	}

	public void toggleDone() {
		this.model.toggle();
	}

	public void edit() {
		this.$el.addClass("editing");
		this.input.focus();
	}

	public void close() {
		boolean value = (Boolean) this.input.val();
		if (!value) {
			this.clear();
		} else {
			this.model.save($map("title", value));
			this.$el.removeClass("editing");
		}
	}

	public void updateOnEnter(Event e) {
		if (e.keyCode == 13) {
			this.close();
		}
	}

	public void clear() {
		this.model.destroy();
	}

}
