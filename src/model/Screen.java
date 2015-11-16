package model;

public abstract class Screen extends EventReciever implements Displayable {
	private Model model;
	
	public Screen(Model model) {
		this.model = model;
	}
	
	protected Model getModel() {
		return model;
	}
	
	protected void exit() {}
}