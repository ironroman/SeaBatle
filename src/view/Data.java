
package src.view;
import javax.swing.*;

/*
 * Player class has three properties
 * 1. Player name
 * 2. Player score
 * 3. Image ID
 */
public class Data
{

    private String NameOfPlayer = null;
    private int NumImage = -1;
    private int score = 0;
    

// Constructors by default
    Data ()
    {

    }
// Constructor with Name, Number of Image and Score
    Data (String name, int NumImage, int score )
    {
        this.NumImage = NumImage;
        this.NameOfPlayer  = name;
        this.score  = score;
    }
       
     //**************************************************************************
/*     
   
 */    
     //*****************************************************************************
        

    
    
    
    public String getPlayerName()
    {
        return NameOfPlayer;
    }

    public void setPlayerName(String NameOfPlayer)
    {
        this.NameOfPlayer = NameOfPlayer;
    }

    public int getNumImage()
    {
        return NumImage;
    }

    public void setNumImage(int NumImage)
    {
        this.NumImage = NumImage;
    }
    

    public int getPlayerScore()
    {
        return score;
    }

    public void setPlayerScore(int score)
    {
        this.score = score;
    }

    
    
}
