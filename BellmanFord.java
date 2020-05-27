class BellmanFord {
	static class Edge {
		int from;
		int to;
		int cost;
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static final int INF = Integer.MAX_VALUE;
	int v;
	int e;
	int r;
	ArrayList<Edge> edges;
	int[] distance;
	public BellmanFord(int v, int e, int r, int[] s, int[] t, int[] d) {
		this.v = v;
		this.e = e;
		this.r = r;
		edges = new ArrayList<>(e);
		for (int i = 0; i < e; i++) {
			edges.add(new Edge(s[i], t[i], d[i]));
		}
	}
	void run() {
		distance = new int[v];
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
	}
	public void ans() {
		run();
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
