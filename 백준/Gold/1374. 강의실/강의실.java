import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		List<Course> courses = new ArrayList<>();

		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int id = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			courses.add(new Course(id, s, e));
		}

		// 시작 순 정렬
		Collections.sort(courses);

		PriorityQueue<Result> results = new PriorityQueue<>();

		for (Course c : courses) {

			boolean change = false;
			Result tmpR = results.poll();
			if (tmpR != null) {
				if (tmpR.e <= c.s) {
					tmpR.e = c.e;
					change = true;
				}
				results.add(tmpR);
			}

			if (!change) {
				results.add(new Result(c.e));
			}
		}

		System.out.println(results.size());
	}

	public static class Course implements Comparable<Course> {
		int id;
		int s;
		int e;

		public Course(int id, int s, int e) {
			this.id = id;
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Course c) {
			return this.s - c.s;
		}
	}

	public static class Result implements Comparable<Result> {
		int e;

		public Result(int e) {
			this.e = e;
		}

		@Override
		public int compareTo(Result r) {
			return this.e - r.e;
		}
	}
}

// 필요한 강의실 수 최소
// 강의의 수 N
// N개의줄
// 강의 번호 강의 시작시간, 강의 종료 시간

// 3 2 14

// 1 3 8
// 4 12 18
// 7 20 25

// 8 6 27

// 5 6 20

// 2 7 13
// 6 15 21


// 시간이 빠른 순으로 하면 어쩌피 사이가 비어도 무관해
// 즉 시작 순으로 정력하고 끝나는 시간을 계속 저장해두면돼
// 그 리스트 중에, 시작 시간이 기존 끝나는 시간 뒤면 갱신하고 없으면 리스트 추가하고.
