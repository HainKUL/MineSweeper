package GameFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MineGenerator
{
    private int width;
    private int height;
    private int mineNum;
    public int[] rands;

    public MineGenerator(int hei,int wid,int mineNr) {
        this.height = hei;
        this.width = wid;
        this.mineNum = mineNr;
        this.rands=new int[this.mineNum];
    }

    public int[] generateMines() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 2; i < width*height+1; i++) {
            list.add(i);//range from 2 to 64,  1 is reserved for avoidMineOnFirstCheck
        }
        Collections.shuffle(list);

        //System.out.print("Old_version_game.Mine location from left to right: ");
        for (int index = 0; index < mineNum; index++)
            rands[index] = list.get(index);////10 numbers ranging from 2 to 64
        Arrays.sort(rands);//sort in ascending order
        return rands;
    }
}
