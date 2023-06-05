//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

@Cancelable
public class PlayerDamageBlockEvent extends EventHandler
{
    public final BlockPos pos;
    public final EnumFacing facing;
    
    public PlayerDamageBlockEvent(final int n, final BlockPos blockPos, final EnumFacing enumFacing) {
        super(n);
        this.pos = blockPos;
        this.facing = enumFacing;
    }
    
    public final BlockPos getPos() {
        return this.pos;
    }
}
