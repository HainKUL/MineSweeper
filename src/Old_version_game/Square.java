package Old_version_game;

public class Square {
    protected boolean isMine;
    protected String unit;
    protected int x,y;//1~8,
    protected boolean flag;
    private boolean open;

    protected Square()
    {

        this.isMine=false;
        this.unit ="*";   //unclicked icon is "*"
        this.x=0; this.y=0;
        this.flag=false;
        open=false;
    }

    public void setUnit(String icon)
    {
        unit=icon;  //here where there should be a number.
    }


    protected boolean isMine()
    {
        return this.isMine;
    }

    protected boolean isFlag()
    {
        return this.flag;
    }

    protected String getUnit()
    {
        return this.unit;
    }

    protected void setFlag(int row,int col)
    {
        this.flag=true;
        this.unit ="F";
    }

    protected void unsetFlag(int row,int col)
    {
        this.flag=false;
        this.unit ="*";

    }

    protected void drawUnit()
    {
        System.out.print(this.unit +"\t");
    }

    protected void getLocation()
    {
        System.out.println("("+x+","+y+")"+"\t");
    }

    protected void openIt()  //打开的宫格记为open
    {
        open=true;
    }

    protected boolean getOpen()
    {
        return open;
    }
}
