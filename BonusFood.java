/*  Execution: N/A
 *
 *  BonusFood.java defines the bonus food objects that can begin
 *  consumed by the Snake during the game, to grow in length and
 *  earn extra points.
 *
 */

public class BonusFood {
    // instance and final variables
    // time represents the random 'time' from the bonusFoodCount
    // 'timer' in Board.java at which the bonus food is to
    // be drawn
    private int xpos, ypos, time;
    final double RADIUS = 0.2;

    // constructor method
    public BonusFood() {
        xpos = (int) (15 * Math.random());
        ypos = (int) (15 * Math.random());
        time = (int) (40 * Math.random());
    }

    /*
    * Description: draw the bonus food on the board
    * Input: N/A
    * Output: PennDraw circle of the bonus food
    */
    public void draw() {
        PennDraw.setPenColor(128, 0, 128);
        PennDraw.filledCircle(xpos, ypos, RADIUS);
    }

    // getter methods
    public int getTime() {
        return time;
    }
    
    public int getXPos() {
        return xpos;
    }

    public int getYPos() {
        return ypos;
    }
}