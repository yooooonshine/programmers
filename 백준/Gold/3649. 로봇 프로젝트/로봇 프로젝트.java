import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;

		while ((str = br.readLine()) != null) {

			int X = Integer.parseInt(str) * 10000000;

			int N = Integer.parseInt(br.readLine());
			int[] pieces = new int[N];
			for (int n = 0; n < N; n++) {
				int tmp = Integer.parseInt(br.readLine());
				pieces[n] = tmp;
			}

			Arrays.sort(pieces);
			int s = 0;
			int e = N - 1;

			boolean flag = false;

			while (s < e) {
				int sum = pieces[s] + pieces[e];
				if (sum == X) {
					System.out.println("yes " + pieces[s] + " " + pieces[e]);
					flag = true;
					break;
				} else if (sum > X) {
					e--;
				} else {
					s++;
				}
			}

			if (!flag) {
				System.out.println("danger");
			}
			str = null;
		}
	}
}