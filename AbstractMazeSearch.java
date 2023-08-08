/* Name: Desmond Frimpong
* Date: April 18, 2023
* File: AbstractMazeSearch.java
* 
* this abstract class implements a searching algorithm for searching the Maze
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class AbstractMazeSearch {
    
    // fields of the abstract class
    private Maze maze;
    protected Cell start, target, cur;
    private int cellReachedCount;

    public AbstractMazeSearch(Maze maze){
        this.maze = maze;
        start = null;
        target = null;
        cur = null;
    }


    public abstract Cell findNextCell();

    public abstract void addCell(Cell next); 

    public abstract int numRemainingCells();
    

    public int getReachedCount(){
        return cellReachedCount;
    }

    public Maze getMaze(){
        return maze;
    }

    public void setTarget(Cell target){
        this.target = target;
    }

    public Cell getTarget(){
        return target;
    }

    public void setCur( Cell cur){
        this.cur = cur;
    }

    public Cell getCur(){
        return cur;
    }

    public void setStart(Cell start){
        this.start = start;
        this.start.setPrev(start);
    }

    public Cell getStart(){
        return start;
    }

    public void reset(){
        start = null;
        target = null;
        cur = null;
    }

    public LinkedList<Cell> traceback(Cell cell){
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();

        while(curCell != null){
            path.addFirst(curCell);

            if(curCell.getCol() == start.getCol() && curCell.getRow() == start.getRow()){
                return path;
            }
            curCell = curCell.getPrev();
        }
        return null;
    }


    /*
     *  
     */
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) throws InterruptedException{


        //set the display to be null - a display will be created only if dispaly == true
        MazeSearchDisplay mazeDisplay = null;


        if(display){
            mazeDisplay = new MazeSearchDisplay(this, 35);
        }

        setStart(start);
        setTarget(target);
        setCur(start);


        addCell(start);
        while(numRemainingCells() > 0){
            Cell nextCell = findNextCell();
            if(display){

                LinkedList<Cell> path = traceback(this.cur);
                if(path.getFirst() == null){  
        
                    Thread.sleep(delay);
                    }
                }else{

                    //your turning 
                    if((cur.getRow() == path.getFirst().getRow()) && nextCell.getRow() != cur.getRow()){
                        if((cur.getCol() == path.getFirst().getCol()) && nextCell.getCol() != cur.getCol()){
                            
                                Thread.sleep(delay);
                            }
                    
                        } else{
                            Thread.sleep(delay);
                        }     
                }
                mazeDisplay.repaint();  
            }

            setCur(nextCell);
            
            for(Cell neighbor: maze.getNeighbors(cur)){
                if(neighbor.getPrev() == null){
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                    if(neighbor.equals(target)){
                        return traceback(target);
                    }
                }
            }
        }

        return null;

       
    }


    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }


    

}
