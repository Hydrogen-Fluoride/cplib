class WarshallFloyd {
	static final int INF = Integer.MAX_VALUE;
	int v;
	int e;
	int[][] length;
	int[][] dp;
	public WarshallFloyd(int v, int e, int[] s, int[] t, int[] d) {
		this.v = v;
		this.e = e;
		length = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				length[i][j] = INF;
			}
		}
		for (int i = 0; i < e; i++) {
			length[s[i]][t[i]] = d[i];
		}
	}
	void run() {
		dp = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (i == j) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = length[i][j];
				}
			}
		}
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				for (int k = 0; k < v; k++) {
					if (dp[j][i] != INF && dp[i][k] != INF) {
						dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
					}
				}
			}
		}
	}
	boolean negative() {
		for (int i = 0; i < v; i++) {
			if (dp[i][i] < 0) {
				return true;
			}
		}
		return false;
	}
	public void ans() {
		run();
		if (negative()) {
			System.out.println("NEGATIVE CYCLE");
			return;
		}
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (dp[i][j] == INF) {
					System.out.print("INF");
				} else {
					System.out.print(dp[i][j]);
				}
				if (j != v - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
