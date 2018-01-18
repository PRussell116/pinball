import java.util.*;
import java.awt.*;

/**
 * Write a description of class pinball5 here.
 * 
 * pinball type 5
 * this ball grows insize when touching a ball
 * this ball changes direction randomly when touching a wall
 */
public class pinball5 extends pinball
{
    
    private int randX;
    private int randY;

    private boolean isBig;
    private String currentPoints;
    private Color validColor;

    private int ballDiffrenceX;
    private int ballDiffrenceY;

    /**
     * Constructor for class pinball5
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel the horizontal velocity of the object
     * @param yVel the vertical velocity of the object
     *  @param objectColor  the color of the object
     *  @param radius  the radius (in pixels) of the object
     *  @param theMachine  the machine this object is in
     */
    public pinball5 (int xPos, int yPos,int xVel, int yVel,Color objectColor, int radius, Machine machine)
    {

        super(xPos, yPos,xVel,yVel,objectColor, radius, machine);

        isBig = false;
    }

    /**
     * calls the move method in pinball class then modifies calling to other methods in pinball5
     */
    public void move()
    {

        randomDirection();
        super.move();
        this.wallBounce();
        this.ballBounce();
        currentPoints = String.valueOf(this.points);
        machine.drawPoints(currentPoints, this.currentXLocation, this.currentYLocation);
    }

    /**
     * method that is called when it hits a wall, chnages location randomly to appear that it
     * bounces off at a random angle 
     */
    public void wallBounce()
    {
        machine.eraseBall(this);
        if((currentXLocation <= (leftWallPosition + radius)))
        {
            currentXLocation = currentXLocation + randX;

        } 
        else if(currentXLocation >= (rightWallPosition - radius))
        {
            currentXLocation = currentXLocation - randX;
        } 

        else if(currentYLocation <= (topWallPosition + radius))
        {
            currentYLocation = currentYLocation + randY;
        }
        else if((currentYLocation >= bottomWallPosition - radius) && ((currentXLocation < lengthToGap)|| 
            (currentXLocation > rightWallPosition - lengthToGap)))

        {
            currentYLocation = currentYLocation - randY;

        }        

        machine.draw(this);
    }

    /**
     * method to decide the size of the random offset from 1 -> 50  
     */
    public void randomDirection()
    {
        Random rand = new Random();
        randX = rand.nextInt(50) + 1;
        randY = rand.nextInt(50) + 1;

    }

    /**
     * method to check if the balls are touching and if they are increase the size by 10% if the ball is not alrady big
     * if it is not big it is decreased by 10%
     * 
     * checks if the balls are overlapping and if move the balls inorder to counter act this by moving the balls
     */
    public void ballBounce()
    {
        machine.eraseBall(this);
        for(pinball currentBall:machine.pinballs)
        {
            //check if touching
            if( this.getRadius() + currentBall.getRadius() >= 
            Math.pow(Math.pow(this.currentXLocation - currentBall.getX(),2) + Math.pow(this.currentYLocation
                    - currentBall.getY(),2),0.5)
            && currentBall != this){

                //if not big get big + move
                if(isBig == false)
                {
                    // check if overlapping balls

                    int RadIncrease =(int) Math.round(this.radius * 0.10);
                    this.radius = this.radius + RadIncrease;
                    if(currentXLocation - currentBall.getX() < 0)
                    {
                        currentXLocation = currentXLocation - RadIncrease;

                    } 

                    else
                    {
                        currentXLocation = currentXLocation + RadIncrease;
                    }

                    if(currentYLocation - currentBall.getY()  < 0)
                    {
                        currentYLocation = currentYLocation - RadIncrease;
                    } 

                    else
                    {
                        currentYLocation = currentYLocation + RadIncrease;
                    }

                    
                    //check if overlapping bumpers

                    checkBumperOverLap();
                    isBig = true;

                }
                else
                {
                    int newRad =(int) Math.round(this.radius * 0.90);
                    this.radius = newRad;

                    isBig = false; 
                }
                // gets stuck if made bigger when near a bumper
            }
        }
        machine.draw(this);
    }

    /**
     * method to check if the ball that has grown is overlapping
     * if overlaping move the ball
     */
    private void checkBumperOverLap()
    {
        for(Bumper currentBumper:machine.bumpers)
        {
            if( this.getRadius() + currentBumper.getRadius() >= 
            Math.pow(Math.pow(this.currentXLocation - currentBumper.getX(),2) + Math.pow(this.currentYLocation
                    - currentBumper.getY(),2),0.5))
            {
                int RadIncrease =(int) Math.round(this.radius * 0.10);

                if(currentXLocation - currentBumper.getX() < 0)
                {
                    currentXLocation = currentXLocation - RadIncrease;

                } 

                else
                {
                    currentXLocation = currentXLocation + RadIncrease;
                }

                if(currentYLocation - currentBumper.getY()  < 0)
                {
                    currentYLocation = currentYLocation - RadIncrease;
                } 

                else
                {
                    currentYLocation = currentYLocation + RadIncrease;
                }

            }

        }

    }
}
