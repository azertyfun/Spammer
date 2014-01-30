package tk.azertyfun.spammer;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class Listener implements HotkeyListener {
	
	protected boolean[] started = new boolean[4];
	protected SpammerThread[] spammers = new SpammerThread[4];
	protected char[] keys = {0, ' ', 1, 2};
	
	public Listener() {
		for(int i = 0; i < started.length; i++)
			started[i] = false;
		JIntellitype.getInstance().registerHotKey(0, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 'Q'); //CTRL+ALT+Q
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 0x70); //CTRL+ALT+F1
		JIntellitype.getInstance().registerHotKey(2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 0x71); //CTRL+ALT+F2
		JIntellitype.getInstance().registerHotKey(3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 0x72); //CTRL+ALT+F3
		JIntellitype.getInstance().addHotKeyListener(this);
	}

	@Override
	public void onHotKey(int key) {
		if(key == 0)
			Spammer.exit();
		
		if(!started[key]) {
			spammers[key] = new SpammerThread(keys[key], key);
			spammers[key].start();
			started[key] = true;
		}
		else {
			started[key] = false;
		}
	}
}
