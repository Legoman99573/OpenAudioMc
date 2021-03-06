package net.openaudiomc.commands.admin;

import net.openaudiomc.commands.OpenAudioCommand;
import net.openaudiomc.core.Main;
import net.openaudiomc.regions.RegionListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRegion implements OpenAudioCommand {

    @Override
    public String getSubCommand() {
        return "region";
    }

    @Override
    public boolean isPlayerCommand() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (Main.get().isRegionsEnabled()) {
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args[1].equalsIgnoreCase("playlist")) {
                        RegionListener.registerRegionPlaylist(args[2], args[3], (Player) sender);
                        sender.sendMessage(Main.PREFIX + "Set region playlist of " + args[2] + " to " + args[3]);
                    } else {
                        sender.sendMessage(Main.PREFIX + "Invalid command, please use /openaudio region <create/delete> [playlist] <region name> [url/playlist name]");
                    }
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("create")) {
                    RegionListener.registerRegion(args[1], args[2], (Player) sender);
                    sender.sendMessage(Main.PREFIX + "Changed the sound of " + args[1] + " to " + args[2]);
                } else {
                    sender.sendMessage(Main.PREFIX + "Invalid command, please use /openaudio region <create/delete> [playlist] <region name> [url/playlist name]");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("delete")) {
                    RegionListener.deleteRegion(args[2]);
                    sender.sendMessage(Main.PREFIX + "Removed the sound off" + args[1]);
                } else {
                    sender.sendMessage(Main.PREFIX + "Invalid command, please use /openaudio region <create/delete> [playlist] <region name> [url/playlist name]");
                }
            } else {
                sender.sendMessage(Main.PREFIX + "Invalid command, please use /openaudio region <create/delete> [playlist] <region name> [url/playlist name]");
            }
        } else {
            sender.sendMessage(Main.PREFIX + "Whoops, that did not go well. Please check console and report this error to the developers.");
        }
    }
}
