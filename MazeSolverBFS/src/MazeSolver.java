//Name -Sithum Lisitha Raigamage
//IIT student No -20222473
//UOW student No -w1999685

import java.util.*;
public class MazeSolver {
    //variables
    private char[][] maze;
    private int startRow, startCol, endRow, endCol;
    private int numRows, numCols;
    private boolean[][] visited;
    private Coordinates[][] predecessor;
    private String[] dirNames = {"Up", "Down", "Left", "Right"};
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private long timeTaken;

    //constructor
    public MazeSolver(char[][] maze) {
        this.maze = maze;
        this.numRows = maze.length;
        this.numCols = maze[0].length;
        this.visited = new boolean[numRows][numCols];
        this.predecessor = new Coordinates[numRows][numCols];
        findStartAndEnd();
    }

    //finding the position of S and F by iterating through a loop
    private void findStartAndEnd() {
        boolean startFound = false, endFound = false;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                //condition for finding S
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    startFound = true;
                    //condition for finding S
                } else if (maze[i][j] == 'F') {
                    endRow = i;
                    endCol = j;
                    endFound = true;
                }
            }
        }
        //error throwing if not found S an F
        if (!startFound || !endFound) {
            throw new IllegalStateException("Start or Finish position not found in the maze.");
        }
    }

    //bfs algorithm to find the shortest path using the logic sliding
    public boolean BFSMethod() {
        long startTime = System.nanoTime();

        //initialized a linked list to add the coordinates
        Queue<Coordinates> queue = new LinkedList<>();
        queue.offer(new Coordinates(startRow, startCol, null));
        visited[startRow][startCol] = true;

        //checking the queue is not empty
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            int row = current.x;
            int col = current.y;

            // Check if the current cell is the destination
            if (row == endRow && col == endCol) {
                //start the time for calculating time complexity
                timeTaken = System.nanoTime() - startTime;
                printPath(current);
                return true;
            }

            // Consider each direction
            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int newRow = row;  // Initialize newRow and newCol for each direction
                int newCol = col;

                // Slide until an obstacle or border is hit
                while (isValid(newRow + dir[0], newCol + dir[1])) {
                    newRow += dir[0];
                    newCol += dir[1];

                    // Check if the destination is reached
                    if (newRow == endRow && newCol == endCol) {
                        //stop the time for calculating time complexity
                        timeTaken = System.nanoTime() - startTime;
                        // Print the path
                        printPath(current);
                        return true;
                    }
                }

                // Check if the new cell is visited and not already in the queue
                if (!visited[newRow][newCol]) {
                    queue.offer(new Coordinates(newRow, newCol, dirNames[i]));
                    visited[newRow][newCol] = true;
                    predecessor[newRow][newCol] = current;
                }
            }
        }
        //get the time
        timeTaken = System.nanoTime() - startTime;
        System.out.println("No path found.");
        return false;
    }
    //print method for printing columns/rows/S position/F position/obstacles and shortest path
    private void printPath(Coordinates destination) {

        System.out.println("Path from S to F:");
        System.out.println("");
        // Outputting maze specifications before displaying the path
        System.out.println("Maze Width: " + numCols);
        System.out.println("Maze Height: " + numRows);
        System.out.println("S Position: (" + (getStartCol() + 1) + ", " + (getStartRow() + 1) + ")");
        System.out.println("F Position: (" + (getEndCol() + 1) + ", " + (getEndRow() + 1) + ")");
        System.out.println(" ");

        // Printing obstacle indexes horizontally in a list
        System.out.println("Obstacle Indexes:");
        StringBuilder obstacleIndexes = new StringBuilder();
        boolean obstacleFound = false;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j] == '0') {
                    if (obstacleFound) {
                        obstacleIndexes.append(", ");
                    }
                    obstacleIndexes.append("(").append(j + 1).append(", ").append(i + 1).append(")");
                    obstacleFound = true;
                }
            }
        }
        if (!obstacleFound) {
            obstacleIndexes.append("None");
        }
        System.out.println(obstacleIndexes.toString());
        System.out.println();

        //linked for getting the coordinates
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = destination;
        while (current != null) {
            path.addFirst(current);
            current = predecessor[current.x][current.y];
        }

        int stepCount = 0;
        Coordinates previous = null;
        for (Coordinates p : path) {
            if (stepCount == 0) {
                //start position
                System.out.println(++stepCount + ". Start at (" + (p.y + 1) + ", " + (p.x + 1) + ")");
            } else {
                //printing between coordinates
                String direction = (p.direction == null && previous != null) ? "Move " + DirectionOfMovement(previous, p) : "Move " + p.direction.toLowerCase();
                if (p.direction == null && previous != null) {
                    System.out.println(++stepCount + ". Move to " + direction + " at (" + (p.y + 1) + ", " + (p.x + 1) + ")");
                } else {
                    System.out.println(++stepCount + ". " + direction + " to (" + (p.y + 1) + ", " + (p.x + 1) + ")");
                }
            }
            previous = p;
        }
        //printing F index
        System.out.println(++stepCount + ". Move to (" + (getEndCol() + 1) + ", " + (getEndRow() + 1) + ")");
        //print with count
        System.out.println(++stepCount + ". Done!");
        System.out.println();
    }

    private String DirectionOfMovement(Coordinates from, Coordinates to) {
        //direction according to the index
        if (from.x == to.x) {
            //directions
            return (from.y < to.y) ? "right" : "left";
        } else {
            //directions
            return (from.x < to.x) ? "down" : "up";
        }
    }

    //valid method for checking the index bound of the array
    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols &&
                maze[row][col] != '0';
    }

    //getter method for returning the time
    public long getTimeTaken() {
        return timeTaken;
    }
    //getter method for returning the startRow
    public int getStartRow() {
        return startRow;
    }
    //getter method for returning the StartColumn
    public int getStartCol() {
        return startCol;
    }
    //getter method for returning the EndRow
    public int getEndRow() {
        return endRow;
    }
    //getter method for returning the EndColumn
    public int getEndCol() {
        return endCol;
    }

}