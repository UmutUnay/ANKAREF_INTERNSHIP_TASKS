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
            String operation_string = scanner.nextLine();
            int operation = 0;
            try
            {
                operation = Integer.parseInt(operation_string);
            }
            catch (Exception err)
            {
                System.out.println("Please enter a valid integer between 1-7");
                continue;
            }

            // Switch cases for the operations
            switch (operation)
            {
                // Addition
                case 1:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s1 = scanner.nextLine();
                    String[] splitted1 = s1.split(" ");
                    double a1 = 0.0;
                    double b1 = 0.0;
                    try
                    {
                        a1 = Double.parseDouble(splitted1[0]);  // To make sure that we dont lose any data
                        b1 = Double.parseDouble(splitted1[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid numbers");
                        break;
                    }
                    System.out.print("|-----> ");
                    System.out.println(add(a1, b1));
                    break;

                // Subtraction
                case 2:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s2 = scanner.nextLine();
                    String[] splitted2 = s2.split(" ");
                    double a2 = 0.0;
                    double b2 = 0.0;
                    try
                    {
                        a2 = Double.parseDouble(splitted2[0]);  // To make sure that we dont lose any data
                        b2 = Double.parseDouble(splitted2[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid numbers");
                        break;
                    }
                    System.out.print("|-----> ");
                    System.out.println(sub(a2, b2));
                    break;

                // Multiplication
                case 3:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s3 = scanner.nextLine();
                    String[] splitted3 = s3.split(" ");
                    double a3 = 0.0;
                    double b3 = 0.0;
                    try
                    {
                        a3 = Double.parseDouble(splitted3[0]);  // To make sure that we dont lose any data
                        b3 = Double.parseDouble(splitted3[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid numbers");
                        break;
                    }
                    System.out.print("|-----> ");
                    System.out.println(mult(a3, b3));
                    break;

                // Division
                case 4:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s4 = scanner.nextLine();
                    String[] splitted4 = s4.split(" ");
                    double a4 = 0.0;
                    double b4 = 0.0;
                    try
                    {
                        a4 = Double.parseDouble(splitted4[0]);  // To make sure that we dont lose any data
                        b4 = Double.parseDouble(splitted4[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid numbers");
                        break;
                    }
                    try
                    {
                        System.out.print("|-----> ");
                        System.out.println(div(a4, b4));
                    }
                    catch (Exception exc)
                    {
                        System.out.println("Error message: " + exc.getMessage());
                    }
                    break;

                // Square root
                case 5:
                    System.out.print("|-----> ");
                    String s5 = scanner.nextLine();
                    double a5 = 0.0;
                    try
                    {
                        a5 = Double.parseDouble(s5);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter a valid number");
                        break;
                    }
                    System.out.print("|-----> ");
                    System.out.println(sq_root(a5));
                    break;

                // Power
                case 6:
                    System.out.print("|-----> ");
                    // Taking the numbers and then parsing them
                    String s6 = scanner.nextLine();
                    String[] splitted6 = s6.split(" ");
                    double a6 = 0.0;
                    double b6 = 0.0;
                    try
                    {
                        a6 = Double.parseDouble(splitted6[0]);  // To make sure that we dont lose any data
                        b6 = Double.parseDouble(splitted6[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid numbers");
                        break;
                    }
                    System.out.print("|-----> ");
                    System.out.println(pow(a6, b6));
                    break;

                // Exit
                case 7:
                    return; // To end the program

                // Default case for when user doesn't enter a valid number
                default:
                    System.out.println("Please enter a valid number between 1-7");
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

    // Addition Function
    public static double add(double x, double y) {return x + y;}

    // Addition Function
    public static double sub(double x, double y) {return x - y;}

    // Subtraction Function
    public static double mult(double x, double y) {return x * y;}

    // Division Function
    public static double div(double x, double y) throws Exception {if (y != 0.0) return x / y; else throw new Exception("Second value can't be 0!");}

    // Square root Function
    public static double sq_root(double x) {return Math.sqrt(x);}

    // Power Function
    public static double pow(double x, double y) {return Math.pow(x,y);}


}
