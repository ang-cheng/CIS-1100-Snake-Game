/*  Execution: N/A
 *
 *  The Board class incorporates all parts of the game, and
 *  is mainly responsible for the execution of SnakeGame.java.
 *
 */

import java.util.ArrayList;

public class Board {
    // instance variables
    private Snake snake;
    private Food food;
    private BonusFood bonusFood;
    private char direction;
    private boolean isGameRunning, onTitleScreen;

    // bonusFoodCount functions as 'real time'
    private int score, bonusFoodCount, highScore;

    /*
    * Description: create a new board for when the game restarts
    *              but keep track of previous high scores
    * Input: high score from previous games if applicable
    * Output: board for the starting of a new game
    */
    public Board(int highScore) {
        PennDraw.setScale(-0.5, 14.5);
        snake = new Snake();
        food = new Food();
        bonusFood = new BonusFood();
        score = 0;
        bonusFoodCount = 0;
        this.highScore = highScore;
        isGameRunning = true;
        onTitleScreen = false;

        // make sure that food does not overlap with snake head
        while (snake.getHead().getXPos() == food.getXPos() || 
                    snake.getHead().getYPos() == food.getYPos()) {
            food = new Food();
        }
    }

    /*
    * Description: checks for if the player changes the direction
    *              of the snake (accounts for reverse direction) and
    *              updates the snake object accordingly. checks if the
    *              snake has eaten the food/bonus food, and adds to snake
    *              length and score if eaten. additionally, if either food
    *              has been eaten, it will create a new food to replace it.
    * Input: N/A
    * Output: change snake direction, add snake length, increase score,
    *         and replace food if food is eaten
    */
    public void update() {
        // check for player direction change
        if (PennDraw.hasNextKeyTyped()) {
            char key = PennDraw.nextKeyTyped();
            
            if (direction == 'w') {
                // account for reverse direction
                if (key != 's') {
                    direction = key;
                    snake.update(direction);
                }
            } else if (direction == 's') {
                // account for reverse direction
                if (key != 'w') {
                    direction = key;
                    snake.update(direction);
                }
            } else if (direction == 'a') {
                // account for reverse direction
                if (key != 'd') {
                    direction = key;
                    snake.update(direction);
                }
            } else if (direction == 'd') {
                // account for reverse direction
                if (key != 'a') {
                    direction = key;
                    snake.update(direction);
                }
            } else {
                direction = key;
                snake.update(direction);
            }
        } else { // if no new key pressed, continue in original direction
            snake.update(direction);
        }

        // snake eats food
        if (snake.getHead().getXPos() == food.getXPos() && 
                    snake.getHead().getYPos() == food.getYPos()) {
            score++;
            ArrayList<SnakeSegment> snakeArray = snake.getSnakeArray();

            // create new food, make sure it is not on the snake chain
            for (int i = 0; i < snakeArray.size(); i++) {
                while (snakeArray.get(i).getXPos() == food.getXPos() || 
                            snakeArray.get(i).getYPos() == food.getYPos()) {
                    food = new Food();
                }
            }
            
            // increase snake chain length
            snake.addSegment();
        }

        // snake eats bonus food
        if (snake.getHead().getXPos() == bonusFood.getXPos() && 
                    snake.getHead().getYPos() == bonusFood.getYPos()) {
            // reset 'timer' and add extra points
            bonusFoodCount = 0;
            score += 3;
            ArrayList<SnakeSegment> snakeArray = snake.getSnakeArray();

            // create new bonus food, make sure it is not on the snake chain
            for (int i = 0; i < snakeArray.size(); i++) {
                while (snakeArray.get(i).getXPos() == bonusFood.getXPos() || 
                        snakeArray.get(i).getYPos() == bonusFood.getYPos()) {
                    bonusFood = new BonusFood();
                }
            }

            // increase snake chain length
            snake.addSegment();
        }

        // draw updated score
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(7, 14, "Score: " + score);
    }

    /*
    * Description: draw the snake, food, and bonus food
    * Input: N/A
    * Output: draw snake/food/bonus food
    */
    public void draw() {
        snake.draw();
        food.draw();

        // if the bonus food's random time condition meets the timer, 
        // draw bonus food. else, increment timer until it meets
        // the bonus food's random time.
        if (bonusFoodCount == bonusFood.getTime()) {
            bonusFood.draw();
        } else {
            bonusFoodCount++;
        }
    }
    
    /*
    * Description: if the snake is out of bounds or has run into
    *              itself, the game is over
    * Input: N/A
    * Output: boolean representing if the game has ended
    */
    public boolean isGameRunning() {
        ArrayList<SnakeSegment> snakeArray = snake.getSnakeArray();
        SnakeSegment head = snake.getHead();

        // if snake is out of bounds
        if (head.getXPos() < 0 || head.getXPos() > 14) {
            isGameRunning = false;
        }
        if (head.getYPos() < 0 || head.getYPos() > 14) {
            isGameRunning = false;
        }

        // if snake runs into itself
        for (int i = 3; i < snakeArray.size(); i++) {
            SnakeSegment currentSegment = snakeArray.get(i);
            if (head.getXPos() == currentSegment.getXPos() && 
                    head.getYPos() == currentSegment.getYPos()) {
                isGameRunning = false;
            }
        }

        return isGameRunning;
    }

    /*
    * Description: show end screen with high score and score
    * Input: N/A
    * Output: PennDraw screen with stats and directions to restart
    */
    public void displayGameOver() {
        PennDraw.clear(157, 193, 131);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(7, 8, "Your High Score: " + highScore);
        PennDraw.text(7, 7, "Your Score: " + score);
        PennDraw.text(7, 6, "Click r to restart.");
        PennDraw.advance();
    }

    /*
    * Description: show title screen with high score
    * Input: N/A
    * Output: PennDraw screen with high score and directions to start
    */
    public void displayTitleScreen() {
        PennDraw.clear(157, 193, 131);
        PennDraw.text(7, 10, "Welcome to Snake!");
        PennDraw.text(7, 7, "High Score: " + highScore);
        PennDraw.text(7, 4, "Click b to begin.");
        PennDraw.advance();
    }

    // getter methods
    public int getScore() {
        return score;
    }

    public boolean getOnTitleScreen() {
        return onTitleScreen;
    }

    // setter methods
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setOnTitleScreen(boolean onTitleScreen) {
        this.onTitleScreen = onTitleScreen;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
}
