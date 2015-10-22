package userInterface;





import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;


public class MenuInterface extends BasicWindow{

	
	
	Button exitButton;
	Button cliButton;
	Group group;
	Button x10x10x10;
	Button x15x15x15;
	Button x20x20x20;
	
	
	public MenuInterface(int width, int height) {
		super(width, height);
	}

	@Override
	void initWigets() {
		
		shell.setLayout(new GridLayout(2,false));
		
		//exit button
		exitButton = new Button(shell, SWT.PUSH);
		exitButton.setText("Exit");
		exitButton.setLayoutData(new GridData(SWT.FILL,SWT.None,false,false,1,1));
		//listener - exit - add in external class
		
		//switch to CLI
		cliButton = new Button(shell, SWT.PUSH);
		cliButton.setText("change to console view");
		cliButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		//listener - CLI - add in external class - change properties?
		
		//choose maze size Radio box
		group = new Group(shell, SWT.RADIO);
		group.setText("Choose maze size ");
		group.setLayout(new RowLayout(SWT.VERTICAL));
		x10x10x10 = new Button(group, SWT.RADIO);
		x10x10x10.setText("10 x 10 x 10");
		x15x15x15 = new Button(group, SWT.RADIO);
		x15x15x15.setText("15 x 15 x 15");
		x20x20x20 = new Button(group, SWT.RADIO);
		x20x20x20.setText("20 x 20 x 20");
		
		
		//Generate maze button - taking parameters from the drop down menu
		Button generateMaze = new Button(shell, SWT.PUSH);
		generateMaze.setText("Start \n Playing");
		generateMaze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		//listener - use drop down selection to add parameters
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
