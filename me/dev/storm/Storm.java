//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm;

import net.minecraftforge.fml.common.*;
import me.dev.storm.manager.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.event.*;
import org.lwjgl.opengl.*;
import org.apache.logging.log4j.*;

@Mod(modid = "storm", name = "storm", version = "0.3")
public class Storm
{
    public static final String MODID = "storm";
    public static final String MODNAME = "storm";
    public static final String MODVER = "0.3";
    public static final Logger LOGGER;
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    @Mod.Instance
    public static Storm INSTANCE;
    private static boolean unloaded;
    public static Minecraft mc;
    
    public static String getVersion() {
        return "0.3";
    }
    
    public static void load() {
        Storm.LOGGER.info("\n\nLoading storm by teej, bikky and blutest");
        Storm.unloaded = false;
        if (Storm.reloadManager != null) {
            Storm.reloadManager.unload();
            Storm.reloadManager = null;
        }
        Storm.textManager = new TextManager();
        Storm.commandManager = new CommandManager();
        Storm.friendManager = new FriendManager();
        Storm.moduleManager = new ModuleManager();
        Storm.rotationManager = new RotationManager();
        Storm.packetManager = new PacketManager();
        Storm.eventManager = new EventManager();
        Storm.speedManager = new SpeedManager();
        Storm.potionManager = new PotionManager();
        Storm.inventoryManager = new InventoryManager();
        Storm.serverManager = new ServerManager();
        Storm.fileManager = new FileManager();
        Storm.colorManager = new ColorManager();
        Storm.positionManager = new PositionManager();
        Storm.configManager = new ConfigManager();
        Storm.holeManager = new HoleManager();
        Storm.LOGGER.info("Managers loaded.");
        Storm.moduleManager.init();
        Storm.LOGGER.info("Modules loaded.");
        Storm.configManager.init();
        Storm.eventManager.init();
        Storm.LOGGER.info("EventManager loaded.");
        Storm.textManager.init(true);
        Storm.moduleManager.onLoad();
        Storm.LOGGER.info("storm successfully loaded!\n");
    }
    
    public static void unload(final boolean unload) {
        Storm.LOGGER.info("\n\nUnloading storm by teej, bikky and blutest");
        if (unload) {
            (Storm.reloadManager = new ReloadManager()).init((Storm.commandManager != null) ? Storm.commandManager.getPrefix() : "-");
        }
        onUnload();
        Storm.eventManager = null;
        Storm.friendManager = null;
        Storm.speedManager = null;
        Storm.holeManager = null;
        Storm.positionManager = null;
        Storm.rotationManager = null;
        Storm.configManager = null;
        Storm.commandManager = null;
        Storm.colorManager = null;
        Storm.serverManager = null;
        Storm.fileManager = null;
        Storm.potionManager = null;
        Storm.inventoryManager = null;
        Storm.moduleManager = null;
        Storm.textManager = null;
        Storm.LOGGER.info("storm unloaded!\n");
    }
    
    public static void reload() {
        unload(false);
        load();
    }
    
    public static void onUnload() {
        if (!Storm.unloaded) {
            Storm.eventManager.onUnload();
            Storm.moduleManager.onUnload();
            Storm.configManager.saveConfig(Storm.configManager.config.replaceFirst("storm/", ""));
            Storm.moduleManager.onUnloadPost();
            Storm.unloaded = true;
        }
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Storm.LOGGER.info("BUG HACK");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        Display.setTitle("Minecraft 1.12.2");
        load();
    }
    
    static {
        LOGGER = LogManager.getLogger("storm");
        Storm.unloaded = false;
    }
}
