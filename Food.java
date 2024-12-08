/*  Execution: N/A
 *
 *  Food.java defines the Food object that the Snake can enableAnimation
 *  to grow in length and earn a point.
 *
 */
 
public class Food {
    // instance variables
    private int xpos, ypos;
    private double radius;

    // constructor methods giving random board position
    public Food() {
        this.xpos = (int) (15 * Math.random());
        this.ypos = (int) (15 * Math.random());
        this.radius = 0.3;
    }

    /*
    * Description: draw the food on the board
    * Input: N/A
    * Output: PennDraw circle of the food
    */
    public void draw() {
        PennDraw.setPenColor(186, 0, 0);
        PennDraw.filledCircle(xpos, ypos, radius);
    }

    // getter methods
    public int getXPos() {
        return xpos;
    }
    
    public int getYPos() {
        return ypos;
    }
}