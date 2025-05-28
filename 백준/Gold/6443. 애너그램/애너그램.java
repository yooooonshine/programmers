
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //단어의 개수

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int n = 1; n <= N; n++) {
			String[] input = br.readLine().split(""); // 입력 배열 오름 순
			boolean[] visit = new boolean[input.length]; // 방문배열

			String[] result = new String[input.length]; // 결과 배열

			Arrays.sort(input);

			recursion(input, visit, result, 0, bw); // 재귀 호출
		}
		bw.flush();
	}

	public static void recursion(String[] input, boolean[] visit, String[] result, int index, BufferedWriter bw) throws Exception {
		if (index >= input.length) {
			for (String s : result) {
				bw.write(s);
			}
			bw.write("\n");
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (visit[i]) continue; // 방문한 경우

			visit[i] = true; // 방문 처리
			result[index] = input[i]; // 결과에 추가

			recursion(input, visit, result, index + 1, bw); // 다음 인덱스 호출

			visit[i] = false; // 방문 취소

			// 다음이 현재와 같다면 같지 않을때 까지 i 이동
			while (i + 1 < input.length && input[i].equals(input[i + 1])) {
				i++;
			}
		}
	}
}