import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Time> times = new PriorityQueue<>();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			times.add(new Time(s, e));
		}

		PriorityQueue<Integer> resultQ = new PriorityQueue<>();

		Time firstT = times.poll();
		resultQ.add(firstT.e);

		while (!times.isEmpty()) {
			Time tmpT = times.poll();

			int s = tmpT.s;
			int e = tmpT.e;

			if (resultQ.peek() <= s) {
				resultQ.poll();
			}
			resultQ.add(e);
		}

		System.out.println(resultQ.size());
	}

	public static class Time implements Comparable<Time> {
		int s;
		int e;

		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Time time) {
			return this.s - time.s;
		}
	}
}