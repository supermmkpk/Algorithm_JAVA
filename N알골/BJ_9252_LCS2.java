import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 16380KB, 116ms
 */

/* <BJ9252_G4 : LCS2
 * <시간제한: 0.4초>
 * LCS(최장 공통 부분 수열): 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 *  Ex) ACAYKP와 CAPCAK의 LCS : ACAK
[입력]
1,2) 문자열. (알파벳 대문자, 길이 <= 1000)
[출력]
1) 두 문자열의 LCS의 길이
2) LCS
여러 가지인 경우에는 아무거나 출력, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.
*/
public class BJ_9252_LCS2 {
    static String given1, given2;
    static int[][] dp = new int[1004][1004];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        given1 = br.readLine();
        given2 = br.readLine();

        //LCS 길이 구하기
        int i = 0, j = 0;
        for(i = 0; i < given1.length(); i++) {
            for(j = 0; j < given2.length(); j++) {
                if(given1.charAt(i) == given2.charAt(j)) { //같으면 길이 증가
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else { //다르면 긴 거 따라가기
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        sb.append(dp[i][j] + "\n"); //길이 
        print(i, j); //LCS

        System.out.println(sb.toString() + "\n");
    }

    //LCS 출력하기(역으로 올라기기)
    static void print(int i, int j) {
        //종료조건!
        if(i < 0 || j < 0 || dp[i][j] == 0)
            return;

        //플랫하게!! 같으면 출력, 재귀적으로 같은 일을 반복한다!
        //거슬러 올라가기
        if(given1.charAt(i - 1) == given2.charAt(j - 1)) {
            print(i - 1, j - 1);
            sb.append(given1.charAt(i - 1));
        }
        else {
            if(dp[i][j - 1] > dp[i - 1][j])
                print(i, j - 1);
            else 
                print(i - 1, j);
        }
    }
}