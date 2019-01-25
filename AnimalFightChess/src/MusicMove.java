
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javazoom.jl.player.Player;

public class MusicMove extends Thread{
	private Player player;
	public MusicMove() {
		this.start();
	}
	public void run() {
		try {
	        BufferedInputStream buffer = new BufferedInputStream(
	                    new FileInputStream("./src/Music/move.mp3"));
	        player = new Player(buffer);
	        player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}