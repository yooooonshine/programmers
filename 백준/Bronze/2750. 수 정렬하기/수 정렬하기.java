import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		// 머지소트
		mergeSort(arr, 0, N - 1);


		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int n = 0; n < N; n++) {
			bw.write(arr[n] + "\n");
		}
		bw.flush();
	}

	public static void mergeSort(int[] arr, int s, int e) {
		if (s >= e) return;

		int mid = (s + e) / 2;
		mergeSort(arr, s, mid);
		mergeSort(arr, mid + 1, e);

		int[] tmp = new int[e - s + 1];
		int tmpI = 0;
		int i1 = s;
		int i2 = mid + 1;

		while (i1 <= mid && i2 <= e) {
			if (arr[i1] <= arr[i2]) {
				tmp[tmpI++] = arr[i1++];
			} else {
				tmp[tmpI++] = arr[i2++];
			}
		}

		while (i1 <= mid) {
			tmp[tmpI++] = arr[i1++];
		}
		while (i2 <= e) {
			tmp[tmpI++] = arr[i2++];
		}

		for (int i = 0; i < tmp.length; i++) {
			arr[s + i] = tmp[i];
		}
	}
}