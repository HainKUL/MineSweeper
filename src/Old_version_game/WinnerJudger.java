package Old_version_game;

public class WinnerJudger
{
    //每一次main循环，计算一下board上剩余的“*”的数量，如果正好和雷数相等，那么就是玩家胜利
    public boolean winnerJudger(Board b)
    {
        int count=0;
        for(int i=0;i<b.rowNum;i++)
        {
            for(int j=0;j<b.colNum;j++)
            {
                if(b.squares[i][j].getUnit().equals("*")||b.squares[i][j].getUnit().equals("F"))//有一个bug，如果例如9个*，1个F，那么应该胜利，但没有被判断为胜利.
                {
                    count++;
                }
            }
        }
        if(count==b.mineNum)//这里的雷数，先默认为10，之后再调整
        {
            return true;
        }
        else return false;
    }
}
