import java.util.Random;
import java.util.Scanner;

public class Game
{
    public static void main(String[] args)
    {
        // Create a random variable
        Random random = new Random();
        int target = random.nextInt(100 - 1) + 1;

        // Create a scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        // Print the start menu
        print_menu();

        // Set and start the timer
        long start_time = System.currentTimeMillis();
        long elapsed_time = 0L;

        // Set the guess counter
        int guess_count_left = 6;

        // Get the user input
        while (elapsed_time <= 60000 && guess_count_left != 0)
        {
            elapsed_time = System.currentTimeMillis() - start_time;
            System.out.print("Guess(g) or get the time(time) : ");
            try
            {
                String user_input = scanner.next();
                switch (user_input)
                {
                    case "g":
                        System.out.print("Guess the number : ");
                        try
                        {
                            int guessed_number = scanner.nextInt();
                            if (guessed_number == target) {guess_count_left--; System.out.println("YOU WON!"); return;}
                            else if (guessed_number >= target + 10) {guess_count_left--; System.out.println("TOO HIGH!"); elapsed_time = System.currentTimeMillis() - start_time;}
                            else if (guessed_number <= target - 10) {guess_count_left--; System.out.println("TOO LOW!"); elapsed_time = System.currentTimeMillis() - start_time;}
                            else if (guessed_number > target) {guess_count_left--; System.out.println("LITTLE HIGH!"); elapsed_time = System.currentTimeMillis() - start_time;}
                            else if (guessed_number < target) {guess_count_left--; System.out.println("LITTLE LOW!"); elapsed_time = System.currentTimeMillis() - start_time;}
                        }
                        catch (Exception e)
                        {
                            System.out.println("An error happened");
                            break;
                        }
                        break;
                    case "time":
                        elapsed_time = System.currentTimeMillis() - start_time;
                        System.out.println("Time elapsed is : " + elapsed_time);
                        break;
                    default:
                        elapsed_time = System.currentTimeMillis() - start_time;
                        System.out.println("Please enter \"g\" or \"time\"");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Please enter \"g\" or \"time\"");
                continue;
            }

        }

        if (elapsed_time >= 60000) {System.out.println("Your time has ended! The target was : " + target);}
        if (guess_count_left == 0) {System.out.println("You have guessed enough!");}

    }





    public static void print_menu()
    {
        System.out.println("__________________________________");
        System.out.println("|                                |");
        System.out.println("|       GUESS THE NUMBER         |");
        System.out.println("|            TO WIN              |");
        System.out.println("|                                |");
        System.out.println("|   To check the remaining time  |");
        System.out.println("|          write \"time\"          |");
        System.out.println("|________________________________|");
    }

}
