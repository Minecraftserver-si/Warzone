package couk.Adamki11s.Database;

import couk.Adamki11s.Warzone.Warzone;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PermissionsCore {

    private boolean hasPermissions = false;

    public boolean doesHaveNode(Player p, String node) {
        if (hasPermissions) {
            if (p.hasPermission(node)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void sendInsufficientPermsMsg(Player p) {
        p.sendMessage(ChatColor.RED + "[Warzone] " + Warzone.li.getObj("You do not have permissions to do this!"));
    }

    public boolean doesHaveSuperNode(Player p, String node) {
        if (hasPermissions) {
            if (p.hasPermission(node) || p.isOp()) {
                return true;
            } else {
                return false;
            }
        } else if (p.isOp()) {
            return true;
        } else {
            return false;
        }
    }
}
