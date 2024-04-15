package mc.tusa.cheatmenu;
import com.comphenix.packetwrapper.wrappers.play.clientbound.WrapperPlayServerLookAt;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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
