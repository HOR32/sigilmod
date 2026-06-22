package com.sickasian.magicshitmod.hud.ManaHud;

import com.sickasian.magicshitmod.PlayerMana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "magicshitmod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ManaHud {

    private static final int BAR_WIDTH  = 182;
    private static final int BAR_HEIGHT = 5;

    private static int regenTimer = 0;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        GuiGraphics graphics = event.getGuiGraphics();
        int screenWidth  = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int x = screenWidth  / 2 - BAR_WIDTH / 2;
        int y = screenHeight - 34;

        float percent = (float) PlayerMana.mana / PlayerMana.maxmana;
        int fillWidth = (int) (BAR_WIDTH * percent);

        graphics.fill(x - 1, y - 1, x + BAR_WIDTH + 1, y + BAR_HEIGHT + 1, 0xFF000000); // outline
        graphics.fill(x,     y,     x + BAR_WIDTH,     y + BAR_HEIGHT,     0xFF222222); // background
        graphics.fill(x,     y,     x + fillWidth,     y + BAR_HEIGHT,     0xFF4444FF); // fill

        String text = PlayerMana.mana + " / " + PlayerMana.maxmana;
        int textX = x + BAR_WIDTH / 2 - mc.font.width(text) / 2;
        int textY = y + BAR_HEIGHT / 2 - 4;
        graphics.drawString(mc.font, text, textX, textY, 0xFFFFFFFF, true);


        regenTimer++;
        if (regenTimer >= 240) {
            regenTimer = 0;
            if (PlayerMana.mana < PlayerMana.maxmana) PlayerMana.mana++;
        }
    }
}