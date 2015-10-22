package util;

import java.awt.BasicStroke;                                                                       
import java.awt.BorderLayout;                                                                      
import java.awt.Color;                                                                             
import java.awt.Font;                                                                              
import java.awt.event.ActionEvent;                                                                 
import java.awt.event.ActionListener;                                                              
import java.awt.event.WindowAdapter;                                                               
import java.awt.event.WindowEvent;                                                                 
                                                                                                   


import javax.swing.BorderFactory;                                                                  
import javax.swing.JFrame;                                                                         
import javax.swing.JPanel;                                                                         
import javax.swing.Timer;                                                                          
                                                                                                   


import org.jfree.chart.ChartPanel;                                                                 
import org.jfree.chart.JFreeChart;                                                                 
import org.jfree.chart.axis.DateAxis;                                                              
import org.jfree.chart.axis.NumberAxis;                                                            
import org.jfree.chart.plot.XYPlot;                                                                
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;                                         
import org.jfree.data.time.Millisecond;                                                            
import org.jfree.data.time.TimeSeries;                                                             
import org.jfree.data.time.TimeSeriesCollection;                                                   
import org.jfree.ui.RectangleInsets;                                                               
                                                                                                   
public class MemoryUsage extends JPanel {                                                          
                                                                                                   
    class DataGenerator extends Timer implements ActionListener {                                  
                                                                                                   
        /*                                                                                         
         * Invoked when an action occurs.                                                          
         *                                                                                         
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)          
         */                                                                                        
        public void actionPerformed(ActionEvent actionevent) {                                     
            long free = Runtime.getRuntime().freeMemory();                                      
            long total = Runtime.getRuntime().totalMemory();                                       
            addTotalObservation(total);                                                            
            addFreeObservation(free);                                                              
        }                                                                                          
                                                                                                   
        DataGenerator(int i) {                                                                     
            super(i, null);                                                                        
            addActionListener(this);                                                               
        }                                                                                          
    }                                                                                              
                                                                                                   
	@SuppressWarnings("deprecation")
	public MemoryUsage(int maxItemAge) {                                                           
        super(new BorderLayout());                                                                 
        total = new TimeSeries("Total Memory",org.jfree.data.time.Millisecond.class);                                            
        total.setMaximumItemAge(maxItemAge);                                                       
        free = new TimeSeries("Free Memory",org.jfree.data.time.Millisecond.class);                                            
        free.setMaximumItemAge(maxItemAge);                                                        
                                                                                                   
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();                    
        timeseriescollection.addSeries(total);                                                     
        timeseriescollection.addSeries(free);                                                      
                                                                                                   
        DateAxis dateaxis = new DateAxis("Time");                                                  
        NumberAxis numberaxis = new NumberAxis("Memory");                                          
        dateaxis.setTickLabelFont(new Font("SansSerif", 0, 12));                                   
        numberaxis.setTickLabelFont(new Font("SansSerif", 0, 12));                                 
        dateaxis.setLabelFont(new Font("SansSerif", 0, 14));                                       
        numberaxis.setLabelFont(new Font("SansSerif", 0, 14));                                     
        dateaxis.setAutoRange(true);                                                               
        dateaxis.setLowerMargin(0.0D);                                                             
        dateaxis.setUpperMargin(0.0D);                                                             
        dateaxis.setTickLabelsVisible(true);                                                       
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());                      
                                                                                                   
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(                
                true, false);                                                                      
        xylineandshaperenderer.setSeriesPaint(0, Color.RED); // 改变第一个Series的颜色             
        xylineandshaperenderer.setSeriesPaint(1, Color.YELLOW);// 改变第二个Series的颜色           
        xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(1F, 0, 2));                      
        xylineandshaperenderer.setSeriesStroke(1, new BasicStroke(1F, 0, 2));                      
                                                                                                   
        XYPlot xyplot = new XYPlot(timeseriescollection, dateaxis, numberaxis, xylineandshaperenderer);                                                           
        xyplot.setBackgroundPaint(Color.BLACK); // 改变背景颜色                                    
        xyplot.setDomainGridlinePaint(Color.white);                                                
        xyplot.setRangeGridlinePaint(Color.white);                                                 
        xyplot.setAxisOffset(new RectangleInsets(1D, 1D, 1D, 1D));                                 
                                                                                                   
        JFreeChart jfreechart = new JFreeChart("JVM Memory Usage", new Font("SansSerif", 1, 24), xyplot, true);                                                
        jfreechart.setBackgroundPaint(Color.white);                                                                                                                                         
        ChartPanel chartpanel = new ChartPanel(jfreechart, true);                                  
        chartpanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLineBorder(Color.black)));                                                                                                                                         
        add(chartpanel);                                                                           
    }                                                                                              
                                                                                                   
    private void addTotalObservation(double d) {                                                   
        total.add(new Millisecond(), d);                                                           
    }                                                                                              
                                                                                                   
    private void addFreeObservation(double d) {                                                    
        free.add(new Millisecond(), d);                                                            
    }                                                                                              
                                                                                                   
    /**                                                                                            
     * @param args                                                                                 
     */                                                                                            
    public static void main(String[] args) {                                                       
        JFrame jframe = new JFrame("JVM Memory Usage");                                            
        MemoryUsage memoryusagedemo = new MemoryUsage(10000);                                      
        jframe.getContentPane().add(memoryusagedemo, "Center");                                    
        jframe.setBounds(200, 120, 1000, 500);                                                     
        jframe.setVisible(true);                                                                   
        (memoryusagedemo.new DataGenerator(1000)).start();                                         
        jframe.addWindowListener(new WindowAdapter() {                                             
            public void windowClosing(WindowEvent windowevent) {                                   
                System.exit(0);                                                                    
            }                                                                                      
        });                                                                                        
    }                                                                                              
                                                                                                   
    private TimeSeries total;                                                                      
    private TimeSeries free;                                                                       
}  