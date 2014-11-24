package userInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GameCanvas extends JPanel implements ActionListener{
	//VARIABLES
	final private Color EMPTY = Color.BLACK;
	final private Color UNIQUEBULDING = Color.GREEN;
	final private Color COMMONBUILDING = Color.YELLOW;
	
	private JPanel gridPanel = new JPanel();
	private JPanel buildingPanel = new JPanel();
	private JPanel logPanel = new JPanel();
	private JTextArea functionArea = new JTextArea();
	private JButton destroyButton = new JButton ("Destroy");
	private JButton saveButton = new JButton ("Save");
	
	private int destroyX;
	private int destroyY;
	private int buildX;
	private int buildY;
	private String buildName;
	private int destroyBuildingIndex;
	
	private JButton [][]grid;
	private JButton []buildings;
	
	private String ActionStatus = "None";
	
	//CONSTRUCTORS
	public GameCanvas(){
		
		setLayout(new FlowLayout());
		
		//Sets layouts that will be used through out the game.
		FlowLayout insidePanelLayouts = new FlowLayout();
		insidePanelLayouts.setVgap(0);
		insidePanelLayouts.setHgap(0);
		
		//Initializes the panels and adds them to the gameCanvas
		gridPanel.setPreferredSize(new Dimension(600,600));
		gridPanel.setMaximumSize(new Dimension(600,600));
		gridPanel.setMinimumSize(new Dimension(600,600));
		gridPanel.setLayout(insidePanelLayouts);
		buildingPanel.setPreferredSize(new Dimension(200,200));
		buildingPanel.setMaximumSize(new Dimension(200,200));
		buildingPanel.setMinimumSize(new Dimension(200,200));
		buildingPanel.setLayout(insidePanelLayouts);
		logPanel.setLayout(insidePanelLayouts);
		
		destroyButton.setBackground(Color.RED);
		destroyButton.setActionCommand("Destroy");
		
		saveButton.setBackground(Color.CYAN);
		saveButton.setActionCommand("Save");
		
		destroyButton.addActionListener(this);
		saveButton.addActionListener(this);
		
		//Set the first promt of the text area.
		functionArea.setText("Welcome to the building game!");
		
		logPanel.add( destroyButton);
		logPanel.add( saveButton);
		logPanel.add( functionArea);
		
		add(gridPanel);
		add(buildingPanel);
		add(logPanel);
		
		//Set panels visible
		gridPanel.setVisible(true);
		buildingPanel.setVisible(true);
		logPanel.setVisible(true);
		//Creates the grid and initializes them.
		grid = new JButton[20][20];
		buildings = new JButton[20];
		for ( int i = 0; i < 20; i++){
			for( int j = 0; j < 20; j++){
				grid[i][j] = new JButton();
				grid[i][j].setActionCommand("Grid " + i + " " + j);
				grid[i][j].setBackground(EMPTY);
				grid[i][j].setOpaque(true);
				grid[i][j].setPreferredSize(new Dimension(30,30));
				grid[i][j].setMaximumSize(new Dimension(30,30));
				grid[i][j].setMinimumSize(new Dimension(30,30));
				grid[i][j].setMargin(new Insets(0, 0, 0, 0));
				grid[i][j].addActionListener(this);				
				gridPanel.add(grid[i][j]);
			}
		}
		//Creates the common building buttons and initializes them.
		for ( int i = 0; i < 5; i++){
			buildings[i] = new JButton("C" + (i + 1));
			buildings[i].setActionCommand("Common" + (i + 1));
			buildings[i].setBackground(COMMONBUILDING);
			buildings[i].setOpaque(true);
			buildings[i].setPreferredSize(new Dimension(40,40));
			buildings[i].setMaximumSize(new Dimension(40,40));
			buildings[i].setMinimumSize(new Dimension(40,40));
			buildings[i].setMargin(new Insets(0, 0, 0, 0));
			buildings[i].addActionListener(this);
			buildingPanel.add(buildings[i]);
		}
		//Creates the unique building buttons and initializes them.
		for ( int i = 5; i < 20; i++){
			buildings[i] = new JButton("U" + (i - 4));
			buildings[i].setActionCommand("Unique" + (i - 4));
			buildings[i].setBackground(UNIQUEBULDING);
			buildings[i].setOpaque(true);
			buildings[i].setPreferredSize(new Dimension(40,40));
			buildings[i].setMaximumSize(new Dimension(40,40));
			buildings[i].setMinimumSize(new Dimension(40,40));
			buildings[i].setMargin(new Insets(0, 0, 0, 0));
			buildings[i].addActionListener(this);
			buildingPanel.add(buildings[i]);
		}
		updateUI();
	}
	
	//Method of placing buildings. It takes the name and the wanted position of the building to be placed
	//and sets the wanted grid as building. After that it also sets the button to not clickable.
	public void placeBuilding( String name, int posX, int posY){
		
		if ( name.contains( "Common")){
			grid[posX][posY].setText("C" + name.substring(name.length()-1));
			grid[posX][posY].setBackground(COMMONBUILDING);
			buildings[Integer.parseInt(name.substring(name.lastIndexOf("n") + 1)) - 1].setEnabled(false);
		}
		else if( name.contains( "Unique")){
				grid[posX][posY].setText("U" + name.substring(name.lastIndexOf("e")+1));
				grid[posX + 1][posY].setText("U" + name.substring(name.lastIndexOf("e")+1));
				grid[posX][posY + 1].setText("U" + name.substring(name.lastIndexOf("e")+1));
				grid[posX + 1][posY + 1].setText("U" + name.substring(name.lastIndexOf("e")+1));
				grid[posX][posY].setBackground(UNIQUEBULDING);
				grid[posX + 1][posY].setBackground(UNIQUEBULDING);
				grid[posX][posY + 1].setBackground(UNIQUEBULDING);
				grid[posX + 1][posY + 1].setBackground(UNIQUEBULDING);
				buildings[Integer.parseInt(name.substring(name.lastIndexOf("e") + 1)) + 4].setEnabled(false);				
		}
		updateUI();
	}

	//Takes the wanted location and cleans that location if there is any building.
	public void destroyBuilding( int x, int y) {
		//Finds if the wanted to be destroy building is common.
		if(grid[x][y].getText().contains("C")){
			//Gives a prompt to the user about what happened.
			functionArea.setText("Common building destroyed!");
			//Sets the button clickable again.
			buildings[Integer.parseInt(grid[x][y].getText().substring(1, grid[x][y].getText().length())) - 1].setEnabled(true);
			//Sets the index for gameEngine to retrieve.
			destroyBuildingIndex = Integer.parseInt(grid[x][y].getText().substring(1, grid[x][y].getText().length())) - 1;
			//Sets button at grid to default.
			grid[x][y].setText("");
			grid[x][y].setBackground(EMPTY);
		}
		//Finds if the wanted to be destroy building is unique.
		else if( grid[x][y].getText().contains("U")){
			//Gives a prompt to the user about what happened.
			functionArea.setText("Unique building destroyed!");
			//Sets the button clickable again.
			buildings[ Integer.parseInt(grid[x][y].getText().substring(1, grid[x][y].getText().length())) + 4].setEnabled(true);
			//Sets the index for gameEngine to retrieve.
			destroyBuildingIndex = Integer.parseInt(grid[x][y].getText().substring(1, grid[x][y].getText().length())) + 4;
			//Checks the every neighbor of clicked button if there is any other building which is the part
			//of that building destroys them also.
			if( grid[x][y + 1].getText().equals(grid[x][y].getText())){
				grid[x][y + 1].setText("");
				grid[x][y + 1].setBackground(EMPTY);
			}
			if( grid[x][y - 1].getText().equals(grid[x][y].getText())){
				grid[x][y - 1].setText("");
				grid[x][y - 1].setBackground(EMPTY);
			}
			if( grid[x + 1][y].getText().equals(grid[x][y].getText())){
				grid[x + 1][y].setText("");
				grid[x + 1][y].setBackground(EMPTY);
			}
			if( grid[x - 1][y].getText().equals(grid[x][y].getText())){
				grid[x - 1][y].setText("");
				grid[x - 1][y].setBackground(EMPTY);
			}
			if( grid[x - 1][y + 1].getText().equals(grid[x][y].getText())){
				grid[x - 1][y + 1].setText("");
				grid[x - 1][y + 1].setBackground(EMPTY);
			}
			if( grid[x + 1][y + 1].getText().equals(grid[x][y].getText())){
				grid[x + 1][y + 1].setText("");
				grid[x + 1][y + 1].setBackground(EMPTY);
			}
			if( grid[x - 1][y - 1].getText().equals(grid[x][y].getText())){
				grid[x - 1][y - 1].setText("");
				grid[x - 1][y - 1].setBackground(EMPTY);
			}
			if( grid[x + 1][y - 1].getText().equals(grid[x][y].getText())){
				grid[x + 1][y - 1].setText("");
				grid[x + 1][y - 1].setBackground(EMPTY);
			}
			//Sets button at grid to default.
			grid[x][y].setText("");
			grid[x][y].setBackground(EMPTY);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//If user clicks to a button which is an instance of common buildings.
		if( e.getActionCommand().contains("Common")){
			ActionStatus = "CommonBuild";
			buildName = e.getActionCommand();
		}
		//If user clicks to a button which is an instance of unique buildings.
		else if( e.getActionCommand().contains("Unique")){
			ActionStatus = "UniqueBuild";
			buildName = e.getActionCommand();
		}
		//If user clicks to a grid after selecting the common building type.
		else if( ActionStatus == "CommonBuild" && e.getActionCommand().contains("Grid")){
			String s = e.getActionCommand();
			ActionStatus = "CommonBuildClicked";			
			buildY = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
			s = s.substring(0, s.lastIndexOf(" "));
			buildX = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
		}
		//If user clicks to a grid after selecting the unique building type.
		else if(ActionStatus == "UniqueBuild" && e.getActionCommand().contains("Grid")){
			String s = e.getActionCommand();
			ActionStatus = "UniqueBuildClicked";			
			buildY = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
			s = s.substring(0, s.lastIndexOf(" "));
			buildX = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
		}
		//If user clicks to the destroy button.
		else if( e.getActionCommand().contains("Destroy")){
			ActionStatus = "Destruction";
			functionArea.setText("Select a building to destroy");
		}
		//If user selects a building from the grid after clicking destroy button
		else if( ActionStatus == "Destruction" && e.getActionCommand().contains("Grid")){
			String s = e.getActionCommand();
			ActionStatus = "DestroyClicked";
			destroyY = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
			s = s.substring(0, s.lastIndexOf(" "));
			destroyX = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1, s.length()));
		}
		//If user clicks to the save button.
		else if( e.getActionCommand().contains("Save")){
			ActionStatus = "Save";
		}
	}

	//Getters and Setters

	
	
	public String getActionStatus() {
		return ActionStatus;
	}

	public int getDestroyBuildingIndex() {
		return destroyBuildingIndex;
	}

	public void setDestroyBuildingIndex(int destroyBuildingIndex) {
		this.destroyBuildingIndex = destroyBuildingIndex;
	}

	public int getBuildX() {
		return buildX;
	}

	public void setBuildX(int buildX) {
		this.buildX = buildX;
	}

	public int getBuildY() {
		return buildY;
	}

	public void setBuildY(int buildY) {
		this.buildY = buildY;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public JButton getDestroyButton() {
		return destroyButton;
	}

	public void setDestroyButton(JButton destroyButton) {
		this.destroyButton = destroyButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public int getDestroyX() {
		return destroyX;
	}

	public void setDestroyX(int destroyX) {
		this.destroyX = destroyX;
	}

	public int getDestroyY() {
		return destroyY;
	}

	public void setDestroyY(int destroyY) {
		this.destroyY = destroyY;
	}

	public void setActionStatus(String actionStatus) {
		ActionStatus = actionStatus;
	}
	
	public JPanel getGridPanel() {
		return gridPanel;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public JPanel getBuildingPanel() {
		return buildingPanel;
	}

	public void setBuildingPanel(JPanel buildingPanel) {
		this.buildingPanel = buildingPanel;
	}

	public JPanel getLogPanel() {
		return logPanel;
	}

	public void setLogPanel(JPanel logPanel) {
		this.logPanel = logPanel;
	}

	public JTextArea getFunctionArea() {
		return functionArea;
	}

	public void setFunctionArea(JTextArea functionArea) {
		this.functionArea = functionArea;
	}

	public JButton[][] getGrid() {
		return grid;
	}

	public void setGrid(JButton[][] grid) {
		this.grid = grid;
	}

	public JButton[] getBuildings() {
		return buildings;
	}

	public void setBuildings(JButton[] buildings) {
		this.buildings = buildings;
	}

	public Color getEMPTY() {
		return EMPTY;
	}

	public Color getUNIQUEBULDING() {
		return UNIQUEBULDING;
	}

	public Color getCOMMONBUILDING() {
		return COMMONBUILDING;
	}
}
