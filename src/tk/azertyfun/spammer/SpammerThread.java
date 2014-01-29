package tk.azertyfun.spammer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class SpammerThread extends Thread {
	
	protected int key;
	protected Robot r;
	protected boolean running = true;
	
	public SpammerThread(int key) {
		this.key = key;
		System.out.println("SpammerThread '" + key + "' créé.");
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
		System.out.println("SpammerThread '" + key + "' stoppé.");
		running = false;
	}
	
	@Override
	public void run() {
		while(running) {
			if(key > 0x02)
				r.keyPress(key);
			if(key == 0x01) {
				r.mousePress(InputEvent.BUTTON1_MASK);
				r.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			if(key == 0x02) {
				r.mousePress(InputEvent.BUTTON2_MASK);
				r.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			
		}
	}

}
