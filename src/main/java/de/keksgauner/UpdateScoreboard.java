package de.keksgauner;

import me.angeschossen.lands.api.events.internal.PlayerLocationAreaEvent;
import me.angeschossen.lands.api.events.player.PlayerAreaLeaveEvent;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.io.IOException;
import java.util.UUID;

public class UpdateScoreboard implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerLocationAreaEvent(PlayerLocationAreaEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        Area area = event.getArea();

        UUID owner = area.getOwnerUID();
        if (owner.equals(player.getUniqueId()))
            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&3Dein Land");
        else
            try {
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&6" + PlayerFetcher.getPlayerOf(owner));
            } catch (IOException e) {
                e.printStackTrace();
            }

        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "&9" + area.getLand().getName());
    }

    @EventHandler
    public void onPlayerAreaLeaveEvent(PlayerAreaLeaveEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&aWilderness");
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "");
    }
}
