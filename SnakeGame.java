/*  Execution: java SnakeGame
 *
 *  Runs the Snake Game using Snake, SnakeSegment, Board, Food
 *  and BonusFood classes. Added a title screen, bonus food, and
 *  high score features. 'b' is used to begin the game from the
 *  the title screen, and 'r' is used to restart the game from the
 *  end screen.
 *
 */
 
public class SnakeGame {
    // instance variables
    private static int highScore;

    public static void main(String[] args) {
        PennDraw.setCanvasSize(500, 500);
        PennDraw.enableAnimation(5);
        highScore = 0;
        Board board = new Board(highScore);
        board.setOnTitleScreen(true);

        // control flow, so listening is always on
        while (true) {
            // title screen
            while (board.getOnTitleScreen()) {
                board.displayTitleScreen();

                // check for if user starts game
                if (PennDraw.hasNextKeyTyped()) {
                    if (PennDraw.nextKeyTyped() == 'b') {
                        board.setOnTitleScreen(false);
                        board.setIsGameRunning(true);
                        board = new Board(highScore);
                    }
                }
            }
            
            // game running
            while (board.isGameRunning()) {
                PennDraw.clear(157, 193, 131);
                board.update();

                // update high score if necessary
                if (board.getScore() > highScore) {
                    highScore = board.getScore();
                    board.setHighScore(highScore);
                }

                board.draw();
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(7, 0, "High Score: " + highScore);
                PennDraw.advance();
            }
            
            // game over
            board.displayGameOver();

            // check for if user wants to play again
            if (PennDraw.hasNextKeyTyped()) {
                if (PennDraw.nextKeyTyped() == 'r') {
                    board.setOnTitleScreen(true);
                }
            }
        }
    }
}