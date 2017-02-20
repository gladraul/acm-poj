package poj;

import java.util.Scanner;

/**
 * @author liuxd
 *
 */
public class Poj1014_01 {
    private static int[] aValNum;
    private static boolean flag = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = 0;
	while(input.hasNext()) {
            k++;
            aValNum = new int[6];
            String[] aInput = input.nextLine().split(" ");
            int sum = 0;
            for(int i = 0; i < 6; i++) {
                aValNum[i] = Integer.parseInt(aInput[i]);
                sum += aValNum[i] * (i + 1);
            }

            if(sum == 0) {
                break;
            }
            
            int need = (sum % 2 == 0 ? sum / 2 : -1);		
            System.out.println(new StringBuilder("Collection #").append(k).append(":"));
            if(need == -1) {
                System.out.println("Can't be divided.");
            }
            else { 
                flag = false;
                dfs(need, 5);
                if(flag == true) {
                    System.out.println("Can be divided.");
                }
                else {
                    System.out.println("Can't be divided.");
                }
            }
			
            System.out.println();
        }
    }

    private static void dfs(int need, int index) {
        if(need == 0) {
            flag = true;
            return;
        } 
        
        if(flag == true) {
            return;
        }

        for(int i = index; i > 0; i--) {
            if(aValNum[i] > 0) {
                if(need >= i + 1) {
                    aValNum[i]--;
                    dfs(need - i - 1, i);
                    
                    if(flag == true) {
                        return;
                    }
                }
                else {
                    i = need;
                }
            }        
        }

    }
}
