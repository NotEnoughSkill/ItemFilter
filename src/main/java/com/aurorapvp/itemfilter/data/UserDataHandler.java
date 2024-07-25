package com.aurorapvp.itemfilter.data;

import com.aurorapvp.itemfilter.ItemFilter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class UserDataHandler {
    ItemFilter ins = ItemFilter.getInstance();

    File dir;
    File userdataF;

    FileConfiguration userdata;

    UUID uuid;

    public UserDataHandler (UUID paramUUID) {
        this.uuid = paramUUID;
        this.userdataF = new File(this.ins.getDataFolder().getAbsolutePath() + File.separator + "userdata" + File.separator, paramUUID + ".yml");
        this.userdata = (FileConfiguration) YamlConfiguration.loadConfiguration(this.userdataF);
    }

    public void createUserData() {
        this.dir = new File(this.ins.getDataFolder() + File.separator + "userdata");
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        }
        if (!this.userdataF.exists()) {
            try {
                this.ins.getLogger().info("Creating empty config: " + this.userdataF);
                this.userdataF.createNewFile();
                this.userdata.set("filtered-items", "");
                saveUserData();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    public ArrayList<String> filteredItemsList() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : getUserdata().getStringList("filtered-items")) {
            if (getUserdata().getStringList("filtered-items").isEmpty()) {
                continue;
            }
            arrayList.add(str);
        }
        return arrayList;
    }

    public boolean checkFiles() {
        if ( this.userdataF.exists()) {
            return true;
        }
        return false;
    }

    public FileConfiguration getUserdata() {
        return this.userdata;
    }

    public void saveUserData() {
        try {
            getUserdata().save(this.userdataF);
        } catch ( Exception exception) {}
    }
}
