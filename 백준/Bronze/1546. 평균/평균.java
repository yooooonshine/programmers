import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer N = Integer.valueOf(br.readLine());

		Float[] scores = Arrays.stream(br.readLine().split(" ")).map(Float::parseFloat).toArray(Float[]::new);

		ArrayList<Object> objects = new ArrayList<>();

		Float max = 0F;

		for (int i = 0; i < N; i++) {
			if(scores[i] > max) {
				max = scores[i];
			}
		}

		Float newAverage = 0F;
		for (int i = 0; i < N; i++) {
			newAverage += scores[i] / max * 100F;
		}
		newAverage = newAverage / N;

		System.out.println(newAverage);
	}
}
