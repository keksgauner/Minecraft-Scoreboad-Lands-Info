package de.keksgauner;

import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.types.RoleFlag;
import me.angeschossen.lands.api.integration.LandsIntegration;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.management.relation.Role;

public final class ScoreboadInfo extends JavaPlugin {

    public static ScoreboadInfo THIS;

    @Override
    public void onEnable() {
        ScoreboadInfo.THIS = this;
        if (Bukkit.getPluginManager().getPlugin("Lands") != null) {
            Bukkit.getPluginManager().registerEvents(new SetScoreboad(), ScoreboadInfo.THIS);
            Bukkit.getPluginManager().registerEvents(new UpdateScoreboard(), ScoreboadInfo.THIS);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
