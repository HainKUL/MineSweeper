package Old_version_game;

public class MediumBoard extends Board
{
    public MediumBoard()
    {
        super();
        rowNum=16;
        colNum=16;
        mineNum=40;
        //这些信息都必须更新一下
        squares = new Square[rowNum][colNum];
        rands=new int[mineNum];
    }
}
