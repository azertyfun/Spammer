package tk.azertyfun.spammer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class SpammerThread extends Thread {
	
	protected int key;
	protected Robot r;
	protected int id;
	
	public SpammerThread(int key, int id) {
		this.key = key;
		this.id = id;
		System.out.println("SpammerThread '" + key + "' créé.");
		try {
			r = new Robot();
		} catch (AWTException e) {
			Spammer.die("Erreur lors de la création du robot :\n" + e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(Spammer.listener.started[id]) {
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
			try {
				Thread.sleep(Spammer.spamSlow);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
