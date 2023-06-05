//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.item.*;
import java.util.*;
import me.dev.storm.event.events.*;
import net.minecraft.client.gui.*;

public class DurabilityAlert extends Module
{
    private Setting<Integer> dura;
    private Setting<Boolean> chad;
    private boolean lowDura;
    
    public DurabilityAlert() {
        super("DurabilityAlert", "Alerts you and your friends if you have low durability", Category.MISC, true, false, false);
        this.dura = (Setting<Integer>)this.register(new Setting("Durability", (T)10, (T)1, (T)100));
        this.chad = (Setting<Boolean>)this.register(new Setting("Australian Mode", (T)true));
        this.lowDura = false;
    }
    
    @Override
    public String onUpdate() {
        this.lowDura = false;
        try {
            for (final ItemStack is : DurabilityAlert.mc.player.getArmorInventoryList()) {
                final float green = (is.getMaxDamage() - (float)is.getItemDamage()) / is.getMaxDamage();
                final float red = 1.0f - green;
                final int dmg = 100 - (int)(red * 100.0f);
                if (dmg > (float)this.dura.getValue()) {
                    continue;
                }
                this.lowDura = true;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (this.lowDura) {
            final ScaledResolution sr = new ScaledResolution(DurabilityAlert.mc);
            DurabilityAlert.mc.fontRenderer.drawStringWithShadow("Warning: Your " + (this.chad.getValue() ? "armour" : "shits") + " is below " + this.dura.getValue() + "%", (float)(sr.getScaledWidth() / 2 - DurabilityAlert.mc.fontRenderer.getStringWidth("Warning: Your armour is below " + this.dura.getValue() + "%") / 2), 15.0f, -65536);
        }
    }
}
