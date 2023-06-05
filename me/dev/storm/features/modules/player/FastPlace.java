//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.player;

import me.dev.storm.features.modules.*;
import net.minecraft.item.*;
import me.dev.storm.util.*;

public class FastPlace extends Module
{
    public FastPlace() {
        super("FastPlace", "Fast everything.", Module.Category.PLAYER, true, false, false);
    }
    
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        if (InventoryUtil.holdingItem(ItemExpBottle.class)) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        return null;
    }
}
