import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : Yeogeru
 * @description : Brute Force
 * @since : 2025-08-03
 */
public class BOJ16637_G3 {
    static int[] number, symbol;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        number = new int[n / 2 + 1];
        symbol = new int[n / 2];
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                number[i / 2] = input[i] - '0';
                continue;
            }
            switch (input[i]) {
                case '+':
                    symbol[i / 2] = 1;
                    break;
                case '-':
                    symbol[i / 2] = 2;
                    break;
                case '*':
                    symbol[i / 2] = 3;
                    break;
            }
        }

        if (number.length == 1) max = number[0];
        if (number.length > 1) find(1, number[0]);

        System.out.println(max);

    }

    static void find(int index, int cur) {
        int next = calc(symbol[index - 1], cur, number[index]);
        if (index == number.length - 1) {
            max = Math.max(max, next);
            return;
        }

        find(index + 1, next);

        next = calc(symbol[index], number[index], number[index + 1]);
        next = calc(symbol[index - 1], cur, next);
        if (index + 1 == number.length - 1) {
            max = Math.max(max, next);
            return;
        }
        find(index + 2, next);

    }

    static int calc(int type, int first, int second) {
        switch (type) {
            case 1:
                return first + second;
            case 2:
                return first - second;
            case 3:
                return first * second;
        }
        return -1;
    }

}
