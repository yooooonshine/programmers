import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int[] C;
	public static int[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		C = new int[N + 1];
		S = new int[N + 1];

		List<Integer> sizeList = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken()); // 색
			int s = Integer.parseInt(st.nextToken()); // 크기
			sizeList.add(s);
			if (!map.containsKey(c)) {
				map.put(c, new ArrayList<>());
			}
			map.get(c).add(s);

			C[n] = c;
			S[n] = s;
		}

		Collections.sort(sizeList);
		for (int i : map.keySet()) {
			Collections.sort(map.get(i));
		}

		Map<Integer, List<Integer>> sum = new HashMap<>();
		for (int i : map.keySet()) {
			sum.put(i, new ArrayList<>());

			if (!map.get(i).isEmpty()) {
				sum.get(i).add(map.get(i).get(0));
				for (int j = 1; j < map.get(i).size(); j++) {
					sum.get(i).add(sum.get(i).get(j - 1) + map.get(i).get(j));
				}
			}
		}

		List<Integer> totalSum = new ArrayList<>();
		totalSum.add(sizeList.get(0));
		for (int i = 1; i < sizeList.size(); i++) {
			totalSum.add(totalSum.get(i - 1) + sizeList.get(i));
		}


		for (int n = 1; n <= N; n++) {
			int c = C[n];
			int s = S[n];

			int totalIndex = binarySearch(sizeList, s);
			if (totalIndex == -1) {
				System.out.println(0);
				continue;
			}
			int totalSumValue = totalSum.get(totalIndex);

			int index = binarySearch(map.get(c), s);
			if (index == -1) {
				System.out.println(totalSumValue);
				continue;
			}
			int sumValue = sum.get(c).get(index);

			int result = totalSumValue - sumValue;

			System.out.println(result);
		}
	}

	public static int binarySearch(List<Integer> list, int target) {
		int left = 0;
		int right = list.size() - 1;
		int result = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (list.get(mid) >= target) {
				right = mid +- 1;
			} else {
				result = mid; // 찾은 인덱스 저장
				left = mid + 1; // 더 작은 값을 찾기 위해 오른쪽 범위를 줄임
			}
		}

		return result; // 찾은 인덱스 반환
	}
}

// 나보다 크기가 작고 다른 색의 공 먹기 -> 그만큼 점수

// 각 플레이어가 잡을 수 있는 모든 공들 크기의 합


// 이분탐색
// 누적합

// 1. 색별로 분류 -> 200000
// 2. 정렬 -> 200000
// 3. 이분 탐색
// 4. 이후에 대해 누적합.(나보다 작은 )
