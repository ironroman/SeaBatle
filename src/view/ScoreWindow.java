
package src.view;
import javax.swing.*;
import java.awt.*;


/**
 * 
 */
public class ScoreWindow extends JFrame
{

    private	JPanel		topPanel;
    private	JTable		table;
    private	JScrollPane scrollPane;

    /**
     *
     * @param playersList  - data to fill score table . Array of users
     */
 //************************************************************************************    
     
     
    
    
//**********************************************************************************    
   
    public ScoreWindow (Data [] playersList)
    {

        // Set the frame characteristics
        setTitle( "Players score" );
        setSize( 300, 200 );
        setBackground( Color.gray );

        // Create a panel to hold all other components
        topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );

        // Create columns names
        String columnNames[] = { "Player name", "Score", "Crop Level" };

        String dataValues[][] = new String[playersList.length][3];
        int length = playersList.length;
        for (int i = 0; i < length; i++ )
        {
            if (playersList[i] != null) {
                dataValues[i][0] = playersList[i].getPlayerName();
                dataValues[i][1] = new String(new Integer(playersList[i].getPlayerScore()).toString());
                dataValues[i][2] = new String(new Integer(playersList[i].getNumImage()).toString());
                
            } else {
                break;
            }
        }
        // Create a new table instance
        table = new JTable( dataValues, columnNames );
        // Add the table to a scrolling pane
        scrollPane = new JScrollPane( table );
        topPanel.add( scrollPane, BorderLayout.CENTER );

        setResizable(false);
        show();

    }
}
