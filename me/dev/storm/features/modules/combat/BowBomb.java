//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.*;
import net.minecraft.util.*;
import me.dev.storm.features.command.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;

public class BowBomb extends Module
{
    private boolean shooting;
    private long lastShootTime;
    public Setting<Boolean> Bows;
    public Setting<Boolean> pearls;
    public Setting<Boolean> eggs;
    public Setting<Boolean> snowballs;
    public Setting<Integer> Timeout;
    public Setting<Integer> spoofs;
    public Setting<Boolean> bypass;
    public Setting<Boolean> debug;
    
    public BowBomb() {
        super("AWP", "stop hitting JA offline bruh", Category.COMBAT, true, false, false);
        this.Bows = (Setting<Boolean>)this.register(new Setting("Bows", (T)true));
        this.pearls = (Setting<Boolean>)this.register(new Setting("Pearls", (T)true));
        this.eggs = (Setting<Boolean>)this.register(new Setting("Eggs", (T)true));
        this.snowballs = (Setting<Boolean>)this.register(new Setting("SnowBallz", (T)true));
        this.Timeout = (Setting<Integer>)this.register(new Setting("Timeout", (T)5000, (T)100, (T)20000));
        this.spoofs = (Setting<Integer>)this.register(new Setting("Spoofs", (T)10, (T)1, (T)300));
        this.bypass = (Setting<Boolean>)this.register(new Setting("Bypass", (T)false));
        this.debug = (Setting<Boolean>)this.register(new Setting("Debug", (T)false));
    }
    
    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() != 0) {
            return;
        }
        if (event.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging packet = (CPacketPlayerDigging)event.getPacket();
            if (packet.getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                final ItemStack handStack = BowBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND);
                if (!handStack.isEmpty() && handStack.getItem() != null && handStack.getItem() instanceof ItemBow && this.Bows.getValue()) {
                    this.doSpoofs();
                    if (this.debug.getValue()) {
                        Command.sendMessage("trying to spoof...");
                    }
                }
            }
        }
        else if (event.getPacket() instanceof CPacketPlayerTryUseItem) {
            final CPacketPlayerTryUseItem packet2 = (CPacketPlayerTryUseItem)event.getPacket();
            if (packet2.getHand() == EnumHand.MAIN_HAND) {
                final ItemStack handStack = BowBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND);
                if (!handStack.isEmpty() && handStack.getItem() != null) {
                    if (handStack.getItem() instanceof ItemEgg && this.eggs.getValue()) {
                        this.doSpoofs();
                    }
                    else if (handStack.getItem() instanceof ItemEnderPearl && this.pearls.getValue()) {
                        this.doSpoofs();
                    }
                    else if (handStack.getItem() instanceof ItemSnowball && this.snowballs.getValue()) {
                        this.doSpoofs();
                    }
                }
            }
        }
    }
    
    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= this.Timeout.getValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            BowBomb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BowBomb.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            for (int index = 0; index < this.spoofs.getValue(); ++index) {
                if (this.bypass.getValue()) {
                    BowBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowBomb.mc.player.posX, BowBomb.mc.player.posY + 1.0E-10, BowBomb.mc.player.posZ, false));
                    BowBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowBomb.mc.player.posX, BowBomb.mc.player.posY - 1.0E-10, BowBomb.mc.player.posZ, true));
                }
                else {
                    BowBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowBomb.mc.player.posX, BowBomb.mc.player.posY - 1.0E-10, BowBomb.mc.player.posZ, true));
                    BowBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowBomb.mc.player.posX, BowBomb.mc.player.posY + 1.0E-10, BowBomb.mc.player.posZ, false));
                }
            }
            if (this.debug.getValue()) {
                Command.sendMessage("spoofed!");
            }
            this.shooting = false;
        }
    }
}
