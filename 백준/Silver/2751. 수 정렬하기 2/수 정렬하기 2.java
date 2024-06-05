import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static int[] A;
	public static int[] tmpA;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		A = new int[N];
		tmpA = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		mergeSort(0, N - 1);

		for (int i = 0; i < N; i++) {
			bw.write(A[i] + "\n");
		}

		bw.flush();
		bw.close();
	}

	public static void mergeSort(int start, int end) {
		int mid = (start + end) / 2;
		if (end - start < 1) {
			return;
		}
		mergeSort(start, mid);
		mergeSort(mid + 1, end);

		int a = start;
		int b = mid + 1;

		for (int i = start; i <= end; i++) {
			tmpA[i] = A[i];
		}

		int index = start;
		while (a <= mid && b <= end) {
			if ( tmpA[a] <= tmpA[b]) {
				A[index] = tmpA[a];
				a++;
				index++;
			} else {
				A[index] = tmpA[b];
				b++;
				index++;
			}
		}

		while (a <= mid) {
			A[index] = tmpA[a];
			a++;
			index++;
		}
		while (b <= end) {
			A[index] = tmpA[b];
			b++;
			index++;
		}
	}
}
