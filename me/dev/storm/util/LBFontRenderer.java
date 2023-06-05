//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.texture.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import java.util.*;
import net.minecraft.util.*;

public class LBFontRenderer extends FontRenderer implements Util
{
    private final ImageAWT defaultFont;
    
    public LBFontRenderer(final Font font) {
        super(LBFontRenderer.mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), (TextureManager)null, false);
        this.defaultFont = new ImageAWT(font);
        this.FONT_HEIGHT = this.getHeight();
    }
    
    public int getHeight() {
        return this.defaultFont.getHeight() / 2;
    }
    
    public int getSize() {
        return this.defaultFont.getFont().getSize();
    }
    
    public int drawStringWithShadow(final String text, final float x, final float y, final int color) {
        return this.drawString(text, x, y, color, true);
    }
    
    public int drawString(final String text, final float x, final float y, final int color, final boolean dropShadow) {
        final float currY = y - 3.0f;
        if (text.contains("\n")) {
            final String[] parts = text.split("\n");
            float newY = 0.0f;
            for (final String s : parts) {
                this.drawText(s, x, currY + newY, color, dropShadow);
                newY += this.getHeight();
            }
            return 0;
        }
        if (dropShadow) {
            this.drawText(text, x + 1.0f, currY + 1.0f, new Color(0, 0, 0, 150).getRGB(), true);
        }
        return this.drawText(text, x, currY, color, false);
    }
    
    private int drawText(final String text, final float x, final float y, final int color, final boolean ignoreColor) {
        if (text == null) {
            return 0;
        }
        if (text.isEmpty()) {
            return (int)x;
        }
        GlStateManager.translate(x - 1.5, y + 0.5, 0.0);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableTexture2D();
        int currentColor = color;
        if ((currentColor & 0xFC000000) == 0x0) {
            currentColor |= 0xFF000000;
        }
        final int alpha = currentColor >> 24 & 0xFF;
        if (text.contains("\u00c2\u00c2§")) {
            final String[] parts = text.split("\u00c2\u00c2§");
            ImageAWT currentFont = this.defaultFont;
            double width = 0.0;
            boolean randomCase = false;
            for (int index = 0; index < parts.length; ++index) {
                final String part = parts[index];
                if (!part.isEmpty()) {
                    if (index == 0) {
                        currentFont.drawString(part, width, 0.0, currentColor);
                        width += currentFont.getStringWidth(part);
                    }
                    else {
                        final String words = part.substring(1);
                        final char type = part.charAt(0);
                        final int colorIndex = "0123456789abcdefklmnor".indexOf(type);
                        switch (colorIndex) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15: {
                                if (!ignoreColor) {
                                    currentColor = (ColorUtils.hexColors[colorIndex] | alpha << 24);
                                }
                                randomCase = false;
                                break;
                            }
                            case 16: {
                                randomCase = true;
                            }
                            case 21: {
                                currentColor = color;
                                if ((currentColor & 0xFC000000) == 0x0) {
                                    currentColor |= 0xFF000000;
                                }
                                randomCase = false;
                                break;
                            }
                        }
                        currentFont = this.defaultFont;
                        if (randomCase) {
                            currentFont.drawString(ColorUtils.randomMagicText(words), width, 0.0, currentColor);
                        }
                        else {
                            currentFont.drawString(words, width, 0.0, currentColor);
                        }
                        width += currentFont.getStringWidth(words);
                    }
                }
            }
        }
        else {
            this.defaultFont.drawString(text, 0.0, 0.0, currentColor);
        }
        GlStateManager.disableBlend();
        GlStateManager.translate(-(x - 1.5), -(y + 0.5), 0.0);
        return (int)(x + this.getStringWidth(text));
    }
    
    public int getColorCode(final char charCode) {
        return ColorUtils.hexColors[getColorIndex(charCode)];
    }
    
    public int getStringWidth(final String text) {
        if (text.contains("\u00c2\u00c2§")) {
            final String[] parts = text.split("\u00c2\u00c2§");
            ImageAWT currentFont = this.defaultFont;
            int width = 0;
            for (int index = 0; index < parts.length; ++index) {
                final String part = parts[index];
                if (!part.isEmpty()) {
                    if (index == 0) {
                        width += currentFont.getStringWidth(part);
                    }
                    else {
                        final String words = part.substring(1);
                        currentFont = this.defaultFont;
                        width += currentFont.getStringWidth(words);
                    }
                }
            }
            return width / 2;
        }
        return this.defaultFont.getStringWidth(text) / 2;
    }
    
    public int getCharWidth(final char character) {
        return this.getStringWidth(String.valueOf(character));
    }
    
    public void onResourceManagerReload(final IResourceManager resourceManager) {
    }
    
    protected void bindTexture(final ResourceLocation location) {
    }
    
    public static int getColorIndex(final char type) {
        switch (type) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                return type - '0';
            }
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f': {
                return type - 'a' + 10;
            }
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o': {
                return type - 'k' + 16;
            }
            case 'r': {
                return 21;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static class ColorUtils
    {
        public static int[] hexColors;
        private static final Random random;
        private static final String magicAllowedCharacters = "\u00c3\u20ac\u00c3\ufffd\u00c3\u201a\u00c3\u02c6\u00c3\u0160\u00c3\u2039\u00c3\ufffd\u00c3\u201c\u00c3\u201d\u00c3\u2022\u00c3\u0161\u00c3\u0178\u00c3\u00c2£\u00c3\u00c2µ\u00c4\u0178\u00c4\u00c2°\u00c4\u00c2±\u00c5\u2019\u00c5\u201c\u00c5\u017e\u00c5\u0178\u00c5\u00c2´\u00c5\u00c2µ\u00c5\u00c2¾\u00c8\u2021 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u00c3\u2021\u00c3\u00c2¼\u00c3\u00c2©\u00c3\u00c2¢\u00c3\u00c2¤\u00c3\u00c2 \u00c3\u00c2¥\u00c3\u00c2§\u00c3\u00c2ª\u00c3\u00c2«\u00c3\u00c2¨\u00c3\u00c2¯\u00c3\u00c2®\u00c3\u00c2¬\u00c3\u201e\u00c3\u2026\u00c3\u2030\u00c3\u00c2¦\u00c3\u2020\u00c3\u00c2´\u00c3\u00c2¶\u00c3\u00c2²\u00c3\u00c2»\u00c3\u00c2¹\u00c3\u00c2¿\u00c3\u2013\u00c3\u0153\u00c3\u00c2¸\u00c2\u00c2£\u00c3\u02dc\u00c3\u2014\u00c6\u2019\u00c3\u00c2¡\u00c3\u00ad\u00c3\u00c2³\u00c3\u00c2º\u00c3\u00c2±\u00c3\u2018\u00c2\u00c2ª\u00c2\u00c2º\u00c2\u00c2¿\u00c2\u00c2®\u00c2\u00c2¬\u00c2\u00c2½\u00c2\u00c2¼\u00c2\u00c2¡\u00c2\u00c2«\u00c2\u00c2»\u00e2\u2013\u2018\u00e2\u2013\u2019\u00e2\u2013\u201c\u00e2\u201d\u201a\u00e2\u201d\u00c2¤\u00e2\u2022\u00c2¡\u00e2\u2022\u00c2¢\u00e2\u2022\u2013\u00e2\u2022\u2022\u00e2\u2022\u00c2£\u00e2\u2022\u2018\u00e2\u2022\u2014\u00e2\u2022\ufffd\u00e2\u2022\u0153\u00e2\u2022\u203a\u00e2\u201d\ufffd\u00e2\u201d\u201d\u00e2\u201d\u00c2´\u00e2\u201d\u00c2¬\u00e2\u201d\u0153\u00e2\u201d\u20ac\u00e2\u201d\u00c2¼\u00e2\u2022\u017e\u00e2\u2022\u0178\u00e2\u2022\u0161\u00e2\u2022\u201d\u00e2\u2022\u00c2©\u00e2\u2022\u00c2¦\u00e2\u2022\u00c2 \u00e2\u2022\ufffd\u00e2\u2022\u00c2¬\u00e2\u2022\u00c2§\u00e2\u2022\u00c2¨\u00e2\u2022\u00c2¤\u00e2\u2022\u00c2¥\u00e2\u2022\u2122\u00e2\u2022\u02dc\u00e2\u2022\u2019\u00e2\u2022\u201c\u00e2\u2022\u00c2«\u00e2\u2022\u00c2ª\u00e2\u201d\u02dc\u00e2\u201d\u0152\u00e2\u2013\u02c6\u00e2\u2013\u201e\u00e2\u2013\u0152\u00e2\u2013\ufffd\u00e2\u2013\u20ac\u00ce\u00c2±\u00ce\u00c2²\u00ce\u201c\u00cf\u20ac\u00ce\u00c2£\u00cf\u0192\u00ce\u00c2¼\u00cf\u201e\u00ce\u00c2¦\u00ce\u02dc\u00ce\u00c2©\u00ce\u00c2´\u00e2\u02c6\u017e\u00e2\u02c6\u2026\u00e2\u02c6\u02c6\u00e2\u02c6\u00c2©\u00e2\u2030\u00c2¡\u00c2\u00c2±\u00e2\u2030\u00c2¥\u00e2\u2030\u00c2¤\u00e2\u0152\u00c2 \u00e2\u0152\u00c2¡\u00c3\u00c2·\u00e2\u2030\u02c6\u00c2\u00c2°\u00e2\u02c6\u2122\u00c2\u00c2·\u00e2\u02c6\u0161\u00e2\ufffd\u00c2¿\u00c2\u00c2²\u00e2\u2013\u00c2 ";
        
        public static String randomMagicText(final String text) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (final char ch : text.toCharArray()) {
                if (ChatAllowedCharacters.isAllowedCharacter(ch)) {
                    final int index = ColorUtils.random.nextInt("\u00c3\u20ac\u00c3\ufffd\u00c3\u201a\u00c3\u02c6\u00c3\u0160\u00c3\u2039\u00c3\ufffd\u00c3\u201c\u00c3\u201d\u00c3\u2022\u00c3\u0161\u00c3\u0178\u00c3\u00c2£\u00c3\u00c2µ\u00c4\u0178\u00c4\u00c2°\u00c4\u00c2±\u00c5\u2019\u00c5\u201c\u00c5\u017e\u00c5\u0178\u00c5\u00c2´\u00c5\u00c2µ\u00c5\u00c2¾\u00c8\u2021 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u00c3\u2021\u00c3\u00c2¼\u00c3\u00c2©\u00c3\u00c2¢\u00c3\u00c2¤\u00c3\u00c2 \u00c3\u00c2¥\u00c3\u00c2§\u00c3\u00c2ª\u00c3\u00c2«\u00c3\u00c2¨\u00c3\u00c2¯\u00c3\u00c2®\u00c3\u00c2¬\u00c3\u201e\u00c3\u2026\u00c3\u2030\u00c3\u00c2¦\u00c3\u2020\u00c3\u00c2´\u00c3\u00c2¶\u00c3\u00c2²\u00c3\u00c2»\u00c3\u00c2¹\u00c3\u00c2¿\u00c3\u2013\u00c3\u0153\u00c3\u00c2¸\u00c2\u00c2£\u00c3\u02dc\u00c3\u2014\u00c6\u2019\u00c3\u00c2¡\u00c3\u00ad\u00c3\u00c2³\u00c3\u00c2º\u00c3\u00c2±\u00c3\u2018\u00c2\u00c2ª\u00c2\u00c2º\u00c2\u00c2¿\u00c2\u00c2®\u00c2\u00c2¬\u00c2\u00c2½\u00c2\u00c2¼\u00c2\u00c2¡\u00c2\u00c2«\u00c2\u00c2»\u00e2\u2013\u2018\u00e2\u2013\u2019\u00e2\u2013\u201c\u00e2\u201d\u201a\u00e2\u201d\u00c2¤\u00e2\u2022\u00c2¡\u00e2\u2022\u00c2¢\u00e2\u2022\u2013\u00e2\u2022\u2022\u00e2\u2022\u00c2£\u00e2\u2022\u2018\u00e2\u2022\u2014\u00e2\u2022\ufffd\u00e2\u2022\u0153\u00e2\u2022\u203a\u00e2\u201d\ufffd\u00e2\u201d\u201d\u00e2\u201d\u00c2´\u00e2\u201d\u00c2¬\u00e2\u201d\u0153\u00e2\u201d\u20ac\u00e2\u201d\u00c2¼\u00e2\u2022\u017e\u00e2\u2022\u0178\u00e2\u2022\u0161\u00e2\u2022\u201d\u00e2\u2022\u00c2©\u00e2\u2022\u00c2¦\u00e2\u2022\u00c2 \u00e2\u2022\ufffd\u00e2\u2022\u00c2¬\u00e2\u2022\u00c2§\u00e2\u2022\u00c2¨\u00e2\u2022\u00c2¤\u00e2\u2022\u00c2¥\u00e2\u2022\u2122\u00e2\u2022\u02dc\u00e2\u2022\u2019\u00e2\u2022\u201c\u00e2\u2022\u00c2«\u00e2\u2022\u00c2ª\u00e2\u201d\u02dc\u00e2\u201d\u0152\u00e2\u2013\u02c6\u00e2\u2013\u201e\u00e2\u2013\u0152\u00e2\u2013\ufffd\u00e2\u2013\u20ac\u00ce\u00c2±\u00ce\u00c2²\u00ce\u201c\u00cf\u20ac\u00ce\u00c2£\u00cf\u0192\u00ce\u00c2¼\u00cf\u201e\u00ce\u00c2¦\u00ce\u02dc\u00ce\u00c2©\u00ce\u00c2´\u00e2\u02c6\u017e\u00e2\u02c6\u2026\u00e2\u02c6\u02c6\u00e2\u02c6\u00c2©\u00e2\u2030\u00c2¡\u00c2\u00c2±\u00e2\u2030\u00c2¥\u00e2\u2030\u00c2¤\u00e2\u0152\u00c2 \u00e2\u0152\u00c2¡\u00c3\u00c2·\u00e2\u2030\u02c6\u00c2\u00c2°\u00e2\u02c6\u2122\u00c2\u00c2·\u00e2\u02c6\u0161\u00e2\ufffd\u00c2¿\u00c2\u00c2²\u00e2\u2013\u00c2 ".length());
                    stringBuilder.append("\u00c3\u20ac\u00c3\ufffd\u00c3\u201a\u00c3\u02c6\u00c3\u0160\u00c3\u2039\u00c3\ufffd\u00c3\u201c\u00c3\u201d\u00c3\u2022\u00c3\u0161\u00c3\u0178\u00c3\u00c2£\u00c3\u00c2µ\u00c4\u0178\u00c4\u00c2°\u00c4\u00c2±\u00c5\u2019\u00c5\u201c\u00c5\u017e\u00c5\u0178\u00c5\u00c2´\u00c5\u00c2µ\u00c5\u00c2¾\u00c8\u2021 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u00c3\u2021\u00c3\u00c2¼\u00c3\u00c2©\u00c3\u00c2¢\u00c3\u00c2¤\u00c3\u00c2 \u00c3\u00c2¥\u00c3\u00c2§\u00c3\u00c2ª\u00c3\u00c2«\u00c3\u00c2¨\u00c3\u00c2¯\u00c3\u00c2®\u00c3\u00c2¬\u00c3\u201e\u00c3\u2026\u00c3\u2030\u00c3\u00c2¦\u00c3\u2020\u00c3\u00c2´\u00c3\u00c2¶\u00c3\u00c2²\u00c3\u00c2»\u00c3\u00c2¹\u00c3\u00c2¿\u00c3\u2013\u00c3\u0153\u00c3\u00c2¸\u00c2\u00c2£\u00c3\u02dc\u00c3\u2014\u00c6\u2019\u00c3\u00c2¡\u00c3\u00ad\u00c3\u00c2³\u00c3\u00c2º\u00c3\u00c2±\u00c3\u2018\u00c2\u00c2ª\u00c2\u00c2º\u00c2\u00c2¿\u00c2\u00c2®\u00c2\u00c2¬\u00c2\u00c2½\u00c2\u00c2¼\u00c2\u00c2¡\u00c2\u00c2«\u00c2\u00c2»\u00e2\u2013\u2018\u00e2\u2013\u2019\u00e2\u2013\u201c\u00e2\u201d\u201a\u00e2\u201d\u00c2¤\u00e2\u2022\u00c2¡\u00e2\u2022\u00c2¢\u00e2\u2022\u2013\u00e2\u2022\u2022\u00e2\u2022\u00c2£\u00e2\u2022\u2018\u00e2\u2022\u2014\u00e2\u2022\ufffd\u00e2\u2022\u0153\u00e2\u2022\u203a\u00e2\u201d\ufffd\u00e2\u201d\u201d\u00e2\u201d\u00c2´\u00e2\u201d\u00c2¬\u00e2\u201d\u0153\u00e2\u201d\u20ac\u00e2\u201d\u00c2¼\u00e2\u2022\u017e\u00e2\u2022\u0178\u00e2\u2022\u0161\u00e2\u2022\u201d\u00e2\u2022\u00c2©\u00e2\u2022\u00c2¦\u00e2\u2022\u00c2 \u00e2\u2022\ufffd\u00e2\u2022\u00c2¬\u00e2\u2022\u00c2§\u00e2\u2022\u00c2¨\u00e2\u2022\u00c2¤\u00e2\u2022\u00c2¥\u00e2\u2022\u2122\u00e2\u2022\u02dc\u00e2\u2022\u2019\u00e2\u2022\u201c\u00e2\u2022\u00c2«\u00e2\u2022\u00c2ª\u00e2\u201d\u02dc\u00e2\u201d\u0152\u00e2\u2013\u02c6\u00e2\u2013\u201e\u00e2\u2013\u0152\u00e2\u2013\ufffd\u00e2\u2013\u20ac\u00ce\u00c2±\u00ce\u00c2²\u00ce\u201c\u00cf\u20ac\u00ce\u00c2£\u00cf\u0192\u00ce\u00c2¼\u00cf\u201e\u00ce\u00c2¦\u00ce\u02dc\u00ce\u00c2©\u00ce\u00c2´\u00e2\u02c6\u017e\u00e2\u02c6\u2026\u00e2\u02c6\u02c6\u00e2\u02c6\u00c2©\u00e2\u2030\u00c2¡\u00c2\u00c2±\u00e2\u2030\u00c2¥\u00e2\u2030\u00c2¤\u00e2\u0152\u00c2 \u00e2\u0152\u00c2¡\u00c3\u00c2·\u00e2\u2030\u02c6\u00c2\u00c2°\u00e2\u02c6\u2122\u00c2\u00c2·\u00e2\u02c6\u0161\u00e2\ufffd\u00c2¿\u00c2\u00c2²\u00e2\u2013\u00c2 ".charAt(index));
                }
            }
            return stringBuilder.toString();
        }
        
        static {
            (ColorUtils.hexColors = new int[16])[0] = 0;
            ColorUtils.hexColors[1] = 170;
            ColorUtils.hexColors[2] = 43520;
            ColorUtils.hexColors[3] = 43690;
            ColorUtils.hexColors[4] = 11141120;
            ColorUtils.hexColors[5] = 11141290;
            ColorUtils.hexColors[6] = 16755200;
            ColorUtils.hexColors[7] = 11184810;
            ColorUtils.hexColors[8] = 5592405;
            ColorUtils.hexColors[9] = 5592575;
            ColorUtils.hexColors[10] = 5635925;
            ColorUtils.hexColors[11] = 5636095;
            ColorUtils.hexColors[12] = 16733525;
            ColorUtils.hexColors[13] = 16733695;
            ColorUtils.hexColors[14] = 16777045;
            ColorUtils.hexColors[15] = 16777215;
            random = new Random();
        }
    }
}
