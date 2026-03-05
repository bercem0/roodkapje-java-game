import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
{
    // Variabelen om te controleren of een toets al is ingedrukt (voor enkelvoudige beweging)
    private boolean upPressed = false; // True als de "up" toets is ingedrukt, voorkomt herhaald bewegen
    private boolean downPressed = false; // True als de "down" toets is ingedrukt, voorkomt herhaald bewegen
    private boolean leftPressed = false; // True als de "left" toets is ingedrukt, voorkomt herhaald bewegen
    private boolean rightPressed = false; // True als de "right" toets is ingedrukt, voorkomt herhaald bewegen

    public void act() {
        // Controleer of het object (appel of bloem) wordt geraakt en handel dit af
        hitObject();

        // Haal de huidige positie van de persoon op
        int oldX = getX(); // Huidige X-positie opslaan
        int oldY = getY(); // Huidige Y-positie opslaan

        // Nieuwe positie initialiseren met de oude positie (standaard geen beweging)
        int newX = oldX; // Nieuwe X-positie begint gelijk aan huidige positie
        int newY = oldY; // Nieuwe Y-positie begint gelijk aan huidige positie

        // Controleer of de "up" toets is ingedrukt
        if (Greenfoot.isKeyDown("up")) {
            if (!upPressed) {  // Alleen bewegen bij de eerste keer indrukken
                newY = oldY - 1;
                upPressed = true;
            }
        } else {
            upPressed = false;  // Reset als toets losgelaten is
        }

        // Controleer of de "down" toets is ingedrukt
        if (Greenfoot.isKeyDown("down")) {
            if (!downPressed) {
                newY = oldY + 1;
                downPressed = true;
            }
        } else {
            downPressed = false;
        }

        // Controleer of de "left" toets is ingedrukt
        if (Greenfoot.isKeyDown("left")) {
            if (!leftPressed) {
                newX = oldX - 1;
                leftPressed = true;
            }
        } else {
            leftPressed = false;
        }

        // Controleer of de "right" toets is ingedrukt
        if (Greenfoot.isKeyDown("right")) {
            if (!rightPressed) {
                newX = oldX + 1;
                rightPressed = true;
            }
        } else {
            rightPressed = false;
        }

        // Controleer of de nieuwe positie buiten het wereldraster valt
        if (newX < 0 || newX > 11 || newY < 0 || newY > 11) {
            System.out.println("Rand bereikt op positie: " + newX + ", " + newY);
            return;  // Beweging niet uitvoeren
        }

        // Controleer of er een boom is op de nieuwe positie
        Actor tree = getOneObjectAtOffset(newX - oldX, newY - oldY, Tree.class);
        if (tree != null) {
            System.out.println("Boom geraakt op positie: " + newX + ", " + newY);
            return;  // Beweging niet uitvoeren
        }

        // Controleer of er een huis is op de nieuwe positie (persoon mag het huis niet binnengaan)
        Actor house = getOneObjectAtOffset(newX - oldX, newY - oldY, House.class);
        if (house != null) {
            System.out.println("Proberen het huis binnen te gaan, beweging gestopt.");
            return;  // Beweging niet uitvoeren
        }

        // Als er geen obstakels zijn, verplaats de persoon naar de nieuwe locatie
        if (newX != oldX || newY != oldY) {
            setLocation(newX, newY);
        }
    }

    // Methode om te controleren of de persoon een appel of bloem raakt en deze op te eten
    public void hitObject() {
        Actor apple = getOneIntersectingObject(Apple.class); //Zoekt naar een object van het type Apple dat de persoon raakt.
        if (apple != null) { //Als er een appel geraakt wordt...
            getWorld().removeObject(apple); //verwijder de appel uit de wereld.
            getWorldOfType(MyWorld.class).updateScore(-1); //verlaag de score met 1 punt (appel is negatief).
            System.out.println("Appel gegeten!"); //Toon een bericht in de console dat de appel is opgegeten.
        }

        Actor flower = getOneIntersectingObject(Flower.class); // Zoekt naar een object van het type Flower dat de persoon raakt.
        if (flower != null) { // Als er een bloem geraakt wordt...
            getWorld().removeObject(flower); //verwijder de bloem uit de wereld.
            getWorldOfType(MyWorld.class).updateScore(1); //verhoog de score met 1 punt (bloem is positief).
            System.out.println("Bloem gegeten!"); //Toon een bericht in de console dat de bloem is opgegeten.
        }
    }
}
