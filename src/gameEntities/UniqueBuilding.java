package gameEntities;

public class UniqueBuilding extends Building{

	//VARIABLES
	private final static int SIZE_X = 2;
	private final static int SIZE_Y = 2;
	
	//CONSTRUCTORS
	public UniqueBuilding( String id, String imagePath, int posX, int posY, int points, boolean isPlaced){
		super(id, imagePath, posX, posY, SIZE_X, SIZE_Y, points, isPlaced);
	}
	
	//Overrides the functionality method and gives it an unique functionality
	@Override
	public String fucntionality() {
		return super.getId() + " is placed and granted you \n" + super.getPoints() + " points!";
	}


}
