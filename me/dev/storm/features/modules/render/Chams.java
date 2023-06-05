//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Chams extends Module
{
    public static Chams INSTANCE;
    public final Setting<Boolean> fill;
    public final Setting<Boolean> xqz;
    public final Setting<Boolean> wireframe;
    public final Setting<Boolean> model;
    public final Setting<Boolean> self;
    public final Setting<Boolean> noInterp;
    public final Setting<Boolean> sneak;
    public final Setting<Boolean> glint;
    public final Setting<Float> lineWidth;
    public final Setting<Boolean> rainbow;
    public final Setting<Color> color;
    public final Setting<Color> lineColor;
    public final Setting<Color> modelColor;
    
    public Chams() {
        super("Chams", "Shows safe spots.", Module.Category.RENDER, false, false, false);
        this.fill = (Setting<Boolean>)this.register(new Setting("Fill", (T)true));
        this.xqz = (Setting<Boolean>)this.register(new Setting("XQZ", (T)true));
        this.wireframe = (Setting<Boolean>)this.register(new Setting("Wireframe", (T)true));
        this.model = (Setting<Boolean>)this.register(new Setting("Model", (T)false));
        this.self = (Setting<Boolean>)this.register(new Setting("Self", (T)true));
        this.noInterp = (Setting<Boolean>)this.register(new Setting("NoInterp", (T)false));
        this.sneak = (Setting<Boolean>)this.register(new Setting("Sneak", (T)false));
        this.glint = (Setting<Boolean>)this.register(new Setting("Glint", (T)false));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)3.0f));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        this.color = (Setting<Color>)this.register(new Setting("Color", (T)new Color(132, 132, 241, 150)));
        this.lineColor = (Setting<Color>)this.register(new Setting("LineColor", (T)new Color(255, 255, 255)));
        this.modelColor = (Setting<Color>)this.register(new Setting("ModelColor", (T)new Color(125, 125, 213, 150)));
        Chams.INSTANCE = this;
    }
    
    public String getInfo() {
        String info = null;
        if (this.fill.getValue()) {
            info = "Fill";
        }
        else if (this.wireframe.getValue()) {
            info = "Wireframe";
        }
        if (this.wireframe.getValue() && this.fill.getValue()) {
            info = "Both";
        }
        return info;
    }
    
    @SubscribeEvent
    public void onRenderPlayerEvent(final RenderPlayerEvent.Pre event) {
        event.getEntityPlayer().hurtTime = 0;
    }
}
