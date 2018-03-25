package Old_version_game;

public class easyBoard extends Board
{
    public easyBoard()
    {
        super();
        rowNum=8;
        colNum=8;
        mineNum=10;
        //这些信息都必须更新一下
        squares = new Square[rowNum][colNum];
        rands=new int[mineNum];
    }
}
