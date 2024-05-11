// Media interface
interface Media {
    void display();
}

// PrintMedia abstract class implementing Media interface
abstract class PrintMedia implements Media {
    protected String title;

    public void setTitle(String title) {
        this.title = title;
    }
}

// SocialMedia abstract class implementing Media interface
abstract class SocialMedia implements Media {
    protected String title;
}

// Book class inheriting from PrintMedia
class Book extends PrintMedia {
    private String name;
    private String ISBN;

    public Book() {} // Default constructor

    public Book(String name, String ISBN, String title) {
        this.name = name;
        this.ISBN = ISBN;
        this.title = title;
    }

    // Getters and setters

    public void display() {
        System.out.println("Book - Name: " + name + ", ISBN: " + ISBN + ", Title: " + title);
    }
}

// Magazine class inheriting from PrintMedia
class Magazine extends PrintMedia {
    private String month;
    private int year;

    public Magazine() {} // Default constructor

    public Magazine(String title, String month, int year) {
        this.title = title;
        this.month = month;
        this.year = year;
    }

    // Getters and setters

    public void display() {
        System.out.println("Magazine - Title: " + title + ", Month: " + month + ", Year: " + year);
    }
}

// Facebook class inheriting from SocialMedia
class Facebook extends SocialMedia {
    private String name;
    private String likes;

    public Facebook() {} // Default constructor

    public Facebook(String name, String likes, String title) {
        this.name = name;
        this.likes = likes;
        this.title = title;
    }

    // Getters and setters

    public void display() {
        System.out.println("Facebook - Name: " + name + ", Likes: " + likes + ", Title: " + title);
    }
}

public class Driver2 {
    public static void main(String[] args) {
        Media[] mediaArray = new Media[10];

        // Populate the array with different types of media
        mediaArray[0] = new Book("Book1", "ISBN1", "Title1");
        mediaArray[1] = new Magazine("Title2", "Month2", 2024);
        mediaArray[2] = new Facebook("Facebook1", "1000", "Title3");

        // Demonstrate polymorphic behavior by calling display() method
        for (Media media : mediaArray) {
            if (media != null) {
                media.display();
            }
        }
    }
}
