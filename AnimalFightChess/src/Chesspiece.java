//本类处理单个棋子事件
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Chesspiece extends JButton{
	int type;//类型从-8到8分为16个棋子
	int width;//棋子宽度
	public Chesspiece(int type, int Width){
		this.type = type;
		this.width = Width;
		//放入初始化灰色棋子
		setPreferredSize(new Dimension(Width,Width)); 
		ImageIcon tem = new ImageIcon("./src/image/chesspiece/0.png");
		Image img = tem.getImage();  
		Image newimg = img.getScaledInstance( Width, Width, java.awt.Image.SCALE_SMOOTH );
		tem = new ImageIcon( newimg );
		setIcon(tem);
		setContentAreaFilled(false);
		setOpaque(false);
		setBorderPainted(false);
	}
	//展示棋子正面
	public void showAnimal() {
		ImageIcon tem = new ImageIcon("./src/image/chesspiece/"+ type + ".png");
		Image img = tem.getImage();  
		Image newimg = img.getScaledInstance( width, width, java.awt.Image.SCALE_SMOOTH );
		tem = new ImageIcon( newimg );
		setIcon(tem);
	}
	//移除棋子
	public void removeAnimal() {
		ImageIcon tem = new ImageIcon("./src/image/chesspiece/99.png");
		Image img = tem.getImage();  
		Image newimg = img.getScaledInstance( width, width, java.awt.Image.SCALE_SMOOTH );
		tem = new ImageIcon( newimg );
		setIcon(tem);
	}
	//隐藏棋子
	public void hideAnimal() {
		ImageIcon tem = new ImageIcon("./src/image/chesspiece/0.png");
		Image img = tem.getImage();  
		Image newimg = img.getScaledInstance( width, width, java.awt.Image.SCALE_SMOOTH );
		tem = new ImageIcon( newimg );
		setIcon(tem);
	}
}