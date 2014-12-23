


package src.view;
public class DataOwnPlayer {

    public  static final int GAME_IN_USE = 0;
    public  static final int GAME_FINISHED = 1;

    private static final int HIGH = 10; // Number of max players in score array
   // private static int hitCounter = 0;
    private static int gameStatus  = GAME_FINISHED;
    private static Data player = null; // Current Player
    private static Data [] playersList = new Data[HIGH]; // Array of players
    private static int count = 0;
    private static int score = 0;

    private DataOwnPlayer() 
    {

    }
    
    public static Data[] getPlayersList()
    {
        return playersList;
    }

    public static void setPlayersList(Data[] playersList)
    {
        DataOwnPlayer.playersList = playersList;
    }

    private static DataOwnPlayer instance = new DataOwnPlayer();

    public static boolean isGameActive ()
    {       
        return (getGameStatus() == GAME_IN_USE);
    }
 
    public static int getGameStatus()
    {
        return gameStatus;
    }

    public static Data getPlayer()
    {
        return player;
    }

    public static void setPlayer(Data player)
    {
        DataOwnPlayer.player = player;
    }

   public static void setGameStatus(int gameStatus)
    {
        instance.gameStatus = gameStatus;
        if(instance.gameStatus == GAME_FINISHED)
            System.out.println(" Polu4il odin");
        else if(instance.gameStatus == GAME_IN_USE)
            System.out.println(" Polu4il null");
    }

 
    
     
    public static void startGame()
    {
        player = new Data();
        setGameStatus(GAME_IN_USE);
        //setHitCounter(hitCounter);
    }

    /**
     * This function save current player to the players array
     * If user has better score on the same picture this score will be replace the older one
     * but if user has played on different image this score will be written as new entry to the players array
     */
    public static void SavePlayer() {
        int i;
        boolean found = false;
        for (i=0; i < count ; i++) {
            if(playersList[i].getPlayerName().equals(player.getPlayerName()) &&
              (playersList[i].getNumImage() == player.getNumImage()))
              {
                found = true;
                
                if (playersList[i].getPlayerScore() <DataOwnPlayer.getInstance().getPlayer().getPlayerScore() )
                {
                    playersList[i] = player;
                    break;
                }
            }
        }

        if (i == HIGH) {
            playersList [i-1] = player;
        } else {
            if ((i < HIGH ) && (found==false)) {
                playersList [i] = player;
                count++;
            }
        }
        // Now we will sort playersArray by score
        BubbleSort();
    }

    /**
     * Bubble sort implementation
     */
    private static void  BubbleSort(){
        for (int i=0; i < count ; i++) {
            for (int j = 1; j < count - i ; j++) {
                if (playersList[j-1].getPlayerScore() < playersList[j].getPlayerScore()){
                    Data temp =  playersList[j];
                    playersList[j] = playersList[j-1];
                    playersList[j-1] = temp;
                }
            }
        }
    }

    public static void stopGame() {
        setGameStatus(GAME_FINISHED);
        //instance.hitCounter = 0;
    }


    

/*    public static int getHitCounter()
    {
        return hitCounter;
    }

    /**
     *
     * @param hitCounter - set hit counter. In this function we check if HitCounter is 0 .
     * HitCount = 0 it mean that game is over and we stopped the game.
     */
  /*  public static void setHitCounter(int hitCounter)
    {
        instance.hitCounter = hitCounter;
        if (instance.hitCounter == 0 ) {
            stopGame();
        }
    }
*/
    // return current instance this function take care of unique  DataOwnPlayer instance
     public static synchronized DataOwnPlayer getInstance()  {
        return instance;
     }
}


