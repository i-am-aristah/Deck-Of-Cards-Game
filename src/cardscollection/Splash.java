package cardscollection;
import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow
{
    // duration is integer value in milliseconds for how long the Splash screen is visible
    private final int duration;
    private JProgressBar loading = null; //progress bar
    private int progress;
    
    public Splash(int dur)
    {
        duration = dur;
        UIManager.put("ProgressBar.selectionForeground", Color.gray.darker());
       
        loading = new JProgressBar(0, 100);
        loading.setStringPainted(true);
      
    }

     
    public void showSplash()
    {
        JPanel content = (JPanel)getContentPane();
        content.setBackground(new Color(255,255,204));

        // Set the window's bounds, centering the window
        int width = 810;
        int height = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("src/Images/icon.jpg"));
        JLabel copyrt = new JLabel
            ("Copyright Composers Inc., 2020, Music Composers Database", 
                        JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(loading, BorderLayout.SOUTH);
        
        Color border = new Color(50, 20, 20, 55);
        content.setBorder(BorderFactory.createLineBorder(border, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try 
        {
            //Increment the progress bar's value to 100 starting from 0
            incProgress(20);
            Thread.sleep(duration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setVisible(false);
    }

    public void incProgress(int amount) //throws InterruptedException
    {
        //Instantiate and start new thread
        ProgressThread up = new ProgressThread(amount);
        up.thread.start();
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Class        ProgressThread
     * Description  Nested class that handles the progress bar
     * @author      <i>Niko Culevski</i>
     * Date         4/3/2020
     * History Log  7/18/2018
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    class ProgressThread 
    {
        private int amount;

        public ProgressThread(int amount)
        {
            this.amount = amount;
        }

        private Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                {
                    //Increment the progress bar until it's value hits 100
                    while (progress < 100) 
                    {
                        progress++;
                        loading.setString(String.valueOf(progress + "%"));
                        try 
                        {
                            Thread.sleep(30);
                        } 
                        catch (InterruptedException ex) 
                        {

                        }
                        loading.setValue(progress);
                    }
                }
            }
        });
    }
}