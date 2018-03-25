package Old_version_game;

import Old_version_game.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Old_version_game.DiffifultySelection dSelection=new Old_version_game.DiffifultySelection();
       // System.out.println("choose the level");
        //选择难度，并且生成相应的界面
        Board b=null;

        while (b == null)
        {
            int userChoice=0;
            System.out.println("Please choose the level: 1: easy; 2: medium; 3:hard");
            try {
                Scanner sc = new Scanner(System.in);
                userChoice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input, try again");
            }

            switch (userChoice)
            {
                case 1: b=new easyBoard(); break;
                case 2: b=new MediumBoard();break;
                case 3:b=new HardBoard();break;
                default:break;
            }
        }

        //生成不同难度的board



        Scan scan = new Scan();

        b.generateMines();
        b.addSquares();
        WinnerJudger judger=new WinnerJudger();

        System.out.println("Draw table:");
        b.drawTable();

        while(true)
        {
            System.out.println("Enter input below: ");
            scan.Scan(b);
            int click_x=Integer.parseInt(scan.inputs[0]);
            int click_y=Integer.parseInt(scan.inputs[1]);
           if(scan.inputs[2].equals("L"))//set flag or unset flag，在这里加入对左键键入的限制
            {
                if(!b.LeftExecution(click_x,click_y))
                    break;

            }
            else if(scan.inputs[2].equals("R")) //right click
           {
               b.RightExecution(click_x,click_y);
           }

           //胜利判断
            if(judger.winnerJudger(b))
            {
                System.out.println("You win");
                break;
            }

        }
    }
}

