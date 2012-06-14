package couk.Adamki11s.Database;

import couk.Adamki11s.Warzone.Warzone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LobbyPlaceHolder {

    File root = new File("plugins/Warzone/Lobby");

    public boolean checkLobby(Player p) {
        String name = p.getName();
        File path = new File(root + File.separator + name + ".lobbydata");
        if (path.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void createLobbyFile(Player p, Location l) {
        String name = p.getName();
        File path = new File(root + File.separator + name + ".lobbydata");
        if (checkLobby(p)) {
            path.delete();
            try {
                path.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            addLobbyDump(p, l);
        } else {
            try {
                path.createNewFile();
                addLobbyDump(p, l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addLobbyDump(Player p, Location l) {
        try {
            File f = new File(root + File.separator + p.getName() + ".lobbydata");
            f.createNewFile();
            YamlConfiguration c = new YamlConfiguration();
            c.set("World", l.getWorld().getName().toString());
            c.set("X", l.getX());
            c.set("Y", l.getY());
            c.set("Z", l.getZ());
            c.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLobbyDumpFile(Player p) {
        File f = new File(root + File.separator + p.getName() + ".lobbydata");
        f.delete();
    }

    public Location getLobbyDump(Player p) {
        File f = new File(root + File.separator + p.getName() + ".lobbydata");
        YamlConfiguration c = new YamlConfiguration();
        try {
            c.load(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LobbyPlaceHolder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LobbyPlaceHolder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(LobbyPlaceHolder.class.getName()).log(Level.SEVERE, null, ex);
        }
        World w = Warzone.server.getWorld(c.getString("World").toString());
        double x = c.getDouble("X", 0), y = c.getDouble("Y", 0), z = c.getDouble("Z", 0);
        return new Location(w, x, y, z);
    }
}
