/* Name: Desmond Frimpong
* Date: April 18, 2023
* File: MazeDepthFirstSearch.java
* 
* CS 231
* Section B
* Project 7
* 
* this class implements the DethFirstSearch algorithm on the Maze
*/


public class MazeDepthFirstSearch extends AbstractMazeSearch{
    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        this.stack = new LinkedList<>();
    }

    /**
     * Add cell to the stack
     */
    public void addCell(Cell next) {
        stack.push(next);
    }

    /**
     * Find Next Cell to explore
     */
    public Cell findNextCell() {
        return stack.pop();
    }

    /**
     * Get the total number of remaining cells
     */
    public int numRemainingCells() {
        return stack.size();
    }

    
}
