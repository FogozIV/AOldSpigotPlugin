package be.fogoziv.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Commands implements Listener{
	static Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "EnderChest");
	static Plugin pl = be.fogoziv.main.Plugin.getPlugin();
	static ItemStack isno = new ItemStack(Material.AIR);
		public static void craft(Player p) {
			p.openWorkbench(null, true);
		}
		public static void unenchanting(Player p) {
			ItemStack item = p.getInventory().getItemInMainHand();
			if(item.getAmount()==0) {
				p.sendMessage("Vous devez avoir un item à désenchanter en main");
				return;
			}
			if(item.getType()== Material.ENCHANTED_BOOK) {
				p.setLevel(p.getLevel()+2);
				p.getInventory().setItemInMainHand(new ItemStack(Material.BOOK));
				return;
			}
			if(item.getEnchantments().isEmpty()) {
				p.sendMessage("Vous devez avoir un item enchanté");
				return;
			}
			if(item.getEnchantments().isEmpty()==false) {
				Material nouveau = item.getType();
				ItemStack remplacement = new ItemStack(nouveau);
				p.getInventory().setItemInMainHand(remplacement);
				p.setLevel(p.getLevel()-2);
			}
		}
		public static void enderChest(Player p ) {
			p.openInventory(p.getEnderChest());
			
		}
		@EventHandler
		public void onInventoryClose(InventoryCloseEvent e) {
			Player p = (Player)e.getPlayer();	
			if(e.getInventory().getName().equals("BackPack of " + p.getName())) {
				backpackclose(p,e);
			}
		}
		private void backpackclose(Player p, InventoryCloseEvent e) {
			if(e.getInventory().getHolder() instanceof Player) {
				String nameofplayer = e.getInventory().getName().substring(12, e.getInventory().getName().length());
				//Player p2 = (Player) e.getInventory().getHolder();
				Player p2 = pl.getServer().getPlayer(nameofplayer);
				if(e.getInventory().getName().contains("BackPack of " + p.getName())) {
					ItemStack invat;
					for(int i =0; i<27; i++) {
						if(e.getInventory().getItem(i)==null) {
							//pl.getConfig().set("Players."+ p.getName()+"." + p.getUniqueId()+".itemstack."+ (i+1), isno);
							pl.getConfig().set("Players."+ p.getName()+".itemstack."+ (i+1), isno);
						}else {
							invat=e.getInventory().getItem(i);
							//pl.getConfig().set("Players."+ p.getName()+"." + p.getUniqueId()+".itemstack."+ (i+1), invat);
							pl.getConfig().set("Players."+ p.getName()+".itemstack."+ (i+1), invat);
						}
								
					}
					pl.saveConfig();
					System.out.println("here");
				}else
					if(e.getInventory().getName().contains("BackPack of "+ p2.getName())) {
						ItemStack invat;
						for(int i =0; i<27; i++) {
							if(e.getInventory().getItem(i)==null) {
								//pl.getConfig().set("Players."+ p.getName()+"." + p.getUniqueId()+".itemstack."+ (i+1), isno);
								pl.getConfig().set("Players."+ p2.getName()+".itemstack."+ (i+1), isno);
							}else {
								invat=e.getInventory().getItem(i);
								//pl.getConfig().set("Players."+ p.getName()+"." + p.getUniqueId()+".itemstack."+ (i+1), invat);
								pl.getConfig().set("Players."+ p2.getName()+".itemstack."+ (i+1), invat);
							}									
						}
						pl.saveConfig();
						System.out.println("here");
					}
				}
		}
		public static void backpack(Player p, Player p2) {
			Inventory invbp = Bukkit.createInventory(p2, 27, "BackPack of " + p2.getName());
			//if(!pl.getConfig().contains("Players." + p2.getName() + "." +p2.getUniqueId())) {
			if(!pl.getConfig().contains("Players." + p2.getName())) {
				p.openInventory(invbp);
			}else {
				ItemStack m;
				int z[] = new int[27];
				for(int i=0; i< 27; i++) {
					//if(pl.getConfig().getItemStack("Players."+ p2.getName()+"." + p2.getUniqueId()+".itemstack."+(i+1)).equals(isno)==false) {
					if(pl.getConfig().getItemStack("Players."+ p2.getName()+".itemstack."+(i+1)).equals(isno)==false) {
						//m = pl.getConfig().getItemStack("Players."+ p2.getName()+"." + p2.getUniqueId()+".itemstack."+(i+1));
						m = pl.getConfig().getItemStack("Players."+ p2.getName()+".itemstack."+(i+1));
						invbp.addItem(new ItemStack(m));
					}
				
				
				
				
				}
				p.openInventory(invbp);
			}
			
			
			
			
			
		}
		public static void backpack(Player p, String string) {
			if(pl.getServer().getPlayer(string)!=null) {
			backpack(p,pl.getServer().getPlayer(string));
			}
		}
		
}
