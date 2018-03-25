package GameFunction;

import gameinterface.InvalidRangeException;
import model.MyLocation;

import java.util.Scanner;


public class Main
{
    public static void main(String args[]) throws InvalidRangeException
    {
        System.out.print("initialize the game: please define board height,width and mineNr:\t");
        Scan scan = new Scan();
        WinnerJudger winner=new WinnerJudger();

        //INITIALIZATION
        int h,w, n;
       while (true)
       {

           Scanner sc = new Scanner(System.in);
           h=sc.nextInt();
           w=sc.nextInt();
           n=sc.nextInt();
           if(h<=0 || w<=0|| n<0 || n>h*w)
           {
               System.out.println("wrong initialize! try again");
           }
           else break;
       }

        MyMinesweeper game1 = new MyMinesweeper(h,w,n);
        game1.printBoard();

        while(true)
        {
            scan.Scan(game1);
            int row=Integer.parseInt(scan.inputs[0]);
            int col=Integer.parseInt(scan.inputs[1]);
            String LorR=scan.inputs[2];

            if(scan.inputs[2].equals("L"))//set flag or unset flag，在这里加入对左键键入的限制
            {
                game1.checkLocation(new MyLocation(row,col));
                if(game1.getLost()){break;}

            }
            else if(scan.inputs[2].equals("R")) //right click
            {
                game1.flagLocation(new MyLocation(row,col));
            }

            if(winner.winnerJudger(game1))
            {
                System.out.println("you win!");
                break;
            }
        }

    }
}
/*public void checkException() {
        try{
            MyMinesweeper game1 = new MyMinesweeper(6,4,10);
            MyMinesweeper game2 = new MyMinesweeper(7,3);
        } catch (InvalidRangeException e) { fail("No InvalidRangeException expected"); }

        try {
            MyMinesweeper game = new MyMinesweeper(-1,5);
            fail("Expected an InvalidRangeException to be thrown"); //fail的意思，如果上一句话通过，那么test'fail，换言之，所期望的是不通过
        } catch (InvalidRangeException e) { }  // This exception was expected
          catch (Exception e) { assertEquals("A different Exception has been trown",InvalidRangeException.class,e.getClass()); }

        try {
          MyMinesweeper game = new MyMinesweeper(5,0);
          fail("Expected an InvalidRangeException to be thrown");
        } catch (InvalidRangeException e) { } // This exception was expected
          catch (Exception e) { assertEquals("A different Exception has been trown",InvalidRangeException.class,e.getClass()); }

        try {
            MyMinesweeper game = new MyMinesweeper(5,5,30);
            fail("Expected an InvalidRangeException to be thrown");
        } catch (InvalidRangeException e) { } // This exception was expected
          catch (Exception e) { assertEquals("A different Exception has been trown",InvalidRangeException.class,e.getClass()); }
    }

    @Test
    public void getWidth() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(6,4);
        assertEquals(4,game.getWidth());
    }

    @Test
    public void getHeight() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(6,4);
        assertEquals(6,game.getHeight());
    }

    @Test
    public void getValueAt() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(3,4, 1);
        assertNull(game.getValueAt(new MyLocation(-1,-1)));
        assertNull(game.getValueAt(new MyLocation(-1,2)));
        assertNull(game.getValueAt(new MyLocation(2,-1)));
        assertNull(game.getValueAt(new MyLocation(3,2)));
        assertNull(game.getValueAt(new MyLocation(2,4)));
        assertNull(game.getValueAt(new MyLocation(3,4)));
        for (int row=0;row<3;row++) {
            for(int col=0;col<4;col++) {
                assertNotNull(game.getValueAt(new MyLocation(row,col)));
            }
        }
    }

    @Test
    public void checkLocation() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(3,4, 1);
        game.checkLocation(new MyLocation(-1,-1));
        game.checkLocation(new MyLocation(-1,2));
        game.checkLocation(new MyLocation(2,-1));
        game.checkLocation(new MyLocation(3,2));
        game.checkLocation(new MyLocation(2,4));
        game.checkLocation(new MyLocation(3,4));
        for (int row=0;row<3;row++) {
            for(int col=0;col<4;col++) {
                game.checkLocation(new MyLocation(row,col));
            }
        }
    }

    @Test
    public void flagLocation() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(8,9, 3);
        game.flagLocation(new MyLocation(-1,-1));
        game.flagLocation(new MyLocation(-1,2));
        game.flagLocation(new MyLocation(8,2));
        game.flagLocation(new MyLocation(3,9));
        game.flagLocation(new MyLocation(3,7));
        assertEquals("F",game.getValueAt(new MyLocation(3,7)));
        game.flagLocation(new MyLocation(3,7));
        //assertEquals("O",game.getValueAt(new MyLocation(3,7)));
        assertEquals("*",game.getValueAt(new MyLocation(3,7)));
    }

    @Test
    public void getNrOfActions() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(7,5, 3);
        game.checkLocation(new MyLocation(-1,-1));
        game.checkLocation(new MyLocation(-1,2));
        game.checkLocation(new MyLocation(7,2));
        game.checkLocation(new MyLocation(3,5));
        assertEquals(0,game.getNrOfActions());
        game.checkLocation(new MyLocation(3,3));
        assertEquals(1,game.getNrOfActions());


        MyMinesweeper game1 = new MyMinesweeper(7,5, 3);
        game1.flagLocation(new MyLocation(2,2));
        assertEquals(0,game1.getNrOfActions());
        game1.checkLocation(new MyLocation(2,2));
        assertEquals(1,game1.getNrOfActions());
    }

    @Test
    public void getNrOfMinesLeft() throws InvalidRangeException {
        MyMinesweeper game = new MyMinesweeper(7,5, 3);
        game.checkLocation(new MyLocation(3,3));
        assertEquals(3,game.getNrOfMinesLeft());
        for (int row=0;row<7;row++) {
            for(int col=0;col<5;col++) {
                game.flagLocation(new MyLocation(row,col));
            }
        }
        assertEquals(0,game.getNrOfMinesLeft());
    }

    @Test
    public void getLost() throws InvalidRangeException {
        for (int row=0;row<7;row++) {
            for(int col=0;col<5;col++) {
                MyMinesweeper game = new MyMinesweeper(7,5, 3);
                game.checkLocation(new MyLocation(row,col));
                assertFalse(game.getLost());//not lost
            }
        }
        MyMinesweeper game = new MyMinesweeper(7,5, 3);
        for (int row=0;row<7;row++) {
            for(int col=0;col<5;col++) {
                game.checkLocation(new MyLocation(row,col));
            }
        }
        assertTrue(game.getLost());//hit the mine, getlost
    }*/