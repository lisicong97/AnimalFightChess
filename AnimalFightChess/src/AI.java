//单人模式下电脑下棋
public class AI {
	static void computerMove(Map map) {
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if(map.map[i][j]<0 && map.stateMap[i][j] != -1) {
					//能吃别人
					if(Check(map, i, j, 'n') == 4) {
						map.animalMap[i][j-1].type=map.animalMap[i][j].type;
						map.animalMap[i][j-1].showAnimal();
						map.stateMap[i][j-1]=1;
						map.map[i][j-1]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 's') == 4) {
						map.animalMap[i][j+1].type=map.animalMap[i][j].type;
						map.animalMap[i][j+1].showAnimal();
						map.stateMap[i][j+1]=1;
						map.map[i][j+1]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 'e') == 4) {
						map.animalMap[i+1][j].type=map.animalMap[i][j].type;
						map.animalMap[i+1][j].showAnimal();
						map.stateMap[i+1][j]=1;
						map.map[i+1][j]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 'w') == 4) {
						map.animalMap[i-1][j].type=map.animalMap[i][j].type;
						map.animalMap[i-1][j].showAnimal();
						map.stateMap[i-1][j]=1;
						map.map[i-1][j]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
				}
			}
		//能逃走
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if(map.map[i][j]<0 && map.stateMap[i][j] != -1) {
					if(Check(map, i, j, 'n') == 2) {
						if(Check(map, i, j, 's') == 1 ) {
							map.animalMap[i][j+1].type=map.animalMap[i][j].type;
							map.animalMap[i][j+1].showAnimal();
							map.stateMap[i][j+1]=1;
							map.map[i][j+1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'e') == 1 ) {
							map.animalMap[i+1][j].type=map.animalMap[i][j].type;
							map.animalMap[i+1][j].showAnimal();
							map.stateMap[i+1][j]=1;
							map.map[i+1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'w') == 1 ) {
							map.animalMap[i-1][j].type=map.animalMap[i][j].type;
							map.animalMap[i-1][j].showAnimal();
							map.stateMap[i-1][j]=1;
							map.map[i-1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
					}
					else if(Check(map, i, j, 's') == 2) {
						if(Check(map, i, j, 'n') == 1 ) {
							map.animalMap[i][j-1].type=map.animalMap[i][j].type;
							map.animalMap[i][j-1].showAnimal();
							map.stateMap[i][j-1]=1;
							map.map[i][j-1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'e') == 1 ) {
							map.animalMap[i+1][j].type=map.animalMap[i][j].type;
							map.animalMap[i+1][j].showAnimal();
							map.stateMap[i+1][j]=1;
							map.map[i+1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'w') == 1 ) {
							map.animalMap[i-1][j].type=map.animalMap[i][j].type;
							map.animalMap[i-1][j].showAnimal();
							map.stateMap[i-1][j]=1;
							map.map[i-1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
					}
					else if(Check(map, i, j, 'e') == 2) {
						if(Check(map, i, j, 'n') == 1 ) {
							map.animalMap[i][j-1].type=map.animalMap[i][j].type;
							map.animalMap[i][j-1].showAnimal();
							map.stateMap[i][j-1]=1;
							map.map[i][j-1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 's') == 1 ) {
							map.animalMap[i][j+1].type=map.animalMap[i][j].type;
							map.animalMap[i][j+1].showAnimal();
							map.stateMap[i][j+1]=1;
							map.map[i][j+1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'w') == 1 ) {
							map.animalMap[i-1][j].type=map.animalMap[i][j].type;
							map.animalMap[i-1][j].showAnimal();
							map.stateMap[i-1][j]=1;
							map.map[i-1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
					}
					else if(Check(map, i, j, 'w') == 2) {
						if(Check(map, i, j, 'n') == 1 ) {
							map.animalMap[i][j-1].type=map.animalMap[i][j].type;
							map.animalMap[i][j-1].showAnimal();
							map.stateMap[i][j-1]=1;
							map.map[i][j-1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 'e') == 1 ) {
							map.animalMap[i+1][j].type=map.animalMap[i][j].type;
							map.animalMap[i+1][j].showAnimal();
							map.stateMap[i+1][j]=1;
							map.map[i+1][j]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
						if(Check(map, i, j, 's') == 1 ) {
							map.animalMap[i][j+1].type=map.animalMap[i][j].type;
							map.animalMap[i][j+1].showAnimal();
							map.stateMap[i][j+1]=1;
							map.map[i][j+1]=map.map[i][j];
							
							map.animalMap[i][j].removeAnimal();
							map.stateMap[i][j]=0;
							map.map[i][j]=0;
							return;
						}
					}
				}
			}
		//翻面棋子
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if(map.stateMap[i][j] == -1) {
					map.animalMap[i][j].showAnimal();
					map.stateMap[i][j]=1;
					return;
				}
			}
		//随便走一步
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if(map.map[i][j]<0 && map.stateMap[i][j] != -1) {
					if(Check(map, i, j, 'n') == 1) {
						map.animalMap[i][j-1].type=map.animalMap[i][j].type;
						map.animalMap[i][j-1].showAnimal();
						map.stateMap[i][j-1]=1;
						map.map[i][j-1]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 's') == 1) {
						map.animalMap[i][j+1].type=map.animalMap[i][j].type;
						map.animalMap[i][j+1].showAnimal();
						map.stateMap[i][j+1]=1;
						map.map[i][j+1]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 'w') == 1) {
						map.animalMap[i-1][j].type=map.animalMap[i][j].type;
						map.animalMap[i-1][j].showAnimal();
						map.stateMap[i-1][j]=1;
						map.map[i-1][j]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
					else if(Check(map, i, j, 'e') == 1) {
						map.animalMap[i+1][j].type=map.animalMap[i][j].type;
						map.animalMap[i+1][j].showAnimal();
						map.stateMap[i+1][j]=1;
						map.map[i+1][j]=map.map[i][j];
						
						map.animalMap[i][j].removeAnimal();
						map.stateMap[i][j]=0;
						map.map[i][j]=0;
						return;
					}
				}
			}
		
	}
	public static int Check(Map map, int i, int j, char dir) {
		if(dir == 'n') {
			int GoNext = 0;
			if(j-1 < 0 || map.stateMap[i][j-1] == -1 || map.map[i][j-1]*map.map[i][j]>0) return 0;//走不通
			if(Math.abs(map.map[i][j-1])==8 && Math.abs(map.map[i][j])==1) GoNext = 1;
			if( map.stateMap[i][j-1] == 0) return 1;//可以走空格
			else if((Math.abs(map.map[i][j-1])==1 && Math.abs(map.map[i][j])==8) || (GoNext!=1)&&(Math.abs(map.map[i][j-1]) > Math.abs(map.map[i][j]))) return 2;//被吃
			else if( Math.abs(map.map[i][j-1]) == Math.abs(map.map[i][j])) return 3;//合并
			else return 4;//吃别人
		}
		if(dir == 's') {
			int GoNext = 0;
			if(j+1 == 4 || map.stateMap[i][j+1] == -1 || map.map[i][j+1]*map.map[i][j]>0) return 0;//走不通
			if(Math.abs(map.map[i][j+1])==8 && Math.abs(map.map[i][j])==1) GoNext = 1;
			if( map.stateMap[i][j+1] == 0) return 1;//可以走空格
			else if((Math.abs(map.map[i][j+1])==1 && Math.abs(map.map[i][j])==8) || (GoNext!=1)&&(Math.abs(map.map[i][j+1]) > Math.abs(map.map[i][j]))) return 2;//被吃
			else if( Math.abs(map.map[i][j+1]) == Math.abs(map.map[i][j])) return 3;//合并
			else return 4;//吃别人
		}
		if(dir == 'e') {
			int GoNext = 0;
			if(i+1 == 4 || map.stateMap[i+1][j] == -1 || map.map[i+1][j]*map.map[i][j]>0) return 0;//走不通
			if(Math.abs(map.map[i+1][j])==8 && Math.abs(map.map[i][j])==1) GoNext = 1;
			if( map.stateMap[i+1][j] == 0) return 1;//可以走空格
			else if((Math.abs(map.map[i+1][j])==1 && Math.abs(map.map[i][j])==8) || (GoNext!=1)&&(Math.abs(map.map[i+1][j]) > Math.abs(map.map[i][j]))) return 2;//被吃
			else if( Math.abs(map.map[i+1][j]) == Math.abs(map.map[i][j])) return 3;//合并
			else return 4;//吃别人
		}
		if(dir == 'w') {
			int GoNext = 0;
			if(i-1 < 0 || map.stateMap[i-1][j] == -1 || map.map[i-1][j]*map.map[i][j]>0) return 0;//走不通
			if(Math.abs(map.map[i-1][j])==8 && Math.abs(map.map[i][j])==1) GoNext = 1;
			if( map.stateMap[i-1][j] == 0) return 1;//可以走空格
			else if((Math.abs(map.map[i-1][j])==1 && Math.abs(map.map[i][j])==8) || (GoNext!=1)&&(Math.abs(map.map[i-1][j]) > Math.abs(map.map[i][j]))) return 2;//被吃
			else if( Math.abs(map.map[i-1][j]) == Math.abs(map.map[i][j])) return 3;//合并
			else return 4;//吃别人
		}
		//System.out.println("error!");
		return -1;
	}
}
