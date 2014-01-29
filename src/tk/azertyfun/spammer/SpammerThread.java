package tk.azertyfun.spammer;

import java.awt.AWTException;
import java.awt.Robot;

public class SpammerThread extends Thread {
	
	protected int key;
	protected Robot r;
	protected boolean running = true;
	
	public SpammerThread(int key) {
		this.key = key;
		System.out.println("SpammerThread créé");
		try {
			r = new Robot();
		} catch (AWTException e) {
			Spammer.die("Erreur lors de la création du robot :\n" + e.getMessage());
		}
	}
	
	public boolean running() {
		return running;
	}
	
	public void stopRunning() {
		running = false;
	}
	
	@Override
	public void run() {
		while(running) {
			r.keyPress(key);
		}
	}

}
