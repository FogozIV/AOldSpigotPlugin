package be.fogoziv.main;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import be.fogoziv.PluginListener;


public class Plugin extends JavaPlugin{
	static org.bukkit.plugin.Plugin plugina;
	@Override
    public void onEnable(){
		getLogger().info("Plugin Furnace démarré");
		saveDefaultConfig();
		plugina = this;
		final FileConfiguration config = this.getConfig();
		/*File configFile = new File("plugin/furnace/config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);*/
		Listener l = new PluginListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(l, this);
		Listener l1 = new Commands();
		pm.registerEvents(l1, this);
    }
	@Override
    public void onDisable(){
		plugina = null;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("furnace")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				furnace(p);
			}else{
				
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("unenchanting")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				Commands.unenchanting(p);
			}else {
				
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("duplicate")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				duplicate(p);
			}else {
				
			}
			return true;
		}	
		if(cmd.getName().equalsIgnoreCase("craft")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				Commands.craft(p);
				
			}else {
				
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("enderchest")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				Commands.enderChest(p);
			}else {
				
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("backpack")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				
				if(args.length!=0) {
					if(args.length >= 1) {
						Commands.backpack(p,args[0]);
					}
				}else {
					Commands.backpack(p,p);
				}
			}else {
				
			}
			return true;
		}
		return false;
		
	}
	
	private void furnace(Player p) {
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(item.getAmount()==0) {
			p.sendMessage("Vous devez avoir l'item que vous voulez cuire dans votre main !");
			getLogger().info("Erreur");
			return;
		}
		if(item.getData().toString().contains("LOG")) {
			cuisson(item, new ItemStack(Material.CHARCOAL), p);
			return;
		}
		if(item.getData().equals(new ItemStack(Material.KELP))) {
			cuisson(item, new ItemStack(Material.DRIED_KELP), p);
			return;
		}
		if(getRecipe(item) != null) {
			cuisson(item,getRecipe(item) , p);
			return;
		}
		p.sendMessage("L'Item que vous avez en main n'est pas cuisable");
		return;
		
		
	}
	private ItemStack getRecipe(ItemStack item) {
		ItemStack result = null;
		Iterator<Recipe> iter = Bukkit.recipeIterator();
		while (iter.hasNext()) {
		   Recipe recipe = iter.next();
		   if (!(recipe instanceof FurnaceRecipe)) continue;
		   if (((FurnaceRecipe) recipe).getInput().getType() != item.getType()) continue;
		   result = recipe.getResult();
		   return result;
		}
		return null;
	}
	private void cuisson(ItemStack input, ItemStack output, Player p) {
		int amount = input.getAmount();
		input.setAmount(0);
		p.getInventory().setItemInMainHand(output);
		p.getInventory().getItemInMainHand().setAmount(amount);
	}

	private void duplicate(Player p) {
		ItemStack items = p.getInventory().getItemInMainHand();
		if(items.getAmount()==0) {
			p.sendMessage("Vous devez avoir un livre à dupliquer en main");
			return;
		}
		if(items.getType()!=Material.ENCHANTED_BOOK) {
			p.sendMessage("Vous devez avoir un livre enchanté en main");
			return;
		}else if(items.getType()==Material.ENCHANTED_BOOK) {
			if(p.getLevel()>=40) {
				ItemStack itemsb = p.getInventory().getItemInOffHand();
				if(p.getInventory().getItemInOffHand().getType().equals(Material.BOOK)) {
					itemsb.setAmount(itemsb.getAmount()-3);
					p.getInventory().setItemInMainHand(itemsb);
					items.setAmount(items.getAmount()+3);
					p.getInventory().setItemInMainHand(items);
					p.setLevel(p.getLevel()-40);
					return;
				}else {
					p.sendMessage("Vous devez avoir 3 livres dans votre seconde main");
					return;
				}
				
			}else {
				p.sendMessage("Vous n'avez pas 40 lvl d'xp");
				return;
			}
		}
	}
	public static org.bukkit.plugin.Plugin getPlugin() {
		return plugina;
	}
	
}
