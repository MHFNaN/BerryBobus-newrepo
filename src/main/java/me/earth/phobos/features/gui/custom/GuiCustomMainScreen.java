package me.earth.phobos.features.gui.custom;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.modules.client.ClickGui;
import me.earth.phobos.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;

public class GuiCustomMainScreen
        extends GuiScreen {
    private final String backgroundURL = "https://cdn.discordapp.com/attachments/1011813311043616789/1013232563470479470/IMG_1279.jpg";
    private final ResourceLocation resourceLocation = new ResourceLocation("textures/background.png");
    private int y;
    private int x;
    private int singleplayerWidth;
    private int multiplayerWidth;
    private int settingsWidth;
    private int exitWidth;
    private int textHeight;
    private float xOffset;
    private float yOffset;

    public static void drawCompleteImage(float posX, float posY, float width, float height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(width, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY < y + height;
    }

    public void initGui() {
        this.x = this.width / 2;
        this.y = this.height / 4 + 48;
        this.buttonList.add(new TextButton(0, this.x, this.y + 20, "1 Player"));
        this.buttonList.add(new TextButton(1, this.x, this.y + 44, "2 Player"));
        this.buttonList.add(new TextButton(2, this.x, this.y + 66, "Settings"));
        this.buttonList.add(new TextButton(3, this.x, this.y + 88, "-Discord-"));
        this.buttonList.add(new TextButton(4, this.x, this.y + 110, "GUILoader"));
        this.buttonList.add(new TextButton(5, this.x, this.y + 132, "Quit"));
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public void updateScreen() {
        super.updateScreen();
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (GuiCustomMainScreen.isHovered(this.x - Phobos.textManager.getStringWidth("Singleplayer") / 2, this.y + 20, Phobos.textManager.getStringWidth("Singleplayer"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen(new GuiWorldSelection(this));
        } else if (GuiCustomMainScreen.isHovered(this.x - Phobos.textManager.getStringWidth("Multiplayer") / 2, this.y + 44, Phobos.textManager.getStringWidth("Multiplayer"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        } else if (GuiCustomMainScreen.isHovered(this.x - Phobos.textManager.getStringWidth("Settings") / 2, this.y + 66, Phobos.textManager.getStringWidth("Settings"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        } else if(GuiCustomMainScreen.isHovered(this.x, this.y + 88, Phobos.textManager.getStringWidth("Discord"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/u2Xzb5Xv5b"));
                }
            } catch (Exception ignored) {
            }
        } else if(GuiCustomMainScreen.isHovered(this.x, this.y + 110, Phobos.textManager.getStringWidth("ClickGui"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            Phobos.moduleManager.enableModule("ClickGui");
        } else if (GuiCustomMainScreen.isHovered(this.x - Phobos.textManager.getStringWidth("Exit") / 2, this.y + 132, Phobos.textManager.getStringWidth("Exit"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.shutdown();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.xOffset = -1.0f * (((float) mouseX - (float) this.width / 2.0f) / ((float) this.width / 32.0f));
        this.yOffset = -1.0f * (((float) mouseY - (float) this.height / 2.0f) / ((float) this.height / 18.0f));
        this.x = this.width / 2;
        this.y = this.height / 4 + 48;
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        this.mc.getTextureManager().bindTexture(this.resourceLocation);
        GuiCustomMainScreen.drawCompleteImage(-16.0f + this.xOffset, -9.0f + this.yOffset, this.width + 32, this.height + 18);
        Phobos.textManager.drawStringBig("SKOBOS", (float) this.x - 50, (float) this.y - 10, Color.white.getRGB(), true);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public BufferedImage parseBackground(BufferedImage background) {
        int height;
        int width = 1920;
        int srcWidth = background.getWidth();
        int srcHeight = background.getHeight();
        for (height = 1080; width < srcWidth || height < srcHeight; width *= 2, height *= 2) {
        }
        BufferedImage imgNew = new BufferedImage(width, height, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage(background, 0, 0, null);
        g.dispose();
        return imgNew;
    }

    private static class TextButton
            extends GuiButton {
        public TextButton(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, Phobos.textManager.getStringWidth(buttonText), Phobos.textManager.getFontHeight(), buttonText);
        }

        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                this.enabled = true;
                this.hovered = (float) mouseX >= (float) this.x - (float) Phobos.textManager.getStringWidth(this.displayString) / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                Phobos.textManager.drawStringWithShadow(this.displayString, (float) this.x - (float) Phobos.textManager.getStringWidth(this.displayString) / 2.0f, this.y, Color.WHITE.getRGB());
                if (this.hovered) {
                    RenderUtil.drawLine((float) (this.x - 1) - (float) Phobos.textManager.getStringWidth(this.displayString) / 2.0f, this.y + 2 + Phobos.textManager.getFontHeight(), (float) this.x + (float) Phobos.textManager.getStringWidth(this.displayString) / 2.0f + 1.0f, this.y + 2 + Phobos.textManager.getFontHeight(), 1.0f, Color.WHITE.getRGB());
                    Phobos.textManager.drawStringSmall("welcome to the skobois!", (float) this.x, (float) this.y - 10, Color.white.getRGB(), false);
                }
            }
        }

        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            return this.enabled && this.visible && (float) mouseX >= (float) this.x - (float) Phobos.textManager.getStringWidth(this.displayString) / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        }
    }
}

