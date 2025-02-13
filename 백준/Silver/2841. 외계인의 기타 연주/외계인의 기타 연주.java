
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer>[] pqs = new PriorityQueue[7];
		for (int i = 1; i <= 6; i++) {
			pqs[i] = new PriorityQueue<>((a, b) -> b - a);
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int line = Integer.parseInt(st.nextToken());
			int pret = Integer.parseInt(st.nextToken());

			while (!pqs[line].isEmpty() && pqs[line].peek() > pret) {
				pqs[line].poll();
				count++;
			}

			if (!pqs[line].isEmpty() && pqs[line].peek() == pret) {
				continue;
			}

			pqs[line].add(pret);
			count++;
		}

		System.out.println(count);
	}
}