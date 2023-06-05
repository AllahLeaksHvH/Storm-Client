//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import me.dev.storm.features.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import java.awt.*;
import me.dev.storm.features.command.*;
import java.util.*;
import org.lwjgl.input.*;
import me.dev.storm.util.*;
import org.lwjgl.opengl.*;
import me.dev.storm.manager.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import java.awt.datatransfer.*;
import java.io.*;

public class PickerButton extends Button
{
    Setting setting;
    private Color finalColor;
    boolean pickingColor;
    boolean pickingHue;
    boolean pickingAlpha;
    public static Tessellator tessellator;
    public static BufferBuilder builder;
    private float hueX;
    private float prevHueX;
    private float alphaX;
    private float prevAlphaX;
    
    public PickerButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.finalColor = setting.getValue();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW;
        final boolean future = ClickGui.getInstance().style.getValue() == ClickGui.Style.FUTURE;
        final boolean dotgod = ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, (future || dotgod) ? ((this.setting.hasBoolean && this.setting.booleanValue) ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(90) : Storm.colorManager.getCurrentWithAlpha(65)) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(55) : Storm.colorManager.getCurrentWithAlpha(26))) : (this.setting.hasBoolean ? (this.setting.booleanValue ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(200) : Storm.colorManager.getCurrentWithAlpha(120)) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077)) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077)));
        try {
            RenderUtil.drawRect(this.x - 1.5f + this.width + 0.6f - 0.5f, this.y + 5.0f, this.x + this.width + 7.0f - 2.5f, this.y + this.height - 4.0f, this.finalColor.getRGB());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Storm.textManager.drawStringWithShadow((newStyle || dotgod) ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), (dotgod && this.setting.hasBoolean && this.setting.booleanValue) ? Storm.colorManager.getCurrentGui(240) : (dotgod ? 11579568 : -1));
        if (this.setting.open) {
            this.drawPicker(this.setting, (int)this.x, (int)this.y + 15, (int)this.x, this.setting.hideAlpha ? ((int)this.y + 100) : ((int)this.y + 103), (int)this.x, (int)this.y + 95, mouseX, mouseY);
            Storm.textManager.drawStringWithShadow("copy", this.x + 2.3f, this.y + 113.0f, this.isInsideCopy(mouseX, mouseY) ? -1 : -5592406);
            Storm.textManager.drawStringWithShadow("paste", this.x + this.width - 2.3f - Storm.textManager.getStringWidth("paste") + 11.7f - 4.6f, this.y + 113.0f, this.isInsidePaste(mouseX, mouseY) ? -1 : -5592406);
            this.setting.setValue(this.finalColor);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            final Setting setting = this.setting;
            boolean open = false;
            if (this.setting.open) {
                open = false;
            }
            setting.open = open;
        }
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            this.setting.booleanValue = !this.setting.booleanValue;
        }
        if (mouseButton == 0 && this.isInsideCopy(mouseX, mouseY) && this.setting.open) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            final String hex = String.format("#%02x%02x%02x%02x", this.finalColor.getAlpha(), this.finalColor.getRed(), this.finalColor.getGreen(), this.finalColor.getBlue());
            final StringSelection selection = new StringSelection(hex);
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            Command.sendMessage("Copied the color to your clipboard.");
        }
        if (mouseButton == 0 && this.isInsidePaste(mouseX, mouseY) && this.setting.open) {
            try {
                if (readClipboard() != null) {
                    if (Objects.requireNonNull(readClipboard()).startsWith("#")) {
                        final String hex = Objects.requireNonNull(readClipboard());
                        final int a = Integer.valueOf(hex.substring(1, 3), 16);
                        final int r = Integer.valueOf(hex.substring(3, 5), 16);
                        final int g = Integer.valueOf(hex.substring(5, 7), 16);
                        final int b = Integer.valueOf(hex.substring(7, 9), 16);
                        if (this.setting.hideAlpha) {
                            this.setting.setValue(new Color(r, g, b));
                        }
                        else {
                            this.setting.setValue(new Color(r, g, b, a));
                        }
                    }
                    else {
                        final String[] color = readClipboard().split(",");
                        this.setting.setValue(new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2])));
                    }
                }
            }
            catch (NumberFormatException e) {
                Command.sendMessage("Bad color format! Use Hex (#FFFFFFFF)");
            }
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int releaseButton) {
        this.pickingAlpha = false;
        this.pickingHue = false;
        this.pickingColor = false;
    }
    
    public boolean isInsideCopy(final int mouseX, final int mouseY) {
        return mouseOver((int)((int)this.x + 2.3f), (int)this.y + 113, (int)((int)this.x + 2.3f) + Storm.textManager.getStringWidth("copy"), (int)(this.y + 112.0f) + Storm.textManager.getFontHeight(), mouseX, mouseY);
    }
    
    public boolean isInsidePaste(final int mouseX, final int mouseY) {
        return mouseOver((int)(this.x + this.width - 2.3f - Storm.textManager.getStringWidth("paste") + 11.7f - 4.6f), (int)this.y + 113, (int)(this.x + this.width - 2.3f - Storm.textManager.getStringWidth("paste") + 11.7f - 4.6f) + Storm.textManager.getStringWidth("paste"), (int)(this.y + 112.0f) + Storm.textManager.getFontHeight(), mouseX, mouseY);
    }
    
    public void drawPicker(final Setting setting, final int pickerX, final int pickerY, final int hueSliderX, final int hueSliderY, final int alphaSliderX, final int alphaSliderY, final int mouseX, final int mouseY) {
        float[] color = { 0.0f, 0.0f, 0.0f, 0.0f };
        try {
            color = new float[] { Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[0], Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[1], Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[2], setting.getValue().getAlpha() / 255.0f };
        }
        catch (Exception exception) {
            Storm.LOGGER.info("storm color picker says it's a bad color!");
        }
        final int pickerWidth = (int)(this.width + 7.4f);
        final int pickerHeight = 78;
        final int hueSliderWidth = pickerWidth + 3;
        final int hueSliderHeight = 7;
        final int alphaSliderHeight = 7;
        if (this.pickingColor && (!Mouse.isButtonDown(0) || !mouseOver(pickerX, pickerY, pickerX + pickerWidth, pickerY + pickerHeight, mouseX, mouseY))) {
            this.pickingColor = false;
        }
        if (this.pickingHue && (!Mouse.isButtonDown(0) || !mouseOver(hueSliderX, hueSliderY, hueSliderX + hueSliderWidth, hueSliderY + hueSliderHeight, mouseX, mouseY))) {
            this.pickingHue = false;
        }
        if (this.pickingAlpha && (!Mouse.isButtonDown(0) || !mouseOver(alphaSliderX, alphaSliderY, alphaSliderX + pickerWidth, alphaSliderY + alphaSliderHeight, mouseX, mouseY))) {
            this.pickingAlpha = false;
        }
        if (Mouse.isButtonDown(0) && mouseOver(pickerX, pickerY, pickerX + pickerWidth, pickerY + pickerHeight, mouseX, mouseY)) {
            this.pickingColor = true;
        }
        if (Mouse.isButtonDown(0) && mouseOver(hueSliderX, hueSliderY, hueSliderX + hueSliderWidth, hueSliderY + hueSliderHeight, mouseX, mouseY)) {
            this.pickingHue = true;
        }
        if (Mouse.isButtonDown(0) && mouseOver(alphaSliderX, alphaSliderY, alphaSliderX + pickerWidth, alphaSliderY + alphaSliderHeight, mouseX, mouseY)) {
            this.pickingAlpha = true;
        }
        if (this.pickingHue) {
            final float restrictedX = (float)Math.min(Math.max(hueSliderX, mouseX), hueSliderX + hueSliderWidth);
            color[0] = (restrictedX - hueSliderX) / hueSliderWidth;
        }
        if (this.pickingAlpha && !setting.hideAlpha) {
            final float restrictedX = (float)Math.min(Math.max(alphaSliderX, mouseX), alphaSliderX + pickerWidth);
            color[3] = 1.0f - (restrictedX - alphaSliderX) / pickerWidth;
        }
        if (this.pickingColor) {
            final float restrictedX = (float)Math.min(Math.max(pickerX, mouseX), pickerX + pickerWidth);
            final float restrictedY = (float)Math.min(Math.max(pickerY, mouseY), pickerY + pickerHeight);
            color[1] = (restrictedX - pickerX) / pickerWidth;
            color[2] = 1.0f - (restrictedY - pickerY) / pickerHeight;
        }
        final int selectedColor = Color.HSBtoRGB(color[0], 1.0f, 1.0f);
        final float selectedRed = (selectedColor >> 16 & 0xFF) / 255.0f;
        final float selectedGreen = (selectedColor >> 8 & 0xFF) / 255.0f;
        final float selectedBlue = (selectedColor & 0xFF) / 255.0f;
        drawPickerBase(pickerX, pickerY, pickerWidth, pickerHeight, selectedRed, selectedGreen, selectedBlue, color[3]);
        this.drawHueSlider(hueSliderX, hueSliderY, pickerWidth + 1, hueSliderHeight, color[0]);
        final int cursorX = (int)(pickerX + color[1] * pickerWidth);
        final int cursorY = (int)(pickerY + pickerHeight - color[2] * pickerHeight);
        if (this.pickingColor) {
            RenderUtill.drawCircle((float)cursorX, (float)cursorY, 6.4f, Color.BLACK.getRGB());
            RenderUtill.drawCircle((float)cursorX, (float)cursorY, 6.0f, ColorUtil.toARGB(this.finalColor.getRed(), this.finalColor.getGreen(), this.finalColor.getBlue(), 255));
        }
        else {
            RenderUtill.drawCircle((float)cursorX, (float)cursorY, 3.4f, Color.BLACK.getRGB());
            RenderUtill.drawCircle((float)cursorX, (float)cursorY, 3.0f, -1);
        }
        if (!setting.hideAlpha) {
            this.drawAlphaSlider(alphaSliderX, alphaSliderY, pickerWidth - 1, alphaSliderHeight, selectedRed, selectedGreen, selectedBlue, color[3]);
        }
        this.finalColor = getColor(new Color(Color.HSBtoRGB(color[0], color[1], color[2])), color[3]);
    }
    
    public static boolean mouseOver(final int minX, final int minY, final int maxX, final int maxY, final int mX, final int mY) {
        return mX >= minX && mY >= minY && mX <= maxX && mY <= maxY;
    }
    
    public static Color getColor(final Color color, final float alpha) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        return new Color(red, green, blue, alpha);
    }
    
    public static void drawPickerBase(final int pickerX, final int pickerY, final int pickerWidth, final int pickerHeight, final float red, final float green, final float blue, final float alpha) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(9);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((float)pickerX, (float)pickerY);
        GL11.glVertex2f((float)pickerX, (float)(pickerY + pickerHeight));
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)(pickerY + pickerHeight));
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)pickerY);
        GL11.glEnd();
        GL11.glDisable(3008);
        GL11.glBegin(9);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)pickerX, (float)pickerY);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glVertex2f((float)pickerX, (float)(pickerY + pickerHeight));
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)(pickerY + pickerHeight));
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)pickerY);
        GL11.glEnd();
        GL11.glEnable(3008);
        GL11.glShadeModel(7424);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public void drawHueSlider(final int x, int y, final int width, final int height, final float hue) {
        int step = 0;
        if (height > width) {
            RenderUtil.drawRect((float)x, (float)y, (float)(x + width), (float)(y + 4), -65536);
            y += 4;
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                drawGradientRect((float)x, y + step * (height / 6.0f), (float)(x + width), y + (step + 1) * (height / 6.0f), previousStep, nextStep, false);
                ++step;
            }
            final int sliderMinY = (int)(y + height * hue) - 4;
            RenderUtil.drawRect((float)x, (float)(sliderMinY - 1), (float)(x + width), (float)(sliderMinY + 1), -1);
            drawOutlineRect(x, sliderMinY - 1, x + width, sliderMinY + 1, Color.BLACK, 1.0f);
        }
        else {
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                gradient(x + step * (width / 6), y, x + (step + 1) * (width / 6) + 3, y + height, previousStep, nextStep, true);
                ++step;
            }
            final int sliderMinX = (int)(x + width * hue);
            RenderUtil.drawRect((float)(sliderMinX - 1), y - 1.2f, (float)(sliderMinX + 1), y + height + 1.2f, -1);
            drawOutlineRect(sliderMinX - 1.2, y - 1.2, sliderMinX + 1.2, y + height + 1.2, Color.BLACK, 0.1f);
        }
    }
    
    public void setHueX(final float x) {
        if (this.hueX == x) {
            return;
        }
        this.prevHueX = this.hueX;
        this.hueX = x;
    }
    
    public float getHueX() {
        if (FpsManager.getFPS() < 20) {
            return this.hueX;
        }
        return this.hueX = this.prevHueX + (this.hueX - this.prevHueX) * PickerButton.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, FpsManager.getFPS()) / 240.0f));
    }
    
    public void drawAlphaSlider(final int x, final int y, final int width, final int height, final float red, final float green, final float blue, final float alpha) {
        boolean left = true;
        for (int checkerBoardSquareSize = height / 2, squareIndex = -checkerBoardSquareSize; squareIndex < width; squareIndex += checkerBoardSquareSize) {
            if (!left) {
                RenderUtil.drawRect((float)(x + squareIndex), (float)y, (float)(x + squareIndex + checkerBoardSquareSize), (float)(y + height), -1);
                RenderUtil.drawRect((float)(x + squareIndex), (float)(y + checkerBoardSquareSize), (float)(x + squareIndex + checkerBoardSquareSize), (float)(y + height), -7303024);
                if (squareIndex < width - checkerBoardSquareSize) {
                    final int minX = x + squareIndex + checkerBoardSquareSize;
                    final int maxX = Math.min(x + width, x + squareIndex + checkerBoardSquareSize * 2);
                    RenderUtil.drawRect((float)minX, (float)y, (float)maxX, (float)(y + height), -7303024);
                    RenderUtil.drawRect((float)minX, (float)(y + checkerBoardSquareSize), (float)maxX, (float)(y + height), -1);
                }
            }
            left = !left;
        }
        drawLeftGradientRect(x, y, x + width, y + height, new Color(red, green, blue, 1.0f).getRGB(), 0);
        final int sliderMinX = (int)(x + width - width * alpha);
        RenderUtil.drawRect((float)(sliderMinX - 1), y - 1.2f, (float)(sliderMinX + 1), y + height + 1.2f, -1);
        drawOutlineRect(sliderMinX - 1.2, y - 1.2, sliderMinX + 1.2, y + height + 1.2, Color.BLACK, 0.1f);
    }
    
    public void setAlphaX(final float x) {
        if (this.alphaX == x) {
            return;
        }
        this.prevAlphaX = this.alphaX;
        this.alphaX = x;
    }
    
    public float getAlphaX() {
        if (FpsManager.getFPS() < 20) {
            return this.alphaX;
        }
        return this.alphaX = this.prevAlphaX + (this.alphaX - this.prevAlphaX) * PickerButton.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, FpsManager.getFPS()) / 240.0f));
    }
    
    public static void drawGradientRect(final double leftpos, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(leftpos, top);
        GL11.glVertex2d(leftpos, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void drawLeftGradientRect(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        PickerButton.builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        PickerButton.builder.pos((double)right, (double)top, 0.0).color((endColor >> 24 & 0xFF) / 255.0f, (endColor >> 16 & 0xFF) / 255.0f, (endColor >> 8 & 0xFF) / 255.0f, (endColor >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)left, (double)top, 0.0).color((startColor >> 16 & 0xFF) / 255.0f, (startColor >> 8 & 0xFF) / 255.0f, (startColor & 0xFF) / 255.0f, (startColor >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)left, (double)bottom, 0.0).color((startColor >> 16 & 0xFF) / 255.0f, (startColor >> 8 & 0xFF) / 255.0f, (startColor & 0xFF) / 255.0f, (startColor >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)right, (double)bottom, 0.0).color((endColor >> 24 & 0xFF) / 255.0f, (endColor >> 16 & 0xFF) / 255.0f, (endColor >> 8 & 0xFF) / 255.0f, (endColor >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void gradient(final int minX, final int minY, final int maxX, final int maxY, final int startColor, final int endColor, final boolean left) {
        if (left) {
            final float startA = (startColor >> 24 & 0xFF) / 255.0f;
            final float startR = (startColor >> 16 & 0xFF) / 255.0f;
            final float startG = (startColor >> 8 & 0xFF) / 255.0f;
            final float startB = (startColor & 0xFF) / 255.0f;
            final float endA = (endColor >> 24 & 0xFF) / 255.0f;
            final float endR = (endColor >> 16 & 0xFF) / 255.0f;
            final float endG = (endColor >> 8 & 0xFF) / 255.0f;
            final float endB = (endColor & 0xFF) / 255.0f;
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(9);
            GL11.glColor4f(startR, startG, startB, startA);
            GL11.glVertex2f((float)minX, (float)minY);
            GL11.glVertex2f((float)minX, (float)maxY);
            GL11.glColor4f(endR, endG, endB, endA);
            GL11.glVertex2f((float)maxX, (float)maxY);
            GL11.glVertex2f((float)maxX, (float)minY);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
        }
        else {
            drawGradientRect(minX, minY, maxX, maxY, startColor, endColor);
        }
    }
    
    public static int gradientColor(final int color, final int percentage) {
        final int r = ((color & 0xFF0000) >> 16) * (100 + percentage) / 100;
        final int g = ((color & 0xFF00) >> 8) * (100 + percentage) / 100;
        final int b = (color & 0xFF) * (100 + percentage) / 100;
        return new Color(r, g, b).hashCode();
    }
    
    public static void drawGradientRect(final float left, final float top, final float right, final float bottom, int startColor, int endColor, final boolean hovered) {
        if (hovered) {
            startColor = gradientColor(startColor, -20);
            endColor = gradientColor(endColor, -20);
        }
        final float c = (startColor >> 24 & 0xFF) / 255.0f;
        final float c2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float c3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float c4 = (startColor & 0xFF) / 255.0f;
        final float c5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float c6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float c7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float c8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, 0.0).color(c2, c3, c4, c).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0).color(c2, c3, c4, c).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, 0.0).color(c6, c7, c8, c5).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0).color(c6, c7, c8, c5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static String readClipboard() {
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        }
        catch (UnsupportedFlavorException | IOException ex2) {
            final Exception ex;
            final Exception exception = ex;
            return null;
        }
    }
    
    public static void drawOutlineRect(double left, double top, double right, double bottom, final Color color, final float lineWidth) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float f4 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float f5 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float f6 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth(lineWidth);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f4, f5, f6, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, 0.0).endVertex();
        bufferbuilder.pos(right, bottom, 0.0).endVertex();
        bufferbuilder.pos(right, top, 0.0).endVertex();
        bufferbuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode(1032, 6914);
    }
    
    static {
        PickerButton.tessellator = Tessellator.getInstance();
        PickerButton.builder = PickerButton.tessellator.getBuffer();
    }
}
