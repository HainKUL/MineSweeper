package GameFunction;


public class Mine extends Square {
    public Mine() {
        super.isMine=true;
        //super.unit="@";
    }
    public void setUnit()
    {
        super.unit="@";   //'if clicked, show this icon'
    }

    protected boolean isMine()
    {
        return super.isMine();
    }
/*
    protected boolean isFlag()
    {
        return super.isFlag();
    }

    @Override
    protected String getUnit()
    {
        return super.getUnit();
    }

    protected void setFlag(int x,int y)
    {
        super.setFlag(x,y);
    }

    protected void unsetFlag(int x,int y)
    {
        super.unsetFlag(x,y);
    }

    @Override
    protected void drawUnit() {//show mine in the table
        super.drawUnit();
    }

    @Override
    protected void getLocation() {//show mine location
        super.getLocation();
    }
    */
}
