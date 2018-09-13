package be.fogoziv;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class PluginListener implements Listener{
	@EventHandler
	public void onExpBottle(ExpBottleEvent e) {
		e.setExperience(30);
	}
}
