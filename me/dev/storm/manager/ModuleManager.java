//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import me.dev.storm.features.*;
import me.dev.storm.features.modules.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.features.modules.player.*;
import me.dev.storm.features.modules.movement.*;
import me.dev.storm.features.modules.render.*;
import me.dev.storm.features.modules.combat.*;
import me.dev.storm.features.modules.misc.*;
import net.minecraftforge.common.*;
import java.util.function.*;
import me.dev.storm.event.events.*;
import java.util.stream.*;
import java.util.*;
import org.lwjgl.input.*;
import me.dev.storm.features.gui.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.util.*;
import me.dev.storm.*;
import java.util.concurrent.*;

public class ModuleManager extends Feature
{
    public ArrayList<Module> modules;
    public List<Module> sortedModules;
    public List<String> sortedModulesABC;
    public Animation animationThread;
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        this.sortedModules = new ArrayList<Module>();
        this.sortedModulesABC = new ArrayList<String>();
    }
    
    public void init() {
        this.modules.add((Module)new ClickGui());
        this.modules.add((Module)new PopChams());
        this.modules.add((Module)new Watermark());
        this.modules.add((Module)new ModifyCrystal());
        this.modules.add((Module)new SwingAnimation());
        this.modules.add((Module)new RPC());
        this.modules.add((Module)new Chams());
        this.modules.add((Module)new StrengthDetect());
        this.modules.add((Module)new Quiver());
        this.modules.add((Module)new Trails());
        this.modules.add((Module)new UnfocusedCPU());
        this.modules.add((Module)new TextRadar());
        this.modules.add((Module)new ChorusPredict());
        this.modules.add((Module)new Anchor());
        this.modules.add((Module)new KillEffects());
        this.modules.add((Module)new GodModule());
        this.modules.add((Module)new FontMod());
        this.modules.add((Module)new Surround());
        this.modules.add((Module)new BurrowESP());
        this.modules.add((Module)new PayloadSpoof());
        this.modules.add((Module)new DurabilityAlert());
        this.modules.add((Module)new SilentXP());
        this.modules.add((Module)new Unicode());
        this.modules.add((Module)new HUD());
        this.modules.add((Module)new BlockHighlight());
        this.modules.add((Module)new HoleESP());
        this.modules.add((Module)new Replenish());
        this.modules.add((Module)new SmallShield());
        this.modules.add((Module)new HandChams());
        this.modules.add((Module)new Trajectories());
        this.modules.add((Module)new FakePlayer());
        this.modules.add((Module)new MCP());
        this.modules.add((Module)new LiquidInteract());
        this.modules.add((Module)new Speedmine());
        this.modules.add((Module)new NoVoid());
        this.modules.add((Module)new ChatModifier());
        this.modules.add((Module)new MCF());
        this.modules.add((Module)new PearlNotify());
        this.modules.add((Module)new AutoGG());
        this.modules.add((Module)new ToolTips());
        this.modules.add((Module)new PopCounter());
        this.modules.add((Module)new Offhand());
        this.modules.add((Module)new AutoTrap());
        this.modules.add((Module)new AutoWeb());
        this.modules.add((Module)new ArrayListNew());
        this.modules.add((Module)new AutoCrystal());
        this.modules.add((Module)new CrystalChams());
        this.modules.add((Module)new Title());
        this.modules.add((Module)new Aura());
        this.modules.add((Module)new Criticals());
        this.modules.add((Module)new HoleFiller());
        this.modules.add((Module)new NameTags());
        this.modules.add((Module)new AutoArmor());
        this.modules.add((Module)new ESP());
        this.modules.add((Module)new Selftrap());
        this.modules.add((Module)new SelfFill());
        this.modules.add((Module)new WhisperSpam());
        this.modules.add((Module)new Announcer());
        this.modules.add((Module)new Clip());
        this.modules.add((Module)new ViewModel());
        this.modules.add((Module)new PearlBait());
        this.modules.add((Module)new ItemPhysics());
        this.modules.add((Module)new CityPhase());
        this.modules.add((Module)new TickShift());
        this.modules.add((Module)new ExplosionChams());
        this.modules.add((Module)new PTargetHud());
        this.modules.add((Module)new CevBreaker());
        this.modules.add((Module)new DamageNumbers());
        this.modules.add((Module)new JumpCircle());
        this.modules.add((Module)new AutoMine());
        this.modules.add((Module)new AntiBurrow());
        this.modules.add((Module)new Speed());
        this.modules.add((Module)new InstantSpeed());
        this.modules.add((Module)new Wireframe());
        this.modules.add((Module)new BowBomb());
        this.modules.add((Module)new SwingHand());
    }
    
    public Module getModuleByName(final String name) {
        for (final Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {
        for (final Module module : this.modules) {
            if (!clazz.isInstance(module)) {
                continue;
            }
            return (T)module;
        }
        return null;
    }
    
    public void enableModule(final Class<Module> clazz) {
        final Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.enable();
        }
    }
    
    public void disableModule(final Class<Module> clazz) {
        final Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.disable();
        }
    }
    
    public void enableModule(final String name) {
        final Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }
    
    public void disableModule(final String name) {
        final Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }
    
    public boolean isModuleEnabled(final String name) {
        final Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }
    
    public boolean isModuleEnabled(final Class<Module> clazz) {
        final Module module = this.getModuleByClass(clazz);
        return module != null && module.isOn();
    }
    
    public Module getModuleByDisplayName(final String displayName) {
        for (final Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public ArrayList<Module> getEnabledModules() {
        final ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (final Module module : this.modules) {
            if (!module.isEnabled()) {
                continue;
            }
            enabledModules.add(module);
        }
        return enabledModules;
    }
    
    public ArrayList<String> getEnabledModulesName() {
        final ArrayList<String> enabledModules = new ArrayList<String>();
        for (final Module module : this.modules) {
            if (module.isEnabled()) {
                if (!module.isDrawn()) {
                    continue;
                }
                enabledModules.add(module.getFullArrayString());
            }
        }
        return enabledModules;
    }
    
    public ArrayList<Module> getModulesByCategory(final Module.Category category) {
        final ArrayList<Module> modulesCategory = new ArrayList<Module>();
        final ArrayList<Module> list;
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                list.add(module);
            }
            return;
        });
        return modulesCategory;
    }
    
    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }
    
    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
        this.modules.forEach(Module::onLoad);
    }
    
    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }
    
    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }
    
    public void onRender2D(final Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }
    
    public void onRender3D(final Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }
    
    public void sortModules(final boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
    }
    
    public void sortModulesABC() {
        (this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName())).sort(String.CASE_INSENSITIVE_ORDER);
    }
    
    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }
    
    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }
    
    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }
    
    public void onUnloadPost() {
        for (final Module module : this.modules) {
            module.enabled.setValue((Object)false);
        }
    }
    
    public void onKeyPressed(final int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof StormGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }
    
    private class Animation extends Thread
    {
        public Module module;
        public float offset;
        public float vOffset;
        ScheduledExecutorService service;
        
        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }
        
        @Override
        public void run() {
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                for (final Module module : ModuleManager.this.sortedModules) {
                    final String text = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    module.offset = ModuleManager.this.renderer.getStringWidth(text) / (float)HUD.getInstance().animationHorizontalTime.getValue();
                    module.vOffset = ModuleManager.this.renderer.getFontHeight() / (float)HUD.getInstance().animationVerticalTime.getValue();
                    if (module.isEnabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (module.arrayListOffset <= module.offset) {
                            continue;
                        }
                        if (Util.mc.world == null) {
                            continue;
                        }
                        final Module module3 = module;
                        module3.arrayListOffset -= module.offset;
                        module.sliding = true;
                    }
                    else {
                        if (!module.isDisabled()) {
                            continue;
                        }
                        if ((int)HUD.getInstance().animationHorizontalTime.getValue() == 1) {
                            continue;
                        }
                        if (module.arrayListOffset < ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                            final Module module4 = module;
                            module4.arrayListOffset += module.offset;
                            module.sliding = true;
                        }
                        else {
                            module.sliding = false;
                        }
                    }
                }
            }
            else {
                for (final String e : ModuleManager.this.sortedModulesABC) {
                    final Module module2 = Storm.moduleManager.getModuleByName(e);
                    final String text2 = module2.getDisplayName() + ChatFormatting.GRAY + ((module2.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module2.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    module2.offset = ModuleManager.this.renderer.getStringWidth(text2) / (float)HUD.getInstance().animationHorizontalTime.getValue();
                    module2.vOffset = ModuleManager.this.renderer.getFontHeight() / (float)HUD.getInstance().animationVerticalTime.getValue();
                    if (module2.isEnabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (module2.arrayListOffset <= module2.offset) {
                            continue;
                        }
                        if (Util.mc.world == null) {
                            continue;
                        }
                        final Module module5 = module2;
                        module5.arrayListOffset -= module2.offset;
                        module2.sliding = true;
                    }
                    else {
                        if (!module2.isDisabled()) {
                            continue;
                        }
                        if ((int)HUD.getInstance().animationHorizontalTime.getValue() == 1) {
                            continue;
                        }
                        if (module2.arrayListOffset < ModuleManager.this.renderer.getStringWidth(text2) && Util.mc.world != null) {
                            final Module module6 = module2;
                            module6.arrayListOffset += module2.offset;
                            module2.sliding = true;
                        }
                        else {
                            module2.sliding = false;
                        }
                    }
                }
            }
        }
        
        @Override
        public void start() {
            System.out.println("Starting animation thread.");
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}
