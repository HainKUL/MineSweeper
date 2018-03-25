package Old_version_game;

public class DiffifultySelection {
    int userChoice;
    Board newBoard;
    //把字符串编程hashcode，让switch能够识别

    /*public int selection(Old_version_game.Board b) {
        while (newBoard == null) {
            System.out.println("Please choose the level: 1: easy; 2: medium; 3:hard");
            try {
                Scanner sc = new Scanner(System.in);
                userChoice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input, try again");
            }

          switch (userChoice)
            {
                case 1: newBoard=new Old_version_game.easyBoard(); break;
                case 2: newBoard=new Old_version_game.MediumBoard();break;
                case 3:newBoard=new Old_version_game.HardBoard();break;
                default:break;
            }
        }

        return choice;
        }*/
}


/*this.inputs =new String[3];
        iw=new Old_version_game.InputWrong(this.inputs,8,8);
        do{
            Scanner sc = new Scanner(System.in);
            inputs[0] = sc.next();//row
            inputs[1] = sc.next();//col
            inputs[2] = sc.next();//R or L
            iw.changeInputs(inputs);
        }while (iw.checker());*/