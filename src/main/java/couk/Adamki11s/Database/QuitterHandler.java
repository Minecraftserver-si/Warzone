package couk.Adamki11s.Database;

import couk.Adamki11s.Warzone.Warzone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class QuitterHandler {

    private File mainDir = new File("plugins/Warzone" + File.separator + "Quitters");

    public void addQuitter(Player p, Location l) {
        try {
            File f = new File(mainDir + File.separator + p.getName() + ".yml");
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

    public Location getQuitterLocation(Player p) {
        File f = new File(mainDir + File.separator + p.getName() + ".yml");
        YamlConfiguration c = new YamlConfiguration();
        try {
            c.load(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuitterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuitterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(QuitterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        World w = Warzone.server.getWorld(c.getString("World").toString());
        double x = c.getDouble("X", 0), y = c.getDouble("Y", 0), z = c.getDouble("Z", 0);
        return new Location(w, x, y, z);
    }

    public void removeQuitter(Player p) {
        new File(mainDir + File.separator + p.getName() + ".yml").delete();
    }

    public boolean doesExist(Player p) {
        if (new File(mainDir + File.separator + p.getName() + ".yml").exists()) {
            return true;
        } else {
            return false;
        }
    }
}
