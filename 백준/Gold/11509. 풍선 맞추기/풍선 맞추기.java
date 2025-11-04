import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Integer>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> nums = new HashMap<>();
		for (int n = 1; n <= N; n++) {
			int v = Integer.parseInt(st.nextToken());

			if (nums.containsKey(v + 1)) {
				if (nums.get(v + 1) == 1) {
					nums.remove(v + 1);
				} else {
					nums.replace(v + 1, nums.get(v + 1) - 1);
				}


			}
			if (nums.containsKey(v)) {
				nums.replace(v, nums.get(v) + 1);
			} else {
				nums.put(v, 1);
			}
		}

		int result = 0;
		for (int n : nums.values()) {
			result += n;
		}
		System.out.println(result);
	}
}


// N개의 풍선
// 왼쪽에서 오른쪽으로 화살 쏨, 임의의 높이
// H에서 풍선을 마주칠 때까지 이동, 마주치면 풍선 삭제, 화살 높이 -1

// 가능한 적은 화살
