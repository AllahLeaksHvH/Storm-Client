//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;

public class ModuleColor extends Module
{
    public static final Setting<Color> daColor;
    
    public ModuleColor() {
        super("Color", "Allows you to customize the client's main colors.", Category.CLIENT, true, false, false);
    }
    
    public static int getColor() {
        return new Color(ModuleColor.daColor.getValue().getRed(), ModuleColor.daColor.getValue().getGreen(), ModuleColor.daColor.getValue().getBlue()).getRGB();
    }
    
    static {
        daColor = new Setting<Color>("Color", new Color(255, 255, 255, 255));
    }
}
