//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui;

import net.minecraft.client.*;
import me.dev.storm.features.gui.components.*;
import me.dev.storm.util.*;
import me.dev.storm.features.modules.client.*;
import java.awt.*;
import me.dev.storm.*;
import me.dev.storm.features.modules.*;
import me.dev.storm.features.gui.components.items.buttons.*;
import me.dev.storm.features.*;
import java.util.function.*;
import java.util.*;
import me.dev.storm.features.gui.components.items.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import java.io.*;

public class StormGui extends GuiScreen
{
    private static StormGui StormGui;
    private static StormGui INSTANCE;
    public static Minecraft mc;
    private final ArrayList<Component> components;
    public static ParticleGenerator particleGenerator;
    int color;
    private StormGui ClickGuiMod;
    
    public StormGui() {
        this.components = new ArrayList<Component>();
        this.color = new Color(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().blue.getValue(), ClickGui.getInstance().green.getValue()).getRGB();
        this.setInstance();
        this.load();
    }
    
    public static StormGui getInstance() {
        if (me.dev.storm.features.gui.StormGui.INSTANCE == null) {
            me.dev.storm.features.gui.StormGui.INSTANCE = new StormGui();
        }
        return me.dev.storm.features.gui.StormGui.INSTANCE;
    }
    
    public static StormGui getClickGui() {
        final StormGui stormGui = me.dev.storm.features.gui.StormGui.StormGui;
        return getInstance();
    }
    
    private void setInstance() {
        me.dev.storm.features.gui.StormGui.INSTANCE = this;
    }
    
    private void load() {
        int x = -84;
        for (final Module.Category category : Storm.moduleManager.getCategories()) {
            final ArrayList<Component> components2 = this.components;
            final String name = category.getName();
            x += 110;
            components2.add(new Component(name, x, 4, true) {
                public void setupItems() {
                    StormGui$1.counter1 = new int[] { 1 };
                    Storm.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton((Button)new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparing((Function<? super E, ? extends Comparable>)Feature::getName)));
    }
    
    public void updateModule(final Module module) {
        for (final Component component : this.components) {
            for (final Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) {
                    continue;
                }
                final ModuleButton button = (ModuleButton)item;
                final Module mod = button.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(mod)) {
                    continue;
                }
                button.initSettings();
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.checkMouseWheel();
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
        me.dev.storm.features.gui.StormGui.particleGenerator.drawParticles(mouseX, mouseY);
        final ScaledResolution res = new ScaledResolution(me.dev.storm.features.gui.StormGui.mc);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int releaseButton) {
        this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public final ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        }
        else if (dWheel > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    public Component getComponentByName(final String name) {
        for (final Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return component;
        }
        return null;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
    }
    
    public static int getRainbowInt(final float seconds, final float saturation, final float brightness, final long index) {
        final float hue = (System.currentTimeMillis() + index) % (int)(seconds * 1000.0f) / (seconds * 1000.0f);
        final int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }
    
    static {
        me.dev.storm.features.gui.StormGui.INSTANCE = new StormGui();
        me.dev.storm.features.gui.StormGui.mc = Minecraft.getMinecraft();
        me.dev.storm.features.gui.StormGui.particleGenerator = new ParticleGenerator(200, me.dev.storm.features.gui.StormGui.mc.displayWidth, me.dev.storm.features.gui.StormGui.mc.displayHeight);
    }
}
