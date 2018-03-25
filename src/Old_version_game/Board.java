package Old_version_game;

import java.util.*;
import static java.util.Collections.sort;

public class Board {
    protected Square[][] squares;
    //public ArrayList<Old_version_game.Square> mines;
    //public Old_version_game.Square[] noMines;
    public ArrayList<Square> flagedSquares;
    protected int x_position, y_position;
    protected int[] rands;
    //int[][] mineArray;
    protected ArrayList<Square> openedSquares;
    protected int rowNum,colNum; //row,,x   col,,,x
    protected int mineNum;


    public Board() {
//        this.squares = new Old_version_game.Square[rowNum][colNum];
        //this.mines=new Old_version_game.Mine[10];//polymorphic assignment
        //this.noMines=new Old_version_game.NoMine[];
        //this.square=new Old_version_game.Square();
        flagedSquares = new ArrayList<Square>();
        this.rands = new int[mineNum];  //这里也不一定是十
        openedSquares=new ArrayList<Square>();
    }

    // generate 10 random numbers which belong to mines
    public void generateMines() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < rowNum*colNum+1; i++) {
            list.add(i);//range from 1 to 64
        }
        Collections.shuffle(list);

        //System.out.print("Old_version_game.Mine location from left to right: ");
        for (int index = 0; index < mineNum; index++)
            rands[index] = list.get(index);////10 numbers ranging from 1 to 64
        Arrays.sort(rands);//sort in ascending order
    }

    public void addSquares()//8x8
    {
        int index = 0, t = 1;//index:0~9; t:1~64
        for (x_position = 1; x_position < rowNum+1; x_position++) {
            for (y_position = 1; y_position < colNum+1; y_position++) {
                Square square;
                if (rands[index] == t)//the square is a mine  t是用来记录square的一维展开坐标的
                {
                    System.out.print(rands[index] + "\t");//check 10 random numbers
                    square = new Mine();//generate a mine square
                    index++;//index must be withing the range: 0~9
                    if (index > mineNum-1)
                        index = mineNum-1;
                } else
                    square = new NoMine();//generate a Old_version_game.NoMine square

                square.x = x_position;
                square.y = y_position;//x,y:1~8
                squares[x_position - 1][y_position - 1] = square;
                t++;
            }
        }
        System.out.println("\n");
    }

    public void drawTable() {//iteration
        for (x_position = 1; x_position < rowNum+1; x_position++) {
            for (y_position = 1; y_position < colNum+1; y_position++)
                System.out.print(squares[x_position - 1][y_position - 1].unit + "\t");//unit can be *,F or @
            System.out.println();
        }
        System.out.println();
    }


    /*public void unsetFlag(int r, int c)//leftClick
    {
        squares[r - 1][c - 1].unsetFlag(r, c);//notice: to be specific, the squares type is either mine or nomine
        flags.remove(squares[r - 1][c - 1].flag);
        drawTable();
    }*/

    public void showAllMimes() {
        for (Square[] squareArray : squares) {
            for (Square square : squareArray) {
                if (square.isMine == true)//hit the mine
                    square.setUnit("@");
            }
        }
    }


    public void RightExecution(int x, int y) {//check if the flag already exists
        int r=x-1, c=y-1;
        if(openedSquares.contains(squares[r][c]))
        {
            System.out.println("No execution");
        }
        else
        {
            if (squares[r][c].isFlag()) {//unset flag
                System.out.println("Update table after unsetting flag:");
                squares[r][c].unsetFlag(r, c);
                flagedSquares.remove(squares[r][c]);
            } else {//set flag
                System.out.println("Update table after setting flag:");
                squares[r][c].setFlag(r, c);
                flagedSquares.add(squares[r][c]);
            }
            drawTable();
        }

    }

    public boolean LeftExecution(int x, int y) {
        //if mines
        if(openedSquares.contains(squares[x - 1][y - 1])||flagedSquares.contains(squares[x - 1][y - 1]))//这里的统计openedSquares这里有纰漏
        {
            System.out.println("No execution");
        }
        else
            {
                openedSquares.add(squares[x - 1][y - 1]);
                if (squares[x - 1][y - 1].isMine)//hit the mine,show all mines
                {
                    showAllMimes();
                    drawTable();
                    System.out.println("Game Over! You lose");
                    return false;   //如果触雷，返回false，给main函数信号
                } else//left click, not mine
                {
                    showSquare(x,y);
                    drawTable();
                }
            }
        return true;  //如果一个循环完美结束，没有雷
    }

//mineArray[j][0]=row;mineArray[j][1]=col;
//mineArray[j]= new int[]{row, col,0,1,2,3};//not good solution,may change the array size

    public int countMines(int x, int y) {
        int count = 0;
        ArrayList<Square> squareRecord=new ArrayList<Square>(); // create an array to record counted squares

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                int rTemp=x-1+i;
                int cTemp=y-1+j;
                if(rTemp<0){rTemp=0;}
                if(cTemp<0){cTemp=0;}
                if(rTemp>rowNum-1){rTemp=rowNum-1;}
                if(cTemp>colNum-1){cTemp=colNum-1;}

                if(!squareRecord.contains(squares[rTemp][cTemp])) //if not counted, then check it
                {
                    if (squares[rTemp][cTemp].isMine()) {
                        count++;
                        squareRecord.add(squares[rTemp][cTemp]);

                    }
                }
            }
        }
        return count;
    }

    public void showSquare(int x, int y)//展现方块的数字或雷的图表
    {
        if (squares[x-1][y-1].isMine()) {
            squares[x-1][y-1].setUnit("@");   //如果是雷，把icon设成这样
        } else {
            int nr = countMines(squares[x-1][y-1].x, squares[x-1][y-1].y);
            if (nr != 0) {
                squares[x-1][y-1].setUnit("" + nr);
            } else //零雷的情况下
            {
                this.showNeighbour(x,y);
            }
        }
        //if the tile is mine, show mine`s icon; if not, first we should know this square`s adjecent square`s states, if
        //show 1 if 2 show 2,,,but if zero, we need sth more.
    }

    protected void showNeighbour(int x,int y) {

                if (this.countMines(x,y) == 0)//如果，这个地方仍然是周围没有雷，那么继续采用showneighbour的方式
                {
                    squares[x-1][y-1].setUnit(" ");  //对于零雷物体，图标设为空，且继续从左上角开始计算有没有雷
                    squares[x-1][y-1].openIt();
                    for(int i=-1;i<2;i++)
                    {
                        for (int j = -1; j < 2; j++)
                        {

                            //限定边界条件。Boundary limitation
                            int rTemp=x+i;
                            int cTemp=y+j;
                            if(rTemp<1){rTemp=1;}
                            if(cTemp<1){cTemp=1;}
                            if(rTemp>rowNum){rTemp=rowNum;}
                            if(cTemp>colNum){cTemp=colNum;}
                            if(squares[rTemp-1][cTemp-1].getOpen())
                            {
                                continue;
                            }

                            showNeighbour(rTemp,cTemp);
                        }
                    }
                }
                else {
                    int temp=countMines(x,y);
                    squares[x-1][y-1].setUnit(""+temp);
                    squares[x-1][y-1].openIt();
                }

    }


}