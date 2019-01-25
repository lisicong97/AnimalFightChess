//进入选择界面
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class Start extends JFrame {
	int choose;//记录用户选项 单人、双人、联网

	public Start() {
		this.setTitle("animal fight chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420,600);
		this.setMinimumSize(new Dimension(400,600));
		GridBagLayout layout = new GridBagLayout();//设置为网格
		this.setLayout(layout); 
		Background background = new Background();
	    this.add(background, new GBC(0,0).setFill(GBC.BOTH).setIpad(400, 600).setWeight(0, 0));  //添加背景
	    //标题
	    Title title = new Title();
	    title.setBounds(0, 0, 400, 300);
	    background.add(title);
	    //三个按钮
	    Choose[] choose = new Choose[4];
	    for(int i=1; i<4; i++) {
	    		choose[i] = new Choose(i);
	    		choose[i].setBounds(100, 250+(i-1)*100, 200, 100);
	    		background.add(choose[i]);
	    		ChooseActionListener CAL = new ChooseActionListener(this, i);//设置监听事件
			choose[i].addActionListener(CAL);
	    }
		this.setVisible(true);
	}
	class ChooseActionListener implements ActionListener{
		int i;
		Start start;
		public ChooseActionListener(Start start, int i) {
			this.i = i;
			this.start = start;
			choose = i;//传递对战模式
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//选择单人
			if(i == 1) {
				Initial initial=new Initial(i, start.getLocation());
				start.dispose();//关闭开始界面
			}
			//选择双人
			if(i == 2) {
				Initial initial=new Initial(i, start.getLocation());
				start.dispose();
			}
			//选择联网
			if(i == 3) {
				Initial initial = new Initial(i, start.getLocation());
				start.dispose();
			}
		}
	}
	//设置背景图
	public class Background extends JPanel {
		ImageIcon icon;
		Image img;
		public Background() {
			icon=new ImageIcon(getClass().getResource("/image/start.png"));
			img=icon.getImage();
			//GridBagLayout layout = new GridBagLayout();//设置为网格布局
	        this.setLayout(null); 
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}
	
	public class Title extends JPanel {
		ImageIcon icon;
		Image img;
		public Title() {
			icon=new ImageIcon(getClass().getResource("/image/title.png"));
			img=icon.getImage();
			setOpaque(false);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}
	public class Choose extends JButton{
		int type;//类型123代表单人、双人、联网
		public Choose(int type){
			this.type = type;
			setPreferredSize(new Dimension(200,100)); 
			ImageIcon tem = new ImageIcon("./src/image/choose" + type + ".png");
			Image img = tem.getImage();  
			Image newimg = img.getScaledInstance( 200, 100, java.awt.Image.SCALE_SMOOTH );
			tem = new ImageIcon( newimg );
			setIcon(tem);
			setContentAreaFilled(false);
			setOpaque(false);
			setBorderPainted(false);
		}
	}
	
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
}
