/*  Execution: N/A
 *
 *  SnakeSegment objects of this class make up the Snake object.
 *
 */

public class SnakeSegment {
    // instance variables
    private int xpos, ypos;
    private double radius;

    // constructor method
    public SnakeSegment(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.radius = 0.4;
    }

    /*
    * Description: draw an individual snake segment
    * Input:  N/A
    * Output: PennDraw blue circle representing snake segment
    */
    public void draw() {
        PennDraw.setPenColor(8, 96, 168);
        PennDraw.filledCircle(xpos, ypos, radius);
    }

    // methods that will be used by the head segment to
    // move the snake in the indicated directions
    public void moveUp() {
        this.ypos++;
    }
    
    public void moveDown() {
        this.ypos--;
    }

    public void moveRight() {
        this.xpos++;
    }

    public void moveLeft() {
        this.xpos--;
    }

    // setter method
    public void setNewPos(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    // getter methods
    public int getXPos() {
        return xpos;
    }

    public int getYPos() {
        return ypos;
    }
}