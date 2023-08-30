import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // номер минуты

        int totalCells = 1; // Начальное количество клеток

        for (int i = 1; i < n; i++) {
            totalCells += 4 * Math.pow(4, i); // Формула для добавления клеток на текущей минуте
        }

        System.out.println(totalCells);
    }
}