//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.client.entity.*;

public class ReverseStep extends Module
{
    private static ReverseStep INSTANCE;
    private final Setting<Boolean> twoBlocks;
    
    public ReverseStep() {
        super("ReverseStep", "ReverseStep.", Module.Category.MOVEMENT, true, false, false);
        this.twoBlocks = (Setting<Boolean>)this.register(new Setting("2Blocks", (T)Boolean.FALSE));
        this.setInstance();
    }
    
    public static ReverseStep getInstance() {
        if (ReverseStep.INSTANCE == null) {
            ReverseStep.INSTANCE = new ReverseStep();
        }
        return ReverseStep.INSTANCE;
    }
    
    private void setInstance() {
        ReverseStep.INSTANCE = this;
    }
    
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        final IBlockState touchingState = ReverseStep.mc.world.getBlockState(new BlockPos(ReverseStep.mc.player.posX, ReverseStep.mc.player.posY, ReverseStep.mc.player.posZ).down(2));
        final IBlockState touchingState2 = ReverseStep.mc.world.getBlockState(new BlockPos(ReverseStep.mc.player.posX, ReverseStep.mc.player.posY, ReverseStep.mc.player.posZ).down(3));
        if (ReverseStep.mc.player.isInLava() || ReverseStep.mc.player.isInWater()) {
            return null;
        }
        if (touchingState.getBlock() == Blocks.BEDROCK || touchingState.getBlock() == Blocks.OBSIDIAN) {
            if (ReverseStep.mc.player.onGround) {
                final EntityPlayerSP player = ReverseStep.mc.player;
                --player.motionY;
            }
        }
        else if (((this.twoBlocks.getValue() && touchingState2.getBlock() == Blocks.BEDROCK) || (this.twoBlocks.getValue() && touchingState2.getBlock() == Blocks.OBSIDIAN)) && ReverseStep.mc.player.onGround) {
            final EntityPlayerSP player2 = ReverseStep.mc.player;
            --player2.motionY;
        }
        return null;
    }
    
    static {
        ReverseStep.INSTANCE = new ReverseStep();
    }
}
