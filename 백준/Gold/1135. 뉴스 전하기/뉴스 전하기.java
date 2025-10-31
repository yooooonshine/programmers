import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Integer>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 트리
		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int tmp = Integer.parseInt(st.nextToken());

			if (tmp == -1) {
				continue;
			}

			adj.get(tmp).add(n);
		}

		int result = recursion(0);
		System.out.println(result);
	}

	public static int recursion(int n) {
		List<Integer> times = new ArrayList<>();

		if (adj.get(n).size() == 0) {
			return 0;
		}

		for (Integer next : adj.get(n)) {
			times.add(recursion(next));
		}

		int max = 0;
		Collections.sort(times, Collections.reverseOrder());
		for (int t = 1; t <= times.size(); t++) {
			if (max < times.get(t - 1) + t) {
				max = times.get(t - 1) + t;
			}
		}

		return max;
	}
}

// 뉴스 전달
// 트리
// 직원은 한명의 직속상사

// 민식이가 루트
// 1분
// 모든 직원이 소식을 듣는데 걸리는 최솟값
//
