import java.util.Map;
import java.util.Stack;

public class LeeCode {
    /**
     * 使括号有效的最小添加
     * 遍历字符串 若遇到啊左括号 则进行入栈
     * 遇到右括号的话 1 栈为空 计数器加一
     *               2 栈不空 栈顶元素进行出栈
     * @param S 括号的字符串
     * @return 使括号有效的最小添加数
     */
    public static int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(int i = 0 ;i<S.length();i++){
            char c = S.charAt(i);
            if(c=='('){
                stack.push(c);
            }else {
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    count++;
                }
            }
        }
        return count+stack.size();
    }
    //判断一个数组是不是132类型 即下标是i<j<k 值a1<a3<a2；
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int last = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = n-1;i>=0;i--){
            if(last > nums[i]){
                return true;
            }
            while(!stack.isEmpty()&&nums[i]>stack.peek()){
                last = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
    /**
     * 出现的第一个‘（’一定是 最外层的字符，所以设置一个标志位Start作为开始分割的标记
     * 而后依次将左括号入栈，若出现右括号一直出栈 直到栈为空
     * 栈为空说明最后一个出栈的为最外层的右括号 所以将这个括号出现的位置进行end标记
     * 最后将源字符串进行start--end分割即可。
     *  将给定的有效括号字符串进行原语化分解
     * @param S 有效括号字符串
     * @return 分解后的字符串
     */
    public static String removeOuterParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        int start = 0;
        int end;
        StringBuilder res = new StringBuilder();
        boolean flag = false;
        for(int i = 0 ;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch == '('){
                stack.push(ch);
                if(!flag){
                    start = i;
                    flag = true;
                }
            }
            if(ch == ')'){
                stack.pop();
                if(stack.isEmpty()){
                    end = i;
                    res.append(S, start+1, end);
                    flag = false;
                    start = end;
                }
            }
        }
        return  res.toString();
    }
    /**
     *以Unix风格给出一个文件的绝对路径 把他改成规范路径
     * 将这个字符串首先用“/”分割开 而后根据条件进行入栈和出栈，最后进行栈内的元素改成规范路径
     * @param path 给定路径
     * @return  规范路径
     */
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String []s = path.split("/");
        for(int i = 0 ; i<s.length;i++){
            if(!stack.isEmpty()&& s[i].equals("..")){
                stack.pop();
            }else  if(!s[i].equals("")&&!s[i].equals(".")&& !s[i].equals("..")){
                stack.push(s[i]);
            }
        }
        if(stack.isEmpty()){
            return "/";
        }
        StringBuilder res  = new StringBuilder();
        for (String aStack : stack) {
            res.append("/").append(aStack);
        }
        return res.toString();
    }
    //计算数组中存储雨水的问题
    //根据每一行进行计算能蓄水的多少  然后进行叠加就能求出总的蓄水量
    public int trap1(int[] height) {
        int sum = 0;
        int max = getMax(height);
        for(int i = 1;i<=max ;i++){
            boolean isstart = false;
            int temp = 0;
            for(int j = 0; j<height.length;j++){
                if(isstart && height[j]<i){
                    temp++;
                }
                if(height[j]>=i){
                    sum += temp;
                    isstart = true;
                    temp = 0;

                }
            }
        }
        return sum;
    }
    private int getMax(int[] height) {
        int max = 0 ;
        for (int aHeight : height) {
            if (max < aHeight) {
                max = aHeight;
            }
        }
        return max;
    }
    //按照列去遍历，每一个列所能存储的水是当前列和左边最大右边最大的最小值的差值
    //所以只需要关心当前列 和当前左边最大的列，  当前右边最大的列 根据木桶效应就可求得
    //最后所能存储的水是每一列所能存储的水的和
    public int trap2(int[] height) {
        int sum = 0;
        for(int i = 1; i <height.length-1 ;i++){
            int max_left = 0;
            for(int j = i-1 ; j>=0 ; j--){
                if(max_left <height[j]){
                    max_left = height[j];
                }
            }
            int max_right = 0 ;
            for(int j = i+1 ; j< height.length;j++){
                if(max_right <height[j]){
                    max_right = height[j];
                }
            }
            int min = Math.min(max_left, max_right);
            if(min >height[i]){
                sum += (min - height[i]);
            }
        }
        return  sum;
    }

    //动态规划，将当前最大的左边和最大的右边保存在数组中 不需要重复计算当前的左边和右边最大的点
    public int trap3(int[] height) {
        int sum = 0 ;
        int []max_left = new int[height.length];
        int []max_right = new int[height.length];
        for(int i = 1 ;i<height.length-1 ; i++){
            max_left[i] = Math.max(max_left[i-1],height[i-1]);
        }
        for(int i = height.length-2;i>=0;i--){
            max_right[i] = Math.max(max_right[i+1],height[i+1]);
        }
        //两个边界的墙无法蓄水 所以不包含两个边界点
        for(int i =1 ; i<height.length -1; i++){
            int min = Math.min(max_left[i],max_right[i]);
            sum+=(min - height[i]);
        }
        return sum;
    }
    //方法三的优化，减少空间复杂度，不使用数组进行存储
    public int trap4(int[] height) {
        int sum = 0;
        int left= 1;
        int right = height.length-2;
        int max_left = 0;
        int max_right = 0;
        for(int i = 1 ; i<height.length-1;i++){
            if(height[left-1]<height[right+1]){
                 max_left = Math.max(height[left-1],max_left);
                 int min = max_left;
                if(min>height[left]){
                    sum+=(min - height[left]);
                }
                left++;
            }else{
                 max_right =Math.max(height[right+1],max_right);
                 int min = max_right;
                if(min > height[right]){
                    sum+= (min - height[right]);
                }
                right --;
            }
        }
        return sum;
    }
    /*
    使用栈进行存储，当遍历到墙的高度的时候，如果当前的高度小于栈顶的高度 ，说明会有积水。入栈
    如果当前的高度大于栈顶的高度，则可以计算有多少积水，计算完，将当前的墙继续入栈
     */
    public int trap5(int[] height) {
         int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0 ;
        while(current <height.length){
            while(!stack.isEmpty()&& height[current]>height[stack.peek()]){
                int h = height[stack.peek()];
                stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                int distance = current - stack.peek()-1;
                int min = Math.min(height[stack.peek()],height[current]);
                sum = sum+distance*(min-h);
            }
            stack.add(current);
            current++;
        }
        return sum;
    }
    public static void main(String[] args) {
        int []arr = {2,0,2};
        LeeCode l = new LeeCode();
        System.out.println(l.trap5(arr));
        System.out.println(LeeCode.simplifyPath("/home/"));
        System.out.println(LeeCode.removeOuterParentheses("(()())(())"));
        System.out.println(LeeCode.minAddToMakeValid("()())("));
    }
}
