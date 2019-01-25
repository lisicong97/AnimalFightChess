
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	public Music() {
		this.start();
	}
	public void run() {
		try {
			while(true) {//设置循环播放背景音
	            BufferedInputStream buffer = new BufferedInputStream(
	                    new FileInputStream("./src/Music/background.mp3"));
	            player = new Player(buffer);
	            	player.play();
			}
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
