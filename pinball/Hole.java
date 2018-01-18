import java.awt.*;

/**
 * A hole in the machine
 * hole will set the points of any ball that passes over it to zero
 * a hole will remove a ball which has a smaller area than it if the ball passes over it
 */
public class Hole extends PinballObject

{

   
    /**
     * Constructor for objects of class hole
     * @param xPos the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param objectRadius  the radius (in pixels) of the object
     * @param theMachine  the machine this object is in
     */
    public Hole(int xPos, int yPos, int radius, Machine machine)
    {
        super(xPos, yPos,Color.BLACK, radius, machine);
        machine.draw(this);

        
        machine.holes.add(this);
    }

    
}