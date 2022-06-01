import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    private static final int INF = Integer.MAX_VALUE / 2;
    private static final int PEAK_NUM = 16;

    public static int getResult(@NotNull String field, @NotNull String species) {
        if (field.length() != 16) {
            throw new IllegalArgumentException("Wrong length of field's data");
        }
        int[][] adjacencyMatrix = convertFieldData(field, species);
        return dijkstra(adjacencyMatrix);
    }

    private static int[][] convertFieldData(String field, String species) {
        String path = "src/main/resources/config.json";
        HashMap<Character, Integer> weightOfStep = Config.getConfig(path,species);
        CharSequence charSequence = field.toLowerCase().subSequence(0, 16);
        int[][] adjacencyMatrix = new int[16][16];

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (j == i) {
                    adjacencyMatrix[i][j] = 0;
                }

                else if (j == i - 1) {
                    //край поля => не может пойти влево
                    if (i % 4 == 0) {
                        adjacencyMatrix[i][j] = INF;
                    } else {
                        adjacencyMatrix[i][j] = weightOfStep.get(charSequence.charAt(j));
                    }
                }

                else if (j == i + 1) {
                    //край поля => не может пойти вправо
                    if ((i + 1) % 4 == 0) {
                        adjacencyMatrix[i][j] = INF;
                    } else {
                        adjacencyMatrix[i][j] = weightOfStep.get(charSequence.charAt(j));
                    }
                }

                else if (j == i + 4 || j == i - 4) {
                    adjacencyMatrix[i][j] = weightOfStep.get(charSequence.charAt(j));
                } else {
                    adjacencyMatrix[i][j] = INF;
                }
            }
        }
        return adjacencyMatrix;
    }

    private static int dijkstra(int[][] graph) {
        boolean[] used = new boolean[PEAK_NUM]; // массив пометок
        int[] dist = new int[PEAK_NUM]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
        Arrays.fill(dist, INF);
        dist[0] = 0; // для начальной вершины положим 0
        for (; ; ) {
            int vertex = -1;
            for (int newVertex = 0; newVertex < PEAK_NUM; newVertex++) // перебираем вершины
                if (!used[newVertex] && dist[newVertex] < INF && (vertex == -1 || dist[vertex] > dist[newVertex])) // выбираем самую близкую непомеченную вершину
                    vertex = newVertex;
            if (vertex == -1) break; // ближайшая вершина не найдена
            used[vertex] = true; // помечаем ее
            for (int newVertex = 0; newVertex < PEAK_NUM; newVertex++)
                if (!used[newVertex] && graph[vertex][newVertex] < INF) // для всех непомеченных смежных
                    dist[newVertex] = Math.min(dist[newVertex], dist[vertex] + graph[vertex][newVertex]); // улучшаем оценку расстояния
        }
        return dist[dist.length - 1];
    }
}
