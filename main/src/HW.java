import java.util.*;

/**
 * Created by huangxiquan on 2020/5/24.
 *
 *
 */
public class HW {

    public static void main(String[] args) {
//        test03();
//        System.out.println(new Integer(3).equals(new Long(3)));
//        String str1 = "java";
//        StringBuilder str2 = new StringBuilder(str1);
//        str2.setCharAt(1,'b');
//        System.out.println(str1);
//        System.out.println(str2);
        System.out.print(1 << 32);

    }

    //矩阵旋转
    private static void test03() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for(int j = 0 ; j < n / 2 ; j++) {
            int m = n - j * 2;
            for(int i = 0 ; i < m - 1; i++) {
                swap(arr,j,i + j,i + j ,n-1-j);
                swap(arr,j,i + j,n - 1 - j ,n - 1 - i - j);
                swap(arr,j,i + j ,n - 1 - i - j ,j);
            }
        }
        printArr(arr);
    }

    private static void swap(int[][] arr, int srcX, int srcY, int desX, int desY) {
        int temp = arr[srcX][srcY];
        arr[srcX][srcY] = arr[desX][desY];
        arr[desX][desY] = temp;
    }


    private static void printArr(int[][] arr) {
        for(int i = 0 ; i < arr.length ; i++) {
            for(int j = 0 ; j < arr.length ; j++) {
                if(j == arr.length - 1) {
                    System.out.print(arr[i][j]);
                }else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            if(i != arr.length - 1 ) {
                System.out.println();
            }
        }
    }
    //字符串排序（长度+大小）
    private static void test02() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < input.length() ; i++) {
            char c = input.charAt(i);
            if(map.containsKey(c)) {
                map.put(c,map.get(c) + 1);
            }else {
                map.put(c,1);
            }
        }
        Set<Character> characters = map.keySet();
        ArrayList<Character> list = new ArrayList<>(characters);
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                if(map.get(a) - map.get(b) > 0) {
                    return -1;
                }else if(map.get(a) - map.get(b) < 0) {
                    return 1;
                }else {
                    return a - b;
                }

            }
        });
        for(int i = 0 ; i < list.size() ; i++) {
            Character c = list.get(i);
            Integer integer = map.get(c);
            for(int j = 0 ; j < integer ; j++) {
                System.out.print(c);
            }
        }
    }

    //股票收益最大
    private static void test01() {
        //7 1 5 3 6 4
        List<Integer> nums = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] split = input.split(" ");
        for(int i = 0 ; i < split.length ; i++) {
            nums.add(Integer.parseInt(split[i]));
        }
        int maxMoney = 0;
        for(int i = 0 ; i < nums.size() ; i++) {
            int max = 0;
            for(int j = i + 1 ; j < nums.size() ; j++) {
                if(nums.get(j) > max) {
                    max = nums.get(j);
                }
            }
            if((max - nums.get(i)) > maxMoney) {
                maxMoney = max - nums.get(i);
            }
        }
        System.out.println(maxMoney);
    }
}
