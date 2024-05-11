import javax.Swing.*

class Shape{
	protected double area;
	protected double volume;
	
	public Shape()
	{
		area=0;
		volume =0;
	}
	
	public Shape(double ar, double vo)
	{
		area = ar;
		volume = vo;
	}
	
	public Shape(Shape ob)
	{
		this.area = ob.area;
		this.volume = ob.volume;
	}
	
	public void getInput()
	{
		area = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the area");
		volume = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the Volume");
	}
	
	@override
	public String toString()
	{
		System.out.println("Area = "+area);
		System.out.println("Volume = "+volume);
		
		return ("Area = "+area + "Volume = "+volume);
	}
}

class Square extends Shape{
	protected double width;
	protected double lenght;
	protected double height;
	
	public Square(double wi, double le, double he){
		Super(lenght*width,lenght*width*height)
		width = wi;
		lenght = le;
		height = he;
	}
	
	@override
	public void getInput()
	{
		lenght = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the length");
		width = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the Width");
		height = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the Height");
		
		area = lenght*width;
		volume = lenght*width*height;
	}
	
	@override
	public String toString()
	{
		
		return ("width = "+width + " lenght = "+ lenght + " height = " + height + " Area = "+area + " Volume = "+volume);
	}
}

class Sphere extends Shape{
	protected double radius;
	public static double pi = 3.14;
	
	public Sphere(double ra){
		Super(4*pi*radius*radius,(4/3)*pi*radius*radius*radius);
		radius = ra;
	}
	
	
		@override
	public void getInput()
	{
		radius = Double.pareDouble(jOptionPane.showInputDialog("null", "Enter the radius");
		
		
		area = 4*pi*radius*radius;
		volume = (4/3)*pi*radius*radius*radius;
	}
	
	@override
	public String toString()
	{
		return ("radius = "+radius  " Area = "+area + " Volume = "+volume);
	}
}

public class Driver2{
	public static void main(String [] args){
		Square sq = new Square(2,3,4);
		Sphere sp = new Sphere(4.5);
		
		System.out.pirntln(sq.toString());
		System.out.pirntln(sp.toString());
		
		
		sq.getInput();
		sp.getInput();
		
		System.out.pirntln(sq.toString());
		System.out.pirntln(sp.toString());
	}
}