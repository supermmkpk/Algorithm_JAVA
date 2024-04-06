import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        결과: 33440KB, 192ms
 */

/*
 * <BJ2529_S1 : 부등호>
 * <시간제한: 1초>
 * 두 부등호 ‘<’ , ‘>’가 k개 나열된 순서열 A가 있다.
 * 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려 한다.
 * 넣을 수 있는 숫자는 0~9 정수이며 선택된 숫자는 모두 달라야 한다.
 * 이때, 부등호 기호를 제거한 뒤, 숫자를 모두 붙였을 때 나오는 여러 정수 중 최댓값과 최솟값을 찾자.
 * [입력]
 * 1) 부등호 개수 k. (2 ≤ k ≤ 9)
 * 2) k개의 부등호 기호.
 * [출력]
 * 1) k+1 자리의 최대
 * 2) 최소 정수
 * (첫 자리가 0인 경우도 정수에 포함되어야 한다)
 */
public class Main_2529_부등호_박봉균 {
    static int K;
    static char[] oper;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 숫자 개수
        oper = new char[K + 4]; // 부등호 배열

        // 부등호 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            oper[i] = st.nextToken().charAt(0);
        }

        permu(0, 0, ""); // 숫자열 구하기

        Collections.sort(list); // 자릿수가 K+1로 고정이므로 문자열 정렬해도 됨

        bw.write(String.valueOf(list.get(list.size() - 1)) + '\n');
        bw.write(String.valueOf(list.get(0)) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    static void permu(int visited, int cnt, String num) {
        // 종료조건
        // 길이 K+1인 경우
        if (cnt == K + 1) {
            list.add(num);
            return;
        }

        // 조건을 만족시키는 0~9 순열 구하기(중복X)
        for (int i = 0; i <= 9; i++) {
            if ((visited & 1 << i) != 0)
                continue;

            if (cnt == 0 || check(num.charAt(cnt - 1), (char) (i + '0'), oper[cnt - 1])) {
                permu(visited | 1 << i, cnt + 1, num + String.valueOf(i));
            }
        }
    }

    // 부등호 관계를 만족하는지 확인하는 함수
    static boolean check(char x, char y, char op) {
        if (x < y && op == '<')
            return true;
        if (x > y && op == '>')
            return true;
        return false;
    }
}
