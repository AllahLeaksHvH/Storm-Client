//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

@Cancelable
public class ProcessRightClickBlockEvent extends EventStage
{
    public BlockPos pos;
    public EnumHand hand;
    public ItemStack stack;
    
    public ProcessRightClickBlockEvent(final BlockPos pos, final EnumHand hand, final ItemStack stack) {
        this.pos = pos;
        this.hand = hand;
        this.stack = stack;
    }
}
