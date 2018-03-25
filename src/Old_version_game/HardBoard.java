package Old_version_game;

public class HardBoard extends Board
{
    public HardBoard()
    {
        super();
        rowNum=16;
        colNum=30;
        mineNum=99;
        //这些信息都必须更新一下
        squares = new Square[rowNum][colNum];
        rands=new int[mineNum];
    }
}
