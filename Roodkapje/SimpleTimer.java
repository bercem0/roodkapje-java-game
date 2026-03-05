/**
 * Write a description of class SimpleTimer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimpleTimer  
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class SimpleTimer
     */
    private long lastMark = System.currentTimeMillis();  // Slaat de tijd op (in milliseconden) toen de timer gestart of gemarkeerd werd

    /**
     * Constructor voor SimpleTimer
     * Initialiseert de timer en zet de starttijd vast.
     */
    public SimpleTimer()
    {
        mark(); // Zet de starttijd op het huidige moment
    }

    /**
     * Geeft het aantal milliseconden terug dat verstreken is sinds de laatste markering.
     * 
     * @return Verstreken tijd in milliseconden sinds laatste mark
     */
    public int millisElapsed()
    {
        return (int) (System.currentTimeMillis() - lastMark);
    }
        
    /**
     * Geeft het aantal seconden terug dat verstreken is sinds de laatste markering.
     * 
     * @return Verstreken tijd in seconden sinds laatste mark
     */
    public int secondsElapsed()
    {
        return millisElapsed() / 1000; // Converteert milliseconden naar seconden
    }
        
    /**
     * Zet de markering op het huidige moment, om de timer opnieuw te starten.
     */
    public void mark() 
    {
        lastMark = System.currentTimeMillis(); // Slaat de huidige tijd op als nieuwe starttijd
    }
}


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
