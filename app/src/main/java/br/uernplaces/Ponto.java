package br.uernplaces;

public class Ponto {
	public double x,y;
	public String title, desc;
	public int i;

	
	public Ponto(double x, double y, String title, String desc, int i) {
		super();
		this.x = x;
		this.y = y;
		this.title = title;
		this.desc = desc;
		this.i = i;
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}	
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	
	
	
}
