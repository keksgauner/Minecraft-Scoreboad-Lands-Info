package de.keksgauner;

import me.angeschossen.lands.api.events.internal.PlayerLocationAreaEvent;
import me.angeschossen.lands.api.events.player.PlayerAreaLeaveEvent;
import me.angeschossen.lands.api.land.Area;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class UpdateScoreboard implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerLocationAreaEvent(PlayerLocationAreaEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        Area area = event.getArea();

        String owner = Bukkit.getOfflinePlayer(event.getArea().getOwnerUID()).getName();
        if(owner == null) {
            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&aWilderness");
            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "");
        } else {
            if (owner.equalsIgnoreCase(player.getName()))
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&3Dein Land");
            else
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&6" + owner);

            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "&9" + area.getLand().getName());
        }
    }

    @EventHandler
    public void onPlayerAreaLeaveEvent(PlayerAreaLeaveEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&aWilderness");
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "");
    }
}
