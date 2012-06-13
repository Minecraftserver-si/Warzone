package couk.Adamki11s.Warzone;

import couk.Adamki11s.Maps.Maps;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class WarzoneCreatureSpawnEvent implements Listener {
    
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getLocation().getWorld() == Maps.Warzone_World) {
            event.setCancelled(true);
        }
    }
}
