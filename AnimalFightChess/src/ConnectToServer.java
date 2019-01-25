import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.server.Skeleton;

import javax.swing.JOptionPane;



public class ConnectToServer{
	Socket socket = null;
	DataOutputStream dos = null;
	BufferedReader br = null;
	String IP = "127.0.0.1";  //此处设置为本机地址
	int PORT = 2333;
	int begin = 1;
	int turn = 0;
	int meRed = 0;
	Map map;
	BoardText board;
	public ConnectToServer(Map map, BoardText board) {
		this.map = map;
		this.board = board;
		try {
			socket = new Socket(IP, PORT);
			Thread thread = new Thread(new Connection());        //为接受数据建立线程
			dos = new DataOutputStream(socket.getOutputStream()); //向服务器传输数据
			thread.start();
		} catch(Exception e) {
			System.out.println("can not connect to server!");
			JOptionPane.showMessageDialog(null, "无法连接服务器！", "error",JOptionPane.WARNING_MESSAGE);//跳出提示框
		}
	}
	
	private class Connection implements Runnable{
		@Override
		public void run() {
			try {
				while(true) {
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String res = br.readLine(); //注意必须以换行符结尾
		            if (res != null) {
		            		System.out.println("12321");
		                //System.out.println(res);
		                if(begin == 1) {
		                		if(res.equals("waiting")) {
				                	board.setForeground(Color.black);
				        			board.setFont(new java.awt.Font("Dialog", 1, 70));
				        			board.setText("waiting");
				        			System.out.println("waiting");
				        			meRed = 1;
				        			begin = 0;
			        			}
		                		else {
		                			board.setForeground(Color.red);
		                			board.setFont(new java.awt.Font("Dialog", 1, 70));
		                			board.setText("RED");
		                			changeToMap(res);
		                			board.change(map, 3);
		                			turn = 1;
		                			System.out.println("begin, wait red");
		                		}
		                		begin = 0;
		                }
		                else {
		                		if(meRed == 1) {
		                			board.change(map, 3);
		                			meRed = 0;
		                		}
		                		System.out.println("change map begin");
		                		changeToMap(res);
		                		board.change(map, 3);
		                		System.out.println("change map finish");
		             		turn = 1;
		                	}
		            }
				}
			} catch (Exception e) {
				System.out.println("Socket Problem");
				e.printStackTrace();
			}
		}
	}
	
	public void passMesg(String str) throws IOException {//转化为字符串发送给服务器
        try {
        		System.out.print("send:");
        		System.out.println(str);
			dos.writeUTF(str);
			dos.flush();
			turn = 0;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static String changeToStr(Map map) {
		String res = "";
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				res += " "+map.map[i][j]+" "+map.stateMap[i][j];
			}
		res+=" ";
		return res;
	}
	
	public void changeToMap(String str) {
		if(str == null) return;
		String single[] = str.split(" ");

		int k=1;
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(single[k]);
				System.out.print(" ");
				map.map[i][j]=Integer.parseInt(single[k++]);
				System.out.print(single[k]);
				System.out.print(" ");
				map.stateMap[i][j]=Integer.parseInt(single[k++]);
				map.animalMap[i][j].type = map.map[i][j];
				if(map.stateMap[i][j] == 1) map.animalMap[i][j].showAnimal();
				else if(map.stateMap[i][j] == -1) map.animalMap[i][j].hideAnimal();
				else if(map.stateMap[i][j] == 0) map.animalMap[i][j].removeAnimal();
			}
		}
	}

}
