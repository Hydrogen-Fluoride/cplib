public class Main {
	static int V;
	static int E;
	static HashMap<Edge, Integer> edges;
	static TreeMap<Integer, ArrayList<Edge>> map;
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		V = sc.nextInt();
		E = sc.nextInt();
		edges = new HashMap<>();
		map = new TreeMap<>();
		for (int i = 0; i < V; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int c = sc.nextInt();
			Edge tmp = new Edge(u, v);
			edges.put(tmp, c);
			map.get(u).add(tmp);
		}
		int maxflow = 0;
		while (true) {
			boolean[] used = new boolean[V];
			int f = dfs(0, 1000000000, used);
			if (f == 0) {
				break;
			} else {
				maxflow += f;
			}
		}
		System.out.println(maxflow);
	}

	static int dfs(int from, int flow, boolean[] used) {
		if (from == V - 1) {
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

class Edge {
	int from;
	int to;
	Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}
}
