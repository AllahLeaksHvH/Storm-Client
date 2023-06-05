//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;
import me.dev.storm.features.gui.*;
import net.minecraft.util.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import me.dev.storm.event.events.*;
import me.dev.storm.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;

public class ClickGui extends Module
{
    private static ClickGui INSTANCE;
    public Setting<String> prefix;
    public Setting<Boolean> blur;
    public Setting<Boolean> customFov;
    public Setting<Float> fov;
    public Setting<Integer> red;
    public Setting<Boolean> snowing;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> hoverAlpha;
    public Setting<Integer> topRed;
    public Setting<Integer> topGreen;
    public Setting<Integer> topBlue;
    public Setting<Integer> alpha;
    public Setting<Boolean> rainbow;
    public Setting<rainbowMode> rainbowModeHud;
    public final Setting<Style> style;
    public Setting<rainbowModeArray> rainbowModeA;
    public Setting<Integer> rainbowHue;
    public Setting<Float> rainbowBrightness;
    public Setting<Float> rainbowSaturation;
    public Setting<Boolean> box2;
    public Setting<Integer> shaderRadius;
    public Setting<Integer> shaderRed;
    public final Setting<Color> color;
    public Setting<Integer> shaderGreen;
    public Setting<Integer> shaderBlue;
    public Setting<Integer> shaderAlpha;
    public Setting<Boolean> shader;
    private StormGui click;
    public static final ResourceLocation GradientIMG;
    
    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT, true, false, false);
        this.prefix = (Setting<String>)this.register(new Setting("Prefix", (T)"-"));
        this.blur = (Setting<Boolean>)this.register(new Setting("Blur", (T)true));
        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", (T)false));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", (T)150.0f, (T)(-180.0f), (T)180.0f));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)254, (T)0, (T)255));
        this.snowing = (Setting<Boolean>)this.register(new Setting("Snowing", (T)true));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)254, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)0, (T)0, (T)255));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)77, (T)0, (T)255));
        this.topRed = (Setting<Integer>)this.register(new Setting("SecondRed", (T)164, (T)0, (T)255));
        this.topGreen = (Setting<Integer>)this.register(new Setting("SecondGreen", (T)164, (T)0, (T)255));
        this.topBlue = (Setting<Integer>)this.register(new Setting("SecondBlue", (T)1, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", (T)92, (T)0, (T)255));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        this.rainbowModeHud = (Setting<rainbowMode>)this.register(new Setting("HRainbowMode", (T)rainbowMode.Static, v -> this.rainbow.getValue()));
        this.style = (Setting<Style>)this.register(new Setting("Style", (T)Style.NEW));
        this.rainbowModeA = (Setting<rainbowModeArray>)this.register(new Setting("ARainbowMode", (T)rainbowModeArray.Static, v -> this.rainbow.getValue()));
        this.rainbowHue = (Setting<Integer>)this.register(new Setting("Delay", (T)240, (T)0, (T)600, v -> this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Float>)this.register(new Setting("Brightness", (T)150.0f, (T)1.0f, (T)255.0f, v -> this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Float>)this.register(new Setting("Saturation", (T)150.0f, (T)1.0f, (T)255.0f, v -> this.rainbow.getValue()));
        this.box2 = (Setting<Boolean>)this.register(new Setting("Box", (T)true));
        this.shaderRadius = (Setting<Integer>)this.register(new Setting("ShaderRadius", (T)3, (T)1, (T)10));
        this.shaderRed = (Setting<Integer>)this.register(new Setting("ShaderRed", (T)253, (T)0, (T)255));
        this.color = (Setting<Color>)this.register(new Setting("Color", (T)new Color(125, 125, 213)));
        this.shaderGreen = (Setting<Integer>)this.register(new Setting("ShaderGreen", (T)253, (T)0, (T)255));
        this.shaderBlue = (Setting<Integer>)this.register(new Setting("ShaderBlue", (T)0, (T)0, (T)255));
        this.shaderAlpha = (Setting<Integer>)this.register(new Setting("ShaderAlpha", (T)255, (T)0, (T)255));
        this.shader = (Setting<Boolean>)this.register(new Setting("Shader", (T)true));
        this.setInstance();
    }
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    public static Colors INSTANCE() {
        return null;
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public String onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
        if (GuiBlur.mc.world != null) {
            if (!getInstance().isEnabled() && !(GuiBlur.mc.currentScreen instanceof GuiContainer) && !(GuiBlur.mc.currentScreen instanceof GuiChat) && !(GuiBlur.mc.currentScreen instanceof GuiConfirmOpenLink) && !(GuiBlur.mc.currentScreen instanceof GuiEditSign) && !(GuiBlur.mc.currentScreen instanceof GuiGameOver) && !(GuiBlur.mc.currentScreen instanceof GuiOptions) && !(GuiBlur.mc.currentScreen instanceof GuiIngameMenu) && !(GuiBlur.mc.currentScreen instanceof GuiVideoSettings) && !(GuiBlur.mc.currentScreen instanceof GuiScreenOptionsSounds) && !(GuiBlur.mc.currentScreen instanceof GuiControls) && !(GuiBlur.mc.currentScreen instanceof GuiCustomizeSkin) && !(GuiBlur.mc.currentScreen instanceof GuiModList) && !(GuiBlur.mc.currentScreen instanceof StormGui)) {
                if (GuiBlur.mc.entityRenderer.getShaderGroup() != null) {
                    GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
            }
            else if (OpenGlHelper.shadersSupported && GuiBlur.mc.getRenderViewEntity() instanceof EntityPlayer) {
                if (GuiBlur.mc.entityRenderer.getShaderGroup() != null) {
                    GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
                try {
                    GuiBlur.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (GuiBlur.mc.entityRenderer.getShaderGroup() != null && GuiBlur.mc.currentScreen == null) {
                GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
        return null;
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Storm.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("prefix set to " + ChatFormatting.DARK_GRAY + Storm.commandManager.getPrefix());
            }
            Storm.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }
    
    @Override
    public void onEnable() {
        ClickGui.mc.displayGuiScreen((GuiScreen)StormGui.getClickGui());
    }
    
    @Override
    public void onDisable() {
        GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }
    
    @Override
    public void onLoad() {
        Storm.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        Storm.commandManager.setPrefix(this.prefix.getValue());
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof StormGui)) {
            this.disable();
        }
    }
    
    @Override
    public String getDisplayInfo() {
        return "NewGui";
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
        GradientIMG = new ResourceLocation("textures/BUG.png");
    }
    
    public enum rainbowModeArray
    {
        Static, 
        Up;
    }
    
    public enum rainbowMode
    {
        Static, 
        Sideway;
    }
    
    public enum Style
    {
        OLD, 
        NEW, 
        FUTURE, 
        DOTGOD;
    }
}
