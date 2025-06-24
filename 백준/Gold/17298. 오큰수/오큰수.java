
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Stack<Num> s = new Stack();
		int[] result = new int[N];
		Arrays.fill(result, -1); // 채우는 거 어떻게 했더라, 배열정렬 설정 어떻게 하지
		for (int n = 0; n < N; n++) {
			while (!s.isEmpty() && s.peek().v < arr[n]) {
				Num num = s.pop();
				result[num.i] = arr[n];
			}
			s.push(new Num(n, arr[n]));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int n = 0; n < N; n++) {
			bw.write(result[n] + " ");
		}

		bw.flush();
	}

	public static class Num {
		int i;
		int v;

		public Num(int i, int v) {
			this.i = i;
			this.v = v;
		}
	}
}

