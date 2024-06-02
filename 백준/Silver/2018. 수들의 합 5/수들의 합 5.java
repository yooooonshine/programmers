import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int N = Integer.parseInt(br.readLine());

		int count = 0;
		int  sum = 0;
		int end = 1;

		while (sum < N) {
			sum += end;
			end ++;
		}
		if (sum == N) {
			count++;
		}
		sum -= 1;

		for (int i = 2; i <= N; i++) {
			if (sum > N) {
				while (sum > N) {
					sum -= end - 1;
					end --;
				}
			} else if (sum < N) {
				while (sum < N) {
					sum += end;
					end ++;
				}
			}
			if (sum == N) {
				count ++;
			}
			sum -= i;
		}

		System.out.println(count);
	}
}
