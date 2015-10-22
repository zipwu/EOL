package entity;

import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Font;  
import java.io.FileOutputStream;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.CategoryLabelPositions;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.DefaultCategoryDataset;  
/** 
 *  条状测试 
 * @author Administrator 
 * 
 */  
public class BarChar {  
  
    public static void main(String[] args) {  
        //创建数据  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        //数据初始化  
        dataset.addValue(1.0, "北京", "苹果");  
        dataset.addValue(7.0, "北京", "香蕉");  
        dataset.addValue(-3.0, "北京", "桔子");  
        dataset.addValue(2.0, "上海", "苹果");  
        dataset.addValue(3.0, "上海", "香蕉");  
        dataset.addValue(2.0, "上海", "桔子");  
        //创建 JFreeChart 对象  
        JFreeChart chart = ChartFactory.createBarChart("Bar Chart 例子","水果(X)", "价格(Y)", dataset,PlotOrientation.VERTICAL,true,true,false);  
        //配置JFreeChart对象相关信息,如:字体大小,颜色,防止乱码  
        BarChar.configFont(chart);  
        
        
        //输出  
        FileOutputStream fos_jpg = null;  
        try {  
            fos_jpg = new FileOutputStream("F:\\java包\\Bar.jpg");  
            ChartUtilities.writeChartAsJPEG(fos_jpg,0.99f,chart,800,600,    null);  
            fos_jpg.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    private static void configFont(JFreeChart chart) {  
        //设置边框宽度  
        chart.setBorderStroke(new BasicStroke(1));  
        //设置边框是否可见  
        chart.setBorderVisible(true);  
        //设置边框着色  
        chart.setBorderPaint(Color.cyan);         
        //设置背景颜色  
        //chart.setBackgroundPaint(Color.YELLOW);         
        // 配置字体  
        Font xfont = new Font("宋体", Font.CENTER_BASELINE, 20);// X轴  
        Font yfont = new Font("宋体", Font.CENTER_BASELINE, 20);// Y轴  
        Font kfont = new Font("宋体", Font.CENTER_BASELINE, 18);// 底部  
        Font titleFont = new Font("微软雅黑", Font.CENTER_BASELINE, 25); // 图片标题  
        CategoryPlot plot = chart.getCategoryPlot();// 图形的绘制结构对象  
        //数据轴网格线条颜色  
        plot.setRangeGridlinePaint(Color.BLUE);  
        //数据轴网格线条笔触  
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));  
        // 图片标题  
        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont));  
  
        // 底部字体样式，防止乱码  
        chart.getLegend().setItemFont(kfont);  
  
        //X轴  
        CategoryAxis domainAxis = plot.getDomainAxis();  
        //设置X轴标题字体  
        domainAxis.setLabelFont(xfont);  
        //设置X轴字体  
        domainAxis.setTickLabelFont(xfont);  
        //设置字体颜色  
        domainAxis.setTickLabelPaint(Color.BLUE);  
        //横轴上的label斜显示  
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);   
        //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);  
        //分类轴边距,同种类型之间的距离  
        //这是分类之间的距离,和BAR与BAR之间的距离有差别  
        //domainAxis.setCategoryMargin(0.2f);  
        //分类轴下（左）边距,就是离左边的距离  
        domainAxis.setLowerMargin(0.1);  
        //分类轴下（右）边距,就是离最右边的距离  
        domainAxis.setUpperMargin(0.1);  
          
        //Y 轴  
        ValueAxis rangeAxis = plot.getRangeAxis();  
        //设置Y轴标题字体  
        rangeAxis.setLabelFont(yfont);  
        //设置Y轴字体  
        rangeAxis.setTickLabelFont(yfont);  
        // 字体颜色  
        rangeAxis.setLabelPaint(Color.RED);   
        //设置Bar的颜色  
        BarRenderer renderer = (BarRenderer) plot.getRenderer();  
          
        renderer.setSeriesPaint(0, Color.gray);  
        renderer.setSeriesPaint(1, Color.orange);  
        // 每个BAR之间的间隔  
        renderer.setItemMargin(0.0f);  
        //每个BAR的最大宽度  
        //renderer.setMaximumBarWidth(0.5f);  
    }  
} 
