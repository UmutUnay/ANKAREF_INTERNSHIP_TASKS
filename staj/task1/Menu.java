import java.util.Scanner;
import java.lang.Math;

public class Menu
{
    public static void main(String[] args)
    {
        while (true)
        {

            Scanner scanner = new Scanner(System.in);

            // Firstly print the menu
            print_menu();

            // Take the input
            int operation = scanner.nextInt(); // To take the operation number
            String end_of_operation_line = scanner.nextLine(); // To end the input with taking the new line



            // Switch cases for the operations
            switch (operation)
            {
                // Addition
                case 1:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s1 = scanner.nextLine();
                    String[] splitted1 = s1.split(" ");
                    double a1 = Double.parseDouble(splitted1[0]);  // To make sure that we dont lose any data
                    double b1 = Double.parseDouble(splitted1[1]);
                    System.out.print("|-----> ");
                    System.out.println(add(a1, b1));
                    break;

                // Subtraction
                case 2:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s2 = scanner.nextLine();
                    String[] splitted2 = s2.split(" ");
                    double a2 = Double.parseDouble(splitted2[0]);  // To make sure that we dont lose any data
                    double b2 = Double.parseDouble(splitted2[1]);
                    System.out.print("|-----> ");
                    System.out.println(sub(a2, b2));
                    break;

                // Multiplication
                case 3:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s3 = scanner.nextLine();
                    String[] splitted3 = s3.split(" ");
                    double a3 = Double.parseDouble(splitted3[0]);  // To make sure that we dont lose any data
                    double b3 = Double.parseDouble(splitted3[1]);
                    System.out.print("|-----> ");
                    System.out.println(mult(a3, b3));
                    break;

                // Division
                case 4:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s4 = scanner.nextLine();
                    String[] splitted4 = s4.split(" ");
                    double a4 = Double.parseDouble(splitted4[0]);  // To make sure that we dont lose any data
                    double b4 = Double.parseDouble(splitted4[1]);
                    System.out.print("|-----> ");
                    System.out.println(div(a4, b4));
                    break;

                // Square root
                case 5:
                    System.out.print("|-----> ");
                    String s5 = scanner.nextLine();
                    double a5 = Double.parseDouble(s5);
                    System.out.print("|-----> ");
                    System.out.println(sq_root(a5));
                    break;

                // Power
                case 6:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s6 = scanner.nextLine();
                    String[] splitted6 = s6.split(" ");
                    double a6 = Double.parseDouble(splitted6[0]);  // To make sure that we dont lose any data
                    double b6 = Double.parseDouble(splitted6[1]);
                    System.out.print("|-----> ");
                    System.out.println(pow(a6, b6));
                    break;

                // Exit
                case 7:
                    return; // To end the program
            }

        }

    }


    // For printing the menu
    public static void print_menu()
    {
        System.out.print("\n"+
        "___________________________________________\n"+
        "|                                         |\n"+
        "|                  MENU                   |\n"+
        "|_________________________________________|\n"+
        "|                                         |\n"+
        "|--> 1. Add  (2 inputs)                   |\n"+
        "|--> 2. Subtract  (2 inputs)              |\n"+
        "|--> 3. Multiply  (2 inputs)              |\n"+
        "|--> 4. Division  (2 inputs)              |\n"+
        "|--> 5. Square root  (1 input)            |\n"+
        "|--> 6. Power  (2 inputs)                 |\n"+
        "|--> 7. Quit                              |\n"+
        "|                                         |\n"+
        "|                                         |\n"+
        "|    Write the number of the operation    |\n"+
        "|             you want to do              |\n"+
        "|_________________________________________|\n"+
        "|\n"+
        "|-----> ");
    }

    // These are addition cases for int and double
    //public int add(int x, int y) {return x + y;}
    //public double add(int x, double y) {return x + y;}
    //public double add(double x, int y) {return x + y;}
    public static double add(double x, double y) {return x + y;}

    // These are subtraction cases for int and double
    //public int sub(int x, int y) {return x - y;}
    //public double sub(int x, double y) {return x - y;}
    //public double sub(double x, int y) {return x - y;}
    public static double sub(double x, double y) {return x - y;}

    // These are multiplication cases for int and double
    // public int mult(int x, int y) {return x * y;}
    // public double mult(int x, double y) {return x * y;}
    // public double mult(double x, int y) {return x * y;}
    public static double mult(double x, double y) {return x * y;}

    // These are division cases for int and double
    // public double div(int x, int y) {return x / y;}
    // public double div(int x, double y) {return x / y;}
    // public double div(double x, int y) {return x / y;}
    public static double div(double x, double y) {return x / y;}

    // These are square root cases for int and double
    // public double sq_root(int x) {return Math.sqrt(x);}
    public static double sq_root(double x) {return Math.sqrt(x);}

    // These are power cases for int and double
    // public int pow(int x, int y) {return Math.pow(x,y);}
    // public double pow(int x, double y) {return Math.pow(x,y);}
    // public double pow(double x, int y) {return Math.pow(x,y);}
    public static double pow(double x, double y) {return Math.pow(x,y);}


}
