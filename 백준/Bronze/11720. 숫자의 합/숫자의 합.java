import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Long[] numbers = new Long[N];

		numbers = Arrays.stream(br.readLine().split("")).map(Long::parseLong).toArray(Long[]::new);

		Long sum = 0L;
		for (Long number : numbers) {
			sum += number;
		}

		System.out.println(sum);
	}
}