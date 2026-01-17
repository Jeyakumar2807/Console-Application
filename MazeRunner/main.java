
public class Main {
    public static void main(String[] args) {
        int[][]triggers={{5,2},{2,5}};
        MazeRunner maze=new MazeRunner(6,6,triggers);
        maze.putTraser(2,4);
        maze.putMonster(1,2);
        maze.putMonster(2,3);
        maze.putMonster(2,5);
        maze.putMonster(3,4);
        maze.puthole(5,2);
        maze.puthole(2,5);
        int shortestpath=MazeRunner.shortestPath(5,1);
        System.out.println("The Shortest path is: "+shortestpath);
    }
}
