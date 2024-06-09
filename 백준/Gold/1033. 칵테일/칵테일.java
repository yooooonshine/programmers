import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] wineRatio = new int[N];

		List<List<Ratio>> adj = new ArrayList();
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Ratio>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aRatio = Integer.parseInt(st.nextToken());
			int bRatio = Integer.parseInt(st.nextToken());

			Ratio tmp = new Ratio(a,b,aRatio,bRatio);
			adj.get(a).add(tmp);
			tmp = new Ratio(b,a,bRatio,aRatio);
			adj.get(b).add(tmp);
		}

		boolean[] visit = new boolean[N];
		dfs(adj, visit, 0, wineRatio);

		int gcd = wineRatio[0];
		for (int i = 1; i < wineRatio.length; i++) {
			int num1 = gcd;
			int num2 = wineRatio[i];

			gcd = getGcd(num1, num2);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < wineRatio.length; i++) {
			sb.append(wineRatio[i] / gcd + " ");
		}

		System.out.println(sb);
	}

	// 시작은 방문했다고 처리해야 한다.
	public static void dfs(List<List<Ratio>> adj, boolean[] visit, int start, int[] wineRatio) {
		if (visit[start]) {
			return;
		}
		visit[start] = true;
		List<Ratio> nodeAdj = adj.get(start);
		for (int i = 0; i < nodeAdj.size(); i++) {

			Ratio ratio = nodeAdj.get(i);
			int a = ratio.getA(); // 얘와 같은 놈
			int b = ratio.getB();
			int aRatio = ratio.getARatio();
			int bRatio = ratio.getBRatio();

			if (visit[b]) {
				continue;
			}
			if (wineRatio[a] != 0) {
				int x = wineRatio[a];
				for (int k = 0; k < wineRatio.length; k++) {
					wineRatio[k] *= aRatio;
				}
				wineRatio[a] = aRatio * x;
				wineRatio[b] = bRatio * x;
			} else {
				wineRatio[a] = aRatio;
				wineRatio[b] = bRatio;
			}

			dfs(adj, visit, b, wineRatio);
		}

	}

	public static int getGcd(int a, int b) {
		int max;
		int min;

		if (a > b) {
			max = a;
			min = b;
		} else {
			max = b;
			min = a;
		}

		int mod = max % min;
		while (mod != 0) {
			max = min;
			min = mod;

			mod = max % min;
		}

		return min;
	}

}

class Ratio {
	public int a;
	public int b;
	public int aRatio;
	public int bRatio;

	public Ratio(int a,int b, int aRatio, int bRatio) {
		this.a = a;
		this.b = b;
		this.aRatio = aRatio;
		this.bRatio = bRatio;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getARatio() {
		return aRatio;
	}

	public int getBRatio() {
		return bRatio;
	}
}
