import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			List<Applicant> apps = new ArrayList<>();
			for (int n = 1; n <= N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				apps.add(new Applicant(a, b));
			}

			Collections.sort(apps);
			int minB = Integer.MAX_VALUE;
			int count = 0;
			for (Applicant app : apps) {
				if (minB > app.b) {
					count++;
					minB = app.b;
				}
			}

			System.out.println(count);
		}
	}

	public static class Applicant implements Comparable<Applicant> {
		int a;
		int b;

		public Applicant(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Applicant app) {
			return this.a - app.a;
		}
	}
}


// 1 2 3 4 5 6 7
// 4 5 6 2 7 1 3
// o x x o x o x
// 특정 라벨을 순서대로 돌면서
// 반대에 가장 큰 순서 기억 -> 업데이트면 뽑힘


// 서류심사 성적과 면접시험 성적 중 하나가 떨어지지 않는 사람.

// 특정 두명을 뽑았을 때 한 사람이 다른 사람에 비해 두 성적모두 떨어지면
// 제외

// 3 6은 2 5에게 밀림
// 7 3은 4 2에게 밀림
// 4 2 합격
// 1 4 합격
// 5 7은 3 6에게 밀림
// 2 5는 1 4에게 밀림
// 6 1 합격

// 1. 특정 A는 모든 다른 사람과 비교해 밀리는 게 있는 지 확인
// 2. 있으면 제외

// log N이나, o(n)만 가능
// 이분탐색 -> 정렬이 필요해
// 투포인터
// dp
//


// 특정 레이블에 대해 나보다 높은 사람에 대해 다른 것도 높은지

// 6 4 7 1 2 3 5
// 1 2 3 4 5 6 7