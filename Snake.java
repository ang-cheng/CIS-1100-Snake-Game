/*  Execution: N/A
 *
 *  Represents the Snake object in the SnakeGame class. The Snake object
 *  consists of an ArrayList of SnakeSegment objects.
 *
 */

import java.util.ArrayList;

public class Snake {
    // instance variables
    private SnakeSegment head;
    private ArrayList<SnakeSegment> snake;

    // constructor method
    public Snake() {
        snake = new ArrayList<SnakeSegment>();
        head = new SnakeSegment(3, 7);
        snake.add(head);
    }

    /*
    * Description: controls the movement of the snake
    *              according to the user keyboard input
    * Input:  the direction that the player wants to move in
    * Output: updates the xpos and ypos of all snake pieces
    */
    public void update(char direction) {
        // move snake segments that are NOT the head
        int prevXPos = snake.get(0).getXPos();
        int prevYPos = snake.get(0).getYPos();
        for (int i = 1; i < snake.size(); i++) {
            SnakeSegment thisSegment = snake.get(i);
            int tempXPos = thisSegment.getXPos();
            int tempYPos = thisSegment.getYPos();
            thisSegment.setNewPos(prevXPos, prevYPos);
            prevXPos = tempXPos;
            prevYPos = tempYPos;
        }

        // move the snake's head in the user input direction
        if (direction == 'w') {
            head.moveUp();
        } else if (direction == 'a') {
            head.moveLeft();
        } else if (direction == 's') {
            head.moveDown();
        } else if (direction == 'd') {
            head.moveRight();
        }
    }

    /*
    * Description: draw all of the segments of the snake
    * Input:  N/A
    * Output: PennDraw representation of the snake
    */
    public void draw() {
        //iterate through snake segments and draw each segment
        for (int i = 0; i < snake.size(); i++) {
            SnakeSegment current = snake.get(i);
            current.draw();

            // indicate which segment is the head
            if (i == 0) {
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.filledCircle(current.getXPos(), current.getYPos(), 0.15);
            }
        }

    }

    /*
    * Description: add a segment to end of the snake chain
    * Input:  N/A
    * Output: add a new snake segment to where to the last segment was
    */
    public void addSegment() {
        SnakeSegment lastSegment = snake.get(snake.size() - 1);
        SnakeSegment newSegment = new SnakeSegment(lastSegment.getXPos(), 
                                            lastSegment.getYPos());
        snake.add(newSegment);
    }

    // getter methods
    public ArrayList<SnakeSegment> getSnakeArray() {
        return snake;
    }

    public SnakeSegment getHead() {
        return head;
    }
}
