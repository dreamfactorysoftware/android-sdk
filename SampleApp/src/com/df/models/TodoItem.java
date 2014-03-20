package com.df.models;

public class TodoItem {

	private String toDo;
	private boolean complete;
	private String id;
	
	public TodoItem (String todo, String id, boolean complete){
		this.toDo = todo;
		this.id = id;
		this.complete = complete;		
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getToDo() {
		return toDo;
	}
	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
