package sort;
/*
* 1, 使用回溯算法实现 集合中的组合算法
* 2，结果中（） 代表选中的结果
* */
public class Backtrack {
    static char[] chs = {'A', 'B', 'C', 'D', 'E'};
    static boolean[] bs = new boolean[chs.length];
    public static void main(String[] args) {
        backtrack(0, 3);
    }
    static void backtrack(int start, int k) {
        if( getSelected() == k ){
            String tmp = "";
            for(int i=0; i< chs.length; i++){
                if(bs[i]){
                    tmp += " ("+chs[i]+")";
                } else {
                    tmp += " "+chs[i];
                }
            }
            System.out.println(tmp);
            return;
        }
        for (int i=start; i< chs.length; i++ ){
            if(bs[i]){
                continue;
            }
            bs[i]=true;
            backtrack(i+1,k);
            bs[i]=false;
        }
    }
    static int getSelected() {
        int number = 0;
        for(int j = 0; j < bs.length; j++ ) {
            if (bs[j]==true) {
                number++;
            }
        }
        return number;
    }
}
