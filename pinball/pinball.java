
import java.util.*;
import java.awt.*;
import java.math.*;
/**
 *Class bumper object in machine that balls bounce off of and get +2 points
 * 
 */
public class pinball extends PinballObject
{
    // instance variables - replace the example below with your own
    protected int velXTravel;
    protected int velYTravel;

    private int ballRConstantX;
    private int bumperRConstantX;
    private int ballRConstantY;
    private int bumperRConstantY;

    private boolean inHole;
    protected int points;
    /**
     * Constructor for objects of class Bumper
     * 
     * @param xPos the horizontal coordiante of the object
     * @param yPos the vertical coordiante of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theMachine  the machine this object is in
     */
    public pinball(int xPos, int yPos,int xVel, int yVel,Color objectColor, int radius, Machine machine)
    {
        super(xPos, yPos,objectColor, radius, machine);

        currentXLocation = xPos; 
        currentYLocation = yPos;
        velYTravel = yVel;
        velXTravel = xVel;

        machine.pinballs.add(this);
        machine.draw(this);
        points = 0;
    }

    /**
     * method to move the ball, the ball checks for other objects and 
     * then has its points drawn on it after it moves
     */
    public void move()
    {
        // remove from universe at the current position
        machine.eraseBall(this);

        // compute new position
        currentYLocation += velYTravel;
        currentXLocation += velXTravel;

        this.checkWalls();
        this.checkBumpers();
        this.checkHoles();
        this.checkBalls();
        this.checkInGap();

        machine.draw(this);
        String currentPoint =String.valueOf(this.points);

        machine.drawPoints(currentPoint, this.currentXLocation, this.currentYLocation);
    }

    /**
     * method for the ball to check if it is touching the walls
     * add 1 point if touching wall

     */
    public void checkWalls(){
        // check if it has hit the leftwall
        if(currentXLocation <= (leftWallPosition + radius)) 
        {
            currentXLocation = leftWallPosition + radius;
            velXTravel = -velXTravel; 
            points = points + 1;
        }

        //check if it has hit right wall
        if(currentXLocation >= (rightWallPosition - radius)) 
        {
            currentXLocation = rightWallPosition - radius;
            velXTravel = -velXTravel; 
            points = points + 1;
        }

        //check if it has hit topwall
        if(currentYLocation <= (topWallPosition + radius)) 
        {
            currentYLocation = topWallPosition + radius;
            velYTravel = -velYTravel; 
            points = points + 1;
        }

        //check if it has hit bottom wall and is not in the gap
        if( (currentYLocation >= bottomWallPosition - radius) && ((currentXLocation < lengthToGap) || (currentXLocation > rightWallPosition - lengthToGap)) ) 
        {
            currentYLocation = bottomWallPosition - radius;
            velYTravel = -velYTravel; 
            points = points + 1;
        }

       

    }

    /**
     * method to check if the balls are touching any of the items stored in machine's arraylist bumpers
     * add 2 points if touching
     * reverse speed if touching
     */
    public void checkBumpers()
    {
        for(PinballObject currentBumper:machine.bumpers)
        {

            if(this.getRadius() + currentBumper.getRadius() >= Math.pow(Math.pow(this.currentXLocation - currentBumper.getX(),2) + Math.pow(this.currentYLocation - currentBumper.getY(),2),0.5))
            {
                velXTravel = -velXTravel;
                velYTravel = -velYTravel; 
                points = points + 2;
            }

        }
    }

    /**
     * method to check if the balls are touching any of the items stored in machine's arraylist holes
     * set points to 0
     * set in hole to true if the area of the ball is less than that of the hole (will get removed)
     */
    public void checkHoles()
    {
        for(PinballObject currentHole:machine.holes)
        {

            if( this.getRadius() + currentHole.getRadius() >= 
            Math.pow(Math.pow(this.currentXLocation - currentHole.getX(),2) + Math.pow(this.currentYLocation
                    - currentHole.getY(),2),0.5))
            {  

                points = 0;

                if(this.getRadius()*this.getRadius()*Math.PI < currentHole.getRadius() * currentHole.getRadius() * Math.PI)
                {

                    inHole = true;

                }

            }

        } 

    }

    /**
     * method to check if the balls are touching any of the items stored in machine's arraylist pinballs
     * increase points by 5
     * reverse speeds
     */   
    public void checkBalls()
    {
        for(pinball currentBall:machine.pinballs)
        {
            if(this.getRadius() + currentBall.getRadius() >= 
            Math.pow(Math.pow(this.currentXLocation - currentBall.getX(),2) 
                + Math.pow(this.currentYLocation - currentBall.getY(),2),0.5)
            && currentBall != this)
            {
                velXTravel = -velXTravel;
                velYTravel = -velYTravel;
                points = points + 5;
            }
        }
    }

    /**
     * method to check if the ball is the the gap 
     * if in the gap machine ends the game
     * 
     */
    public void checkInGap()
    {
        if( (currentXLocation > lengthToGap) && (currentXLocation < rightWallPosition - lengthToGap) && (currentYLocation > bottomWallPosition - radius) )
        {
            machine.endGame();
        }

    }

    public int getPoints()
    {
        return points;
    }

    public boolean getIfInHole()
    {
        return inHole;
    }
}
