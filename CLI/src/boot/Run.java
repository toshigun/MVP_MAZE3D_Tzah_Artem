package boot;


import model.MyModel;
import presenter.Presenter;
import view.MyCLIView;

//
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
		
		//MVP
		MyCLIView v = new MyCLIView();
		MyModel m = new MyModel();
		Presenter p = new Presenter(v,m);
		v.addObserver(p);
		m.addObserver(p);
		p.getView().start();
		
		
	}
	
	
}
