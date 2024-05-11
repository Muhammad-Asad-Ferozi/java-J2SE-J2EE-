interface TwoDimentionalShape
{
	public void getArea();
}

class Circle implements TwoDimentionalShape
{
	public Double radius;
	public Double area;
	public Circle()
	{
		radius =0.0;
		area = 0.0;
	}
	public Circle(Double ra)
	{
		radius =ra;
		area = 3.14 * ra *ra;
	}
	
	public void getArea()
	{
		System.out.println(area);
	}

	public String toString()
	{
		return("Radius = "+radius + " Area = " +area+"");
	}
	public void inputRadius(Double ra)
	{
		radius =ra;
		area = 3.14 * ra *ra;
	}
}


class Triangle implements TwoDimentionalShape{
	public Double base;
	public Double height;
	public Double area;
	public Triangle()
	{
		base =0.0;
		height =0.0;
		area = 0.0;
	}
	public Triangle(Double ba, Double hi)
	{
		base = ba;
		height = hi;
		area =  0.5 * base *height;
	}
	
	public void getArea()
	{
		System.out.println(area);
	}
	
	public String toString()
	{
		return("Base = "+base + "Height = "+height + " Area = " +area+"");
	}
	public void inputBase(Double ra)
	{
		base=ra;
		area =  0.5 * base *height;
	}
	public void inputHeight(Double ra)
	{
		height=ra;
		area =  0.5 * base *height;
	}
}

class Square  implements TwoDimentionalShape{
	public Double length;
	public Double area;
	public Square()
	{
		length =0.0;
		area = 0.0;
	}
	public Square(Double ra)
	{
		length =ra;
		area = ra *ra;
	}
	
	public void getArea()
	{
		System.out.println(area);
	}
	
	public String toString()
	{
		return("length = "+length + " Area = " +area+"");
	}
	public void inputRadius(Double ra)
	{
		length =ra;
		area = ra *ra;
	}
}

public class Driver{
	public static void main(String [] args)
	{
		TwoDimentionalShape[] arr = new TwoDimentionalShape[3];
		arr[0] = new Circle(2.0);
		arr[1] = new Square(3.0);
		arr[2] = new Triangle(2.0,3.0);
		Circle ci  = null;
		Square sq =null;
		Triangle ti=null;
		for(TwoDimentionalShape it: arr)
		{
			it.getArea();
			System.out.println(it);
			if(it instanceof Circle)
			{
				 ci = (Circle) it;
			}
			else if(it instanceof Triangle)
			{
				ti = (Triangle) it;
			}
			else if(it instanceof Square)
			{
				sq = (Square) it;
			}
		}
		
		System.out.println(ci);
		System.out.println(ti);
		System.out.println(sq);
		
		
	}
}

