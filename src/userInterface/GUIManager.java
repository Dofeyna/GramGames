package userInterface;

import gameEntities.Building;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIManager extends JFrame{
	//VARIABLES
	private final int NUMBERofPANELS = 2;
	private JPanel panels [] = new JPanel [NUMBERofPANELS];
	private GameCanvas canvas;
	private Menu menu;
	private final int HEIGHT = 800;
	private final int WIDTH = 600;
	//CONSTRUCTORS
	public GUIManager(){
		initFrame();
		panels[0] = menu;
		panels[1] = canvas;
		for ( int i = 0; i < NUMBERofPANELS; i++)
			panels[i].setVisible(false);
		panels[0].setVisible(true);
		setVisible(true);
	}
	
	//METHODS
	public void initFrame(){
	
		//Creating JPanels and adding them to the frame.
		setLayout( new BorderLayout());
		canvas = new GameCanvas();
		menu = new Menu();
		setResizable(false);
		add(menu, BorderLayout.PAGE_START);

		
		//Necessity for closing windows. It also kills the application.
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		//Initializing the JFrame properties.
		setBackground(new java.awt.Color( 0, 0, 0));
		setBounds(new java.awt.Rectangle( 40, 40, 50, 50));
		setSize(new java.awt.Dimension(WIDTH, HEIGHT));
		setMaximumSize(new java.awt.Dimension(WIDTH, HEIGHT));
		
	
	}
	
	public void openGame(){
		add(canvas, BorderLayout.CENTER);
		panels[0].setVisible(false);
		panels[1].setVisible(true);
	}

	public void placeBuilding( String name, int posX, int posY){
		canvas.placeBuilding( name, posX, posY);
	}
	
	public GameCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(GameCanvas canvas) {
		this.canvas = canvas;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public JPanel[] getPanels() {
		return panels;
	}

	public void setPanels(JPanel[] panels) {
		this.panels = panels;
	}

	public int getNUMBERofPANELS() {
		return NUMBERofPANELS;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}
	

}
