//本类把信息输出到记录板
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.multi.MultiScrollBarUI;
import javax.swing.text.ChangedCharSetException;

public class BoardText extends JLabel{
	int Turn=1;//1为红方回合 -1为蓝方回合
	public BoardText() {
		setHorizontalAlignment(CENTER);
		setForeground(Color.black);
		setFont(new java.awt.Font("Dialog", 1, 70));
		setText("WELCOME");
	}
	//棋子每移动一次都改变回合
	public void change(Map map, int Mode) {
		if(Turn > 0) {
			MusicMove teMove = new MusicMove();
			setForeground(Color.blue);
			setFont(new java.awt.Font("Dialog", 1, 70));
			setText("BLUE");
			Turn = -Turn;
			if(Mode == 1 && judge(map)!=1) {//如果是电脑的回合
				try {
					//Thread.sleep(1000); //电脑思考一秒钟
				} catch (Exception e) {
					e.printStackTrace();
				}
				AI.computerMove(map);
				Turn = -Turn;
				setForeground(Color.red);
				setFont(new java.awt.Font("Dialog", 1, 70));
				setText("RED");
			}
		}
		else {
			setForeground(Color.red);
			setFont(new java.awt.Font("Dialog", 1, 70));
			setText("RED");
			Turn = -Turn;
		}
		judge(map);//每次都判断有没有人赢
	}
	public int judge(Map map) {
		int flagBlue = 1;
		int flagRed = 1;
		int flagTie = 1;
		for(int i=0; i<4; i++)//遍历查看是否都大于／小于0
			for(int j=0; j<4; j++) {
				if(map.map[i][j]!=0) flagTie = 0;
				if(map.map[i][j]>0) flagBlue = 0;
				if(map.map[i][j]<0) flagRed = 0;
			}		
		if(flagTie == 1) {
			setForeground(Color.black);
			setFont(new java.awt.Font("Dialog", 1, 70));
			setText("TIE!");
			return 1;
		}
		if(flagBlue == 1) {
			setForeground(Color.blue);
			setFont(new java.awt.Font("Dialog", 1, 70));
			setText("BLUE WIN!");
			return 1;
		}
		if(flagRed == 1) {
			setForeground(Color.red);
			setFont(new java.awt.Font("Dialog", 1, 70));
			setText("RED WIN!");
			return 1;
		}
		return 0;
	}
}
