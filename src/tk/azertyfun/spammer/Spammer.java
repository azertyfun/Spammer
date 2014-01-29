package tk.azertyfun.spammer;

import java.awt.SystemTray;

import javax.swing.JOptionPane;

import com.melloware.jintellitype.JIntellitype;

public class Spammer {

	public static void main(String[] args) {
		
		//On vérifie que l'utilisateur tourne bien sous windows
		if(!(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)) {
			die("À cause du manque du supprot de java pour la gestion des raccourcis clavier globaux, ce programme n'est fonctionnel qu'avec windows.\n" +
				"Java a détecté votre OS comme étant " + System.getProperty("os.name"));
		}
		
		System.loadLibrary("JIntellitype");
		
		JIntellitype.getInstance();
		
		//On vérifie qu'une autre instance de Spammer n'est pas déjà en train de tourner
		if(JIntellitype.checkInstanceAlreadyRunning("Spammer_azertyfun")) {
			die("Une autre instance de Spammer est déjà en cours");
		}
		
		//On vérifie que le SystemTray est bien supporté par l'OS (le contraire serait toutefois improbable puisque l'utilisateur tourne sous windows). ^^
		if(!SystemTray.isSupported()) {
			die("La classe SystemTray ne semble pas être supportée par votre système.");
		}
		
		if(!JIntellitype.isJIntellitypeSupported()) {
			die("JIntellitype n'est pas supporté par votre système.");
		}
		
		//On crée le tray
		@SuppressWarnings("unused")
		Tray tray = new Tray();
		
		//On crée un listener JIntelliType
		@SuppressWarnings("unused")
		Listener l = new Listener();
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
