
/**
 * 动态规划：背包问题
 * 1   2   3   4   5   6   7   8   9  10

   0   0   6   6   6   6   6   6   6   6
   0   6   6   9   9   9   9   9   9   9
   0   6   6   9   9   9   9  11  11  14
   0   6   6   9   9   9  10  11  13  14
   0   6   6   9   9  12  12  15  15  15
 *
 *
 *	引用	https://www.nowcoder.com/discuss/3574
 *	 第二件物品可以被放入背包内，那么便会出现两种情况：
 *	（1）将第二件物品放入背包，那么背包中物品的最大价值是多少呢？因为第二件物品重量为weight[1]=2，在将第二件物品放入背包之前，
 *		背包的容量应为j-weight[1]=10-2=8，此时背包的最大价值是m[0][8]，因此若将第二件物品放入背包，其背包的最大价值m[1][j]=m[0][j-weight[1]]+v[1]；
 *	
 *	（2）不将第二件物品放入背包，那么此时背包中物品的最大价值依然为只放入第一件物品时背包的最大价值，即m[1][j]=m[0][j]；
 */
public class pack {

	
	public static void main(String[] args) {

		int []weight={2,2,6,5,4}; 	//物品重量
		int []v={2,3,5,4,6}; 		//物品价值
		int volume=10;				//背包容量
		int []x=new int[5];			//记录物品装入情况，0表示不转入，1表示装入
		x[0]=1; 					//初始值表示第一个物品已装入背包
		int [][]m=new int[5][volume+1];	//二组表，行代表装i件物品，列代表背包容量。其中第0列表示背包容量为0时背包的最大价值为0

		for(int i=1;i<=volume;i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		System.out.println();
		//初始化第一行，即背包中装入第一件物品
		for(int j=1;j<=volume;j++){
			if(j>=weight[0]){
				m[0][j]=v[0];
			}
			System.out.printf("%4d", m[0][j]);
		}
		System.out.println();

		//初始化二维表其它行
		for(int i=1;i<5;i++){

			for(int j=1;j<=volume;j++){
				//物品重量与背包容量比较
				if(j < weight[i]) {					
					m[i][j]=m[i-1][j]; 
				}else{
					//容量为j-weight[i]表示为第i件物品保留了其空间weight[i]
					//i>0时，递归关系m[i][j]=max{m[i-1][j-w[i]]+v[i]，m[i-1][j]}。
					if((m[i-1][j-weight[i]] + v[i]) > m[i-1][j]) {
						m[i][j]=m[i-1][j-weight[i]]+v[i]; //选择价值较大者
					}else {
						m[i][j]=m[i-1][j];
					}
				}

				System.out.printf("%4d", m[i][j]);
			}
			System.out.println();
		}
		System.out.println("背包的最大价值为："+m[weight.length-1][volume]);
		//根据以上的递归关系，找被pick的那个
		for(int i=4;i>=1;i--){
			if(m[i][volume]>m[i-1][volume]){
				x[i]=1; 		//装入背包
				volume-=weight[i]; 	//物品i装入背包之前背包的容量
			}else {
				x[i]=0; 		//没有装入背包
			}
		}
		x[0]=m[0][volume]>0?1:0;
		
		System.out.print("装入背包的物品编号是：");
		for(int i=0;i<5;i++){
			if(x[i]==1) {
				System.out.printf("%2d",(i+1));
			}
		}

	}

}
