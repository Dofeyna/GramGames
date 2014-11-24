package gameEntities;

public class CommonBuilding extends Building{

	//VARIABLES
	private final static int SIZE_X = 1;
	private final static int SIZE_Y = 1;
	private final static int POINTS = 5;
	
	//CONSTRUCTORS
	public CommonBuilding( String id, String imagePath, int posX, int posY, boolean isPlaced){
		super(id, imagePath, posX, posY, SIZE_X, SIZE_Y, POINTS, isPlaced);
	}

	//Overrides the functionality method and gives it a common functionality
	@Override
	public String fucntionality() {
		return "Common building is placed \n and granted you " + POINTS +" points!";
	}
}
