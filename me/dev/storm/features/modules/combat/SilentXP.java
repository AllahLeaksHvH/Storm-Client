//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import me.dev.storm.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class SilentXP extends Module
{
    public Setting<Mode> mode;
    public Setting<Boolean> antiFriend;
    public Setting<Bind> key;
    public Setting<Boolean> groundOnly;
    private boolean last;
    private boolean on;
    
    public SilentXP() {
        super("KeyXP", "Silent XP", Category.COMBAT, false, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.MIDDLECLICK));
        this.antiFriend = (Setting<Boolean>)this.register(new Setting("AntiFriend", (T)true));
        this.key = (Setting<Bind>)this.register(new Setting("Key", (T)new Bind(-1), v -> this.mode.getValue() != Mode.MIDDLECLICK));
        this.groundOnly = (Setting<Boolean>)this.register(new Setting("BelowHorizon", (T)false));
    }
    
    @Override
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        switch (this.mode.getValue()) {
            case PRESS: {
                if (this.key.getValue().isDown()) {
                    this.throwXP(false);
                    break;
                }
                break;
            }
            case TOGGLE: {
                if (this.toggled()) {
                    this.throwXP(false);
                    break;
                }
                break;
            }
            default: {
                if (this.groundOnly.getValue() && SilentXP.mc.player.rotationPitch < 0.0f) {
                    return null;
                }
                if (Mouse.isButtonDown(2)) {
                    this.throwXP(true);
                    break;
                }
                break;
            }
        }
        return null;
    }
    
    private boolean toggled() {
        if (this.key.getValue().getKey() == -1) {
            return false;
        }
        if (!Keyboard.isKeyDown(this.key.getValue().getKey())) {
            this.last = true;
        }
        else {
            if (Keyboard.isKeyDown(this.key.getValue().getKey()) && this.last && !this.on) {
                this.last = false;
                return this.on = true;
            }
            if (Keyboard.isKeyDown(this.key.getValue().getKey()) && this.last && this.on) {
                this.last = false;
                return this.on = false;
            }
        }
        return this.on;
    }
    
    private void throwXP(final boolean mcf) {
        final RayTraceResult result;
        if (mcf && this.antiFriend.getValue() && (result = SilentXP.mc.objectMouseOver) != null && result.typeOfHit == RayTraceResult.Type.ENTITY && result.entityHit instanceof EntityPlayer) {
            return;
        }
        final int xpSlot = InventoryUtil.findHotbarBlock(ItemExpBottle.class);
        final boolean offhand = SilentXP.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE;
        if (xpSlot != -1 || offhand) {
            final int oldslot = SilentXP.mc.player.inventory.currentItem;
            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(xpSlot, false);
            }
            SilentXP.mc.playerController.processRightClick((EntityPlayer)SilentXP.mc.player, (World)SilentXP.mc.world, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(oldslot, false);
            }
        }
    }
    
    public enum Mode
    {
        MIDDLECLICK, 
        TOGGLE, 
        PRESS;
    }
}
