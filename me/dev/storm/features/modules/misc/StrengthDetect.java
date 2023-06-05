//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import me.dev.storm.features.command.*;
import java.util.*;

public class StrengthDetect extends Module
{
    public static final Minecraft mc;
    private final Set<EntityPlayer> str;
    
    public StrengthDetect() {
        super("StrengthNotify", "Notifies who has the strength potion effect.", Category.MISC, true, false, false);
        this.str = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
    }
    
    @Override
    public String onUpdate() {
        for (final EntityPlayer player : StrengthDetect.mc.world.playerEntities) {
            if (player.equals((Object)StrengthDetect.mc.player)) {
                continue;
            }
            if (player.isPotionActive(MobEffects.STRENGTH) && !this.str.contains(player)) {
                Command.sendMessage(player.getDisplayNameString() + " has strength");
                this.str.add(player);
            }
            if (!this.str.contains(player)) {
                continue;
            }
            if (player.isPotionActive(MobEffects.STRENGTH)) {
                continue;
            }
            Command.sendMessage(player.getDisplayNameString() + " doesnt have strength anymore");
            this.str.remove(player);
        }
        return null;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
