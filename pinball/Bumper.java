import java.awt.*;

/**
 * class bumper ball will reverse when colliding with the bumper and gain 2 points
 */
public class Bumper extends PinballObject

{

    /**
     * Constructor for objects of class Bumper
     */
    public Bumper(int xPos, int yPos, int radius, Machine machine)
    {
        super(xPos, yPos,Color.GRAY, radius, machine);
        machine.draw(this);

        machine.bumpers.add(this);
    }

}
