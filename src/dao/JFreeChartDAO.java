package dao;

import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import util.BarChartData;
import util.PieChartData;

public class JFreeChartDAO {
	
	/*public JFreeChart BarChart(ArrayList<BarChartData> list,String ChartTitle,String CategroyString,String CategroyValue)
	{
		if (list!=null&&list.size()>0) {
			DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
			for (int i = 0; i < list.size(); i++) {
				BarChartData data = list.get(i);
				barDataset.addValue(data.getValue(), data.getRowKey(), data.getColumnKey());
			}
			JFreeChart chart = ChartFactory.createBarChart3D(ChartTitle,CategroyString,CategroyValue,barDataset, PlotOrientation.VERTICAL, true, true,false);
			
			chart.setBorderStroke(new BasicStroke(1));//设置边框宽度     
	        chart.setBorderVisible(true); //设置边框是否可见
	        chart.setBorderPaint(Color.cyan); //设置边框着色         
	        chart.setBackgroundPaint(Color.BLACK); //设置背景颜色          
	        // 配置字体  
	        Font xfont = new Font("微软雅黑", Font.CENTER_BASELINE, 20);// X轴  
	        Font yfont = new Font("微软雅黑", Font.CENTER_BASELINE, 20);// Y轴  
	        Font kfont = new Font("微软雅黑", Font.CENTER_BASELINE, 18);// 底部  
	        Font titleFont = new Font("微软雅黑", Font.CENTER_BASELINE, 25); // 图片标题
	        
	        chart.setTitle(new TextTitle(chart.getTitle().getText(),titleFont));
	        chart.getLegend().setItemFont(kfont);
	        
	        CategoryPlot plot = chart.getCategoryPlot();//图形的绘制结构对象
	        
	        CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setLabelFont(xfont);//设置X轴标题字体
	        domainAxis.setTickLabelFont(xfont);//设置X轴字体   
	        domainAxis.setTickLabelPaint(Color.BLACK);//设置字体颜色  
	        
	        ValueAxis rangeAxis = plot.getRangeAxis();//Y 轴 
	        rangeAxis.setLabelFont(yfont);//设置Y轴标题字体  
	        rangeAxis.setTickLabelFont(yfont);//设置Y轴字体  
	        rangeAxis.setLabelPaint(Color.RED);// 字体颜色
	        
	        BarRenderer renderer = (BarRenderer) plot.getRenderer();//设置Bar的颜色  
	        renderer.setSeriesPaint(0, Color.gray);//设置Bar的颜色   
	        renderer.setSeriesPaint(1, Color.orange);//设置Bar的颜色   
	        renderer.setItemMargin(0.0f);// 每个BAR之间的间隔  
			
			return chart;
		}
		return null;
		
	}
	*/
	public JFreeChart PieChart(ArrayList<PieChartData> list,String ChartTitle)
	{
		if (list!=null&&list.size()>0) {
			DefaultPieDataset PieDataset = new DefaultPieDataset();
			for (int i = 0; i < list.size(); i++){
				PieChartData data = list.get(i);
				PieDataset.setValue(data.getKeyString(), data.getValueDouble());
			}
		    JFreeChart pieChart = ChartFactory.createPieChart(ChartTitle, PieDataset, true, true, false);
			PiePlot piePlot = (PiePlot) pieChart.getPlot(); 
			
			piePlot.setSectionOutlinesVisible(false);//设置边框是否可见
			piePlot.setForegroundAlpha(0.8f); //设置图片透明度
			
			
		    Font font = new Font("微软雅黑", Font.CENTER_BASELINE, 30);//定义字体格式，中文标题需要使用中文字体，否则显示方块    
		    TextTitle title = new TextTitle(ChartTitle); //定义图片标题  
		    title.setFont(font);//设置标题的格式 
		  	pieChart.setTitle(title);//把标题设置到图片里面  
		  	pieChart.getLegend().setItemFont(new Font("微软雅黑", Font.CENTER_BASELINE, 24));//设置图例字体
		  	piePlot.setLabelFont(new Font("微软雅黑", Font.CENTER_BASELINE, 18));//设置标签字体
		  	
		  	//piePlot.setExplodePercent("杭州", 0.10); //爆炸模式,使Pie的一块分离出去,不支持3D
		  //设置上面的样式,0表示KEY,1表示VALUE,2表示百分之几,DecimalFormat用来显示百分比的格式  
		    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}({2})",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
			
			return pieChart;
		}
		return null;
		
	}
	public static void main(String[] args) {
		ArrayList<PieChartData> list = new ArrayList<PieChartData>();
		PieChartData date1 = new PieChartData();
		date1.setKeyString("技术");
		date1.setValueDouble((double) 6);
		list.add(date1);
		PieChartData date2 = new PieChartData();
		date2.setKeyString("消防");
		date2.setValueDouble((double) 8);
		list.add(date2);
		PieChartData date3 = new PieChartData();
		date3.setKeyString("运维");
		date3.setValueDouble((double) 20);
		list.add(date3);
		
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
        //输出  
        FileOutputStream fos_jpg03 = null;  
        try {  
            fos_jpg03 = new FileOutputStream("F:\\java包\\Bar01.jpg");  
            ChartUtilities.writeChartAsJPEG(fos_jpg03,0.99f,chart,800,600,null);  
            fos_jpg03.close();  
            System.out.println("保存成功");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
		ArrayList<BarChartData> list2 = new ArrayList<BarChartData>();
		BarChartData data4 = new BarChartData(97,"吴家烨","数学");
		BarChartData data5 = new BarChartData(80,"李嘉诚","数学");
		BarChartData data6 = new BarChartData(34,"王思聪","数学");
		BarChartData data7 = new BarChartData(60,"吴家烨","物理");
		BarChartData data8 = new BarChartData(78,"李嘉诚","物理");
		BarChartData data9 = new BarChartData(90,"王思聪","物理");
		list2.add(data4);
		list2.add(data5);
		list2.add(data6);
		list2.add(data7);
		list2.add(data8);
		list2.add(data9);
		
		JFreeChartDAO jDao = new JFreeChartDAO();

		
	
		FileOutputStream fos_jpg = null;  //创建输出流
        try {             
            fos_jpg = new FileOutputStream("F:\\java包\\Pie.jpg");  
            //用工具把图象写到硬盘,支持两种格式,JPG,PNG,还支持MAP  
            ChartUtilities.writeChartAsJPEG(fos_jpg,0.75f,jDao.PieChart(list,"成绩分析"),640,480,null);  
            fos_jpg.close();  
            System.out.println("保存成功");
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        
/*        JFreeChart chart2 = jDao.BarChart(list2, "成绩分析", "科目", "成绩");
        if (chart2!=null) {
        	 FileOutputStream fos_jpg02 = null;  //创建输出流
             try {             
                 fos_jpg = new FileOutputStream("F:\\java包\\Bar.jpg");   
                 ChartUtilities.writeChartAsJPEG(fos_jpg02,0.99f,chart2,640,480,null);  
                 System.out.println("保存成功");
                 fos_jpg.close();
             } catch (Exception e) {  
                 e.printStackTrace();  
             } 
		}
       */

	}

}
