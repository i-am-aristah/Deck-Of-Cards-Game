package cardscollection;

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;


public class PrintUtilities implements Printable
{
    private final double SCALE_FACTOR = 0.7;
    private Component componentToBePrinted;

    public PrintUtilities(Component componentToBePrinted)
    {
        this.componentToBePrinted = componentToBePrinted;
    }

    public static void printComponent(Component c)
    {
        new PrintUtilities(c).print();
    }
 
    public void print() 
    {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog())
            try 
            {
                printJob.print();
            } 
            catch(PrinterException pe) 
            {
                System.out.println("Error printing: " + pe);
            }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  print()
     * Description  Creates an instance of the PrinterJob an calls its 
     *              setPrintable and print methods.
     * @param       g Graphics
     * @param       pageFormat PageFormat
     * @param       pageIndex int
     * @author      <i>Marty Hall</i>
     * Date         4/3/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
    {
        if (pageIndex > 0) 
        {
            return(NO_SUCH_PAGE);
        } else 
        {
            //pageFormat.setPaper(new Paper());
//            Paper myPaper = new Paper();
//            myPaper.setSize(576, 828);
//            myPaper.setImageableArea(0, 0, 828, 576);
//            pageFormat.setOrientation(PageFormat.LANDSCAPE);
//            pageFormat.setPaper(myPaper);
           
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(pageFormat.getImageableX(), 
                    pageFormat.getImageableY());
            g2d.scale(SCALE_FACTOR,SCALE_FACTOR); 
            
            disableDoubleBuffering(componentToBePrinted);
            componentToBePrinted.paint(g2d);
            enableDoubleBuffering(componentToBePrinted);
            return(PAGE_EXISTS);
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       disableDoubleBuffering()
     * Description  The speed and quality of printing suffers dramatically if
     *              any of the containers have double buffering turned on. So 
     *              this turns if off globally.
     * @see         enableDoubleBuffering
     * @param       c Component
     * @author      <i>Marty Hall</i>
     * Date         4/3/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public static void disableDoubleBuffering(Component c) 
    {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    /** Re-enables double buffering globally.
       * @param c  Component*/
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       enableDoubleBuffering 
     * Description  Re-enables double buffering globally.         
     * @param       c Component
     * @author      <i>Marty Hall</i>
     * Date         4/3/2020
     * History Log  7/18/2018  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public static void enableDoubleBuffering(Component c) 
    {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
}