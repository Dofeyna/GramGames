package gameEntities;

public class Grid {
	
	//VARIABLES
	private final int UNIQUE = 2;
	private final int COMMON = 1;
	private final int EMPTY = 0;
	private String id;
	private int sizeX;
	private int sizeY;
	private int gameBoard[][];
	
	//CONSTRUCTORS
	public Grid( String id,int sizeX, int sizeY){
		this.id = id;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		gameBoard = new int[sizeX][sizeY];
	}
	
	//METHODS
	
	/*
	 * Placing a new building. Checking if the wanted grid place is empty and checking
	 * the type of building that is wanted to be placed. If wanted credentials are satisfied
	 * allowing user to build the wanted construction. If not return false.
	*/
	public boolean placeNewBuilding( Building b, int positionX, int positionY){
		//If the parameter is common building.
		if( b instanceof CommonBuilding){
			//If given location is not empty, warn the user about it.
			if( gameBoard[ positionX][ positionY] != EMPTY){
				System.out.println("There is a building here!");
				return false;
			}
			//If given location is empty, place the building to the given location.
			else{
				System.out.println("Common building placed!");
				gameBoard[ positionX][ positionY] = COMMON;
				return true;
			}
		}
		//If the parameter is common building.
		else{
			//If given location or any of its neighbors is not empty, warn the user about it.
			if( gameBoard[ positionX][ positionY] != EMPTY || 
				gameBoard[ positionX + 1][ positionY] != EMPTY ||
				gameBoard[ positionX][ positionY + 1] != EMPTY ||
				gameBoard[ positionX + 1][ positionY + 1] != EMPTY){
				System.out.println("There is a building here!");
				return false;
			}
			//If given location and its neighbours empty, place the building to the given location and to its neighbours.
			else{
				gameBoard[ positionX][ positionY] = UNIQUE;
				gameBoard[ positionX + 1][ positionY] = UNIQUE;
				gameBoard[ positionX][ positionY + 1] = UNIQUE;
				gameBoard[ positionX + 1][ positionY + 1] = UNIQUE;
				System.out.println("Unique building placed!");
				return true;
			}
		}
	}
	
	/*
	 * Destroying a placed building if there is one. Replacing it with empty
	 * place.
	*/
	public boolean destroyBuilding( int positionX, int positionY){
		if( gameBoard[positionX][positionY] == EMPTY){
			System.out.println("There is nothing to be destroyed!");
			return false;
		}
		else{
			if( gameBoard[positionX][positionY] == COMMON){
				gameBoard[positionX][positionY] = EMPTY;
				System.out.println("Common building destroyed!");
				return true;
			}
			else{
				gameBoard[ positionX][ positionY] = EMPTY;
				if( positionX < 19)
					gameBoard[ positionX + 1][ positionY] = EMPTY;
				if( positionY < 19)
					gameBoard[ positionX][ positionY + 1] = EMPTY;
				if( positionX < 19 && positionY < 19)
					gameBoard[ positionX + 1][ positionY + 1] = EMPTY;
				System.out.println("Unique building destroyed!");
				return true;
			}
		}
	}

	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(int[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

}
