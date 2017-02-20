package poj;

import java.util.Scanner;

/**
 * @author liuxd
 *
 */
public class Poj1014_01 {
    private static int[] aValNum;//保存输入值
    private static boolean flag = false;//标记递归是否结束，快速结束递归

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
                flag = false;//必须，不然flag状态和上次状态相同。
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

    //深度优先搜索
    //need表示中间状态，当need等于0时，表示搜索到解
    //index表示搜索的位置
    private static void dfs(int need, int index) {
        if(need == 0) {
            flag = true;
            return;
        } 
        
        if(flag == true) {
            return;
        }

        for(int i = index; i > 0; i--) {//将叶节点搜索一遍
            if(aValNum[i] > 0) {
                if(need >= i + 1) {
                    aValNum[i]--;
                    dfs(need - i - 1, i);
                    
                    if(flag == true) {
                        return;
                    }
                }
                else {//剪枝
                    i = need;
                }
            }        
        }

    }
}
