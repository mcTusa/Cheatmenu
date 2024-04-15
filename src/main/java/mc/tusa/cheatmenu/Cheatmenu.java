package mc.tusa.cheatmenu;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import mc.tusa.cheatmenu.Commands.CheatMenuCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Cheatmenu extends JavaPlugin {

    public CheatMenuCommand cheatMenuCommand;

    public Map<Player, Functions> cheaters = new HashMap<>();

    @Override
    public void onEnable() {
        cheatMenuCommand = new CheatMenuCommand(this);
        // Plugin startup logic
        loadCommands();
        loadListeners();
    }

    private void loadCommands() {
        getCommand("cheatgui").setExecutor(cheatMenuCommand);
    }

    private void loadListeners() {
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
