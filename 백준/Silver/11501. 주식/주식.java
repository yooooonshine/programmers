import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine()); // 날 수
            long[] prices = new long[N]; // 주가 배열

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Long.parseLong(st.nextToken());
            }

            // 뒤에서부터 최대 이익 계산
            long maxPrice = 0; // 현재까지의 최대 주가
            long profit = 0; // 총 이익

            for (int i = N - 1; i >= 0; i--) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i]; // 최대값 갱신
                } else {
                    profit += maxPrice - prices[i]; // 이익 계산
                }
            }

            bw.write(profit + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}