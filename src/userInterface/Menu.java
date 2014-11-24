package userInterface;

import java.awt.BorderLayout;

import javax.swing.*;

public class Menu extends JPanel{

	//VARIABLES
	private JButton continueGame = new JButton("Continue");
	private JButton newGame = new JButton("New Game");
	//CONSTRUCTORS
	public Menu(){
		initMenu();
	}
	//METHODS
	private void initMenu(){
		
		continueGame.setActionCommand("Continue");
		newGame.setActionCommand("New Game");
		add(continueGame, BorderLayout.CENTER);
		add(newGame, BorderLayout.CENTER);
		
		
	}
	//Getters and Setters
	public JButton getContinueGame() {
		return continueGame;
	}
	public void setContinueGame(JButton continueGame) {
		this.continueGame = continueGame;
	}
	public JButton getNewGame() {
		return newGame;
	}
	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}
	
}
