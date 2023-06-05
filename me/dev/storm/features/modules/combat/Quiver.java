//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import me.dev.storm.util.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.item.*;

public class Quiver extends Module
{
    private final Setting<Integer> tickDelay;
    
    public Quiver() {
        super("Quiver", "Rotates and shoots yourself with good potion effects", Category.COMBAT, true, false, false);
        this.tickDelay = (Setting<Integer>)this.register(new Setting("TickDelay", (T)3, (T)0, (T)8));
    }
    
    @Override
    public String onUpdate() {
        if (Quiver.mc.player != null) {
            if (Quiver.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && Quiver.mc.player.isHandActive() && Quiver.mc.player.getItemInUseMaxCount() >= this.tickDelay.getValue()) {
                Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Quiver.mc.player.cameraYaw, -90.0f, Quiver.mc.player.onGround));
                Quiver.mc.playerController.onStoppedUsingItem((EntityPlayer)Quiver.mc.player);
            }
            final List<Integer> arrowSlots;
            if ((arrowSlots = InventoryUtil.getItemInventory(Items.TIPPED_ARROW)).get(0) == -1) {
                return null;
            }
            int speedSlot = -1;
            int strengthSlot = -1;
            for (final Integer slot : arrowSlots) {
                if (PotionUtils.getPotionFromItem(Quiver.mc.player.inventory.getStackInSlot((int)slot)).getRegistryName().getPath().contains("swiftness")) {
                    speedSlot = slot;
                }
                else {
                    if (!Objects.requireNonNull(PotionUtils.getPotionFromItem(Quiver.mc.player.inventory.getStackInSlot((int)slot)).getRegistryName()).getPath().contains("strength")) {
                        continue;
                    }
                    strengthSlot = slot;
                }
            }
        }
        return null;
    }
    
    @Override
    public void onEnable() {
    }
    
    private int findBow() {
        return InventoryUtil.getItemHotbar((Item)Items.BOW);
    }
    
    @Override
    public String getDisplayInfo() {
        return "Active";
    }
}
