package model;

import view.View;

public final class Model {	
	private Screen currentScreen;
	private View view;
	
	public Model() {
		this.view = new View(this);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Model m = new Model();
	}
}