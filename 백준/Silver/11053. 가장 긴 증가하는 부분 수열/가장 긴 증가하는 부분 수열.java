import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] intList = new int[N];
        int[] DP = new int[N];
        for (int i = 0; i < N; i++) {
            DP[i] = 1;
        }
        //intList[0]은 공백
        StringTokenizer st = new StringTokenizer(br.readLine()); // br.readLine을 통해 읽은 뭍자열을 쪼개주는 객체
        for (int i = 0; i < N; i++) {
            intList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){
                if(intList[i] > intList[j] && DP[i] < DP[j] + 1 ){
                    DP[i] = DP[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i : DP) {
            if (max < i) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
