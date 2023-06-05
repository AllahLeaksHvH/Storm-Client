//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.client.gui.*;
import me.dev.storm.features.modules.combat.autocity.*;
import net.minecraft.init.*;
import me.dev.storm.util.trollhack.*;
import net.minecraft.entity.player.*;
import me.dev.storm.util.*;
import net.minecraft.entity.*;
import me.dev.storm.*;
import java.util.*;

public class AntiBurrow extends Module
{
    public static BlockPos pos;
    private final Setting<Double> range;
    private final Setting<Boolean> toggle;
    
    private boolean isOnLiquid() {
        final double d = AntiBurrow.mc.player.posY - 0.03;
        for (int i = MathHelper.floor(AntiBurrow.mc.player.posX); i < MathHelper.ceil(AntiBurrow.mc.player.posX); ++i) {
            for (int j = MathHelper.floor(AntiBurrow.mc.player.posZ); j < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++j) {
                final BlockPos blockPos = new BlockPos(i, MathHelper.floor(d), j);
                if (AntiBurrow.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isInLiquid() {
        final double d = AntiBurrow.mc.player.posY + 0.01;
        for (int i = MathHelper.floor(AntiBurrow.mc.player.posX); i < MathHelper.ceil(AntiBurrow.mc.player.posX); ++i) {
            for (int j = MathHelper.floor(AntiBurrow.mc.player.posZ); j < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++j) {
                final BlockPos blockPos = new BlockPos(i, (int)d, j);
                if (AntiBurrow.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        if (AntiBurrow.mc.currentScreen instanceof GuiHopper) {
            return null;
        }
        final EntityPlayer entityPlayer = this.getTarget(this.range.getValue());
        if (this.toggle.getValue()) {
            this.disable();
        }
        if (entityPlayer == null) {
            return null;
        }
        AntiBurrow.pos = new BlockPos(entityPlayer.posX, entityPlayer.posY + 0.5, entityPlayer.posZ);
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)AntiBurrow.pos)) {
                return null;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AntiBurrow.mc.player.posX, AntiBurrow.mc.player.posY + 2.0, AntiBurrow.mc.player.posZ))) {
                return null;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AntiBurrow.mc.player.posX, AntiBurrow.mc.player.posY - 1.0, AntiBurrow.mc.player.posZ))) {
                return null;
            }
            if (AntiBurrow.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.WEB) {
                return null;
            }
        }
        if (AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.AIR && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.WEB && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.REDSTONE_WIRE && !this.isOnLiquid() && !this.isInLiquid() && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.WATER && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.LAVA) {
            AntiBurrow.mc.playerController.onPlayerDamageBlock(AntiBurrow.pos, BlockUtil.getRayTraceFacing(AntiBurrow.pos));
        }
        return null;
    }
    
    public AntiBurrow() {
        super("AntiBurrow", "AntiBurrow", Category.COMBAT, true, false, false);
        this.range = (Setting<Double>)this.register(new Setting("Range", (T)5.0, (T)1.0, (T)8.0));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
    }
    
    private EntityPlayer getTarget(final double d) {
        EntityPlayer entityPlayer = null;
        double d2 = Math.pow(d, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AntiBurrow.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, d)) {
                if (Storm.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    d2 = AntiBurrow.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AntiBurrow.mc.player.getDistanceSq((Entity)entityPlayer2) >= d2) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    d2 = AntiBurrow.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
}
