import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;

public class q3 {
    public static class Edge {
        int src;
        int end;
        int wt;

        public Edge(int src, int end, int wt) {
            this.end = end - 1;
            this.src = src - 1;
            this.wt = wt;
        }

    }

    private static void shortestPath(int vertices, int src, int end, int edges, Edge[] edgeList) {
        int[] distance = new int[vertices];
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[src] = 0;
        for (int i = 0; i < vertices - 1; i++) {
            for (int j = 0; j < edges; j++) {
                if (distance[edgeList[j].src] != Integer.MAX_VALUE
                        && distance[edgeList[j].src] + edgeList[j].wt < distance[edgeList[j].end]) {
                    distance[edgeList[j].end] = distance[edgeList[j].src] + edgeList[j].wt;
                    parent[edgeList[j].end] = edgeList[j].src;
                }
            }

        }
        for (int j = 0; j < edges; j++) {
            if (distance[edgeList[j].src] != Integer.MAX_VALUE
                    && distance[edgeList[j].src] + edgeList[j].wt < distance[edgeList[j].end]) {
                System.out.println("Negative Cycles Exist");
                return;
            }
        }
        Stack<Integer> tempStack = new Stack<Integer>();
        int node = end;
        while (node != -1) {
            tempStack.push(node + 1);
            node = parent[node];
        }
        System.out.print("Path: ");
        while (!tempStack.empty()) {
            System.out.print(tempStack.pop() + " ");
        }
        System.out.println("\nDistance From Source: " + distance[end]);

    }

    private static void allPaths(int vertices, int source, int destination, boolean[][] graph, ArrayList<Integer> v,
                                 int distance, boolean[] visited) {
        v.add(source);
        visited[source] = true;
        if (source == destination) {
            System.out.print("\nPath: ");
            for (Integer integer : v) {
                System.out.print(integer + 1 + " ");
            }
            System.out.println("\nDistance From Source: " + distance);
            visited[source] = false;
            v.remove(v.size() - 1);
            return;
        }

        for (int i = 0; i < vertices; i++) {
            if (visited[i] == false && graph[source][i]) {
                allPaths(vertices, i, destination, graph, v, distance + 1, visited);
            }
        }
        visited[source] = false;
        v.remove(v.size() - 1);
    }

    public static void main(String args[]) {
        Scanner user_Input = new Scanner(System.in);
        System.out.print("Enter number of Vertices: ");
        int vertices = user_Input.nextInt();
        System.out.print("Enter number of Edges: ");
        int edges = user_Input.nextInt();
        Edge[] edge_List = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter Edge with source vertex, destination and weight of edge: ");
            int src = user_Input.nextInt(), end = user_Input.nextInt(), wt = user_Input.nextInt();
            edge_List[i] = new q3.Edge(src, end, wt);
        }
        System.out.println("Enter Source and Destination");
        int src = user_Input.nextInt(), end = user_Input.nextInt();
        user_Input.close();

        System.out.println("\nShortest Path:");
        shortestPath(vertices, src - 1, end - 1, edges, edge_List);

        System.out.println("\nAll Paths: ");
        boolean[][] graph = new boolean[vertices][vertices];
        boolean[] visited = new boolean[vertices];
        for (Edge edge : edge_List) {
            graph[edge.src][edge.end] = true;
        }
        ArrayList<Integer> v = new ArrayList<Integer>();
        allPaths(vertices, src - 1, end - 1, graph, v, 0, visited);
    }
}