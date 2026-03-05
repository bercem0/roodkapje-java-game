import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flower extends Actor
{
    /**
     * Act - do whatever the Flower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        hitObject(); // Controleer op botsing met de speler
    }
    public void hitObject()
    {
        Actor object = getOneIntersectingObject(Flower.class); // Zoek naar botsing met andere flower
        if(object != null){
            getWorld().removeObject(object); // Verwijder deze bloem uit de wereld
            getWorldOfType(MyWorld.class).updateScore(1); // Verhoog de score met 1
        }
    }
}
