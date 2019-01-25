import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.ChangedCharSetException;


public class Initial extends JFrame{
	private JPanel chess = new ChessBackground();//棋盘
	private JPanel recorder = new Board();//记录比分窗口
	private BoardText boardText = new BoardText();//记录比分窗口
	int CacheX;//记录棋子移动前位置
	int CacheY;
	int  Mode;//单人、双人、联网
	ConnectToServer connect;
	
	public Initial(int chooseMode, Point Loc) {//初始化界面
		Mode = chooseMode;
		recorder.add(boardText);
		this.setLocation(Loc.x, Loc.y);
		this.setTitle("animal fight chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,600);
		this.setMinimumSize(new Dimension(420,460));
		GridBagLayout layout = new GridBagLayout();//设置为网格
        this.setLayout(layout); 
        this.add(recorder, new GBC(0,0).setFill(GBC.BOTH).setIpad(0, 0).setWeight(1, 1));  
        this.add(chess, new GBC(0,1).setFill(GBC.BOTH).setIpad(400, 120).setWeight(0, 0));
        AddChesspiece();//添加随机的棋子
        this.setVisible(true);
	}
	
	//GridBagConstraints的简便函数
	public class GBC extends GridBagConstraints{  
	   //初始化左上角位置  
	   public GBC(int gridx, int gridy){  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	   }
	   //初始化左上角位置和所占行数和列数  
	   public GBC(int gridx, int gridy, int gridwidth, int gridheight){  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	      this.gridwidth = gridwidth;  
	      this.gridheight = gridheight;  
	   }  
	   //对齐方式  
	   public GBC setAnchor(int anchor){  
	      this.anchor = anchor;  
	      return this;  
	   }  
	   //是否拉伸及拉伸方向  
	   public GBC setFill(int fill){  
	      this.fill = fill;  
	      return this;  
	   }
	   //x和y方向上的增量  
	   public GBC setWeight(double weightx, double weighty){  
	      this.weightx = weightx;  
	      this.weighty = weighty;  
	      return this;  
	   }
	   //外部填充  
	   public GBC setInsets(int distance){  
	      this.insets = new Insets(distance, distance, distance, distance);  
	      return this;  
	   }  
	   //外填充  
	   public GBC setInsets(int top, int left, int bottom, int right){  
	      this.insets = new Insets(top, left, bottom, right);  
	      return this;  
	   }
	   //内填充  
	   public GBC setIpad(int ipadx, int ipady){  
	      this.ipadx = ipadx;  
	      this.ipady = ipady;  
	      return this;  
	   }  
	}  
	
	//以下两个函数均用于在panel中加入元素
	public class ChessBackground extends JPanel {
		ImageIcon icon;
		Image img;
		public ChessBackground() {
			icon=new ImageIcon(getClass().getResource("/image/chessBackground.png"));
			img=icon.getImage();
			GridBagLayout layout = new GridBagLayout();//设置为网格布局
	        this.setLayout(layout); 
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}
	public class Board extends JPanel {
		ImageIcon icon;
		Image img;
		public Board() {
			icon=new ImageIcon(getClass().getResource("/image/board.png"));
			img=icon.getImage();
			BorderLayout layout = new BorderLayout();
	        this.setLayout(layout); 
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}
	//初始化地图，添加棋子
	public void AddChesspiece() {
		Map map = new Map(getWidth()/6);//棋子大小为宽的1/6
		if(Mode == 3) {
			connect = new ConnectToServer(map, boardText);
			try {
				connect.passMesg(connect.changeToStr(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				//加入4*4网格中
				chess.add(map.animalMap[i][j], new GBC(i,j).setFill(GBC.BOTH).setWeight(100, 100));
				MyActionListener myActionListener = new MyActionListener(i, j, map);//设置监听事件
				map.animalMap[i][j].addActionListener(myActionListener);
			}
	}
	
	class MyActionListener implements ActionListener{
		int i,j;
		Map map;
		public MyActionListener(int i, int j, Map map) {
			super();
			this.i=i;
			this.j=j;
			this.map=map;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Mode == 1 || Mode == 2 ||(Mode == 3 && connect.turn == 1)) {
				if(checkPush() == 0) {//没有已选棋子
					//如果棋子是暗的，亮出
					if(map.stateMap[i][j]==-1) {
						map.animalMap[i][j].showAnimal();
						map.stateMap[i][j]=1;
						boardText.change(map, Mode);
						if (Mode == 3) {
							try {
								connect.passMesg(connect.changeToStr(map));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					//如果有棋子，选中
					else if(map.stateMap[i][j]==1 && boardText.Turn*map.map[i][j]>0) {
						CacheX = i;
						CacheY = j;
						map.stateMap[i][j]=2;
					}
				}
				else if(checkPush() == 1 && map.stateMap[i][j]!=-1) {//已经选棋子
					if((i==CacheX&&j==CacheY+1)||(i==CacheX&&j==CacheY-1)||(i==CacheX+1&&j==CacheY)||(i==CacheX-1&&j==CacheY)) {//只能移动一格
						if(map.map[i][j] * map.map[CacheX][CacheY] <= 0) {//只能和敌人战斗
							int GoNext = 0;
							if(Math.abs(map.map[i][j])==8 && Math.abs(map.map[CacheX][CacheY])==1) GoNext = 1;
							if((Math.abs(map.map[i][j])==1 && Math.abs(map.map[CacheX][CacheY])==8) || (GoNext!=1)&&(Math.abs(map.map[i][j]) > Math.abs(map.map[CacheX][CacheY]))){//如果被吃了
								map.animalMap[CacheX][CacheY].removeAnimal();
								map.stateMap[CacheX][CacheY]=0;
								map.map[CacheX][CacheY]=0;
								boardText.change(map, Mode);
								if (Mode == 3) {
									try {
										connect.passMesg(connect.changeToStr(map));
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
							else if ( Math.abs(map.map[i][j]) == Math.abs(map.map[CacheX][CacheY])){//如果两个一样强，都消失
								map.animalMap[CacheX][CacheY].removeAnimal();
								map.stateMap[CacheX][CacheY]=0;
								map.map[CacheX][CacheY]=0;
								map.animalMap[i][j].removeAnimal();
								map.stateMap[i][j]=0;
								map.map[i][j]=0;
								boardText.change(map, Mode);
								if (Mode == 3) {
									try {
										connect.passMesg(connect.changeToStr(map));
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
							else {//如果吃了别人
								map.animalMap[i][j].type=map.animalMap[CacheX][CacheY].type;
								map.animalMap[i][j].showAnimal();
								map.stateMap[i][j]=1;
								map.map[i][j]=map.map[CacheX][CacheY];
								
								map.animalMap[CacheX][CacheY].removeAnimal();
								map.stateMap[CacheX][CacheY]=0;
								map.map[CacheX][CacheY]=0;
								
								boardText.change(map, Mode);
								if (Mode == 3) {
									try {
										connect.passMesg(connect.changeToStr(map));
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
						}
						else {
							map.stateMap[CacheX][CacheY]=1;//否则取消选择
						}
					}
					else {
						map.stateMap[CacheX][CacheY]=1;//否则取消选择
					}
				}
				else {
					map.stateMap[CacheX][CacheY]=1;//否则取消选择
				}
			}
		}
		
		//检查有没有已选择动物
		public int checkPush() {
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++) {
					if(map.stateMap[i][j]==2) {
						return 1;
					}
				}
			return 0;
		}
		
	}

}
