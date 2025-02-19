class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        for (int i = deliveries.length - 1; i >= 0;) {
            // 배달 개수와 수거 개수가 존재하지 않을때 다음 index 탐색
            if (deliveries[i] == 0 && pickups[i] == 0) {
                i--;
                continue;
            }

            calcul(cap, deliveries, i);
            calcul(cap, pickups, i);

            // 배달과 수거가 같이 이루어 지므로 (이동 거리 * 2)가 총 이동 거리
            answer += (i + 1) * 2;
        }

        return answer;
    }

    private void calcul(int cap, int[] arr, int index) {
        // 배달 : 물류 창고에서 index의 집까지 가면서 총 배달 할 수 있는 개수
        // 수거 : index의 집에서 물류 창고까지 돌아오면서 총 수거 할 수 있는 개수
        while (index >= 0) {
            if (cap >= arr[index]) {
                cap -= arr[index];
                arr[index--] = 0;
            } else {
                arr[index] -= cap;
                break; // 더 이상 배달이나 수거가 불가능하면 멈춤
            }
        }
    }
}
// for문을 위에서 아래로
// 둘다 0이면 --
// 아니면 밑으로 내리면서 모두 제거

// arr값이 cap이상이면 arr - cap, return
// arr값이 cap 이하면 arr=0, cap-arr