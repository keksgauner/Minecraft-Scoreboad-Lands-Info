package de.keksgauner;

import me.angeschossen.lands.api.events.internal.PlayerLocationAreaEvent;
import me.angeschossen.lands.api.events.player.PlayerAreaLeaveEvent;
import me.angeschossen.lands.api.land.Area;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class UpdateScoreboard implements org.bukkit.event.Listener {

    // Es soll nicht doppelt aufgerufen werden
    private static final ArrayList<Player> players = new ArrayList<>();

    @EventHandler
    public void onPlayerLocationAreaEvent(PlayerLocationAreaEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        Area area = event.getArea();

        if(!players.contains(player)) {
            String owner = Bukkit.getOfflinePlayer(event.getArea().getOwnerUID()).getName();
            assert owner != null;
            if (owner.equalsIgnoreCase(player.getName()))
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&3Dein Land");
            else
                new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&6" + owner);

            new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "&9" + area.getLand().getName());
            players.add(player);
        }
    }

    @EventHandler
    public void onPlayerAreaLeaveEvent(PlayerAreaLeaveEvent event) {
        Player player = event.getLandPlayer().getPlayer();
        players.remove(player);
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_owner", "&aWilderness");
        new CreateScoreboad(player.getScoreboard()).setTeamPrefix("lands_name", "");
    }

    // Wenn ein Spieler den Server verlässt, wird der Spieler falls nötig aus der Liste entfernt
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        players.remove(player);
    }
}
