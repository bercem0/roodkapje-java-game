import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public int score=0; // Huidige score van de speler
    public Label scoreBoard; // Label om de score weer te geven
    public Label timeLabel; // Label om de verstreken tijd te tonen
    public SimpleTimer timer = new SimpleTimer(); // Timer voor het meten van tijd

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(12, 12, 50); 
        prepare(); // Bereid de wereld voor door objecten toe te voegen
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        scoreBoard=new Label("Score:0" ,30); // Maak een scorebord label met tekst "Score:0"
        addObject(scoreBoard,8,0); // Voeg scorebord toe aan wereld op positie (8,0)
        
        // Definieer het doolhof: 1 betekent boom, 0 betekent vrije ruimte
        int [][]maze= 
            {
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            };
            
        // Loop door het doolhof-array en voeg bomen toe waar '1' staat
        for(int y=0; y<12; y++) {
            for(int x=0; x<12; x++) {
                if(maze[y][x] == 1){
                    addObject(new Tree(), x, y);  // Voeg boom toe op positie (x,y)
                }
            }
        }

        // Maak een label om tijd weer te geven
        timeLabel = new Label("Time: 0", 30);
        addObject(timeLabel, 6, 0); // Voeg tijdlabel toe aan de wereld
        
        timer.mark(); // Start de timer vanaf dit moment
        
        // Voeg appels toe op vaste posities
        Apple apple = new Apple();
        addObject(apple,9,1);
        Apple apple2 = new Apple();
        addObject(apple2,7,6);
        Apple apple3 = new Apple();
        addObject(apple3,0,9);
        Apple apple4 = new Apple();
        addObject(apple4,10,9);
        Apple apple5 = new Apple();
        addObject(apple5,4,0);
        
        // Voeg bloemen toe op vaste posities
        Flower flower = new Flower();
        addObject(flower,0,3);
        Flower flower2 = new Flower();
        addObject(flower2,11,3);
        Flower flower3 = new Flower();
        addObject(flower3,5,11);
        Flower flower4 = new Flower();
        addObject(flower4,2,6);
        Flower flower5 = new Flower();
        addObject(flower5,11,6);
        Flower flower6 = new Flower();
        addObject(flower6,0,11);
        Flower flower7 = new Flower();
        addObject(flower7,4,7);
        flower7.setLocation(7,4); // Verplaats bloem7 naar (7,4)
        flower7.setLocation(9,11); // Verplaats bloem7 opnieuw naar (9,11)
        
        // Voeg de speler toe aan de wereld op positie (5,4)
        Person person = new Person();
        addObject(person,5,4);
        person.setLocation(5,4); // Herhaal voor zekerheid
        person.setLocation(5,4);
    }

    /**
     * Voegt een bepaald aantal objecten toe van het type 'name' (bijv. flowers, apples, houses).
     *
     * @param name Naam van het objecttype als String
     * @param count Aantal objecten dat toegevoegd moet worden
     */
    public void addObjects(String name, int count) {
        int counter=count; // Teller voor toe te voegen objecten
        while(counter!=0)
        {
            int x=Greenfoot.getRandomNumber(12); // Willekeurige x-positie
            int y=Greenfoot.getRandomNumber(12); // Willekeurige y-positie
            
            // Check of er al objecten op deze plek staan
            List<Tree> trees=getObjectsAt(x, y, Tree.class);
            List<Flower> flowers=getObjectsAt(x, y, Flower.class);
            List<Apple> apples=getObjectsAt(x, y, Apple.class);
        
        if(trees.isEmpty() && flowers.isEmpty() && apples.isEmpty())
        {
            // Voeg het juiste object toe afhankelijk van de naam
            if(name.equals("flowers")){
                addObject(new Flower(),x,y);
            } else if (name.equals("apples")){
                addObject(new Apple(),x,y);
            } else if (name.equals("houses")){
                addObject(new House(),x,y);
            }
            counter--;  // Verminder de teller na toevoegen
        }
        }
    }
    
    /**
     * Update de score met een bepaalde waarde en beheer huizen op basis van de score.
     *
     * @param i Het aantal punten om de score mee te verhogen of verlagen
     */
    public void updateScore(int i)
    {
        score=score+i; // Verhoog of verlaag score
        scoreBoard.setValue("score:" +score);  // Update de score label
        
        if(score >= 3)
        {
            addObjects("houses",1); // Voeg een huis toe zodra score >= 3
        }else{
            // Verwijder alle huizen als score lager is dan 3
            List <House> h=getObjects(House.class);
            removeObjects(h);
        }
    }
    
    // Wordt elke act() cyclus aangeroepen.
    // Houdt de tijd bij en stopt het spel na 60 seconden.
    public void act() 
    {
        int elapsed = timer.secondsElapsed(); // Verkrijg verstreken tijd in seconden
        timeLabel.setValue("Time: " + elapsed); // Update tijd label
        
        if (elapsed > 60) { 
            showText("Verloren! Tijd op!", getWidth()/2, getHeight()/2); // Toon bericht
            Greenfoot.stop(); // Stop het spel
        }
    }

    
}
