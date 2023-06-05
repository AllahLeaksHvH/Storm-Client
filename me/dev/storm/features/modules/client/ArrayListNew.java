//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.*;
import me.dev.storm.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import me.dev.storm.util.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ArrayListNew extends Module
{
    Setting<Integer> mode;
    Setting<Float> saturation;
    Setting<Float> brightness;
    Setting<Integer> red;
    Setting<Integer> green;
    Setting<Integer> blue;
    LBFontRenderer renderer;
    
    public ArrayListNew() {
        super("List", "better array list", Category.CLIENT, true, false, false);
        this.mode = (Setting<Integer>)this.register(new Setting("Mode", (T)0, (T)0, (T)1));
        this.saturation = (Setting<Float>)this.register(new Setting("Saturation", (T)0.9f, (T)0.0f, (T)1.0f));
        this.brightness = (Setting<Float>)this.register(new Setting("Brightness", (T)1.0f, (T)0.0f, (T)1.0f));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
    }
    
    @Override
    public void onEnable() {
        this.renderer = new LBFontRenderer(new Font("Arial", 0, 50));
    }
    
    @SubscribeEvent
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        int counter = 1;
        for (final Module module : Storm.moduleManager.sortedModules) {
            if (this.mode.getValue() == 0) {
                final String display = module.getDisplayName() + ((module.getDisplayInfo() != null) ? (" " + ChatFormatting.GRAY + module.getDisplayInfo()) : "");
                final int color = SkyRainbow(counter * 100, this.saturation.getValue(), this.brightness.getValue());
                RenderUtil.drawRectangleCorrectly(0, 10 + counter * 11 - 2, ArrayListNew.mc.fontRenderer.getStringWidth(display) + 4, ArrayListNew.mc.fontRenderer.FONT_HEIGHT + 2, new Color(0, 0, 0, 100).getRGB());
                ArrayListNew.mc.fontRenderer.drawString(display, 2, 10 + counter * 11, color);
                ++counter;
            }
            else {
                final String display = module.getDisplayName() + ((module.getDisplayInfo() != null) ? (" " + ChatFormatting.GRAY + module.getDisplayInfo()) : "");
                final int color = alphaStep(new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()), 50, counter * 2 + 10).getRGB();
                ArrayListNew.mc.fontRenderer.drawString(display, 2, 10 + counter * 11, color);
                ++counter;
            }
        }
    }
    
    public static Color alphaStep(final Color color, final int index, final int count) {
        final float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        float brightness = Math.abs((System.currentTimeMillis() % 2000L / 1000.0f + index / (float)count * 2.0f) % 2.0f - 1.0f);
        brightness = 0.5f + 0.5f * brightness;
        hsb[2] = brightness % 2.0f;
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }
    
    public static int SkyRainbow(final int var, final float bright, final float st) {
        double v1 = Math.ceil((double)(System.currentTimeMillis() + var * 109)) / 5.0;
        return Color.getHSBColor(((float)((v1 %= 360.0) / 360.0) < 0.5) ? (-(float)(v1 / 360.0)) : ((float)(v1 / 360.0)), st, bright).getRGB();
    }
}
