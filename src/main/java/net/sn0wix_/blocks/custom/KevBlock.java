package net.sn0wix_.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;
import java.net.URL;

public class KevBlock extends Block {
    public KevBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            player.sendMessage(Text.literal("<Kev_Block> ").append(Text.translatable("text.artsbykevmod.subscribe").styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("text.artsbykevmod.subscribe"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/@ArtsByKev")).withColor(Formatting.BLUE).withBold(true).withUnderline(true))));
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.youtube.com/@ArtsByKev").toURI());
                } catch (Exception ignored) {
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
