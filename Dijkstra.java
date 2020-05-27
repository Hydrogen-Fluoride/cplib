class Dijkstra {
	static final int INF = Integer.MAX_VALUE;
	int v;
	int e;
	int r;
	int[] distance;
	TreeMap<Integer, ArrayList<Edge>> map;
	public Dijkstra(int v, int e, int r, int[] s, int[] t, int[] d) {
		this.v = v;
		this.e = e;
		this.r = r;
		map = new TreeMap<>();
		for (int i = 0; i < v; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			map.get(s[i]).add(new Edge(s[i], t[i], d[i]));
		}
	}
	void run() {
		distance = new int[v];
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
				if (distance[edge.from] == INF) {
					continue;
				}
				if (distance[edge.to] > distance[edge.from] + edge.cost) {
					distance[edge.to] = distance[edge.from] + edge.cost;
					queue.add(new Node(edge.to, distance[edge.to]));
				}
			}
		}
	}
	public void ans() {
		run();
		for (int i = 0; i < v; i++) {
			if (distance[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}
	}
}
