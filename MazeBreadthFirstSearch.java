/* Name: Desmond Frimpong
* Date: April 18, 2023
* File: MazeBreadthFirstSearch.java
* 
* CS 231
* Section B
* Project 7
* 
* this class implements the BreadthFirstSearch algorithm on the Maze
*/


public class MazeBreadthFirstSearch extends AbstractMazeSearch{

    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        this.queue = new LinkedList<>();
    }

    /**
     * Add cell to the queue
     */
    public void addCell(Cell next) {
        queue.offer(next);
    }

    /**
     * Find next cell to explore
     */
    public Cell findNextCell() {
        return queue.poll();
    }

    /**
     * Get the remaining number of cells to explore
     */
    public int numRemainingCells() {
        return queue.size();
    }

}
