package GameFunction;

public class Square {
    protected String unit;
    protected int xPosi,yPosi;//1~8
    protected boolean isMine;
    protected boolean isFlag;
    protected boolean isOpen;

    protected Square() {
        this.unit ="*";//unclicked icon is "*"
        this.xPosi=0; this.yPosi=0;
        this.isMine=false;
        this.isFlag=false;
        this.isOpen=false;
    }

    protected String getUnit() {
        return this.unit;
    }

    protected void getLocation() {
        System.out.println("("+xPosi+","+yPosi+")"+"\t");
    }

    protected boolean isMine() {
        return this.isMine;
    }

    protected boolean isFlag() {
        return this.isFlag;
    }

    protected boolean isOpen() {
        return this.isOpen;
    }

    public void setUnit(String icon) {
        this.unit=icon;
    }

    protected void setFlag(int row,int col){//there must be args,for NoMine
        this.isFlag=true;
        this.unit ="F";
    }

    protected void unsetFlag(int row,int col) {
        this.isFlag=false;
        this.unit ="*";
    }

    protected void openIt() {//打开的宫格记为open
        this.isOpen=true;
    }

    protected void drawUnit() {
        System.out.print(this.unit +"\t");
    }
}