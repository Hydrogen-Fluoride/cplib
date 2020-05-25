public class Main {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int v = sc.nextInt();
		int e = sc.nextInt();
		int r = sc.nextInt();
		TreeMap<Integer, ArrayList<Edge>> map = new TreeMap<>();
		for (int i = 0; i < v; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			int s = sc.nextInt();
			int t = sc.nextInt();
			int d = sc.nextInt();
			map.get(s).add(new Edge(s, t, d));
		}
		int[] distance = new int[v];
		final int INF = 1000000000;
		for (int i = 0; i < v; i++) {
			if (i != r) {
				distance[i] = INF;
			}
		}
		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		for (int i = 0; i < v; i++) {
			queue.add(new Node(i, distance[i]));
		}
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.cost != distance[node.num]) {
				continue;
			}
			for (Edge edge: map.get(node.num)) {
				if (distance[edge.to] > distance[edge.from] + edge.cost) {
					distance[edge.to] = distance[edge.from] + edge.cost;
					queue.add(new Node(edge.to, distance[edge.to]));
				}
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

class Node {
	int num;
	int cost;
	Node(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}
