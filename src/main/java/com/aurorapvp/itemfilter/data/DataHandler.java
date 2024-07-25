package com.aurorapvp.itemfilter.data;

import com.aurorapvp.itemfilter.ItemFilter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class DataHandler {
    ItemFilter plugin = ItemFilter.getInstance();

    private static DataHandler ins = new DataHandler();

    public File configF;
    public File langF;

    public FileConfiguration config;
    public FileConfiguration lang;

    public static DataHandler getInstance() {
        return ins;
    }

    public void createDataFiles() {
        this.configF = new File(this.plugin.getDataFolder(), File.separator + "config.yml");
        this.langF = new File(this.plugin.getDataFolder(), File.separator + "lang.yml");
        if (!this.configF.exists()) {
            this.configF.getParentFile().mkdirs();
            this.plugin.saveResource("config.yml", false);
        }
        if (!this.langF.exists()) {
            this.langF.getParentFile().mkdirs();
            this.plugin.saveResource("lang.yml", false);
        }
        this.config = (FileConfiguration) new YamlConfiguration();
        this.lang = (FileConfiguration) new YamlConfiguration();
        try {
            this.config.load(this.configF);
            this.lang.load(this.langF);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException iOException) {
            iOException.printStackTrace();
        }
    }

    public FileConfiguration getLang(){
        return this.lang;
    }

    public void saveFiles() {
        try {
            this.config.save(this.configF);
            this.lang.save(this.langF);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void reloadFiles() {
        try {
            this.config.load(this.configF);
            this.lang.load(this.langF);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
