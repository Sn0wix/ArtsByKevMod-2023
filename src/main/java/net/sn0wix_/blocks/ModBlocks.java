package net.sn0wix_.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.blocks.custom.KevBlock;
import net.sn0wix_.item.ModItemGroup;

public class ModBlocks {
    public static final Block KEV_BLOCK = registerBlock("kev_block", new KevBlock(FabricBlockSettings.of(Material.STONE)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ArtsByKevModMain.MOD_ID, name),block);
    }

    private static void registerBlockItem(String name, Block block){
        Item item = Registry.register(Registries.ITEM, new Identifier(ArtsByKevModMain.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ARTS_BY_KEV_ITEM_GROUP).register(entries -> entries.add(item));
    }

    public static void registerModBlocks(){
        ArtsByKevModMain.LOGGER.info("Registerion Mod Blocks for " + ArtsByKevModMain.MOD_ID);
    }
}
