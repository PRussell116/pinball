import java.awt.*;
import java.util.*;
import java.math.*;
/**
 * An object that exists in the pinball. The object bounces off
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 

 * 
 * @author 806925 
 */

public class PinballObject
{
    protected int currentXLocation;
    protected int currentYLocation;
    protected Color colour;
    protected int radius;
    protected Machine machine;

    protected final int leftWallPosition;
    protected final int rightWallPosition;
    protected final int topWallPosition;
    protected final int bottomWallPosition;

    protected final int lengthToGap;

    private double area;

    /**
     * Constructor for objects of class Pinball_Obj
     * 
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theMachine  the machine this object is in
     */
    public PinballObject(int xPos, int yPos, Color objectColor, int objectRadius, Machine theMachine)
    {
        currentXLocation = xPos;
        currentYLocation = yPos;
        colour = objectColor;
        radius = objectRadius;
        machine = theMachine;

        leftWallPosition = machine.getLeftWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTop();
        bottomWallPosition = machine.getBottom();
        lengthToGap = machine.getLengthToGap();

        area = Math.PI * radius * radius;

    }

    
    /**
     * return the horizontal position of this object
     */
    public int getX()
    {
        return currentXLocation;
    }

    /**
     * return the vertical position of this object
     */
    public int getY()
    {
        return currentYLocation;
    }

    /**
     * return the radius of this object
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * return the diameter of this object
     */
    public int getDiameter()
    {
        return 2*radius;
    }

    public double getArea()
    {
        return area;
    }

    /**
     * return the colour of this object
     */
    public Color getColor()
    {
        return colour;
    }

}
