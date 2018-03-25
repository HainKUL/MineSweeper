package Old_version_game;

import java.util.Scanner;

public class Scan {
    public String[] inputs;
    private InputWrong iw;

    public void Scan(Board b){

        this.inputs =new String[3];
        iw=new InputWrong(b.rowNum,b.colNum);
        do{
            Scanner sc = new Scanner(System.in);
            inputs[0] = sc.next();//row
            inputs[1] = sc.next();//col
            inputs[2] = sc.next();//R or L
            iw.changeInputs(inputs);
        }while (iw.checker());


        System.out.println("Your Input is as below:");
        System.out.println("Row:" + inputs[0] +"\t" + "Colume:"+ inputs[1] + "\t"+"Right/Left Click:"+ inputs[2]);
    }

}
