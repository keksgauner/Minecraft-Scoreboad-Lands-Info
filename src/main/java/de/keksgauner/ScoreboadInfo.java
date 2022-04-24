package de.keksgauner;

import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.types.RoleFlag;
import me.angeschossen.lands.api.integration.LandsIntegration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ScoreboadInfo extends JavaPlugin {

    public static ScoreboadInfo THIS;
    private static LandsIntegration landsIntegration;

    @Override
    public void onEnable() {
        ScoreboadInfo.THIS = this;
        if (Bukkit.getPluginManager().getPlugin("Lands") != null) {
            Bukkit.getPluginManager().registerEvents(new SetScoreboad(), ScoreboadInfo.THIS);
            landsIntegration = new LandsIntegration(THIS);

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
