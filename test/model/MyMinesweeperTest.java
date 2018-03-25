package model;

import gameinterface.InvalidRangeException;
import GameFunction.MyMinesweeper;
import org.junit.Test;
import static org.junit.Assert.*;

import gameinterface.Location;
import org.junit.Before;
import java.util.ArrayList;

public class MyMinesweeperTest {

    @Test
    public void checkException() {
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
    }

}