
/**
 * 背包问题扩展：项目投资
 * 5个项目可投资project，对应收益为gain。现在有资金total，问如何选择项目可获得最大收益
 * 2018-06-07
 *
 */
public class invest {

	public static void main(String[] args) {
		
		int project[]= {15,20,22,17,25};	//5个项目投资额
		int gain[]   = { 6, 7,4, 5, 8};		//5个项目收益
		
		int total =55;		//预算
		int [][]m = new int[5][total + 1];
		
		int []x=new int[5];	//记录物品装入情况，0表示不转入，1表示装入
		
		for(int i=1;i<=total;i++) {
			if(i>=project[0]) {
				m[0][i]=gain[0];
			}
		}

		for (int i = 1; i < project.length; i++) {
			for(int invest=1;invest<=total;invest++) {
				if(invest<project[i]) {
					//总额不能投资第i个项目			
					m[i][invest]=m[i-1][invest];
				}
				else {
					
					if(m[i-1][invest-project[i]]+gain[i] > m[i-1][invest]) {
						m[i][invest]=m[i-1][invest-project[i]]+gain[i];		//*1
					}else {
						m[i][invest]=m[i-1][invest];
					}
				}
			}
		}
		System.out.println("项目投资的最大收益："+m[project.length-1][total]);

		System.out.print("投资项目编号是：");
		for (int i = project.length-1; i > 0; i--) {
			//根据*1 33行做该判断
			if(m[i][total] > m[i-1][total]) {
				x[i]=1;
				total-=project[i];
			}
			else {
				x[i]=0;
			}

		}
		x[0]=m[0][total]>0?1:0;
		for(int i=0;i<project.length;i++){
			if(x[i] == 1)
				System.out.printf("%2d",(i+1));

		}
	}
}
