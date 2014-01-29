package tk.azertyfun.spammer;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class Listener implements HotkeyListener {
	
	protected boolean spammer_space_started = false;
	SpammerThread spammer_space;
	
	public Listener() {
		JIntellitype.getInstance().registerHotKey(0, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 'Q'); //CTRL+ALT+Q
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 0x70); //CTRL+ALT+F1
		JIntellitype.getInstance().addHotKeyListener(this);
	}

	@Override
	public void onHotKey(int key) {
		switch(key) {
			case 0:
				Spammer.exit();
			break;
			case 1:
				if(!spammer_space_started) {
					spammer_space = new SpammerThread(' ');
					spammer_space.start();
					spammer_space_started = true;
				}
				else {
					spammer_space.stopRunning();
					spammer_space_started = false;
				}
				break;
			default:
				break;
		}
	}
}
