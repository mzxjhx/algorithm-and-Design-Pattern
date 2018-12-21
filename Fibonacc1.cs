using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DesignPattern
{

    /// <summary>
    /// 
    /// </summary>
    public class Fibonacc1
    {

        //问题：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
        //解释：
        /**
         * 假设f(n)是n个台阶跳的次数:
         * f(1) = 1
         * f(2) 会有两种方式，1次1级或2级，跳了一级还剩一级，跳了两级还剩0级：f(2) = f(2-1) + f(2-2)
         * f(3) 会有三种方式，1阶、2阶、3阶，那么就是第一次跳出1阶后面剩下：f(3-1);第一次跳出2阶，剩下f(3-2)；第一次3阶，那么剩下f(3-3).因此结论是f(3) = f(3-1)+f(3-2)+f(3-3)
         * f(n) = f(n-1) + f(n-2)+f(n-3)+...+f(1)
         * f(n-1)=f(n-2) + f(n-2)+f(n-3)+...+f(1)
         * 由以上两式可得：f(n) = 2f(n-1)
         */

        /// <summary>
        /// 递归调用,此种方式时间和空间开销很大
        /// </summary>
        /// <param name="number"></param>
        /// <returns></returns>
        public int JumpFloorIdigui(int number)
        {
            if (number <= 0) {
                return 0;
            }
            else if (number == 1)
            {
                return 1;
            }
            else {
                Console.WriteLine(string.Format("number={0:G}", number));
                return 2 * JumpFloorIdigui(number - 1);
            }
        }

        /// <summary>
        /// 换种思路：n阶台阶好比n位二进制数，经过第i位时为1，不经过是为0。
        /// 设有8级台阶，直接跳过n级是00000001，这是一种跳法。一阶一阶跳则是11111111，是第二种跳法。跳过第一阶011111111，是第三种跳法。  但是，最终要跳到最后一阶，故最后一位必须为1
        /// 依次类推，8阶台阶的所有跳法总数=（8-1）位二进制数的所有组合，2^(8-1)次方。
        /// n阶则是2^(n-1)次方
        /// </summary>
        /// <param name="number"></param>
        /// <returns></returns>
        public int JumpFloorIPow(int number) {
            return (int)Math.Pow(2, number - 1);
        }

        /// <summary>
        /// 思路同上，位运算算法效率更高
        /// </summary>
        /// <param name="number"></param>
        /// <returns></returns>
        public int JumpFloorI(int number)
        {
            return 1 << --number;
        }

        //换种跳法：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
        /**
         * f(1) = 1
         * f(2) 会有两种方式，1次1级或2级，跳了一级还剩一级，跳了两级还剩0级：f(2) = f(2-1) + f(2-2)
         * f(3) = f(3-1) + f(3-2)
         **/

        /// <summary>
        /// 
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public long JumpFloorII1(int n)
        {

            if (n < 0)
                return 0;
            int[] fibArry = { 0, 1, 2 };
            if (n < 3)
                return fibArry[n];
            long nReturn = 0L;
            long fibFirst = 1L;
            long fibTow = 2L;   //两种，1次1阶或一次两阶跳完
            for (int i = 3; i <= n; i++)
            {
                nReturn = fibFirst + fibTow;
                fibFirst = fibTow;
                fibTow = nReturn;
            }
            return nReturn;

        }

        /// <summary>
        /// 递归算法的数组表示
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public long JumpFloorII2(int n) 
        {
            long[] fn = new long[n + 1];
            fn[0] = 0;
            fn[1] = 1;
            fn[2] = 2;
            for (int i = 3; i <= n; i++)
            {
                fn[i] = fn[i - 1] + fn[i - 2];
            }
            return fn[n];
        }

    }
}
