//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.command.commands;

import me.dev.storm.features.command.*;
import com.mojang.realmsclient.gui.*;

public class CoordCommand extends Command
{
    public CoordCommand() {
        super("coords", new String[0]);
    }
    
    public void execute(final String[] commands) {
        if (commands.length >= 3) {
            sendMessage(ChatFormatting.GREEN + commands[0] + "'s coords are: " + commands[1] + ", " + commands[2] + ChatFormatting.GREEN + " [Overworld]");
        }
    }
}
