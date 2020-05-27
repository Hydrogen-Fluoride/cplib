class FordFulkerson {
	static class Edge {
		int from;
		int to;
		Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	static final int INF = Integer.MAX_VALUE;
	int v;
	int e;
	TreeMap<Integer, ArrayList<Edge>> map;
	HashMap<Edge, Integer> edges;
	public FordFulkerson(int V, int e, int[] u, int[] v, int[] c) {
		this.v = V;
		this.e = e;
		map = new TreeMap<>();
		edges = new HashMap<>();
		for (int i = 0; i < this.v; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			Edge tmp = new Edge(u[i], v[i]);
			map.get(u[i]).add(tmp);
			edges.put(tmp, c[i]);
		}
	}
	void ans() {
		int maxflow = 0;
		while (true) {
			boolean[] used = new boolean[v];
			int f = dfs(0, INF, used);
			if (f == 0) {
				break;
			} else {
				maxflow += f;
			}
		}
		System.out.println(maxflow);
	}
	int dfs(int from, int flow, boolean[] used) {
		if (from == v - 1) {
			return flow;
		}
		used[from] = true;
		for (Edge edge: map.get(from)) {
			if (!used[edge.to] && edges.get(edge) > 0) {
				int d = dfs(edge.to, Math.min(flow, edges.get(edge)), used);
				if (d > 0) {
					edges.put(edge, edges.get(edge) - d);
					Edge rev = new Edge(edge.to, edge.from);
					if (edges.containsKey(rev)) {
						edges.put(rev, edges.get(rev) + d);
					} else {
						edges.put(rev, d);
					}
					map.get(edge.to).add(rev);
					return d;
				}
			}
		}
		return 0;
	}
}
