import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> availableShelves = new PriorityQueue<>();
        for (int i = 1; i <= m; i++) {
            availableShelves.add(i);
        }

        Map<Integer, Integer> itemToShelf = new HashMap<>();

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] event = reader.readLine().split(" ");
            char type = event[0].charAt(0);
            int itemNumber = Integer.parseInt(event[1]);

            if (type == '+') {
                if (!availableShelves.isEmpty()) {
                    int shelf = availableShelves.poll();
                    itemToShelf.put(itemNumber, shelf);
                    output.append(shelf).append("\n");
                }
            } else {
                if (itemToShelf.containsKey(itemNumber)) {
                    int shelf = itemToShelf.get(itemNumber);
                    availableShelves.add(shelf);
                    itemToShelf.remove(itemNumber);
                }
            }
        }

        System.out.println(output);
    }
}
