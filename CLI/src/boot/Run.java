package boot;


import model.MyModel;
import presenter.Presenter;
import userInterface.MenuInterface;
import view.MyGraphicView;


public class Run {

	
	public static void main(String[] args){

		
		/* MVC
		Presenter p = new Presenter();
		Model model = new MyModel(p);
		View view = new MyCLIView(p);
		p.setModel(model);
		p.setUi(view);
		
		p.getUi().start();
		
		*/
		/*
		//MVP - CLI
		MyCLIView v = new MyCLIView();
		MyModel m = new MyModel();
		Presenter p = new Presenter(v,m);
		v.addObserver(p);
		m.addObserver(p);
		p.getView().start();
		
		*/
		MyGraphicView v = new MyGraphicView();
		MyModel m = new MyModel();
		Presenter p = new Presenter(v,m);
		v.addObserver(p);
		m.addObserver(p);
		MenuInterface MI = new MenuInterface(500, 300);
		MI.run();
	}
	
	
}
