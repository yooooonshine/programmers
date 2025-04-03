
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 소수 구하기
		boolean[] primeNs = new boolean[10000]; // 1~9999
		Arrays.fill(primeNs, true);
		getPrimes(primeNs);
		for (int i = 0; i <= 999; i++) {
			primeNs[i] = false;
		}

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			String[] nums = br.readLine().split(" ");
			String s = nums[0];
			String e = nums[1];

			// 불가능하면 impossible
			int bfs = bfs(primeNs, s, e);

			if (bfs == -1) {
				System.out.println("Impossible");
			} else {
				System.out.println(bfs);
			}
		}
	}

	public static void getPrimes(boolean[] primeNs) {

		for (int i = 2; i <= 9999; i++) {
			if (!primeNs[i]) {
				continue;
			}

			int index = 2;
			while (i * index <= 9999) {
				primeNs[i * index] = false;
				index++;
			}
		}


	}

	public static int bfs(boolean[] primeNs, String s, String e) {
		Queue<Vertex> q = new LinkedList<>();
		q.add(new Vertex(s, 0));

		boolean[] visited = new boolean[10000];
		visited[Integer.parseInt(s)] = true;

		while (!q.isEmpty()) {
			Vertex v = q.poll();
			String num = v.num;
			int count = v.count;

			if (Objects.equals(num, e)) {
				return count;
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j <= 9; j++) {
					StringBuilder sb = new StringBuilder(String.valueOf(num));
					sb.setCharAt(i, (char) ('0' + j));
					int nextNum = Integer.parseInt(sb.toString());

					if (!visited[nextNum] && primeNs[nextNum]) {
						visited[nextNum] = true;
						q.add(new Vertex(sb.toString(), count + 1));
					}
				}
			}
		}

		return -1;
	}

	public static class Vertex {
		String num;
		int count;

		public Vertex(String num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}

//4자리 소수


// 1. 네자리 소수를 모두 구한다.
// 최단경로 탐색, bfs 돌린다.

