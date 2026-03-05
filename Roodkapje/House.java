import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class House here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class House extends Actor
{
    /**
     * Act - do whatever the House wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        checkHouse(); // Controleer of speler het huis aanraakt
    }
    
    /**
     * Als de speler het huis raakt, stopt het spel en toont een overwinningsbericht met de verstreken tijd.
     */
      public void checkHouse()
    {
    if (isTouching(Person.class)) { // Controleer of het huis wordt geraakt door de speler
        MyWorld world = (MyWorld) getWorld(); // Verkrijg een referentie naar de wereld
        int time = world.timer.secondsElapsed(); // Haal de verstreken tijd op
        world.showText("Gewonnen! Time: " + time + "s", 300, 200); // Toon een winnend bericht met tijd
        Greenfoot.stop(); // Stop het spel
    }
    }
      
}
