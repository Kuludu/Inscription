package net.kuludu.main;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InscriptionListerner implements Listener {
	@EventHandler(priority=EventPriority.MONITOR)
       public void onKill(PlayerDeathEvent e){ 
           ItemStack weapon = e.getEntity().getItemInHand();       
           ItemMeta weaponmeta = weapon.getItemMeta();
           if(weapon==null||weapon.getType()==Material.AIR){
        	   return;
           }
           if(!weapon.hasItemMeta())
        	   return;
           String deader = e.getEntity().getName();	 
           ArrayList<String> lore = (ArrayList<String>) weapon.getItemMeta().getLore();
           lore.add("这把血淋林的剑击杀了"+deader);
           weapon.setItemMeta(weaponmeta);
           e.getEntity().getInventory().addItem(weapon);
           e.getEntity().getKiller().sendMessage("你杀了"+e.getEntity()+"因此获得武器铭刻效果");
           e.getEntity().sendMessage("你被"+e.getEntity().getKiller().getName()+"杀了,他的武器获得了铭刻效果");
       }
}
