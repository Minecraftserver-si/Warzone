package couk.Adamki11s.Lobby;

import couk.Adamki11s.Warzone.Warzone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ArenaConfig {

    private File root = new File("plugins/Warzone/Configuration");

    public void createAC() {
        File f = new File(root + File.separator + "LobbyConfig.yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
                YamlConfiguration c = new YamlConfiguration();
                c.load(f);
                c.set("NPCSpawnInLobby", true);
                Warzone.doNPCSpawn = true;
                c.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        } else {
            loadAC();
        }
    }

    private void loadAC() {
        File f = new File(root + File.separator + "LobbyConfig.yml");
        YamlConfiguration c = new YamlConfiguration();
        try {
            c.load(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArenaConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArenaConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(ArenaConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        Warzone.doNPCSpawn = c.getBoolean("NPCSpawnInLobby", true);
        try {
            c.save(f);
        } catch (IOException ex) {
            Logger.getLogger(ArenaConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
