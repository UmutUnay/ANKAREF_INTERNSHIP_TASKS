import java.util.List;
import java.util.stream.*;

public class Question
{
    public static void main(String[] args)
    {
        // There are two streams I can use for this question
        // One being normal String<Integer> and the other one
        // being IntStream. Since IntStream saves time and power
        // by not using auto-boxing I will be using that.

        // For printing I need to hold hence I will use List
        // instead of IntStream
        // On a note this list is not mutable
        List<Integer> list_of_numbers = IntStream.rangeClosed(1, 100).boxed().toList();

        // Since the numbers which are divisible by 4 are also divisible by 2 just filtering for 4
        long answer1 = list_of_numbers.stream().filter((n) -> n % 4 == 0).count();

        // Number of odd numbers
        long answer2 = list_of_numbers.stream().filter((n) -> n % 2 == 1).count();
        // Total of odd numbers
        long answer3 = list_of_numbers.stream().filter((n) -> n % 2 == 1).reduce(0, Integer::sum);
        // Number of even numbers
        long answer4 = list_of_numbers.stream().filter((n) -> n % 2 == 0).count();
        // Total of even numbers
        long answer5 = list_of_numbers.stream().filter((n) -> n % 2 == 0).reduce(0, Integer::sum);


        // Printing the results
        System.out.println("The number of numbers which are divisible by 2 and 4 between 1 and 100 : " + answer1);
        System.out.println("The number of odd numbers between 1 and 100 : " + answer2);
        System.out.println("The total of odd numbers between 1 and 100 : " + answer3);
        System.out.println("The number of even numbers between 1 and 100 : " + answer4);
        System.out.println("The total of even numbers between 1 and 100 : " + answer5);

    }
}
