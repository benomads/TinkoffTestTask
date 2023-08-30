import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Width
        int m = scanner.nextInt(); // Height


        int currentNumber = 1;

        for (int diagSum = 1; diagSum <= n + m - 1; diagSum++) {
            int minRow = Math.max(1, diagSum - n + 1); // Start row for the current diagonal
            int maxRow = Math.min(m, diagSum); // End row for the current diagonal

            for (int row = minRow; row <= maxRow; row++) {
                int col = diagSum - row + 1; // Calculate the column for the current row
                System.out.print(currentNumber);
                currentNumber++;

                if (row != maxRow || col != n) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }
}
