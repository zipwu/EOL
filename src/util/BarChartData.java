package util;

public class BarChartData {
	private double Value;
	private String rowKey;
	private String columnKey;
	public BarChartData(double value,String row, String column)
	{
		this.Value = value;
		this.rowKey = row;
		this.columnKey = column;
	}
	public double getValue() {
		return Value;
	}
	public void setValue(double value) {
		Value = value;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	
}
