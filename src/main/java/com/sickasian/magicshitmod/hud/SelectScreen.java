package com.sickasian.magicshitmod.hud;

import com.sickasian.magicshitmod.ModItems.custom.SpelBookItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SelectScreen extends Screen {

    private static final int BG_WIDTH = 176;
    private static final int BG_HEIGHT = 120;

    private int leftPos;
    private int topPos;

    public SelectScreen() {
        super(Component.literal("Select Option"));
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - BG_WIDTH) / 2;
        this.topPos  = (this.height - BG_HEIGHT) / 2;

        addRenderableWidget(Button.builder(
                Component.literal("Fire"),
                btn -> select(0)
        ).bounds(leftPos + 10, topPos + 20, 156, 20).build());

        addRenderableWidget(Button.builder(
                Component.literal("Ice"),
                btn -> select(1)
        ).bounds(leftPos + 10, topPos + 45, 156, 20).build());

        addRenderableWidget(Button.builder(
                Component.literal("to be continued"),
                btn -> select(2)
        ).bounds(leftPos + 10, topPos + 70, 156, 20).build());
    }

    private void select(int option) {
        SpelBookItem.selected = option;  // directly set the variable
        this.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        graphics.fill(leftPos, topPos, leftPos + BG_WIDTH, topPos + BG_HEIGHT, 0xCC101010);
        graphics.renderOutline(leftPos, topPos, BG_WIDTH, BG_HEIGHT, 0xFFFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, topPos + 6, 0xFFFFFF);

        // Show current selection
        if (SpelBookItem.selected >= 0) {
            graphics.drawString(this.font,
                    "Current: Option " + (SpelBookItem.selected + 1),
                    leftPos + 10, topPos + 100, 0xAAAAAA);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}