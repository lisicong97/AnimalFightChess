//记录地图数据
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Map {
	int[][] map;
	int[][] stateMap;
	Chesspiece[][] animalMap;
	public Map(int Width) {
		map = new int[4][4];
		stateMap = new int[4][4];
		animalMap = new Chesspiece[4][4];
		List list = shuffle();
		Iterator ite = list.iterator();
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				map[i][j] = (int) ite.next();
				animalMap[i][j] = new Chesspiece(map[i][j], Width);
				stateMap[i][j] = -1;
			}
	}
	//打乱棋子
	public List shuffle() {
		int[] x = {-8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8};
		List list = new ArrayList();
		for(int i = 0;i < x.length;i++)
			list.add(x[i]);
		Collections.shuffle(list);
		return list;
	}
}
