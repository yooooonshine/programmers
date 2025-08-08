
import java.util.*;
import java.io.*;

public class Main {

	public static int N; // 트리 정점수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		N = Integer.parseInt(br.readLine());

		List<List<Integer>> adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		for (int n = 1; n <= N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		int Q = Integer.parseInt(br.readLine());
		for (int q = 1; q <= Q; q++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			if (t == 1) {
				// 단절점, k번 정점이 단절점?
				if (adj.get(k).size() == 1) {
					System.out.println("no");
				} else {
					System.out.println("yes");
				}
			} else {
				// 단절선, k번 선이 단절선?
				System.out.println("yes");
			}
		}
	}
}

// 단절점 : 점 제거 -> 그래프 둘로 나뉨
// 단절선 :

// 단절점은 리프노드
// 자식이 1개면 단절점,
// 자식이 0개면 단절점,

//