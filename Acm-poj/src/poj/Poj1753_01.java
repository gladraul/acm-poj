package poj;

import java.util.Scanner;
import java.util.Arrays;

/**
 * @author liuxd
 *
 */
public class Poj1753_01 {

    public static void main(String[] args) {        
        Scanner input = new Scanner(System.in);

        while(true) {
            char[] arrValue = new char[16]; 
            for(int i = 0; i < 4; i++) {
                if(input.hasNextLine()) {
                    String tmp = input.next();
                    for(int j = 0; j < 4; j++) {
                        arrValue[4 * i + j] = tmp.charAt(j);
                    }
                }
            }

            if(isGoal(arrValue)) {
                System.out.println("0");
                continue;
            }
            
            //w
            char[] arrW = Arrays.copyOf(arrValue, 16);    
            int stepW = flipArray(arrW, 'w');
            if(!wAll(arrW)) {
                stepW = 17;
            }
           
            //b
            char[] arrB = Arrays.copyOf(arrValue, 16);    
            int stepB = flipArray(arrB, 'b');
            if(!bAll(arrB)) {
                stepB = 17;
            }
            
            if(stepW != 17 || stepB != 17) {
                System.out.println(min(stepW, stepB));    
            }
            else {
                System.out.println("Impossible");
            }
        }    
    }

    //判断是否是目标状态
    private static boolean isGoal(char[] arr) {
        return wAll(arr) || bAll(arr);
    }

    //判断是否是全w
    private static boolean wAll(char[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 'w') {
                return false;
            }
        }
        return true;
    }

    //判断是否是全b
    private static boolean bAll(char[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 'b') {
                return false;
            }
        }
        return true;
    }

    //翻转字符
    private static char flip(char c) {
        if(c == 'w') {
            return 'b';
        }
        else {
            return 'w';
        }
    }

    //按照字符c翻转数组arr
    private static int flipArray(char[] arr, char c) {
        int step = 0;//达到全字符c的步数
        if(arr[0] != c) {
            arr[0] = c;
            arr[1] = flip(arr[1]);
            arr[4] = flip(arr[4]);
            step++; 
        }
            
        //位置5为起点
        for(int i = 5; i < 16; i++) {
            switch(i) {
            case 8:
                if(arr[4] != c) {
                    arr[4] = c;
                    arr[8] = flip(arr[8]);
                    arr[9] = flip(arr[9]);
                    arr[12] = flip(arr[12]);
                    step++;
                }
                break;

            case 7:
            case 11:
                if(arr[i - 4] != c) {
                    arr[i - 4] = c;
                    arr[i] = flip(arr[i]);
                    arr[i - 1] = flip(arr[i - 1]);
                    arr[i + 4] = flip(arr[i + 4]);
                    step++;
                }
                break;

            case 12:
                if(arr[8] != c) {
                    arr[8] = c;
                    arr[12] = flip(arr[12]);
                    arr[13] = flip(arr[13]);
                    step++;
                }
                break;

            case 13:
            case 14:
                if(arr[i - 4] != c) {
                    arr[i - 4] = c;
                    arr[i] = flip(arr[i]);
                    arr[i - 1] = flip(arr[i - 1]);
                    arr[i + 1] = flip(arr[i + 1]);
                    step++;
                }
                break;

            case 15:
                if(arr[11] != c) {
                    arr[11] = c;
                    arr[15] = flip(arr[15]);
                    arr[14] = flip(arr[14]);
                    step++;
                }
                break;

            default:
                if(arr[i - 4] != c) {
                    arr[i - 4] = c;
                    arr[i - 1] = flip(arr[i - 1]);
                    arr[i] = flip(arr[i]);
                    arr[i + 1] = flip(arr[i + 1]);
                    arr[i + 4] = flip(arr[i + 4]);
                    step++;
                }

                break;
            }
                
        }
        return step;
    } 

    //返回较小值
    private static int min(int a, int b) {
        if(a <= b) {
            return a;
        }
        return b;
    }
}
