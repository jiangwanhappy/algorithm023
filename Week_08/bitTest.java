package bit;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/2/24 14:01
 * @Content
 */
public class bitTest {

    @Test
    public void test() {
        int n = -2147483648;
        System.out.println(isPowerOfTwo(n));
    }

    //191 位1的个数
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
/*题目限制：输入必须是长度为 32 的 二进制串 。
方法1、for loop : 0 --> 32
2、%2 --> x&1 ，x = x/2 --> (x=x>>1)
3、while (n != 0) {count++; x= x&(x-1)}这种方法的好处是有多少个1 就循环多少次
综上，使用方法三最佳
 */
        int count = 0;
        /*
        方法二：%2 --> x&1 ，x = x/2 --> (x=x>>1)
        循环32次
        */
//       while (n > 0) {
//           if ((n & 1)== 1) {
//               count++;
//               n = n >> 1;
//           }
//       }
       /*
       方法三：x&(x-1) 清零最低位的1
        有多少个1，就循环多少次
        */
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    //231 2的幂
    public boolean isPowerOfTwo(int n) {
/*
是否是 2 的幂次方，即二进制是否有且仅有一个1
 */
        if (n == 0) return false;
        long x = (long) n;//如测试n = -2147483648; 没有(long) n就会报错
        return (x & (x - 1)) == 0;
    }

    //190 颠倒二进制
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        /*
        方法一：将int --> 二进制 0101...--> string "010101..." -->int
        这样不符合计算机它的高效运算的规则，本身计算机就是二进制的保存和运算 ，这样转化来去 就是脱裤子放屁
        方法二：位运算
         */
        int result = 0;
        int tmp;
        for (int i = 0; i <= 31; i++) {
            tmp = n >> i;
            tmp = tmp & 1;//得到倒数第i位的值
            tmp = tmp << (31 - i);//将倒数第i位的值挪到前面第i位
            result |= tmp;//将第i位的值与之前的结果值拼接
        }
        return result;
    }

}
