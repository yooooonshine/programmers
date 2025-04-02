import java.util.*;
import java.io.*;

public class Main {

	public static int k;

	public static int[] maxR;
	public static int[] minR;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());

		List<List<Edge>> adjMax = new ArrayList<>();
		for (int i = 0; i <= k + 1; i++) {
			adjMax.add(new ArrayList<>());
		}
		List<List<Edge>> adjMin = new ArrayList<>();
		for (int i = 0; i <= k + 1; i++) {
			adjMin.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= k; i++) {
			if (st.nextToken() .equals("<")) {
				adjMax.get(i + 1).add(new Edge(i + 1, i));
				adjMin.get(i).add(new Edge(i, i + 1));

			} else {
				adjMax.get(i).add(new Edge(i, i + 1));
				adjMin.get(i + 1).add(new Edge(i + 1, i));
			}
		}

		maxR = new int[k + 2];
		minR = new int[k + 2];

		topologySortMax(adjMax);
		topologySortMin(adjMin);

		for (int i = 1; i <= k + 1; i++) {
			System.out.print(maxR[i]);
		}
		System.out.println();

		for (int i = 1; i <= k + 1; i++) {
			System.out.print(minR[i]);
		}
		System.out.println();
	}

	public static void topologySortMax(List<List<Edge>> adjMax) {
		int index = 9;

		int[] in = new int[k + 2];
		int[] order = new int[k + 2];

		for (int i = 1; i <= k + 1; i++) {
			for (Edge edge : adjMax.get(i)) {
				in[edge.e]++;
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= k + 1; i++) {
			if (in[i] == 0) {
				pq.add(new Edge(i, i));
			}
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int nowE = edge.e;

			maxR[nowE] = index--;

			for (Edge next : adjMax.get(nowE)) {
				in[next.e]--;

				if (in[next.e] == 0) {
					pq.add(new Edge(next.e, next.e));
				}
			}
		}
	}

	public static void topologySortMin(List<List<Edge>> adjMax) {
		int index = 0;

		int[] in = new int[k + 2];
		int[] order = new int[k + 2];

		for (int i = 1; i <= k + 1; i++) {
			for (Edge edge : adjMax.get(i)) {
				in[edge.e]++;
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= k + 1; i++) {
			if (in[i] == 0) {
				pq.add(new Edge(i, i));
			}
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int nowE = edge.e;

			minR[nowE] = index++;

			for (Edge next : adjMax.get(nowE)) {
				in[next.e]--;

				if (in[next.e] == 0) {
					pq.add(new Edge(next.e, next.e));
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int s;
		int e;

		public Edge(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.e - edge.e;
		}
	}
}
// k개 나열
// <>가
// 0~ 9 정수
// 부등호 관계를 만족시키는 정수
// 하나 이상 -> 토폴로지 소트
// 최댓값, 최솟값.

// 토폴로지 소트 어떻게 했더라
// 집입 차수 모두 구하고
// 진입 차수가 0인 것부터 시작
// 0 주위의 노드 방문후 진입 차수 1씩 제거, 0이면 큐에 넣기

// 가장 큰 숫자는 1 < 2 방향으로 큰 수부터 나열
// 가장 작은 숫자는 작은 숫자부터 0 < 1 순으로 나열

// 다만 큐는 우선순위 큐를 사용하여, 앞쪽 부등호 먼저 사용.

// 왼쪽부터 1
