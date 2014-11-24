package gameLogic;

import java.awt.event.*;
import java.io.*;

import javax.swing.JTextArea;

import dataManagement.*;
import gameEntities.*;
import userInterface.*;

/*The main class of the project which handles both view classes (userInterface) and model classes (gameEntities, dataManagement)
 * every problem that is sent by any package entity is handeled here therefore it creates a easily environment to work on. This
 * class also handles with the exceptions and such.
 */

public class GameEngine implements ActionListener{

	
	/**
	 * The gameEngine class is the facade class of the program which handles every 
	 * class by creating objects of them. This class uses the facade pattern and 
	 * it is highly object oriented.
	 * @throws FileNotFoundException 
	 *
	 */
	
	//VARIABLES
	private GUIManager gm;
	private Grid g;
	private Building b[];
	private FileManager fm;
	private String config;
	
	/**
	 * @throws FileNotFoundException
	 * constructor of the gameEngine class
	 */
	//CONSTRUCTORS
	public GameEngine() throws FileNotFoundException {
		
		gm = new GUIManager();
		g = new Grid("Grid",20,20);
		b = new Building[20];
		fm = new FileManager();
		
		gm.getMenu().getContinueGame().addActionListener(this);
		gm.getMenu().getNewGame().addActionListener(this);
	}
	//METHODS
	//Main method which is necessary to run the program. It is in GameEngine class which makes iseasiy to follow because
	//GameEngine class is the facade class of the program.
	public static void main(String[] args) throws FileNotFoundException {
		
		GameEngine engine = new GameEngine();
		engine.gameLoop();
	}
	//Initialization method of the GameEngine class which is called at the constructor to initialize the whole game.
	public void init ( boolean newGame){
		//Calls the opening game method which handles with the destroying menu JPanel and calls the game canvas.
		gm.openGame();
		//Create a new board which will initialize the grid object
		int newBoard[][] = new int[20][20];
		
		//Creates every building for the first time
		b[0] = new CommonBuilding("Common1", "deneme", 0, 0, false);
		b[1] = new CommonBuilding("Common2", "deneme", 0, 0, false);
		b[2] = new CommonBuilding("Common3", "deneme", 0, 0, false);
		b[3] = new CommonBuilding("Common4", "deneme", 0, 0, false);
		b[4] = new CommonBuilding("Common5", "deneme", 0, 0, false);
		
		b[5] = new UniqueBuilding("Unique1", "deneme", 0, 0, 6, false);
		b[6] = new UniqueBuilding("Unique2", "deneme", 0, 0, 7, false);
		b[7] = new UniqueBuilding("Unique3", "deneme", 0, 0, 8, false);
		b[8] = new UniqueBuilding("Unique4", "deneme", 0, 0, 9, false);
		b[9] = new UniqueBuilding("Unique5", "deneme", 0, 0, 10, false);
		b[10] = new UniqueBuilding("Unique6", "deneme", 0, 0, 11, false);
		b[11] = new UniqueBuilding("Unique7", "deneme", 0, 0, 12, false);
		b[12] = new UniqueBuilding("Unique8", "deneme", 0, 0, 13, false);
		b[13] = new UniqueBuilding("Unique9", "deneme", 0, 0, 14, false);
		b[14] = new UniqueBuilding("Unique10", "deneme", 0, 0, 15, false);
		b[15] = new UniqueBuilding("Unique11", "deneme", 0, 0, 16, false);
		b[16] = new UniqueBuilding("Unique12", "deneme", 0, 0, 17, false);
		b[17] = new UniqueBuilding("Unique13", "deneme", 0, 0, 18, false);
		b[18] = new UniqueBuilding("Unique14", "deneme", 0, 0, 19, false);
		b[19] = new UniqueBuilding("Unique15", "deneme", 0, 0, 20, false);
		
		//Starts a new game
		if( newGame){
			for (int i = 0; i < 20; i++){
				for( int j = 0; j < 20; j++){
					newBoard[i][j] = 0;
				}
			}
			g.setGameBoard(newBoard);
		}
		//Continues from saved game
		else{
			config = fm.getConfig();
			//Getting the locations and names from file and puts them to the corresponding places 
			for ( int i = 19; i > -1; i--){
				b[i].setPosY( Integer.parseInt(config.substring((config.lastIndexOf(" ") + 1), config.lastIndexOf("/"))));
				config = config.substring(0, config.lastIndexOf(" "));
				b[i].setPosX( Integer.parseInt(config.substring(config.lastIndexOf(" ") + 1)));
				config = config.substring(0, config.lastIndexOf(" "));
				b[i].setId(config.substring(config.lastIndexOf("/") + 1));
				config = config.substring(0, config.lastIndexOf("/") + 1);
				if( b[i].getPosX() != 0){
					b[i].setPlaced(true);
					g.placeNewBuilding(b[i], b[i].getPosX(), b[i].getPosY());
					gm.placeBuilding( b[i].getId(), b[i].getPosX(), b[i].getPosY());
				}
				
			}
		}
	}
	//This function is called every 10ms to give user time to give an input. Every game should have a loop and this methods
	//handles that job. If user wants to place or destroy a building or even wants to save his/her game, all of the given examples are 
	//handled here.
	public void gameLoop() throws FileNotFoundException{	
		String actionStatus = "";
		//Start of the loop
		while( true){
			//To make sure that system has enough time to do the given work.
			try {
			    Thread.sleep(10);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			//Check the status of the game.
			actionStatus = gm.getCanvas().getActionStatus();
			//If save button is pressed, opens a file and saves the data to that file.
			if( actionStatus == "Save"){
				config = "";
				for( int i = 0; i < 20; i++){
					config += b[i].getId() + " " + b[i].getPosX() + " " + b[i].getPosY() + "/";
				}
				fm.setConfig( config);
				//Make sure that the actionStatus set back to default.
				gm.getCanvas().setActionStatus("None");
				gm.getCanvas().getFunctionArea().setText("Game is saved!");
			}
			//If the destroy button clicked and then a building is chosen to be destroyed. 
			else if( actionStatus == "DestroyClicked" ){
				//Delete the building from GUI.
				gm.getCanvas().destroyBuilding(gm.getCanvas().getDestroyX(), gm.getCanvas().getDestroyY());
				//Delete the building from model(Grid.java)
				g.destroyBuilding(gm.getCanvas().getDestroyX(), gm.getCanvas().getDestroyY());
				//Make sure that the building is set back to default which is not placed and do not have any location data.
				b[gm.getCanvas().getDestroyBuildingIndex()].setPlaced(false);
				b[gm.getCanvas().getDestroyBuildingIndex()].setPosX(0);
				b[gm.getCanvas().getDestroyBuildingIndex()].setPosY(0);
				//Make sure that the actionStatus set back to default.
				gm.getCanvas().setActionStatus("None");
				
			}
			//If any building is selected and a place is selected from grid.
			else if(actionStatus == "CommonBuildClicked"){
				//Getting the index of the building from its name.
				int building;
				building = Integer.parseInt( gm.getCanvas().getBuildName().substring(gm.getCanvas().getBuildName().lastIndexOf("n") + 1, gm.getCanvas().getBuildName().length())) - 1;
				//Find the building and find that if the building is placed or there is a building at
				//the wanted location. If there is give prompt if not build the wanted building to the wanted
				//location and give a prompt.
				if(g.placeNewBuilding(b[building], gm.getCanvas().getBuildX(), gm.getCanvas().getBuildY())){
					//Set building as placed and set its location by assigning its x and y values.
					b[building].setPlaced(true);
					b[building].setPosX(gm.getCanvas().getBuildX());
					b[building].setPosY(gm.getCanvas().getBuildY());
					//Place building on the GUI object.
					gm.getCanvas().placeBuilding( gm.getCanvas().getBuildName(), gm.getCanvas().getBuildX(), gm.getCanvas().getBuildY());
					//Prompt the functionality of the building.
					gm.getCanvas().getFunctionArea().setText(b[building].fucntionality());
				}
				//If there is another building prompt the message.
				else
					gm.getCanvas().getFunctionArea().setText("There is another building at that location!");
				//Make sure that the actionStatus set back to default.
				gm.getCanvas().setActionStatus("None");
			}
			//Find the building and find that if the building is placed or there is a building at
			//the wanted location. If there is give prompt if not build the wanted building to the wanted
			//location and give a prompt.
			else if(actionStatus == "UniqueBuildClicked"){
				//Getting the index of the building from its name.
				int building;
				building = Integer.parseInt( gm.getCanvas().getBuildName().substring(gm.getCanvas().getBuildName().lastIndexOf("e") + 1)) + 4;
				//Check if user wants to build the unique building to the location which is not allowed.
				//Because if unique building is tried to be built to the edges, game might crush, therefore
				//this if statement makes sure that there will be no problems on that regard.
				if( gm.getCanvas().getBuildX() < 19 && gm.getCanvas().getBuildY() < 19){
					if(g.placeNewBuilding(b[building], gm.getCanvas().getBuildX(), gm.getCanvas().getBuildY())){
						//Set building as placed and set its location by assigning its x and y values.
						b[building].setPlaced(true);
						b[building].setPosX(gm.getCanvas().getBuildX());
						b[building].setPosY(gm.getCanvas().getBuildY());
						//Place building on the GUI object.
						gm.getCanvas().placeBuilding( gm.getCanvas().getBuildName(), gm.getCanvas().getBuildX(), gm.getCanvas().getBuildY());
						//Prompt the functionality of the building.
						gm.getCanvas().getFunctionArea().setText(b[building].fucntionality());
					}
					else
						gm.getCanvas().getFunctionArea().setText("There is another building at that location!");
				}
				//Give an error message if user wants to place the building to the unwanted place.
				else{
					gm.getCanvas().getFunctionArea().setText("You cannot build to that location!");
				}
				//Make sure that the actionStatus set back to default.
				gm.getCanvas().setActionStatus("None");
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if( "Continue".equals(e.getActionCommand())){
			init(false);
		}
		else if ("New Game".equals(e.getActionCommand())){
			init(true);
		}		
	}
	
}
