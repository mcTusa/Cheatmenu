package mc.tusa.cheatmenu;
import com.comphenix.packetwrapper.wrappers.play.clientbound.WrapperPlayServerEntityLook;
import com.comphenix.packetwrapper.wrappers.play.clientbound.WrapperPlayServerEntityTeleport;
import com.comphenix.packetwrapper.wrappers.play.clientbound.WrapperPlayServerLookAt;
import com.comphenix.packetwrapper.wrappers.play.clientbound.WrapperPlayServerPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TeleportUtils {

    public static void teleport(Player player, Location location)
    {
        WrapperPlayServerLookAt wrapperPlayServerLookAt = new WrapperPlayServerLookAt();
        wrapperPlayServerLookAt.setX(location.getX());
        wrapperPlayServerLookAt.setY(location.getY());
        wrapperPlayServerLookAt.setZ(location.getZ());
        wrapperPlayServerLookAt.setFromAnchor(WrapperPlayServerLookAt.Anchor.EYES);
        wrapperPlayServerLookAt.sendPacket(player);

    }
}
