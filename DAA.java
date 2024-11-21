import java.util.Scanner;

public class Main {

    // Method to find the optimal route using the nearest neighbor heuristic
    public static int[] findOptimalRoute(int[][] distanceMatrix) {
        int n = distanceMatrix.length; // Number of airports
        boolean[] visited = new boolean[n]; // Track visited airports
        int[] route = new int[n + 1]; // Store the route, including returning to the starting airport
        int totalDistance = 0;

        int currentAirport = 0; // Start from the first airport (e.g., hub)
        visited[currentAirport] = true;
        route[0] = currentAirport;

        // Loop to find the nearest unvisited airport
        for (int i = 1; i < n; i++) {
            int nearestAirport = -1;
            int minDistance = Integer.MAX_VALUE;

            // Find the nearest unvisited airport
            for (int j = 0; j < n; j++) {
                if (!visited[j] && distanceMatrix[currentAirport][j] < minDistance) {
                    nearestAirport = j;
                    minDistance = distanceMatrix[currentAirport][j];
                }
            }

            // Move to the nearest airport
            if (nearestAirport != -1) {
                visited[nearestAirport] = true;
                route[i] = nearestAirport;
                totalDistance += minDistance;
                currentAirport = nearestAirport;
            }
        }

        // Return to the starting airport
        totalDistance += distanceMatrix[currentAirport][0];
        route[n] = 0; // End at the starting airport

        System.out.println("Total Flight Distance: " + totalDistance);
        return route;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of airports
        System.out.print("Enter the number of airports: ");
        int n = scanner.nextInt();

        // Initialize the distance matrix
        int[][] distanceMatrix = new int[n][n];

        // Get the distance matrix values from the user
        System.out.println("Enter the distance matrix (row by row): ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Distance from airport " + i + " to airport " + j + ": ");
                distanceMatrix[i][j] = scanner.nextInt();
            }
        }

        // Find the optimal route using the nearest neighbor heuristic
        int[] optimalRoute = findOptimalRoute(distanceMatrix);

        // Print the optimal route
        System.out.print("Optimal Flight Route: ");
        for (int i = 0; i < optimalRoute.length; i++) {
            System.out.print(optimalRoute[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}