
import java.util.*;
import java.io.*;

public class Main {
	public static Map<Integer, Point> myM;
	public static List<List<Edge>> pL;
	public static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		float myX = Float.parseFloat(st.nextToken());
		float myY = Float.parseFloat(st.nextToken());

		st = new StringTokenizer(br.readLine());
		float dX = Float.parseFloat(st.nextToken());
		float dY = Float.parseFloat(st.nextToken());

		myM = new HashMap<>();
		myM.put(1, new Point(myX, myY));
		myM.put(2, new Point(dX, dY));

		int index = 3;
		n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());

			myM.put(index, new Point(x, y));
			index++;
		}

		pL = new ArrayList<>();
		for (int i = 0; i <= n + 2; i++) {
			pL.add(new ArrayList<>());
		}

		for (int i = 1; i <= n + 2; i++) {
			if (i == 1) {
				for (int j = i + 1; j <= n + 2; j++) {
					float x1 = myM.get(i).x;
					float y1 = myM.get(i).y;
					float x2 = myM.get(j).x;
					float y2 = myM.get(j).y;

					pL.get(i).add(new Edge(j, ((float)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2))) / 5F));
				}
				continue;
			}

			for (int j = 1; j <= n + 2; j++) {
				if (i == j) {
					continue;
				}

				float x1 = myM.get(i).x;
				float y1 = myM.get(i).y;
				float x2 = myM.get(j).x;
				float y2 = myM.get(j).y;

				pL.get(i).add(new Edge(j, calcD(x1, y1, x2, y2)));
			}
		}

		System.out.println(dkj(1, 2));
	}

	public static float dkj(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0F));

		boolean[] visit = new boolean[n + 3];

		float[] distance = new float[n + 3];
		for (int i = 1; i <= n + 2; i++) {
			distance[i] = 10000000F;
		}
		distance[start] = 0F;

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int e = edge.e;
			float t = edge.t;

			if (visit[e]) {
				continue;
			}
			visit[e] = true;

			for (Edge tmpE : pL.get(e)) {

				if (distance[tmpE.e] > distance[e] + tmpE.t) {
					distance[tmpE.e] =  distance[e] + tmpE.t;
					pq.add(new Edge(tmpE.e, distance[tmpE.e]));
				}
			}
		}

		return distance[end];
	}

	public static class Edge implements Comparable<Edge> {
		int e;
		float t;

		public Edge(int e, float t) {
			this.e = e;
			this.t = t;
		}

		@Override
		public int compareTo(Edge o) {
			return Float.compare(this.t, o.t);
		}
	}

	public static float calcD(float x, float y, float dX, float dY) {
		float d = (float)Math.sqrt((double) (Math.pow(x - dX, 2) + Math.pow(y - dY, 2)));

		float t1 = d / 5F;

		if (d > 50F) {
			return Math.min(t1, (d - 50F) / 5F + 2F);
		} else if (d == 50F) {
			return 2F;
		} else {
			return Math.min(t1, (50F - d) / 5F + 2F);
		}
	}

	public static class Point {
		float x;
		float y;

		public Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
}

// 나는 5 m/s
// 대포는 50m 2초 25m/s
// 경로 찾기 a -> b 다익스트라? O(ElogV)

// 두 개의 실수 현재 X, Y
// 목적지 X, Y
// n 대포 수
// n 줄 대포 위치


// 25m이면 5s, 7s
// 30m이면 6s, 6s

// 결국에는 목적지 및 대포의 직선 거리 상에서 대포를 쏘개 된다.
// 대포의 직선사이에 대포 위치에도 노드를 둔다.
// 모든 노드 사이의 e를 구한다,
// 다익스트라를 돌린다.
// 이러면 방향이 존재한다.
// 무관하다.
// 두 개의 노드를 골라
// 한쪽이 대포면 직선상 대포끝지점의 노드를 추가한다.
// 새 노드까지의 길을 단방향으로,

// 그냥 모든 노드간의 최단 거리를 계산하면 되는구나
// 노드별로 n번 돌려서
// 대포면
// 대포 + 걷기
// 걷기 비교하여 길 추가

// 대포 아니면 걷기

// 위는 모두 단방향으로




// 다익스트라 우선순위 큐
// 방문배열
// while pq 빌때까지
// pq pop
// for 인접노드에 대해
// if (distance[위치] > distance[이전노드] + w)
// distance 업데이트, pq에 넣기
// 여기서 visit체크를 해줘야 하나

