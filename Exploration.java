/* Name: Desmond Frimpong
* Date: April 18, 2023
* File: Exploration.java
* 
* this class explores the relationship between: the density of the obstacles to the probability of reaching
    the target; the lenghts of the path found by DFS and BFS; the average number of cells explored by DFS and BFS
*/

import java.util.Random;

public class Exploration {


    public static void main(String[] args) throws InterruptedException{
        int sum = 0;

        for(int i = 0; i < 100; i++){
        
        int r1,c1,r2,c2;
        Random random = new Random();

        Maze maze = new Maze(25, 25, 0.2, 0.1, 0.1);
        AbstractMazeSearch searcher = new MazeBreadthFirstSearch(maze);

        r1 = random.nextInt(20);
        c1 = random.nextInt(20);
        r2 = random.nextInt(20);
        c2 = random.nextInt(20);

        while(!(maze.get(r1, c1).getType().equals(CellType.FREE))){
            r1 = random.nextInt(20);
            c1 = random.nextInt(20);
        }

        while(!(maze.get(r2, c2).getType().equals(CellType.FREE)) || (r1 == r2 && c1 == c2)){
            r2 = random.nextInt(20);
            c2 = random.nextInt(20);
        }

            if(searcher.search(maze.get(0, 0), maze.get(20, 15), false, 10) != null){
                sum += searcher.search(maze.get(0, 0), maze.get(20, 15), false, 10).size();
            }else{
                sum += 0;
            }

        }
        System.out.println(searcher.search(maze.get(0, 0), maze.get(10, 10), true, 10));
    }
    
}
