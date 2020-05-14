
public class MoveSquare {
	private final int 
			UP_RIGHT = 0, 
			UP_LEFT = 1, 
			DOWN_RIGHT = 2, 
			DOWN_LEFT = 3, 
			RIGHT_UP = 4, 
			RIGHT_DOWN = 5, 
			LEFT_UP = 6, 
			LEFT_DOWN = 7;
	private MoveSquare[] validMove;
	private int x, y;
	private boolean visited;
	
	public MoveSquare(int x, int y) {
		visited = false;
		this.x = x;
		this.y = y;
		
		validMove = new MoveSquare[8];
		for(int i = 0; i < validMove.length; i++) {
			validMove[i] = null;
		}
		
	}
	
	public void setMoveSquare(MoveSquare ms, int direction) {
		validMove[direction] = ms;
	}
	
	public int validMoves() {
		int validMovesCounter = 0;
		for(int i = 0; i < validMove.length; i++)
			if(validMove[i] != null)
				validMovesCounter++;
		
		return validMovesCounter;
	}
	
	public void moveHere() {
		visited = true;
	}
}
