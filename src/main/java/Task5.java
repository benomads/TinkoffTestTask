import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]); // Количество домиков
        int m = Integer.parseInt(nm[1]); // Количество дорожек

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] sfh = reader.readLine().split(" ");
            int s = Integer.parseInt(sfh[0]); // Номер первого домика
            int f = Integer.parseInt(sfh[1]); // Номер второго домика
            int h = Integer.parseInt(sfh[2]); // Высота дорожки
            edges.add(new Edge(s, f, h));
        }

        edges.sort(Comparator.comparingInt(e -> e.height));

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int maxHeight = -1;
        for (Edge edge : edges) {
            int parentS = findParent(parent, edge.start);
            int parentF = findParent(parent, edge.finish);

            if (parentS != parentF) {
                maxHeight = edge.height;
                parent[parentF] = parentS;
            }
        }

        System.out.println(maxHeight);
    }

    static int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }
        return parent[node];
    }

    static class Edge {
        int start;
        int finish;
        int height;

        public Edge(int start, int finish, int height) {
            this.start = start;
            this.finish = finish;
            this.height = height;
        }
    }
}