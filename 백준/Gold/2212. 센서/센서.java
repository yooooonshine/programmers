import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 센서 개수
		int K = Integer.parseInt(br.readLine()); // 집중국 개수

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(arr);

		int max = arr[arr.length - 1] - arr[0];

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			return b - a;
		});
		for (int i = 0; i <= arr.length - 2; i++) {
			pq.add(arr[i + 1] - arr[i]);
		}

		for (int i = 1; i<= K - 1; i++) {
			if (pq.isEmpty()) {
				break;
			}
			int tmp = pq.poll();

			max -= tmp;
		}

		System.out.println(max);
	}
}

// N개의 센서
// K개의 집중국
// 각 집중국의 수신 가능 영역의 길이의 합 최소 -> 최소 신장 트리? X

// 원점 거리
// 주의 모든 센서의 좌표가 다를 필요 없다..?

// 1 3 6 7 9
// 거리의 합
// 3 -1 + 9 - 6 = 5
// 3 / 6 7 8 / 10 12 14 15 / 18 / 20
// 18-20 14-15 6-8 10-12 3

// 17 - 3 - 2 - 3 - 2 = 7

// n - 1번 깍기

// 좌표들 중복 제거
// 좌표들 오름차순 정렬 (최대 거리 구하기)
// 좌표 간격들을 저장(좌표 및 간격) 간격순대로 정렬(내림차순)
// 간격 N - 1 빼기