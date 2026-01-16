import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {
    private char[][]snakeboard=null;
    private Queue<Node>queue=new LinkedList<>();
    private Queue<Node>food=new LinkedList<>();
    private Scanner sc=new Scanner(System.in);
    Snake(int row,int col)
    {
        snakeboard=new char[row][col];
        queue.add(new Node(0,0));
        //food queue

        food.add(new Node(1,0));
        food.add(new Node(2,2));
        food.add(new Node(3,4));
        food.add(new Node(5,2));
        food.add(new Node(4,5));

        //in order to display frst food
        displayfood(food.poll());
    }
    public void snakeMove(int row,int col)
    {
        if(row>=0&&row< snakeboard.length&&col>=0&&col< snakeboard[0].length)
        {
            queue.add(new Node(row,col));
            if(snakeboard[row][col]!='X')
            {
                Node node=queue.poll();
                int r=node.getRow();
                int c=node.getColumn();
                snakeboard[r][c]='\0';
            }
            if(snakeboard[row][col]=='X')
            {
                if(food.isEmpty())
                {
                    moveforwardandprint(row,col);
                    System.out.println("Game Over!!!!!.........");
                    System.exit(0);
                }
                displayfood(food.poll());
            }
            if(snakeboard[row][col]=='.')
            {
                System.out.println("gamr over!!!!!........");
                System.exit(0);
            }
            moveforwardandprint(row,col);
            if(!queue.isEmpty())
            {
                System.out.println("Enter a Position: ");
                char direction=sc.next().charAt(0);

                if(direction=='U'||direction=='u')
                {
                    snakeMove(--row,col);
                }
                if(direction=='D'||direction=='d')
                {
                    snakeMove(++row,col);
                }
                if(direction=='L'||direction=='l')
                {
                    snakeMove(row,--col);
                }
                if(direction=='R'||direction=='r')
                {
                    snakeMove(row,++col);
                }
            }
        }
        else {
            System.out.print("Invalid Move");
            System.exit(0);
        }
    }
    public void moveforwardandprint(int row,int col)
    {
        snakeboard[row][col]='.';
        printsnake();
    }
    public void displayfood(Node node){
        int r=node.getRow();
        int c=node.getColumn();
        snakeboard[r][c]='X';
    }
    public void printsnake()
    {
        for(int i=0;i< snakeboard.length;i++)
        {
            for(int j=0;j<snakeboard[0].length;j++)
            {
                System.out.print(snakeboard[i][j]+" ");
            }
            System.out.println();
        }
    }
}
