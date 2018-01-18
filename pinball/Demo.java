// problems:-

//multip collision

// growing ball on bumper  -> check bumper + move

// comments + java documentation
// code quality
// restarting the demo 

import java.awt.*;
import java.util.*;
/**
 * Class to demonstrate functionality of the Pinball machine
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Demo
{
    private Machine machine;

    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
        machine = new Machine();
    }

    /**
     * Main part of the demo creates all the objects and then signals the machine to begin the game
     */
    public void main()
    {
        machine.resetMachine();

        Bumper bump1 = new Bumper(250,250,50,machine);
        Bumper bump2 = new Bumper(300,350,15,machine);
        Bumper bump3 = new Bumper(400,200,20,machine);

        Hole hole1 = new Hole(72,250,25,machine);
        Hole hole2 = new Hole(420,350,25,machine);
        Hole hole3 = new Hole(82,32,20,machine);

        pinball obj2 = new pinball7(250,50,-3, 1, -13, 11, Color.CYAN, 17,machine);
        pinball obj3 = new pinball7(450, 125, -1, -1,-15, -15, Color.YELLOW, 40, machine);
        pinball obj4 = new pinball7(100, 200, 2, -2,20, -20, Color.MAGENTA, 25, machine);

        pinball obj5 = new pinball5(500, 300, -1, -1, Color.BLUE, 35, machine);
        pinball obj6 = new pinball5(100, 250, 2, -2, Color.GREEN, 50, machine);
        pinball obj7 = new pinball5(500,30, 10,15, Color.RED, 17,machine);

        machine.beginGame();

    }
    /**
     * method to test the functionality of the holes with the balls
     */
    public void testHole()
    {
        machine.resetMachine();
        Hole hole1 = new Hole(250,250,15,machine);
        Hole hole2 = new Hole(400,250,50,machine);

        pinball obj2 = new pinball7(20,250,10, 20, 0, 10, Color.BLUE, 20,machine);

        machine.beginGame();
    }

    /**
     * method to test the functionality of the bumpers with the balls
     */
    public void testBumper()
    {
        machine.resetMachine();
        Bumper bump1 = new Bumper(250,250,100,machine);

        pinball obj2 = new pinball5(20,250,10,0, Color.BLUE, 20,machine);
        machine.beginGame();
    }

    /**
     * method to test the functionality gap and the points system
     */
    public void testPointsAndGap()
    {
        machine.resetMachine();
        Bumper bump1 = new Bumper(200,250,20,machine);

        pinball obj1 = new pinball5(300, 40, 0, 12, Color.RED, 10, machine);
        pinball obj2 = new pinball5(20,250,10,0, Color.BLUE, 20,machine);

        machine.beginGame();
    }
}
