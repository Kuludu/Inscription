package net.kuludu.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
         public void onEnable(){
        	 getLogger().info("铭刻插件加载完成！");
        	 getServer().getPluginManager().registerEvents(new InscriptionListerner(),this);
        	 File file = new File(getDataFolder()+"//config.yml");
        	 FileConfiguration config=YamlConfiguration.loadConfiguration(file);
        	if(!getDataFolder().exists()){
        		getDataFolder().mkdir();
        	}
        	if(!file.exists()){
        		config.set("isEnable:", "Enable");
        		try {
					config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	 String con = config.getString("isEnable:");
        	 if(con.equals("Disenable")){
        		 getServer().getPluginManager().disablePlugin(this);
        	 }
        	}
         }
         
         public void onDisable(){
  		   getLogger().info("铭刻插件卸载完成！");
  	           }
         
         public boolean onCommand(CommandSender sender,Command cmd,String label,String args[]){
        	 File file = new File(getDataFolder()+"//config.yml");
        	 FileConfiguration config=YamlConfiguration.loadConfiguration(file);
        	 boolean staues = false;
        	  if(label.equalsIgnoreCase("ins")){
        		  if(sender.isOp()==false){
   				   sender.sendMessage("权限不足");
   				   return false;
   			   }
        		if(args.length==0){
        			sender.sendMessage(ChatColor.GREEN+"===铭刻插件V0.0.1 By:Kuludu===");
        			sender.sendMessage(ChatColor.DARK_GREEN+"===输入“/ins s e/d”开启或关闭铭刻效果===");
        			sender.sendMessage(ChatColor.DARK_GREEN+"===输入“/ins rl“重新加载插件===");
        			sender.sendMessage(ChatColor.GREEN+"===插件反馈邮箱:plugin@kuludu.net===");
        		}
        		if(args.length>=1){
        			if(args[0].equals("s")){
        			 if(args.length>=2){	
        				 if(args[1].equals("e")){
        					 config.set("isEnable:", "Enable");
        					 try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
        				 }else if (args[1].equals("d")){
        					 config.set("isEnable:", "Disenable");
        					 try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
						sender.sendMessage(ChatColor.RED+"未知的指令！，输入”/ins”获取帮助");
						}
        			 }
        			}else if(args[0].equals("rl")){
        				getServer().getPluginManager().disablePlugin(this);
        				getServer().getPluginManager().enablePlugin(this);
        			}else{
        			sender.sendMessage(ChatColor.RED+"未知的指令！，输入”/ins”获取帮助");
        			}
        		}
        	  }
			return staues;       	 
         }
}
