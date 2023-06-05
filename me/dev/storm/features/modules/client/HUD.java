//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import me.dev.storm.features.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.gui.*;
import me.dev.storm.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;
import java.text.*;
import java.util.*;
import org.lwjgl.opengl.*;
import me.dev.storm.features.gui.*;
import java.awt.*;
import net.minecraft.init.*;
import java.util.function.*;
import net.minecraft.client.renderer.*;
import me.dev.storm.util.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.dev.storm.event.events.*;

public class HUD extends Module
{
    private static final ResourceLocation box;
    private static final ItemStack totem;
    private static HUD INSTANCE;
    private final Setting<Boolean> grayNess;
    private final Setting<Boolean> renderingUp;
    private final Setting<Boolean> waterMark;
    private final Setting<Boolean> modeVer;
    private final Setting<Boolean> arrayList;
    private final Setting<Boolean> coords;
    private final Setting<Boolean> direction;
    private final Setting<Boolean> armor;
    private final Setting<Boolean> totems;
    private final Setting<Boolean> greeter;
    private final Setting<Boolean> speed;
    private final Setting<Boolean> potions;
    private final Setting<Boolean> ping;
    private final Setting<Boolean> tps;
    private final Setting<Boolean> fps;
    private final Setting<Boolean> lag;
    private final Timer timer;
    private Map<String, Integer> players;
    public Setting<Boolean> textRadar;
    public Setting<Integer> textRadarUpdates;
    public Setting<Boolean> rainbowPrefix;
    public Setting<Integer> rainbowSpeed;
    public Setting<String> gameTitle;
    public Setting<String> command;
    public Setting<TextUtil.Color> bracketColor;
    public Setting<TextUtil.Color> commandColor;
    public Setting<String> commandBracket;
    public Setting<String> commandBracket2;
    public Setting<Boolean> notifyToggles;
    public Setting<Boolean> magenDavid;
    public Setting<Integer> animationHorizontalTime;
    public Setting<Integer> animationVerticalTime;
    public Setting<RenderingMode> renderingMode;
    public Setting<Integer> waterMarkY;
    public Setting<Boolean> time;
    public Setting<Integer> lagTime;
    public float hue;
    public Map<Integer, Integer> colorHeightMap;
    private int color;
    private boolean shouldIncrement;
    private int hitMarkerTimer;
    
