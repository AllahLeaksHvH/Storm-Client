//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.command.commands;

import me.dev.storm.features.command.*;
import me.dev.storm.*;
import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.*;

public class BindCommand extends Command
{
    public BindCommand() {
        super("bind", new String[] { "<module>", "<bind>" });
    }
    
    public void execute(final String[] commands) {
        if (commands.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        final String rkey = commands[1];
        final String moduleName = commands[0];
        final Module module = Storm.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            sendMessage("Unknown module '" + module + "'!");
            return;
        }
        if (rkey == null) {
            sendMessage(module.getName() + " is bound to " + ChatFormatting.GRAY + module.getBind().toString());
            return;
        }
        int key = Keyboard.getKeyIndex(rkey.toUpperCase());
        if (rkey.equalsIgnoreCase("none")) {
            key = -1;
        }
        if (key == 0) {
            sendMessage("Unknown key '" + rkey + "'!");
            return;
        }
        module.bind.setValue(new Bind(key));
        sendMessage("Bind for " + ChatFormatting.GREEN + module.getName() + ChatFormatting.WHITE + " set to " + ChatFormatting.GRAY + rkey.toUpperCase());
    }
}
