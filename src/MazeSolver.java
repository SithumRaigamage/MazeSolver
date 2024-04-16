public class MazeSolver {
    // initialized variables
    private char [][] maze;
    private int StartRow;
    private int StartCol;
    private int endRow;
    private int endCol;
    private int numRows;
    private int numCols;
    private int[] [] directions ={{-1,0},{1,0},{0,-1},{0,1}};

    //constructor
    public MazeSolver(char [][] maze){
        this.maze=maze;
        this.numRows=maze.length;
        this.numCols=maze[0].length;

    }


}
