package couk.Adamki11s.Warzone;

import com.topcat.npclib.NPCManager;
import couk.Adamki11s.Commands.WarzoneCommands;
import couk.Adamki11s.Database.*;
import couk.Adamki11s.Extras.Colour.ExtrasColour;
import couk.Adamki11s.Games.Solo.*;
import couk.Adamki11s.Languages.LangInterface;
import couk.Adamki11s.Lobby.ArenaConfig;
import couk.Adamki11s.Maps.*;
import couk.Adamki11s.NPC.NPC_Control;
import couk.Adamki11s.NPC.NPC_Handler;
import couk.Adamki11s.NPC.NPC_Messages;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Warzone extends JavaPlugin {

    public static QuitterHandler quitterHandle = new QuitterHandler();
    public static NPC_Handler npc_handle;
    public static InventoryData inventData = new InventoryData();
    public static MapDataLoader mdl = new MapDataLoader();
    public static PoolDataLoader pdl = new PoolDataLoader();
    public static NPC_Messages npcMessages = new NPC_Messages();
    public static Server server;
    public static Plugin plugin;
    public static JavaPlugin jp;
    public static String prefix = "[Warzone]",
            version;
    public static Logger log = Logger.getLogger("Minecraft");
    public static HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
    public static HashMap<MapData, Gamedata> mapData = new HashMap<MapData, Gamedata>();
    public static NPCManager npcManage;
    public static NPC_Control npc;
    public static ExtrasColour ec = new ExtrasColour();
    public static boolean doNPCSpawn = false;

    @Override
    public void onDisable() {
        server.getScheduler().cancelTasks(plugin);
        if (doNPCSpawn) {
            npc_handle.despawnNPCS();
        }
        if (!Initialise.mysqlEnabled) {
            try {
                Initialise.core.close();
            } catch (Exception ex) {
            }
        } else {
            try {
                Initialise.sqlCore.close();
            } catch (Exception ex) {
            }
        }
        log.info(prefix + " Warzone, version " + version + " disabled successfully!");
    }
    Maps mapsClass = new Maps();
    Ranks rnks = new Ranks();
    PermissionsCore pc = new PermissionsCore();
    public static LangInterface li;
    private LanguageConfiguration langConfig = new LanguageConfiguration();

    @Override
    public void onEnable() {
        version = this.getDescription().getVersion();
        plugin = this;
        jp = this;
        server = getServer();
        PluginManager pm = server.getPluginManager();
        loadMaps();
        new Initialise().init();
        mapsClass.initi();
        langConfig.setup();
        li = new LangInterface(langConfig.loadLang());
        loadData();
        new ArenaConfig().createAC();
        rnks.initialiseRanks();
        mdl.loadMapData();
        pdl.loadPoolingData();
        getCommand("warzone").setExecutor(new WarzoneCommands());
        if (Maps.worldFound) {
            log.info(li.getObj("[Warzone] By Adamki11s of the MineDev team."));
            log.info(prefix + " Warzone," + li.getObj("version") + " " + version + " " + li.getObj("enabled successfully") + "!");
        }

        npcManage = new NPCManager(this);
        npc = new NPC_Control(this, npcManage);
        npc_handle = new NPC_Handler(npcManage, npc);
        npc_handle.spawnDefaultNPCS();

        if (doNPCSpawn) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Warzone.plugin, new Runnable() {

                public void run() {
                    npc_handle.enableNPCAIController();
                }
            }, 100L);

        }

        pm.registerEvents(new WarzoneCreatureSpawnEvent(), this);
        pm.registerEvents(new WarzoneEntityListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);
        pm.registerEvents(new WarzoneBlockListener(), this);
        pm.registerEvents(new WarzoneBlockListener(), this);
        pm.registerEvents(new WarzoneBlockListener(), this);
        pm.registerEvents(new WarzoneBlockListener(), this);
        pm.registerEvents(new WarzonePlayerListener(), this);

    }

    public void loadMaps() {
        mapList.put(MapName.ASCENSION, new ASCENSION());
        mapList.put(MapName.CASTLE, new CASTLE());
        mapList.put(MapName.DUNGEON, new DUNGEON());
        mapList.put(MapName.OVERFLOW, new OVERFLOW());
        mapList.put(MapName.PLAINES, new PLAINES());
        mapList.put(MapName.TOMB, new TOMB());
        mapList.put(MapName.BLIZZARD, new BLIZZARD());
        mapList.put(MapName.JUNGLE, new JUNGLE());
        mapList.put(MapName.BLIND, new BLIND());
        mapList.put(MapName.CONTAINMENT, new CONTAINMENT());
        mapList.put(MapName.AFTERMATH, new AFTERMATH());
        mapList.put(MapName.CRYPT, new CRYPT());
        mapList.put(MapName.HOMETREE, new HOMETREE());
        mapList.put(MapName.AURORA, new AURORA());
        mapList.put(MapName.ABYSS, new ABYSS());
        mapList.put(MapName.BURROW, new BURROW());
        mapList.put(MapName.LAPUTA, new LAPUTA());
        mapList.put(MapName.DOME, new DOME());
        mapList.put(MapName.NUKETOWN, new NUKETOWN());
    }

    public void loadData() {
        mapData.put(MapData.ASCENSION, new ASCENSION_GD());
        mapData.put(MapData.CASTLE, new CASTLE_GD());
        mapData.put(MapData.DUNGEON, new DUNGEON_GD());
        mapData.put(MapData.OVERFLOW, new OVERFLOW_GD());
        mapData.put(MapData.PLAINES, new PLAINES_GD());
        mapData.put(MapData.TOMB, new TOMB_GD());
        mapData.put(MapData.BLIZZARD, new BLIZZARD_GD());
        mapData.put(MapData.JUNGLE, new JUNGLE_GD());
        mapData.put(MapData.BLIND, new BLIND_GD());
        mapData.put(MapData.CONTAINMENT, new CONTAINMENT_GD());
        mapData.put(MapData.AFTERMATH, new AFTERMATH_GD());
        mapData.put(MapData.CRYPT, new CRYPT_GD());
        mapData.put(MapData.HOMETREE, new HOMETREE_GD());
        mapData.put(MapData.AURORA, new AURORA_GD());
        mapData.put(MapData.ABYSS, new ABYSS_GD());
        mapData.put(MapData.BURROW, new BURROW_GD());
        mapData.put(MapData.LAPUTA, new LAPUTA_GD());
        mapData.put(MapData.DOME, new DOME_GD());
        mapData.put(MapData.NUKETOWN, new NUKETOWN_GD());
    }

    public static enum GameType {

        RANKED,
        SOCIAL;

        @Override
        public String toString() {
            char first = super.toString().charAt(0);
            String rest = super.toString().substring(1, super.toString().length()).toLowerCase();
            return first + rest;
        }
    }

    public static enum MapData {

        ASCENSION,
        CASTLE,
        DUNGEON,
        OVERFLOW,
        PLAINES,
        TOMB,
        JUNGLE,
        BLIZZARD,
        BLIND,
        CONTAINMENT,
        AFTERMATH,
        CRYPT,
        HOMETREE,
        AURORA,
        ABYSS,
        BURROW,
        LAPUTA,
        DOME,
        NUKETOWN;

        @Override
        public String toString() {
            String result = super.toString();
            return result.toUpperCase();
        }
    }

    public static enum MapName {

        ASCENSION,
        CASTLE,
        DUNGEON,
        OVERFLOW,
        PLAINES,
        TOMB,
        JUNGLE,
        BLIZZARD,
        BLIND,
        CONTAINMENT,
        AFTERMATH,
        CRYPT,
        HOMETREE,
        AURORA,
        ABYSS,
        BURROW,
        LAPUTA,
        DOME,
        NUKETOWN;

        @Override
        public String toString() {
            String result = super.toString();
            return result.toUpperCase();
        }
    }
}
