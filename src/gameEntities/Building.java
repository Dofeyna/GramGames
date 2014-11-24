package gameEntities;

import javax.swing.ImageIcon;

//Parent class for buildings that can be override by other classes which extends to it
abstract public class Building {
	
	//VARIABLES
	private String id;
	protected int sizeX;
	protected int sizeY;
	protected int posX;
	protected int posY;
	private ImageIcon image;
	protected int points;
	private boolean isPlaced;
		
	//CONSTRUCTORS

	//Constructor with initializing
	public Building( String id, String imagePath, int posX, int posY, int sizeX, int sizeY, int points, boolean isPlaced){
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.image = new ImageIcon( imagePath);
		this.points = points;
		this.isPlaced = isPlaced;
	}
	
	//METHODS
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
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setImage( ImageIcon image)
	{
		this.image = image;
	}
	
	public ImageIcon getImageIcon() {
		return image;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}

	//Abstract method that will be initialized inside children classes
	public abstract String fucntionality();
}
