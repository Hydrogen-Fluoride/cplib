public class Main {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int v = sc.nextInt();
		int e = sc.nextInt();
		int r = sc.nextInt();
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < e; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int d = sc.nextInt();
			edges.add(new Edge(a, b, d));
		}
		int[] distance = new int[v];
		final int INF = 1000000000;
		for (int i = 0; i < v; i++) {
			distance[i] = INF;
		}
		distance[r] = 0;

		for (int i = 0; i < v - 1; i++) {
			for (Edge edge: edges) {
				if (distance[edge.from] == INF) continue;
				if (distance[edge.from] + edge.cost < distance[edge.to]) {
					distance[edge.to] = distance[edge.from] + edge.cost;
				}
			}
		}
		for (Edge edge: edges) {
			if (distance[edge.from] == INF) continue;
			if (distance[edge.from] + edge.cost < distance[edge.to]) {
				System.out.println("NEGATIVE CYCLE");
				return;
			}
		}
		for (int i = 0; i < v; i++) {
			if (distance[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}
	}
}

class Edge {
	int from;
	int to;
	int cost;
	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}
