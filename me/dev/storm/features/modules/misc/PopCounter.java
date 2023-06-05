//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import java.util.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.command.*;

public class PopCounter extends Module
{
    public static HashMap<String, Integer> TotemPopContainer;
    private static PopCounter INSTANCE;
    
    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Category.MISC, true, false, false);
        this.setInstance();
    }
    
    public static PopCounter getInstance() {
        if (PopCounter.INSTANCE == null) {
            PopCounter.INSTANCE = new PopCounter();
        }
        return PopCounter.INSTANCE;
    }
    
    private void setInstance() {
        PopCounter.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        PopCounter.TotemPopContainer.clear();
    }
    
    public void onDeath(final EntityPlayer player) {
        if (PopCounter.TotemPopContainer.containsKey(player.getName())) {
            final int l_Count = PopCounter.TotemPopContainer.get(player.getName());
            PopCounter.TotemPopContainer.remove(player.getName());
            if (l_Count == 1) {
                Command.sendSilentMessage("" + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " died after popping a totem.");
            }
            else {
                Command.sendSilentMessage("" + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " died after popping " + l_Count + " totems.");
            }
        }
    }
    
    public void onTotemPop(final EntityPlayer player) {
        if (fullNullCheck()) {
            return;
        }
        if (PopCounter.mc.player.equals((Object)player)) {
            return;
        }
        int l_Count = 1;
        if (PopCounter.TotemPopContainer.containsKey(player.getName())) {
            l_Count = PopCounter.TotemPopContainer.get(player.getName());
            PopCounter.TotemPopContainer.put(player.getName(), ++l_Count);
        }
        else {
            PopCounter.TotemPopContainer.put(player.getName(), l_Count);
        }
        if (l_Count == 1) {
            Command.sendSilentMessage("" + ChatFormatting.LIGHT_PURPLE + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + "one" + ChatFormatting.GRAY + " totem");
        }
        else {
            Command.sendSilentMessage("" + ChatFormatting.LIGHT_PURPLE + player.getName() + " popped " + ChatFormatting.RED + l_Count + ChatFormatting.LIGHT_PURPLE + " totems");
        }
    }
    
    static {
        PopCounter.TotemPopContainer = new HashMap<String, Integer>();
        PopCounter.INSTANCE = new PopCounter();
    }
}
