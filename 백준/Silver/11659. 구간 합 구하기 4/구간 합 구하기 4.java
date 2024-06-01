import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		Integer N = Integer.parseInt(NM[0]);
		Integer M = Integer.parseInt(NM[1]);

		int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

		int[] partialSums = new int[N + 1];
		partialSums[0] = 0;
		for (int i = 1; i <= N; i++) {
			partialSums[i] = numbers[i - 1] + partialSums[i - 1];
		}

		for (int i = 1; i <= M; i++) {
			String[] locations = br.readLine().split(" ");
			int start = Integer.parseInt(locations[0]);
			int end = Integer.parseInt(locations[1]);

			System.out.println(partialSums[end] - partialSums[start - 1]);
		}
	}
}
