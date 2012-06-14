package couk.Adamki11s.Database;

import couk.Adamki11s.Languages.LangInterface.Langs;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageConfiguration {

    public void setup() {
        File lang = new File("plugins/Warzone/Language/Lang.yml");
        if (!lang.exists()) {
            try {
                lang.createNewFile();
                YamlConfiguration c = new YamlConfiguration();
                try {
                    c.load(lang);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidConfigurationException ex) {
                    Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.set("Language", "EN");
                c.save(lang);
                writeNotes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeNotes() {
        File lang = new File("plugins/Warzone/Language/Lang.yml");
        FileWriter fstream;
        try {
            fstream = new FileWriter(lang, true);
            BufferedWriter fbw = new BufferedWriter(fstream);
            fbw.write("#Supported languages: Danish, English, French, German.");
            fbw.newLine();
            fbw.write("#For English : EN");
            fbw.newLine();
            fbw.write("#For French : FR");
            fbw.newLine();
            fbw.write("#For German : DE");
            fbw.newLine();
            fbw.write("#For Danish : DA");
            fbw.newLine();
            fbw.write("#For Dutch : NL");
            fbw.newLine();
            fbw.write("#For Polish : PL");
            fbw.newLine();
            fbw.write("#Credits go to : @Zeerix for the German translation, @Simon Sejer Nielsen for the Danish translation, @OrtwinS for the Dutch translation,"
                    + "@mmoboy for the Polish translation and @Ethneldryt for the French translation.");
            fbw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Langs loadLang() {
        File lang = new File("plugins/Warzone/Language/Lang.yml");
        String langRes = "";
        YamlConfiguration c = new YamlConfiguration();
        try {
            c.load(lang);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        langRes = c.getString("Language", "EN");
        try {
            c.save(lang);
        } catch (IOException ex) {
            Logger.getLogger(LanguageConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (langRes.equalsIgnoreCase("EN")) {
            System.out.println("[Warzone] Language setting : ENGLISH");
            writeNotes();
            return Langs.ENGLISH;
        } else if (langRes.equalsIgnoreCase("FR")) {
            System.out.println("[Warzone] Language setting : FRENCH");
            writeNotes();
            return Langs.FRENCH;
        } else if (langRes.equalsIgnoreCase("DE")) {
            System.out.println("[Warzone] Language setting : GERMAN");
            writeNotes();
            return Langs.GERMAN;
        } else if (langRes.equalsIgnoreCase("DA")) {
            System.out.println("[Warzone] Language setting : DANISH");
            writeNotes();
            return Langs.DANISH;
        } else if (langRes.equalsIgnoreCase("NL")) {
            System.out.println("[Warzone] Language setting : DUTCH");
            writeNotes();
            return Langs.DUTCH;
        } else if (langRes.equalsIgnoreCase("PL")) {
            System.out.println("[Warzone] Language setting : POLISH");
            writeNotes();
            return Langs.POLISH;
        } else {
            System.out.println("[Warzone][ERROR] Language set to ENGLISH by Default.");
            System.out.println("[Warzone][ERROR] Invalid property in configuration file!");
            writeNotes();
            return Langs.ENGLISH;
        }
    }
}
