import java.awt.*;
import java.util.*;
import java.math.*;
/**
 * class pinball 7, object in machine intheirts from pinball
 * cnages velocity when touching a wall between a given min and max
 * changes colour when colliding with a ball
 * 
 * @author 806925 
 * @version (a version number or a date)
 */
public class pinball7 extends pinball
{

    private int maxXVel;
    private int maxYVel;

    private int minXVel;
    private int minYVel;
    private int directionX;
    private int directionY;

    private String currentPoints;
    private int currentCol;
    private ArrayList<Color> vaildCols;
    /**
     * constructor for pinball 7
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param minXVel the minimum horizontal velocity of the object
     * @param minYVel the mimimum vertical velocity of the object
     * @param MaxXVel the maximum horizontal velocity of the object
     * @param MaxXVel the maximum vertical velocity of the object
     *  @param objectColor  the color of the object
     *  @param radius  the radius (in pixels) of the object
     *  @param theMachine  the machine this object is in
     */
    public pinball7(int xPos, int yPos,int minXVel, int minYVel ,int maxXVel, int maxYVel,Color objectColor, int radius, Machine machine)
    {
        super(xPos, yPos,minXVel,minYVel,objectColor, radius, machine);
        this.maxXVel = maxXVel;
        this.maxYVel = maxYVel;
        this.minXVel = minXVel;
        this.minYVel = minYVel;

        vaildCols = new ArrayList<Color>();
        vaildCols.add(Color.CYAN);
        vaildCols.add(Color.MAGENTA);
        vaildCols.add(Color.YELLOW);
        currentCol = 0;

        currentPoints = String.valueOf(this.points);
        machine.drawPoints(currentPoints, this.currentXLocation, this.currentYLocation);

    }

    /**
     * method move calls the move method in pinball then calls aditional functionality methods
     */
    public void move()
    {
        super.move();
        this.wallBounce();
        this.ballBounce();  
        currentPoints = String.valueOf(this.points);
        machine.drawPoints(currentPoints, this.currentXLocation, this.currentYLocation);
    }

    /**
     * checks if the ball is touching and wall and if it is calls method to 
     */
    public void wallBounce()
    {
        machine.eraseBall(this);
        if( (currentXLocation <= (leftWallPosition + radius))||
        (currentXLocation >= (rightWallPosition - radius))||
        (currentYLocation <= (topWallPosition + radius))||
        ( (currentYLocation >= bottomWallPosition - radius) && ( (currentXLocation < lengthToGap) || (currentXLocation > rightWallPosition - lengthToGap) ) )) {
            changeVel();
        }        

        machine.draw(this);
    }

    /**
     * chnages the velocity of the balls depending on thier direction and what speed they are currently at
     */
    public void changeVel()
    {

        //works but is shit
        if(this.velXTravel == maxXVel){
            this.velXTravel = minXVel;

        }
        else if(this.velXTravel == -maxXVel){
            this.velXTravel = -minXVel;
        }

        else if(this.velXTravel == minXVel){
            this.velXTravel = maxXVel;

        }
        else{
            this.velXTravel = -maxXVel;
        }

        if(this.velYTravel == maxYVel){
            this.velYTravel = minYVel;

        }
        else if(this.velYTravel == -maxYVel){
            this.velYTravel = -minYVel;
        }

        else if(this.velYTravel == minYVel){
            this.velYTravel = maxYVel;

        }
        else{
            this.velYTravel = -maxYVel;
        }
    }

    /**
     * checks if touching another ball and if it is call the change colour method
     */
    public void ballBounce(){
        machine.eraseBall(this);
        for(pinball currentBall:machine.pinballs){
            if( this.getRadius() + currentBall.getRadius() >= 
            Math.pow(Math.pow(this.currentXLocation - currentBall.getX(),2) + Math.pow(this.currentYLocation
                    - currentBall.getY(),2),0.5)
            && currentBall != this){

                changeCol();

            }

        }
        machine.draw(this);
    }

    /**
     * chnages the colour of the object using the index of the color arraylist
     */
    public void changeCol(){
        if(currentCol >= 2){
            currentCol = 0;
        }
        else{
            currentCol++;
        }

        this.colour = vaildCols.get(currentCol);

    }
}