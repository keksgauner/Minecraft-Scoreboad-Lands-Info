package de.keksgauner;

import me.angeschossen.lands.api.integration.LandsIntegration;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class CreateScoreboad {

    private Scoreboard scb;
    private Objective obj;

    public CreateScoreboad(Player player) {
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&aWilderness");
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "");

        String name = sText("&8&l> &cKrafity &8&l<");

        scb = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = scb.registerNewObjective("Infos", "dummy", name, RenderType.INTEGER);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(sText(" ")).setScore(6); // Space

        obj.getScore(sText("&7&l» &6Lands Name &7&l«")).setScore(5);
        obj.getScore(createTeam("lands_name", "§1")).setScore(4);

        obj.getScore(sText("  ")).setScore(3); // Space

        obj.getScore(sText("&7&l» &5Lands Owner &7&l«")).setScore(2);
        obj.getScore(createTeam("lands_owner", "§2")).setScore(1);

        obj.getScore(sText("   ")).setScore(0); // Space

        player.setScoreboard(scb);
    }

    public CreateScoreboad(Scoreboard scb) {
        this.scb = scb;
        this.obj = scb.getObjective("Infos");
    }

    public Component cText(String text) {
        return Component.text(ChatColor.translateAlternateColorCodes('&', text));
    }

    public String sText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public void setTeamPrefix(String team, String prefix) {
        getTeam(team).prefix(cText(prefix));
    }

    public void setTitle(String title) {
        obj.displayName(cText(title));
    }

    public String createTeam(String team, String key) {
        getTeam(team).addEntry(key);
        return key;
    }

    public Team getTeam(String team) {
        if(scb.getTeam(team) == null)
            return scb.registerNewTeam(team);
        else
            return scb.getTeam(team);
    }
}