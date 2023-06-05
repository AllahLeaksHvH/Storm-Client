//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class CityPhase extends Module
{
    public Setting<Float> timeout;
    
    public CityPhase() {
        super("CityPhase", "CityPhase", Module.Category.MOVEMENT, true, false, false);
        this.timeout = (Setting<Float>)this.register(new Setting("timeout", (T)5, (T)1, (T)10));
    }
    
    public CityPhase(final String name, final String description, final Module.Category category, final boolean hasListener, final boolean hidden, final boolean alwaysListening) {
        super(name, description, category, hasListener, hidden, alwaysListening);
        this.timeout = (Setting<Float>)this.register(new Setting("timeout", (T)5, (T)1, (T)10));
    }
    
    public boolean movingByKeys() {
        return CityPhase.mc.gameSettings.keyBindForward.isKeyDown() || CityPhase.mc.gameSettings.keyBindBack.isKeyDown() || CityPhase.mc.gameSettings.keyBindLeft.isKeyDown() || CityPhase.mc.gameSettings.keyBindRight.isKeyDown();
    }
    
    public double roundToClosest(final double num, final double low, final double high) {
        final double d1 = num - low;
        final double d2 = high - num;
        if (d2 > d1) {
            return low;
        }
        return high;
    }
}
