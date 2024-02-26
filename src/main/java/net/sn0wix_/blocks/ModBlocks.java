package net.sn0wix_.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.blocks.custom.KevBlock;

public class ModBlocks {
    public static final Block KEV_BLOCK = registerBlock("kev_block", new KevBlock(FabricBlockSettings.copy(Blocks.STONE)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ArtsByKevModMain.MOD_ID, name),block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, new Identifier(ArtsByKevModMain.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        ArtsByKevModMain.LOGGER.info("Registerion Mod Blocks for " + ArtsByKevModMain.MOD_ID);
    }
}
