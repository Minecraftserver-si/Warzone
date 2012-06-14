package couk.Adamki11s.NPC;

import com.topcat.npclib.NPCManager;
import com.topcat.npclib.entity.HumanNPC;
import com.topcat.npclib.entity.NPC;
import couk.Adamki11s.Warzone.Warzone;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NPC_Control {

    public Warzone main;
    public HashMap<String, HumanNPC> npcs = new HashMap<String, HumanNPC>();
    public NPCManager NpcManager;

    public NPC_Control(Warzone main, NPCManager NpcManager) {
        this.main = main;
        this.NpcManager = NpcManager;
    }

    public void setItemInHand(String reference, Material m) {
        npcs.get(reference).setItemInHand(m);
    }

    public void setPositionRotation(String reference, double x, double y, double z, float yaw, float pitch) {
        npcs.get(reference).moveTo(new Location(npcs.get(reference).getBukkitEntity().getWorld(), x, y, z, yaw, pitch));
    }

    public void setPositionRotation(String reference, Location l) {
        npcs.get(reference).moveTo(l);
    }

    public void die(String reference) {
        //NOT DIE!!
        npcs.get(reference).removeFromWorld();
    }

    public void actAsHurt(String reference) {
        npcs.get(reference).actAsHurt();
    }

    public void animateArmSwing(String reference) {
        npcs.get(reference).animateArmSwing();
    }

    public HashMap<String, HumanNPC> getMap() {
        return npcs;
    }

    public void spawn(String reference, Location loc) {
        if (!npcs.containsKey(reference)) {
            npcs.put(reference, (HumanNPC)NpcManager.spawnHumanNPC(reference, loc));
        } else {
            NpcManager.spawnHumanNPC(reference, loc);
        }
    }

    public void despawn(String reference) {
        if (!check(reference)) {
            return;
        }
        NpcManager.despawnHumanByName(reference);
        npcs.remove(reference);
    }

    public boolean check(String reference) {
        if (npcs.get(reference) != null) {
            return true;
        } else {
            return false;
        }
    }

    ////////////////////////
    ///Additional Methods///
    ////////////////////////
    public void move(String reference, double x, double y, double z, World w) {
        npcs.get(reference).pathFindTo(new Location(w, x, y, z), 999999999, null);
    }

    public void move(String reference, Location loc) {
        npcs.get(reference).pathFindTo(loc, 999999999, null);
    }

    public boolean isSneaking(String reference) {
        return npcs.get(reference).getEntity().isSneaking();
    }

    public void setSneaking(String reference, boolean sneak) {
        npcs.get(reference).setSneaking(sneak);
    }

    public boolean isSleeping(String reference) {
        return ((Player)npcs.get(reference).getBukkitEntity()).isSleeping();
    }

    public int getHealth(String reference) {
        return ((Player)npcs.get(reference).getBukkitEntity()).getHealth();
    }

    public void mount(String reference, String reference2) {
        npcs.get(reference).getEntity().mount(npcs.get(reference2).getEntity());
    }

    public boolean isMounted(String reference) {
        if (npcs.get(reference).getBukkitEntity().getPassenger() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void unmount(String reference, Entity mounted) {
        mounted.setPassenger(null);
    }

    public void damage(String reference, int damage) {
        Player p = (Player) npcs.get(reference).getBukkitEntity();
        int health = p.getHealth();
        if (damage > health) {
            npcs.get(reference).actAsHurt();
            despawn(reference);
        } else {
            p.damage(damage);
        }
    }

    public World getWorld(String reference) {
        return npcs.get(reference).getBukkitEntity().getLocation().getWorld();
    }

    public Location getLocation(String reference) {
        return npcs.get(reference).getBukkitEntity().getLocation();
    }

    public void setLocation(Location l, String reference) {
        npcs.get(reference).getBukkitEntity().teleport(l);
    }

    public Entity getBukkitEntity(String reference) {
        return npcs.get(reference).getBukkitEntity();
    }
}
