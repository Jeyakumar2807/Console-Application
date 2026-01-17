import java.util.LinkedList;
import java.util.Queue;

public class MazeRunner {
    private static char[][]maze;
    static int[][]trigger;
    MazeRunner(int row,int col,int[][]trigger)
    {
        maze=new char[row][col];
        MazeRunner.trigger=trigger;
        initializemaze();
    }
    public void initializemaze()
    {
        for(int i=0;i< maze.length;i++)
        {
            for(int j=0;j<maze[0].length;j++)
            {
                maze[i][j]='O';
            }
        }
    }
    public static void printmaze()
    {
        for(int i=0;i< maze.length;i++)
        {
            for(int j=0;j<maze[0].length;j++)
            {
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean puthole(int row,int col)
    {
        row--;
        col--;
        if(row>=maze.length||col>maze[0].length) return false;
        maze[row][col]='H';
        return true;
    }
    public boolean putTraser(int row,int col)
    {
        row--;
        col--;
        if(row>= maze.length||col>=maze[0].length) return false;
        maze[row][col]='T';
        return true;
    }
    public boolean putMonster(int row,int col)
    {
        row--;
        col--;
        if(row>=maze.length||col>=maze[0].length) return false;
        maze[row][col]='M';
        return true;
    }
    private static class Node{
        int row,col,steps;
        Node previous;
        Node(int row,int col,int steps,Node previous)
        {
            this.row=row;
            this.col=col;
            this.steps=steps;
            this.previous=previous;
        }
    }
    public static void printshortestpath(Node node)
    {
        Node current=node;
        while(current!=null)
        {
            if(maze[current.row][current.col]!='T'&&maze[current.row][current.col]!='A')
            {
                maze[current.row][current.col]='P';
            }
            current=current.previous;
        }
        System.out.println("Maze with path:");
        printmaze();
    }
    public static boolean istrigger(int row,int col)
    {
        for(int[]t:trigger)
        {
            if(t[0]-1==row&&t[1]-1==col) return true;
        }
        return false;
    }
    public static int shortestPath(int row,int col)
    {
        row--;
        col--;
        if(row>=maze.length||col>=maze[0].length) return -1;
        if(maze[row][col]=='T') return 0;
        maze[row][col]='A';
        int r=maze.length;
        int c=maze[0].length;
        boolean[][]vis=new boolean[r][c];
        int[][]directions={{1,0},{-1,0},{0,1},{0,-1}};
        Queue<Node>queue=new LinkedList<>();
        queue.add(new Node(row,col,0,null));
        vis[row][col]=true;
        while(!queue.isEmpty())
        {
            Node current=queue.poll();
            for(int[]direction:directions)
            {
                int nextrow= current.row+direction[0];
                int nextcol= current.col+direction[1];
                if(nextrow>=0&&nextrow<r&&nextcol>=0&&nextcol<c&&!vis[nextrow][nextcol]&&
                        (maze[nextrow][nextcol]=='M'&&istrigger(nextrow,nextcol)||maze[nextrow][nextcol]!='M')&&maze[nextrow][nextcol]!='H')
                {
                    if(maze[nextrow][nextcol]=='T')
                    {
                        MazeRunner.printshortestpath(current);
                        return current.steps+1;
                    }
                    queue.add(new Node(nextrow,nextcol, current.steps+1,current));
                    vis[nextrow][nextcol]=true;
                }
            }
        }
        return -1;
    }

}
