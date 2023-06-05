//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.command.commands;

import me.dev.storm.features.command.*;
import me.dev.storm.*;
import me.dev.storm.manager.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class FriendCommand extends Command
{
    public FriendCommand() {
        super("friend", new String[] { "<add/del/name/clear>", "<name>" });
    }
    
    public void execute(final String[] commands) {
        if (commands.length == 1) {
            if (Storm.friendManager.getFriends().isEmpty()) {
                sendMessage("Friend list empty D:.");
            }
            else {
                String f = "Friends: ";
                for (final FriendManager.Friend friend : Storm.friendManager.getFriends()) {
                    try {
                        f = f + friend.getUsername() + ", ";
                    }
                    catch (Exception ex) {}
                }
                sendMessage(f);
            }
            return;
        }
        if (commands.length != 2) {
            if (commands.length >= 2) {
                final String s = commands[0];
                switch (s) {
                    case "add": {
                        Storm.friendManager.addFriend(commands[1]);
                        sendMessage(ChatFormatting.GREEN + commands[1] + " has been friended");
                    }
                    case "del": {
                        Storm.friendManager.removeFriend(commands[1]);
                        sendMessage(ChatFormatting.RED + commands[1] + " has been unfriended");
                    }
                    default: {
                        sendMessage("Unknown Command, try friend add/del (name)");
                        break;
                    }
                }
            }
            return;
        }
        final String s2 = commands[0];
        switch (s2) {
            case "reset": {
                Storm.friendManager.onLoad();
                sendMessage("Friends got reset.");
            }
            default: {
                sendMessage(commands[0] + (Storm.friendManager.isFriend(commands[0]) ? " is friended." : " isn't friended."));
            }
        }
    }
}
