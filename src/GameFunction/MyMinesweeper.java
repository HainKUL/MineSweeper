package GameFunction;


import gameinterface.Location;
import model.MyLocation;
import gameinterface.MineSweeperModel;
import gameinterface.InvalidRangeException;

import java.util.ArrayList;
import java.util.Arrays;

public class MyMinesweeper implements MineSweeperModel {
    private int height, width, mineNum;
    private Square[][] squares;
    private MineGenerator mg;
    private int NrOfActions;
    private boolean isLost;
    private ArrayList<Square> flagedSquares;
    private ArrayList<Square> openedSquares;
    int[] rands;

    public MyMinesweeper(int hei,int wid,int mineNr) throws InvalidRangeException {
        if(hei<=0||wid<=0||mineNr>(wid*hei)){
            throw new InvalidRangeException();
        }
        this.height=hei;
        this.width=wid;
        this.mineNum=mineNr;
        this.squares=new Square[height][width];
        this.mg=new MineGenerator(height,width,mineNum);
        this.NrOfActions=0;
        this.isLost=false;
        flagedSquares = new ArrayList<Square>();
        openedSquares=new ArrayList<Square>();
        rands=new int[mineNum];

        if(mineNum!=0)
        {
            mg.generateMines();
            rands = mg.rands;
        }
        else
            rands= new int[]{0};
        addSquares();
    }

    public MyMinesweeper(int hei,int wid) throws InvalidRangeException {
        if(hei<=0||wid<=0){
            throw new InvalidRangeException();
        }
        this.height=hei;
        this.width=wid;
        this.mineNum=0;
        this.squares=new Square[height][width];
        this.mg=new MineGenerator(height,width,mineNum);
        this.NrOfActions=0;
        this.isLost=false;
        flagedSquares = new ArrayList<Square>();
        openedSquares=new ArrayList<Square>();
        rands= new int[]{0};
        addSquares();
    }
    public Square[][] getSquares()
    {
        return squares;
    }

    public int getMineNum()
    {
        return mineNum;
    }
    public void addSquares()
    {
        int index = 0, t = 1;//index:0~9; t:1~64
        int x_position,y_position;
        for (x_position = 0; x_position < height; x_position++) {
            for (y_position = 0; y_position < width; y_position++) {
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

                squares[x_position][y_position] = square;
                t++;
            }
        }
        System.out.println("\nthe above sequence shows the mine location\n");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getValueAt(Location location) {
        int row=location.getRow();
        int col=location.getColumn();
        if(row>=0&&row<height&&col>=0&&col<width) {
            return squares[row][col].getUnit();
        }
        else
            return null;
    }

    public void avoidMineOnFirstCheck(int row,int col)
    {
        int i;
        for(i=0;i<mineNum;i++)
        {
            if(rands[i]==row*width+col+1)
                break;//find out the index of rands
        }
        rands[i]=1;//move to upper-left corner
        Arrays.sort(rands);//shuffle again
    }

    public void checkLocation(Location location) {//left click
        int row=location.getRow();//start from 0
        int col=location.getColumn();
        if(row>=0&&row<height&&col>=0&&col<width) {//check if within the range
            //avoid the first check is a mine
            if (NrOfActions==0&&squares[row][col].isMine) {
                avoidMineOnFirstCheck(row,col);
                addSquares();
            }

            if(openedSquares.contains(squares[row][col])||flagedSquares.contains(squares[row][col]))//这里的统计openedSquares这里有纰漏
            {
                System.out.println("No execution,either the position is a flag or is a checked(open)");
            }
            else
            {
                openedSquares.add(squares[row][col]);
                if (squares[row][col].isMine)
                {//hit the mine,show all mines
                    this.isLost=true;
                    showAllMimes();
                    //printBoard();//drawTable();
                    System.out.println("Game Over! You lose");

                }
                else {//not mine,show neighbour
                    //showNoMine(x,y);
                    //drawTable();
                    showSquare(row,col);
                    //printBoard();
                }
                //this.NrOfActions++;
                //printBoard();
            }
            this.NrOfActions++;
            printBoard();
        }
    }

    public void flagLocation(Location location) {//right click
        int row=location.getRow();//start from 0
        int col=location.getColumn();
        if(row>=0&&row<height&&col>=0&&col<width) {//check if within the range
            if (squares[row][col].isFlag()) {//already flaged, unset flag
                squares[row][col].unsetFlag(row, col);
                flagedSquares.remove(squares[row][col]);
                this.mineNum++;
                printBoard();
            }
            else if(openedSquares.contains(squares[row][col])) {//square was previously open
                System.out.println("No execution, the location has been checked(open)");
            }
            else {//not flaged yet, set flag
                squares[row][col].setFlag(row, col);
                flagedSquares.add(squares[row][col]);
                //if(squares[row][col].isMine)//if flag location is mine, mineNr--
                    this.mineNum--;
                printBoard();
            }
        }
        //printBoard();
    }

    public int getNrOfActions() {
        return this.NrOfActions;
    }

    public long getNrOfMinesLeft() {
        return this.mineNum;
    }

    public boolean getLost() {
        return this.isLost;
    }

    public void printBoard() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                System.out.print(" " + getValueAt(new MyLocation(i,j)) + " ");
            }
            System.out.println();
        }
        System.out.println("nr of actions: " + getNrOfActions() + " :: mines to go: " + getNrOfMinesLeft());
    }

    public void showAllMimes() {
        for (Square[] squareArray : squares) {
            for (Square square : squareArray) {
                if (square.isMine == true)//hit the mine
                    square.setUnit("@");
            }
        }
    }

    public int countMines(int x, int y) {
        int count = 0;
        ArrayList<Square> squareRecord=new ArrayList<Square>(); // create an array to record counted squares

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                int rTemp=x+i;
                int cTemp=y+j;
                if(rTemp<0){rTemp=0;}
                if(cTemp<0){cTemp=0;}
                if(rTemp>height-1){rTemp=height-1;}
                if(cTemp>width-1){cTemp=width-1;}

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
        if (squares[x][y].isMine()) {
            squares[x][y].setUnit("@");   //如果是雷，把icon设成这样
        } else {
            int nr = countMines(x,y);
            if (nr != 0) {
                squares[x][y].setUnit("" + nr);
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
            squares[x][y].setUnit(" ");  //对于零雷物体，图标设为空，且继续从左上角开始计算有没有雷
            squares[x][y].openIt();
            for(int i=-1;i<2;i++)
            {
                for (int j = -1; j < 2; j++)
                {

                    //限定边界条件。Boundary limitation
                    int rTemp=x+i;
                    int cTemp=y+j;
                    if(rTemp<1){rTemp=1;}
                    if(cTemp<1){cTemp=1;}
                    if(rTemp>=height){rTemp=height-1;}
                    if(cTemp>=width){cTemp=width-1;}
                    if(squares[rTemp][cTemp].isOpen())
                    {
                        continue;
                    }

                    showNeighbour(rTemp,cTemp);
                }
            }
        }
        else {
            int temp=countMines(x,y);
            squares[x][y].setUnit(""+temp);
            squares[x][y].openIt();
        }

    }

}
