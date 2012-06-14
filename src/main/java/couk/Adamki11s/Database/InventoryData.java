package couk.Adamki11s.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryData {

    private File mainDir = new File("plugins/Warzone"),
            inventoryRoot = new File(mainDir + File.separator + "Inventory");

    public void saveInventory(Player p, ItemStack[] iss) {
        checkFile(p);
        YamlConfiguration c = new YamlConfiguration();
        int index = 0;
        for (ItemStack is : iss) {
            if (is != null) {
                c.set("Inventory.Slot.Type." + index, iss[index].getType().toString());
                c.set("Inventory.Slot.Amount." + index, iss[index].getAmount());
            } else {
                c.set("Inventory.Slot.Type." + index, Material.AIR.toString());
                c.set("Inventory.Slot.Amount." + index, 0);
            }
            index++;
        }
        try {
            c.save(new File(inventoryRoot + File.separator + p.getName() + ".inv"));
        } catch (IOException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItemStack[] loadInventory(Player p) {
        YamlConfiguration c = new YamlConfiguration();
        ItemStack[] invent = new ItemStack[36];
        try {
            c.load(new File(inventoryRoot + File.separator + p.getName() + ".inv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 36; i++) {
            Material m = Material.getMaterial(c.getString("Inventory.Slot.Type." + i, Material.AIR.toString()));
            int amount = c.getInt("Inventory.Slot.Amount." + i, 0);
            if (m != Material.AIR && amount > 0) {
                invent[i] = new ItemStack(m, amount);
            } else {
                invent[i] = null;
            }
        }

        return invent;
    }

    public boolean isInNeedOfInventoryLoading(Player p) {
        File tmp = new File(inventoryRoot + File.separator + p.getName() + ".inv");
        if (tmp.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkFile(Player p) {
        File tmp = new File(inventoryRoot + File.separator + p.getName() + ".inv");
        if (tmp.exists()) {
            tmp.delete();
        } else {
            try {
                tmp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
