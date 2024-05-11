// Abstract Shape class
abstract class Shape {
    protected double area;
    protected double perimeter;

    // Abstract methods
    abstract void calculateArea();
    abstract void calculatePerimeter();

    // Concrete method
    public void displayInfo() {
        System.out.println("Shape: " + this.getClass().getSimpleName());
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }
}

// Square class extending Shape
class Square extends Shape {
    private double length;

    Square(double length) {
        this.length = length;
        calculateArea();
        calculatePerimeter();
    }

    void calculateArea() {
        area = length * length;
    }

    void calculatePerimeter() {
        perimeter = 4 * length;
    }
}

// Rectangle class extending Shape
class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        calculateArea();
        calculatePerimeter();
    }

    void calculateArea() {
        area = length * width;
    }

    void calculatePerimeter() {
        perimeter = 2 * (length + width);
    }
}

// Circle class extending Shape
class Circle extends Shape {
    private double radius;
    private final double PI = 3.14;

    Circle(double radius) {
        this.radius = radius;
        calculateArea();
        calculatePerimeter();
    }

    void calculateArea() {
        area = PI * radius * radius;
    }

    void calculatePerimeter() {
        perimeter = 2 * PI * radius;
    }
}

// Driver class
public class Driver1 {
    public static void main(String[] args) {
        // Creating objects of Square, Rectangle, and Circle
        Square square = new Square(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Circle circle = new Circle(3);

        // Displaying information of each shape
        square.displayInfo();
        rectangle.displayInfo();
        circle.displayInfo();
    }
}
