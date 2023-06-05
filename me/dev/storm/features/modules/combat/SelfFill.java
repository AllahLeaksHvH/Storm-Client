//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import me.dev.storm.util.*;
import me.dev.storm.features.command.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import java.util.*;

public class SelfFill extends Module
{
    private BlockPos originalPos;
    private int oldSlot;
    
    public SelfFill() {
        super("SelfFill", "burrow", Category.COMBAT, true, false, false);
        this.oldSlot = -1;
    }
    
    @Override
    public void onEnable() {
        this.originalPos = new BlockPos(SelfFill.mc.player.posX, SelfFill.mc.player.posY, SelfFill.mc.player.posZ);
        if (SelfFill.mc.world.getBlockState(new BlockPos(SelfFill.mc.player.posX, SelfFill.mc.player.posY, SelfFill.mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) || this.intersectsWithEntity(this.originalPos)) {
            this.toggle();
            return;
        }
        this.oldSlot = SelfFill.mc.player.inventory.currentItem;
    }
    
    @Override
    public String onUpdate() {
        if (ItemUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            Command.sendMessage("Can't find obsidian in hotbar!");
            this.toggle();
            return null;
        }
        ItemUtil.switchToSlot(ItemUtil.findHotbarBlock(BlockObsidian.class));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 0.41999998688698, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 0.7531999805211997, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 1.00133597911214, SelfFill.mc.player.posZ, true));
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY + 1.16610926093821, SelfFill.mc.player.posZ, true));
        ItemUtil.placeBlock(this.originalPos, EnumHand.MAIN_HAND, true, true, false);
        SelfFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY - 6.395812, SelfFill.mc.player.posZ, false));
        ItemUtil.switchToSlot(this.oldSlot);
        Minecraft.getMinecraft().player.setSneaking(false);
        this.toggle();
        return null;
    }
    
    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : SelfFill.mc.world.loadedEntityList) {
            if (entity.equals((Object)SelfFill.mc.player)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onDisable() {
        Minecraft.getMinecraft().player.setSneaking(false);
        super.onDisable();
    }
}
