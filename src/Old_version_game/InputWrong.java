package Old_version_game;

public class InputWrong
{
    private String[] inputs;
    private int rowNum;
    private int colNum;
    private boolean isWrong;

    public InputWrong(int rowNum,int colNum)
    {
        this.inputs=new String[3];
        this.rowNum=rowNum;
        this.colNum=colNum;
        isWrong=true;
    }

    //correct input obey 3 criteria
    protected boolean checker()
    {
        boolean cri0,cri1,cri2,cri;
        cri0 = (Integer.parseInt(inputs[0])>=0) && (Integer.parseInt(inputs[0])<rowNum);//larger or equal than 0, smaller than row
        cri1 = (Integer.parseInt(inputs[1])>=0) && (Integer.parseInt(inputs[1])<colNum);//i[1]
        cri2 = (inputs[2].equals("L")) || (inputs[2].equals("R")); //i[2]
        cri = cri0 && cri1 && cri2;
        isWrong=!cri;

        if(isWrong)
        {
            System.out.println("wrong input, try again");
        }
        return isWrong;
    }

    protected void changeInputs(String[] inputs)
    {
        this.inputs[0]=inputs[0];
        this.inputs[1]=inputs[1];
        this.inputs[2]=inputs[2];
    }

}
