import javax.swing.JOptionPane;

class Shape {
    protected double area;
    protected double volume;

    public Shape() {
        area = 0;
        volume = 0;
    }

    public Shape(double ar, double vo) {
        area = ar;
        volume = vo;
    }

    public Shape(Shape ob) {
        this.area = ob.area;
        this.volume = ob.volume;
    }

    public void getInput() {
        area = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the area"));
        volume = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Volume"));
    }

    @Override
    public String toString() {
        return "Area = " + area + "\nVolume = " + volume;
    }
}

class Square extends Shape {
    protected double width;
    protected double length;
    protected double height;

    public Square(double wi, double le, double he) {
        super(le * wi, le * wi * he);
        width = wi;
        length = le;
        height = he;
    }

    @Override
    public void getInput() {
        length = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the length"));
        width = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Width"));
        height = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Height"));

        area = length * width;
        volume = length * width * height;
    }

    @Override
    public String toString() {
        return "Width = " + width + "\nLength = " + length + "\nHeight = " + height + "\n" + super.toString();
    }
}

class Sphere extends Shape {
    protected double radius;
    public static double pi = 3.14;

    public Sphere(double ra) {
        super(4 * pi * ra * ra, (4 / 3) * pi * ra * ra * ra);
        radius = ra;
    }

    @Override
    public void getInput() {
        radius = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the radius"));

        area = 4 * pi * radius * radius;
        volume = (4 / 3) * pi * radius * radius * radius;
    }

    @Override
    public String toString() {
        return "Radius = " + radius + "\n" + super.toString();
    }
}

public class Driver2 {
    public static void main(String[] args) {
        Square sq = new Square(2, 3, 4);
        Sphere sp = new Sphere(4.5);

        System.out.println(sq.toString());
        System.out.println(sp.toString());

        sq.getInput();
        sp.getInput();

        System.out.println(sq.toString());
        System.out.println(sp.toString());
    }
}
