package tk.azertyfun.spammer;

import java.awt.SystemTray;

import javax.swing.JOptionPane;

import com.melloware.jintellitype.JIntellitype;

public class Spammer {
	
	public static Listener listener;
	public static final int spamSlow = 10; //En millisecondes, le temps entre chaque "spam" de touche.

	public static void main(String[] args) {
		
		//On v�rifie que l'utilisateur tourne bien sous windows
		if(!(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)) {
			die("� cause du manque du supprot de java pour la gestion des raccourcis clavier globaux, ce programme n'est fonctionnel qu'avec windows.\n" +
				"Java a d�tect� votre OS comme �tant " + System.getProperty("os.name"));
		}
		
		System.loadLibrary("JIntellitype");
		
		JIntellitype.getInstance();
		
		//On v�rifie qu'une autre instance de Spammer n'est pas d�j� en train de tourner
		if(JIntellitype.checkInstanceAlreadyRunning("Spammer_azertyfun")) {
			die("Une autre instance de Spammer est d�j� en cours");
		}
		
		//On v�rifie que le SystemTray est bien support� par l'OS (le contraire serait toutefois improbable puisque l'utilisateur tourne sous windows). ^^
		if(!SystemTray.isSupported()) {
			die("La classe SystemTray ne semble pas �tre support�e par votre syst�me.");
		}
		
		if(!JIntellitype.isJIntellitypeSupported()) {
			die("JIntellitype n'est pas support� par votre syst�me.");
		}
		
		//On cr�e le tray
		@SuppressWarnings("unused")
		Tray tray = new Tray();
		
		//On cr�e un listener JIntelliType
		Spammer.listener = new Listener();
	}
	
	
	public static void die(String message) {
		JOptionPane.showMessageDialog(null, message, "Erreur fatale", JOptionPane.ERROR_MESSAGE);
		System.err.println(message);
		JIntellitype.getInstance().cleanUp();
		System.exit(-1);
	}

	public static void exit() {
		JIntellitype.getInstance().cleanUp();
		System.exit(0);
	}
}
