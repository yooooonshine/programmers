import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] belt = new int[N + 1]; // 1부터
		for (int n = 1; n <= N; n++) {
			belt[n] = Integer.parseInt(br.readLine());
		}

		Map<Integer, Integer> riceCount = new HashMap<>();

		int s = 1;
		int e = s + k - 1;

		for (int i = s; i <= e; i++) {
			push(belt[i], riceCount);
		}
		push(c, riceCount);

		int max = 0;

		while (s <= N) {
			if (max < riceCount.size()) {
				max = riceCount.size();
			}

			pop(belt[s], riceCount);

			s++;
			if (e == N) {
				e = 0;
			}
			e++;

			push(belt[e], riceCount);

		}

		System.out.println(max);
	}

	public static void push(int v, Map<Integer, Integer> map) {
		if (map.containsKey(v)) {
			map.replace(v, map.get(v) + 1);
		} else {
			map.put(v, 1);
		}
	}

	public static void pop(int v, Map<Integer, Integer> map) {
		if (map.get(v) > 1) {
			map.replace(v, map.get(v) - 1);
		} else {
			map.remove(v);
		}
	}
}

// 한 위치부터 k개 -> 정액 가격
// 정액가격 시 쿠폰의 초밥 무료(없으면 생성)

// 초밥의 가짓수 최댓값


// NlogN까지

// 슬라이드 윈도우
// 있는 숫자 - 개수 딕셔너리
// s, e
// s++, e++
// s꺼 빼기, e꺼 추가하기
// 딕셔너리 개수 세기
// 미리 쿠폰은 넣어두기