    public HUD() {
        super("HUD", "HUD Elements rendered on your screen", Category.CLIENT, true, false, false);
        this.grayNess = (Setting<Boolean>)this.register(new Setting("Gray", (T)false));
        this.renderingUp = (Setting<Boolean>)this.register(new Setting("RenderingUp", (T)false, "Orientation of the HUD-Elements."));
        this.waterMark = (Setting<Boolean>)this.register(new Setting("Watermark", (T)false, "displays watermark"));
        this.modeVer = (Setting<Boolean>)this.register(new Setting("Version", (T)true, v -> this.waterMark.getValue()));
        this.arrayList = (Setting<Boolean>)this.register(new Setting("ActiveModules", (T)false, "Lists the active modules."));
        this.coords = (Setting<Boolean>)this.register(new Setting("Coords", (T)false, "Your current coordinates"));
        this.direction = (Setting<Boolean>)this.register(new Setting("Direction", (T)false, "The Direction you are facing."));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (T)false, "ArmorHUD"));
        this.totems = (Setting<Boolean>)this.register(new Setting("Totems", (T)false, "TotemHUD"));
        this.greeter = (Setting<Boolean>)this.register(new Setting("Welcomer", (T)false, "The time"));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", (T)false, "Your Speed"));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (T)false, "Your Speed"));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (T)false, "Your response time to the server."));
        this.tps = (Setting<Boolean>)this.register(new Setting("TPS", (T)false, "Ticks per second of the server."));
        this.fps = (Setting<Boolean>)this.register(new Setting("FPS", (T)false, "Your frames per second."));
        this.lag = (Setting<Boolean>)this.register(new Setting("LagNotifier", (T)false, "The time"));
        this.timer = new Timer();
        this.players = new HashMap<String, Integer>();
        this.textRadar = (Setting<Boolean>)this.register(new Setting("TextRadar", (T)Boolean.FALSE, "A TextRadar"));
        this.textRadarUpdates = (Setting<Integer>)this.register(new Setting("TRUpdates", (T)500, (T)0, (T)1000));
        this.rainbowPrefix = (Setting<Boolean>)this.register(new Setting("RainbowPrefix", (T)false));
        this.rainbowSpeed = (Setting<Integer>)this.register(new Setting("PrefixSpeed", (T)20, (T)0, (T)100, v -> this.rainbowPrefix.getValue()));
        this.gameTitle = (Setting<String>)this.register(new Setting("Title", (T)"Storm"));
        this.command = (Setting<String>)this.register(new Setting("Command", (T)"Storm"));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BracketColor", (T)TextUtil.Color.GOLD));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("NameColor", (T)TextUtil.Color.YELLOW));
        this.commandBracket = (Setting<String>)this.register(new Setting("Bracket", (T)"["));
        this.commandBracket2 = (Setting<String>)this.register(new Setting("Bracket2", (T)"]"));
        this.notifyToggles = (Setting<Boolean>)this.register(new Setting("ChatNotify", (T)false, "notifys in chat"));
        this.magenDavid = (Setting<Boolean>)this.register(new Setting("MagenDavid", (T)false, "draws magen david"));
        this.animationHorizontalTime = (Setting<Integer>)this.register(new Setting("AnimationHTime", (T)500, (T)1, (T)1000, v -> this.arrayList.getValue()));
        this.animationVerticalTime = (Setting<Integer>)this.register(new Setting("AnimationVTime", (T)50, (T)1, (T)500, v -> this.arrayList.getValue()));
        this.renderingMode = (Setting<RenderingMode>)this.register(new Setting("Ordering", (T)RenderingMode.ABC));
        this.waterMarkY = (Setting<Integer>)this.register(new Setting("WatermarkPosY", (T)2, (T)0, (T)20, v -> this.waterMark.getValue()));
        this.time = (Setting<Boolean>)this.register(new Setting("Time", (T)false, "The time"));
        this.lagTime = (Setting<Integer>)this.register(new Setting("LagTime", (T)1000, (T)0, (T)2000));
        this.colorHeightMap = new HashMap<Integer, Integer>();
        this.setInstance();
    }
    
    public static HUD getInstance() {
        if (HUD.INSTANCE == null) {
            HUD.INSTANCE = new HUD();
        }
        return HUD.INSTANCE;
    }
    
    private void setInstance() {
        HUD.INSTANCE = this;
    }
    
    @Override
    public String onUpdate() {
        if (this.shouldIncrement) {
            ++this.hitMarkerTimer;
        }
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
        if (this.timer.passedMs(getInstance().textRadarUpdates.getValue())) {
            this.players = this.getTextRadarPlayers();
            this.timer.reset();
        }
        return null;
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        final int width = this.renderer.scaledWidth;
        final int height = this.renderer.scaledHeight;
        if (this.textRadar.getValue()) {
            this.drawTextRadar(0);
        }
        this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
        if (this.waterMark.getValue()) {
            final String string = this.command.getPlannedValue() + ChatFormatting.WHITE + (this.modeVer.getValue() ? " v0.3" : "");
            if (ClickGui.getInstance().rainbow.getValue()) {
                if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                    this.renderer.drawString(string, 2.0f, this.waterMarkY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                }
                else {
                    final int[] arrayOfInt = { 1 };
                    final char[] stringToCharArray = string.toCharArray();
                    float f = 0.0f;
                    for (final char c : stringToCharArray) {
                        this.renderer.drawString(String.valueOf(c), 2.0f + f, this.waterMarkY.getValue(), ColorUtil.rainbow(arrayOfInt[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                        f += this.renderer.getStringWidth(String.valueOf(c));
                        ++arrayOfInt[0];
                    }
                }
            }
            else {
                this.renderer.drawString(string, 2.0f, this.waterMarkY.getValue(), this.color, true);
            }
        }
        final int[] counter1 = { 1 };
        int j = (HUD.mc.currentScreen instanceof GuiChat && !this.renderingUp.getValue()) ? 14 : 0;
        if (this.arrayList.getValue()) {
            if (this.renderingUp.getValue()) {
                if (this.renderingMode.getValue() == RenderingMode.ABC) {
                    for (int k = 0; k < Storm.moduleManager.sortedModulesABC.size(); ++k) {
                        final String str = Storm.moduleManager.sortedModulesABC.get(k);
                        this.renderer.drawString(str, (float)(width - 2 - this.renderer.getStringWidth(str)), (float)(2 + j * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        ++j;
                        ++counter1[0];
                    }
                }
                else {
                    for (int k = 0; k < Storm.moduleManager.sortedModules.size(); ++k) {
                        final Module module = Storm.moduleManager.sortedModules.get(k);
                        final String str2 = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                        this.renderer.drawString(str2, (float)(width - 2 - this.renderer.getStringWidth(str2)), (float)(2 + j * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        ++j;
                        ++counter1[0];
                    }
                }
            }
            else if (this.renderingMode.getValue() == RenderingMode.ABC) {
                for (int k = 0; k < Storm.moduleManager.sortedModulesABC.size(); ++k) {
                    final String str = Storm.moduleManager.sortedModulesABC.get(k);
                    j += 10;
                    this.renderer.drawString(str, (float)(width - 2 - this.renderer.getStringWidth(str)), (float)(height - j), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
            else {
                for (int k = 0; k < Storm.moduleManager.sortedModules.size(); ++k) {
                    final Module module = Storm.moduleManager.sortedModules.get(k);
                    final String str2 = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    j += 10;
                    this.renderer.drawString(str2, (float)(width - 2 - this.renderer.getStringWidth(str2)), (float)(height - j), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
        }
        final String grayString = this.grayNess.getValue() ? String.valueOf(ChatFormatting.GRAY) : "";
        int i = (HUD.mc.currentScreen instanceof GuiChat && this.renderingUp.getValue()) ? 13 : (this.renderingUp.getValue() ? -2 : 0);
        if (this.renderingUp.getValue()) {
            if (this.potions.getValue()) {
                final List<PotionEffect> effects = new ArrayList<PotionEffect>(Minecraft.getMinecraft().player.getActivePotionEffects());
                for (final PotionEffect potionEffect : effects) {
                    final String str3 = Storm.potionManager.getColoredPotionString(potionEffect);
                    i += 10;
                    if (ClickGui.getInstance().rainbow.getValue()) {
                        this.renderer.drawString(str3, (float)(width - this.renderer.getStringWidth(str3) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(str3, (float)(width - this.renderer.getStringWidth(str3) - 2), (float)(height - 2 - i), potionEffect.getPotion().getLiquidColor(), true);
                    }
                }
            }
            if (this.speed.getValue()) {
                final String str2 = grayString + "Speed " + ChatFormatting.WHITE + Storm.speedManager.getSpeedKpH() + " km/h";
                i += 10;
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            if (this.time.getValue()) {
                final String str2 = grayString + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                i += 10;
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            if (this.tps.getValue()) {
                final String str2 = grayString + "TPS " + ChatFormatting.WHITE + Storm.serverManager.getTPS();
                i += 10;
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            final String fpsText = grayString + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
            final String str4 = grayString + "Ping " + ChatFormatting.WHITE + Storm.serverManager.getPing();
            if (this.renderer.getStringWidth(str4) > this.renderer.getStringWidth(fpsText)) {
                if (this.ping.getValue()) {
                    i += 10;
                    this.renderer.drawString(str4, (float)(width - this.renderer.getStringWidth(str4) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
                if (this.fps.getValue()) {
                    i += 10;
                    this.renderer.drawString(fpsText, (float)(width - this.renderer.getStringWidth(fpsText) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
            else {
                if (this.fps.getValue()) {
                    i += 10;
                    this.renderer.drawString(fpsText, (float)(width - this.renderer.getStringWidth(fpsText) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
                if (this.ping.getValue()) {
                    i += 10;
                    this.renderer.drawString(str4, (float)(width - this.renderer.getStringWidth(str4) - 2), (float)(height - 2 - i), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
        }
        else {
            if (this.potions.getValue()) {
                final List<PotionEffect> effects = new ArrayList<PotionEffect>(Minecraft.getMinecraft().player.getActivePotionEffects());
                for (final PotionEffect potionEffect : effects) {
                    final String str3 = Storm.potionManager.getColoredPotionString(potionEffect);
                    if (ClickGui.getInstance().rainbow.getValue()) {
                        this.renderer.drawString(str3, (float)(width - this.renderer.getStringWidth(str3) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(str3, (float)(width - this.renderer.getStringWidth(str3) - 2), (float)(2 + i++ * 10), potionEffect.getPotion().getLiquidColor(), true);
                    }
                }
            }
            if (this.speed.getValue()) {
                final String str2 = grayString + "Speed " + ChatFormatting.WHITE + Storm.speedManager.getSpeedKpH() + " km/h";
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            if (this.time.getValue()) {
                final String str2 = grayString + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            if (this.tps.getValue()) {
                final String str2 = grayString + "TPS " + ChatFormatting.WHITE + Storm.serverManager.getTPS();
                this.renderer.drawString(str2, (float)(width - this.renderer.getStringWidth(str2) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++counter1[0];
            }
            final String fpsText = grayString + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
            final String str4 = grayString + "Ping " + ChatFormatting.WHITE + Storm.serverManager.getPing();
            if (this.renderer.getStringWidth(str4) > this.renderer.getStringWidth(fpsText)) {
                if (this.ping.getValue()) {
                    this.renderer.drawString(str4, (float)(width - this.renderer.getStringWidth(str4) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
                if (this.fps.getValue()) {
                    this.renderer.drawString(fpsText, (float)(width - this.renderer.getStringWidth(fpsText) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
            else {
                if (this.fps.getValue()) {
                    this.renderer.drawString(fpsText, (float)(width - this.renderer.getStringWidth(fpsText) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
                if (this.ping.getValue()) {
                    this.renderer.drawString(str4, (float)(width - this.renderer.getStringWidth(str4) - 2), (float)(2 + i++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++counter1[0];
                }
            }
        }
        final boolean inHell = HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().equals("Hell");
        final int posX = (int)HUD.mc.player.posX;
        final int posY = (int)HUD.mc.player.posY;
        final int posZ = (int)HUD.mc.player.posZ;
        final float nether = inHell ? 8.0f : 0.125f;
        final int hposX = (int)(HUD.mc.player.posX * nether);
        final int hposZ = (int)(HUD.mc.player.posZ * nether);
        i = ((HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0);
        final String coordinates = ChatFormatting.WHITE + "XYZ " + ChatFormatting.RESET + (inHell ? (posX + ", " + posY + ", " + posZ + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]" + ChatFormatting.RESET) : (posX + ", " + posY + ", " + posZ + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]"));
        final String direction = this.direction.getValue() ? Storm.rotationManager.getDirection4D(false) : "";
        final String coords = this.coords.getValue() ? coordinates : "";
        i += 10;
        if (ClickGui.getInstance().rainbow.getValue()) {
            final String rainbowCoords = this.coords.getValue() ? ("XYZ " + (inHell ? (posX + ", " + posY + ", " + posZ + " [" + hposX + ", " + hposZ + "]") : (posX + ", " + posY + ", " + posZ + " [" + hposX + ", " + hposZ + "]"))) : "";
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(direction, 2.0f, (float)(height - i - 11), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                this.renderer.drawString(rainbowCoords, 2.0f, (float)(height - i), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] counter2 = { 1 };
                final char[] stringToCharArray2 = direction.toCharArray();
                float s = 0.0f;
                for (final char c2 : stringToCharArray2) {
                    this.renderer.drawString(String.valueOf(c2), 2.0f + s, (float)(height - i - 11), ColorUtil.rainbow(counter2[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    s += this.renderer.getStringWidth(String.valueOf(c2));
                    ++counter2[0];
                }
                final int[] counter3 = { 1 };
                final char[] stringToCharArray3 = rainbowCoords.toCharArray();
                float u = 0.0f;
                for (final char c3 : stringToCharArray3) {
                    this.renderer.drawString(String.valueOf(c3), 2.0f + u, (float)(height - i), ColorUtil.rainbow(counter3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    u += this.renderer.getStringWidth(String.valueOf(c3));
                    ++counter3[0];
                }
            }
        }
        else {
            this.renderer.drawString(direction, 2.0f, (float)(height - i - 11), this.color, true);
            this.renderer.drawString(coords, 2.0f, (float)(height - i), this.color, true);
        }
        if (this.armor.getValue()) {
            this.renderArmorHUD(true);
        }
        if (this.totems.getValue()) {
            this.renderTotemHUD();
        }
        if (this.greeter.getValue()) {
            this.renderGreeter();
        }
        if (this.lag.getValue()) {
            this.renderLag();
        }
    }
    
    public Map<String, Integer> getTextRadarPlayers() {
        return EntityUtil.getTextRadarPlayers();
    }
    
    public void renderGreeter() {
        final int width = this.renderer.scaledWidth;
        String text = "";
        if (this.greeter.getValue()) {
            text = text + "Welcome To Storm " + HUD.mc.player.getDisplayNameString();
        }
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 2.0f, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] counter1 = { 1 };
                final char[] stringToCharArray = text.toCharArray();
                float i = 0.0f;
                for (final char c : stringToCharArray) {
                    this.renderer.drawString(String.valueOf(c), width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f + i, 2.0f, ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    i += this.renderer.getStringWidth(String.valueOf(c));
                    ++counter1[0];
                }
            }
        }
        else {
            this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 2.0f, this.color, true);
        }
    }
    
    public void renderLag() {
        final int width = this.renderer.scaledWidth;
        if (Storm.serverManager.isServerNotResponding()) {
            final String text = ChatFormatting.RED + "Server not responding " + MathUtil.round(Storm.serverManager.serverRespondingTime() / 1000.0f, 1) + "s.";
            this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 20.0f, this.color, true);
        }
    }
    
    @Override
    public void onTick() {
        Display.setTitle((String)this.gameTitle.getValue());
        if (!(ClickGui.mc.currentScreen instanceof StormGui)) {
            final int colorSpeed = 101 - this.rainbowSpeed.getValue();
            final float hue = System.currentTimeMillis() % (360 * colorSpeed) / (360.0f * colorSpeed);
            this.hue = hue;
            float tempHue = hue;
            for (int i = 0; i <= 510; ++i) {
                this.colorHeightMap.put(i, Color.HSBtoRGB(tempHue, ClickGui.getInstance().rainbowSaturation.getValue().intValue() / 255.0f, ClickGui.getInstance().rainbowBrightness.getValue().intValue() / 255.0f));
                tempHue += 0.0013071896f;
            }
        }
    }
    
    public void renderTotemHUD() {
        final int width = this.renderer.scaledWidth;
        final int height = this.renderer.scaledHeight;
        int totems = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            totems += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (totems > 0) {
            GlStateManager.enableTexture2D();
            final int i = width / 2;
            final int iteration = 0;
            final int y = height - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
            final int x = i - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.totem, x, y);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.totem, x, y, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(totems + "", (float)(x + 19 - 2 - this.renderer.getStringWidth(totems + "")), (float)(y + 9), 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderArmorHUD(final boolean percent) {
        final int width = this.renderer.scaledWidth;
        final int height = this.renderer.scaledHeight;
        GlStateManager.enableTexture2D();
        final int i = width / 2;
        int iteration = 0;
        final int y = height - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
        for (final ItemStack is : HUD.mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) {
                continue;
            }
            final int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(is, x, y);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, is, x, y, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (is.getCount() > 1) ? (is.getCount() + "") : "";
            this.renderer.drawStringWithShadow(s, (float)(x + 19 - 2 - this.renderer.getStringWidth(s)), (float)(y + 9), 16777215);
            if (!percent) {
                continue;
            }
            int dmg = 0;
            final int itemDurability = is.getMaxDamage() - is.getItemDamage();
            final float green = (is.getMaxDamage() - (float)is.getItemDamage()) / is.getMaxDamage();
            final float red = 1.0f - green;
            if (percent) {
                dmg = 100 - (int)(red * 100.0f);
            }
            else {
                dmg = itemDurability;
            }
            this.renderer.drawStringWithShadow(dmg + "", (float)(x + 8 - this.renderer.getStringWidth(dmg + "") / 2), (float)(y - 11), ColorUtil.toRGBA((int)(red * 255.0f), (int)(green * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final AttackEntityEvent event) {
        this.shouldIncrement = true;
    }
    
    @Override
    public void onLoad() {
        Storm.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    public void drawTextRadar(final int yOffset) {
        if (!this.players.isEmpty()) {
            int y = this.renderer.getFontHeight() + 7 + yOffset;
            for (final Map.Entry<String, Integer> player : this.players.entrySet()) {
                final String text = player.getKey() + " ";
                final int textheight = this.renderer.getFontHeight() + 1;
                this.renderer.drawString(text, 2.0f, (float)y, ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                y += textheight;
            }
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && this.equals(event.getSetting().getFeature())) {
            Storm.commandManager.setClientMessage(this.getCommandMessage());
        }
    }
    
    public String getCommandMessage() {
        if (this.rainbowPrefix.getPlannedValue()) {
            final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
            stringBuilder.insert(0, "§+");
            stringBuilder.append("§r");
            return stringBuilder.toString();
        }
        return TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue()) + TextUtil.coloredString(this.command.getPlannedValue(), this.commandColor.getPlannedValue()) + TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue());
    }
    
    public String getRainbowCommandMessage() {
        final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
        stringBuilder.insert(0, "§+");
        stringBuilder.append("§r");
        return stringBuilder.toString();
    }
    
    public String getRawCommandMessage() {
        return this.commandBracket.getValue() + this.command.getValue() + this.commandBracket2.getValue();
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
        totem = new ItemStack(Items.TOTEM_OF_UNDYING);
        HUD.INSTANCE = new HUD();
    }
    
    public enum RenderingMode
    {
        Length, 
        ABC;
    }
}
