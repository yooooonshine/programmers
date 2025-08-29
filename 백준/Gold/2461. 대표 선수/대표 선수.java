
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학급의 수
		M = Integer.parseInt(st.nextToken()); // 학생의 수

		int min = Integer.MAX_VALUE;
		int max = 0;

		int[][] strs = new int[N + 1][M + 1];
		List<Student> list = new ArrayList<>();
		for (int n = 1;  n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				strs[n][m] = Integer.parseInt(st.nextToken());

				if (strs[n][m] < min) {
					min = strs[n][m];
				}
				if (strs[n][m] > max) {
					max = strs[n][m];
				}

				list.add(new Student(strs[n][m], n));
			}
		}

		Collections.sort(list);

		int s = 0;
		int e = max - min;

		int result = e;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (isPossible(mid, list)) {
				e = mid - 1;
				result = Math.min(result, mid);
			} else {
				s = mid + 1;
			}
		}
		System.out.println(result);
	}

	public static boolean isPossible(int mid, List<Student> list) {
		int s = 0;
		int e = 0;

		Map<Integer, Integer> freq = new HashMap<>();
		while (e < list.size()) {
			Student nowS = list.get(e);
			freq.put(nowS.cls, freq.getOrDefault(nowS.cls, 0) + 1);

			while (list.get(e).num - list.get(s).num > mid) {
				Student leftS = list.get(s);
				freq.put(leftS.cls, freq.get(leftS.cls) - 1);
				if (freq.get(leftS.cls) == 0) {
					freq.remove(leftS.cls);
				}
				s++;
			}

			if (freq.size() == N) {
				return true;
			}
			e++;
		}

		return false;
	}

	public static class Student implements Comparable<Student> {
		int num;
		int cls;

		public Student(int num, int cls) {
			this.num = num;
			this.cls = cls;
		}

		@Override
		public int compareTo(Student o) {
			return Integer.compare(this.num, o.num);
		}
	}
}

// 둘다 이분탐색해야 하네

// 전체를 한 리스트에 넣고,(반 번호를 명시해두자.)
// 최대가 요건 안넘도록 그 안에 모든 반 있는지