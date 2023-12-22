package nl.budget.view;

import javafx.scene.layout.Pane;

public abstract class AbstractView {

	protected RootView rootView;

	public void initRootView(RootView rootView) {
		this.rootView = rootView;
	}
	
	public abstract void initComponents();
	
	public abstract Pane getView();
	
}
