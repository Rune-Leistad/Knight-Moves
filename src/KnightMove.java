import java.awt.EventQueue;
import java.util.Arrays;


public class KnightMove {

	public static final int SIZE = 6;
	
	public final int[][] MOVES = {{-1, -2},{1, -2},{-1, 2},{1, 2},{2, 1},{2, -1},{-2, 1},{-2, -1}};
	
	private int iterations, confirmedMoves;
	private boolean[][] visited; 
	private int[][] timeOfVisit, priorityList;
	
	// private GUI gui;
	
	/*
	 * 
	 * Constructor KnightMove
	 * 
	 */
 	private KnightMove() {
		iterations = 0;
		confirmedMoves = 0;

		// sørger for at alle verdier er satt til false;
		visited = new boolean[SIZE][SIZE];
		timeOfVisit = new int[SIZE][SIZE];
		priorityList = new int[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			for(int y = 0; y < SIZE; y++) {
				visited[i][y] = false;
				timeOfVisit[i][y] = 0;
				priorityList[i][y] = 0;
			}
		}
		
		initPriority();
	}
 	
 	private void initPriority() {
 		
		for(int i = 0; i < SIZE; i++) {
			for(int y = 0; y < SIZE; y++) {
				priorityList[i][y] = validMoves(i, y);
			}
		}
 	}
 	
	private int validMoves(int startx, int starty) {
		int valids = 0;
		for(int i = 0; i < MOVES.length; i++) {
			int x = startx + MOVES[i][0];
			int y = starty + MOVES[i][1];
			
			if(y < SIZE && y >= 0) {
				if(x < SIZE && x>= 0)
					valids++;
			}
		}
		
		return valids;
	}
 	
	public static void main(String[] args) {
		
		KnightMove kn = new KnightMove();
		kn.calculate();
	}

	public void calculate() {
		
		moveKnight(0, 0);
		for(int i = 0; i < SIZE; i++) {
			for(int y = 0; y < SIZE; y++) {
				System.out.format("%5d",(timeOfVisit[i][y]));
			}
			System.out.println();
		}
		//gui.setNumbers(timeOfVisit);
		for(int i = 0; i < SIZE; i++)
			System.out.println(Arrays.toString(priorityList[i]));
		System.out.println("Iterations: " + iterations);

	}
	
	public void moveKnight(int xpos, int ypos) {
		iterations++; //counts number of runs through this code
		confirmedMoves++;
		visited[xpos][ypos] = true;
		timeOfVisit[xpos][ypos] = confirmedMoves;
		
		for(int i = 0; i < MOVES.length; i++) {
			int tempx = xpos + MOVES[i][0];
			int tempy = ypos + MOVES[i][1];
			if(moveInBounds(tempx, tempy))
				priorityList[xpos + MOVES[i][0]][ypos + MOVES[i][1]]--;
		}
		
		
		int[][] priorityQueue = highestPrio(xpos, ypos);
		
		for(int i = 0; i < priorityQueue.length; i++) {
			int checkX = xpos + priorityQueue[i][0];
			int checkY = ypos + priorityQueue[i][1];
			
			if(moveInBounds(checkX, checkY) && !(visited[checkX][checkY]))
				moveKnight(xpos+priorityQueue[i][0], ypos + priorityQueue[i][1]);
		}
		
		
			
		if(confirmedMoves < SIZE*SIZE) {
			visited[xpos][ypos] = false;
			timeOfVisit[xpos][ypos] = 0;
			confirmedMoves--;
			
			for(int i = 0; i < MOVES.length; i++) {
				int tempx = xpos + MOVES[i][0];
				int tempy = ypos + MOVES[i][1];
				if((tempx >= 0 && tempx < SIZE) && (tempy >= 0 && tempy < SIZE))
					priorityList[xpos + MOVES[i][0]][ypos + MOVES[i][1]]++;
			}
		}
	}
	
	private boolean moveInBounds(int xpos, int ypos) {
		if((xpos < SIZE && xpos >= 0) && (ypos < SIZE && ypos>= 0)) {
			if(visited[xpos][ypos])
				return false;
			return true;
		}
		return false;
	}
	
	private int[][] highestPrio(int x, int y) {
		int[][] tempPrio = new int[priorityList[x][y]][];
		int nextInPrio = 0;
		
		// Finds the valid moves and puts them in tempPrio
		for(int i = 0; i < MOVES.length; i++) {
			int checkX = x+MOVES[i][0];
			int checkY = y+MOVES[i][1];
			if(moveInBounds(checkX, checkY)) {
				int[] tempWithPrio = {MOVES[i][0], MOVES[i][1], priorityList[checkX][checkY] - 1};
//				System.out.println("Next in prio: " + nextInPrio);
//				System.out.println("At " + x + " - " + y);
				tempPrio[nextInPrio++] = tempWithPrio;
			}
		}
		
		int[][] returnPrio = new int[tempPrio.length][];
		for(int i = 0; i < returnPrio.length; i++) {
			int tempLowest = tempPrio[i][2];
			int tempLowestPos = i;
			for(int a = i+1; a < returnPrio.length; a++) {
				if(tempPrio[a][2] < tempLowest) {
					tempLowestPos = a;
					tempLowest = tempPrio[a][2];
				}
			}
			
			int[] returnMove = {tempPrio[tempLowestPos][0], tempPrio[tempLowestPos][1]};
			returnPrio[i] = returnMove;
		}
		
		return returnPrio;
	}
}
