import java.awt.*;
import java.util.*;

/**
 * A pinball machine, with a sample demo
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Machine
{
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    private int bottomEdge;
    private int rightEdge;
    private int lengthToGap;        // the distance between the edge of the machine and the start of the gap
    private int gapWidth = 50;

    protected ArrayList<Bumper> bumpers;
    protected ArrayList<Hole> holes;
    protected ArrayList<pinball> pinballs;
    private boolean gameOver;
    private int totalScore;
    private String victoryLine;
    /**
     * Create a machine with default name and size
     */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();

        bumpers = new ArrayList<Bumper>();
        holes = new ArrayList<Hole>();
        pinballs = new ArrayList<pinball>();
        gameOver = false;

    }

    /**
     *  Create a machine with given name and size
     *  @param name The name to give the machine
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
    public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        lengthToGap = (rightEdge / 2) - gapWidth;
        drawMachine();
    }

    /**
     * Erase a PinballObject from the view of the pinball machine
     * 
     * @param pinballObj The object to be erased
     */
    public void erase(PinballObject pinballObj)
    {
        machine.eraseCircle(pinballObj.getX() - pinballObj.getRadius(), pinballObj.getY()- pinballObj.getRadius(), pinballObj.getDiameter());
    }

    public void eraseBall(pinball ball)
    {
        machine.eraseCircle(ball.getX() - ball.getRadius(), ball.getY()- ball.getRadius(), ball.getDiameter());

        // added so that if it overlaps nothing is erased
        drawMachine();
        for(PinballObject currentBumper:bumpers){
            draw(currentBumper);
        }
        for(PinballObject currentHole:holes){
            draw(currentHole);
        }
    }

    /**
     * Draw an PinballObject at its current position onto the view of the pinball machine
     * 
     * @param pinballObj The object to be drawn
     */
    public void draw(PinballObject pinballObj)
    {
        machine.setForegroundColor(pinballObj.getColor());
        machine.fillCircle(pinballObj.getX() - pinballObj.getRadius(), pinballObj.getY() - pinballObj.getRadius(), pinballObj.getDiameter());
    }

    /**
     * Draw the edge of the pinball machine 
     */
    public void drawMachine()
    {
        machine.setForegroundColor(Color.DARK_GRAY);

        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge

        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }

    /**
     * Return the edge of the left-hand wall
     */
    public int getLeftWall()
    {
        return leftEdge + 10;
    }

    public int getRightWall()
    {
        return rightEdge - 10;
    }

    public int getTop(){
        return topEdge + 10;
    }

    public int getBottom(){
        return bottomEdge - 10;

    }

    public int getLengthToGap(){
        return lengthToGap;

    }

    /**
     * Introduces a small delay in ball movement, for smooth running
     */

    public void pauseMachine()
    {
        machine.wait(50);
    }

    /**
     * Resets the machine back to initial view, with no pinballs
     */
    public void resetMachine()
    {
        machine.erase();

        drawMachine();
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void endGame()
    {
        gameOver = true;
        for(pinball currentPinball:pinballs){
            totalScore = totalScore + currentPinball.getPoints();

        }

        
        victoryLine = "You have scored " + totalScore + " points";
        machine.drawString(victoryLine, rightEdge / 2 , bottomEdge / 2);
    }

    public void drawPoints(String points, int xPos, int yPos)
    {
        machine.setForegroundColor(Color.WHITE);
        machine.drawString(points, xPos, yPos);

    }

    public void beginGame()
    {
        gameOver = false;
        while (isGameOver() == false){
            pauseMachine();
            // small delay

            Iterator<pinball> it = pinballs.iterator();
            while(it.hasNext()){

                pinball currentPinball = it.next();
                currentPinball.move();
                if(currentPinball.getIfInHole() == true){
                    pinballs.remove(currentPinball);
                    erase(currentPinball);
                    break;
                }

            }
        }
    }
}