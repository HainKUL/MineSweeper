package Old_version_game;

public class NoMine extends Square {
    int SurrMineNr;//surrounding mine numbers

    public NoMine()
    {
        super.isMine=false;
        //super.unit=" ";
    }
    public void setUnit(String icon)
    {
        unit=icon;  //here where there should be a number.
    }

    protected boolean isMine()
    {
        return super.isMine();
    }

    protected boolean isFlag()
    {
        return super.isFlag();
    }

    protected String getUnit()
    {
        return super.getUnit();
    }

    protected void setFlag(int x, int y)
    {
        super.setFlag(x,y);
    }

    protected void unsetFlag(int x,int y)
    {
        super.unsetFlag(x,y);
    }

    @Override
    protected void drawUnit() {
        super.drawUnit();
    }

    @Override
    protected void getLocation() {
        super.getLocation();
    }

    public void surroundingMineNr(int nr)
    {
        this.SurrMineNr=nr;
        super.unit=Integer.toString(SurrMineNr);
    }

}