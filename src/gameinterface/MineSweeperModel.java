package gameinterface;


import model.MyLocation;

/**
 * MineSweeper model interface, to make it easier to attach whatever kind of UI or test class to the game
 */
public interface MineSweeperModel {
    /**
     * Returns the width of the game board
     * @return width of board
     */
    int getWidth();

    /**
     * Returns the height of the game board
     * @return height of board
     */
    int getHeight();

    /**
     * Returns the value of a cell on the game board  //就是一个cell的值
     * @param location the location of the cell with row and column
     * @return value of cell on specified row and column
     */
    String getValueAt(gameinterface.Location location);

    /**
     * Method to fire a checkLocation on a cell on the game board   //这个应该是左键执行
     * @param location the location of the cell with row and column
     */
    void checkLocation(gameinterface.Location location);

    /**
     * Method to fire a flagLocation on a cell on the game board  //这个应该是右键插上flag
     * @param location the location of the cell with row and column
     */
    void flagLocation(gameinterface.Location location);
    /**
     * The number of actions so far   //被点了几次？  不明用意--the number of successful left-click performance
     * @return the number of clicks
     */
    int getNrOfActions();

    /**
     * The number of mines left uncovered by a flag   //还剩几个雷
     * @return the number of mines to discover
     */
    long getNrOfMinesLeft();

    /**
     * Method to indicate the game is lost (by clicking a mine)   //卧槽，点到雷了
     * @return true if the game is lost
     */
    boolean getLost();

    /**
     * Method to getValue a game board   //看起来像是最终结局用的东西。
     */
    default void printBoard() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                System.out.print(" " + getValueAt(new MyLocation(i,j)) + " ");
            }
            System.out.println();
        }
        System.out.println("nr of actions: " + getNrOfActions() + " :: mines to go: " + getNrOfMinesLeft());
    }
}
