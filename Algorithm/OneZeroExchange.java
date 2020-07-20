package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合算法
 * 在线性代数中使用 0|1置换法得到排列组合
 * 描述：
 *1、开一个数组a[m]，其下标表示1到m个数
 *  2、数组元素的值为1表示其下标代表的元素被选中，为0则没选中。
 *  3、首先初始化，将数组a[0]~a[n-1]个元素置1，a[n]~a[m-1]置0，由此得到第一个组合
 *  4、从左到右扫描，找到a[i]=1,a[i+1]=0的组合 ，找到第一个这种组合后将其值对换，同时将a数组i左边的所有“1”全部移动到数组的最左端
 *  5、当第一个“1”移动到数组的m-n的位置，即n个“1”全部移动到最右端时，就得 到了最后一个组合。
 *  比如组合 [A,B,C,D,E]中5取3的组合情况，按算法步骤如下：
 *  1 1 1 0 0
 *  1 1 0 1 0
 *  1 0 1 1 0
 *  0 1 1 1 0 : 此时 i=3，转换为  0 1 1 0 1 ，然后移动左侧的1， 0 1 1 0 1 ->  1 1 0 0 1
 *  1 1 0 0 1
 *  1 0 1 0 1
 *  0 1 1 0 1
 *  1 0 0 1 1
 *  0 1 0 1 1
 *  0 0 1 1 1
 */
public class OneZeroExchange {

    //返回n中选m的组合序列 1<=m<=n
    public List<int[]> generateCombination(int n, int m) {
        List<int[]> list = new ArrayList<>();
        int[] tempNum = new int[n];
        boolean flag; // 判断算法的结束
        // 初始化数组，同时得到第一组组合系列
        for (int i = 0; i < n; i++) {
            if (i < m) {
                tempNum[i] = 1;
            } else {
                tempNum[i] = 0;
            }
        }
        int[] b = tempNum.clone();
        list.add(b);
        if (n == m) {
            return list;
        }
        do {
            int pose = 0; // 记录改变的位置
            int sum = 0; // 记录改变位置 左侧 1 的个数
            // 然后从左到右扫描数组元素值的“10”组合，找到第一个“10”组合后将其变为“01”
            for (int i = 0; i < (n - 1); i++) {
                if (tempNum[i] == 1 && tempNum[i + 1] == 0) {
                    tempNum[i] = 0;
                    tempNum[i + 1] = 1;
                    pose = i;
                    break;
                }
            }
            // 同时将其左边的所有“1”全部移动到数组的最左端。
            for (int i = 0; i < pose; i++) {
                if (tempNum[i] == 1)
                    sum++;
            }
            for (int i = 0; i < pose; i++) {
                if (i < sum)
                    tempNum[i] = 1;
                else
                    tempNum[i] = 0;
            }
            // 判断是否为最后一个组合：当第一个“1”移动到数组的m-n的位置，即n个“1”全部移动到最右端时，就得到了最后一个组合。
            flag = false;
            for (int i = n - m; i < n; i++) {
                if (tempNum[i] == 0)
                    flag = true;
            }
            int[] a = tempNum.clone();
            list.add(a);
        } while (flag);
        return list;
    }

    public void displayCombinationObject(String[] object, int n, int m) {
        System.out.println("------" + n + "中选" + m + "的组合的种类总数有："
                + countOfCombination(n, m) + "--------");
        List<int[]> list = generateCombination(n, m);
        String s = "";
        int j;
        int[] a;
        for (int i = 0; i < list.size(); i++) {
            a = list.get(i);
            for (j = 0; j < a.length; j++) {
                if (1 == a[j]) {
                    s += ("\t(" + object[j] + ")");
                } else {
                    s += ("\t" + object[j] );
                }
            }
            System.out.println(s);
            s = "";
        }
    }

    //返回n中选m的组合的种类总数
    public int countOfCombination(int n, int m) {
        int sum = 1;
        int div = 1;
        for (int i = n; i >= (n - m + 1); i--) {
            sum *= i;
        }
        for (int j = 1; j <= m; j++) {
            div *= j;
        }
        return sum / div;
    }

    public static void main(String[] args) {
        OneZeroExchange pac = new OneZeroExchange();
        String[] object5 = { "A", "B", "C", "D", "E" , "F", "G"};
        pac.displayCombinationObject(object5, 7, 4);
    }

}
