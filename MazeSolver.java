/**
Find the boolean value of the statement
there exists at least one path through a maze, starting at a designated beginning and ending at treasure
?
**/

public class MazeSolver {

    //fields
    // new instances of Maze and Displayer
	public Maze newMaze;
	public Displayer displayer;
	private int stepsTaken;

	public boolean pathExists;
	private static int[] directionArray = new int[] {Maze.NORTH, Maze.EAST, Maze.SOUTH, Maze.WEST};

    //methods

	// base case testing overloading constructor without displayer
	public MazeSolver (Maze mazeGiven) {

		newMaze = mazeGiven;

		//helper method
		pathExists = mazeSolution();
	}



	public String toString() {
		if (pathExists) {
			return "At least one path exists, solving the maze! :)";
		}
		else {
			return "No path exists to solve the maze... :(";
		}
	}

	private boolean mazeSolution() {
		
		displayer.atTopOfWindow( newMaze + "step " + stepsTaken++);
		
		//base cases
		if (newMaze.explorerIsOnA() == Maze.TREASURE) {

			// test if the base case is working for just treasure
			//return false;

			return true;
		}
		
		else if (newMaze.explorerIsOnA() == Maze.WALL) {
			
			// test if the base case is working for just a wall
            // return true; 
			
			return false;
		}

		//recursive cases
		else {
			
			for (int direction:directionArray) {

				Maze snap = new Maze(newMaze); //snapshot of the maze so you can go back if this direction doesn't work out
				newMaze.dropA(Maze.WALL); //place a wall so you don't go back
				newMaze.go(direction); //step forward
				

			//recursive abstraction
				if (mazeSolution()) { //if there is a valid solution
					return mazeSolution(); //aka true
				}

			newMaze = snap; //step back if it didn't work

			}
	
			return false; //should only be reached if you've exhausted all paths
	
		}
	}

}
