package tk.azertyfun.spammer;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

public class Tray {
	
	protected PopupMenu popup;
	protected TrayIcon trayIcon;
	protected final SystemTray tray = SystemTray.getSystemTray();;
	protected MenuItem exitItem;
	
	public Tray() {
		popup = new PopupMenu();
		URL url = getClass().getResource("images/icon.gif");
		if(url == null)
			Spammer.die("Impossible de charger l'icon de tray");
		trayIcon = new TrayIcon(new ImageIcon(url, "icon tray").getImage());
		
		exitItem = new MenuItem("Quitter");
		
		popup.add(exitItem);
		
		trayIcon.setPopupMenu(popup);
		
		try {
			tray.add(trayIcon);
		} catch(AWTException e) {
			Spammer.die("Impossible d'ajouter l'icône de tray.");
		}
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spammer.exit();
			}
		});
	}
	
	protected Image createImage(String path) {
		return (new ImageIcon(path)).getImage();
	}
}
