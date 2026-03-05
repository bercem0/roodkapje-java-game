import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Actor
{
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        hitApple(); // Controleer of de appel geraakt wordt
    }
    
    /**
     * Controleert of dit appel-object een ander appel-object raakt.
     * Als dat zo is, verwijder het object en trek 1 punt van de score af.
     */
      public void hitApple()
    {
        Actor object = getOneIntersectingObject(Apple.class); // Zoek naar botsing met andere appel
        if(object != null){
            getWorld().removeObject(object); // Verwijder de appel uit de wereld
            getWorldOfType(MyWorld.class).updateScore(-1); // Verlaag de score met 1
        }
    }
}
