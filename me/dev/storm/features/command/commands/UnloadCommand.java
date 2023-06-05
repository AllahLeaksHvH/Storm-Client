//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.command.commands;

import me.dev.storm.features.command.*;
import me.dev.storm.*;

public class UnloadCommand extends Command
{
    public UnloadCommand() {
        super("unload", new String[0]);
    }
    
    public void execute(final String[] commands) {
        Storm.unload(true);
    }
}
