package net.sn0wix_.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.blocks.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup ARTS_BY_KEV_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(ArtsByKevModMain.MOD_ID, "arts_by_kev_item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.artsbykevmod.arts_by_kev_item_group"))
                    .icon(() -> new ItemStack(ModItems.ARTS_BY_KEV_SPAWN_EGG)).entries(ModItemGroup::addEntries).build());

    public static void registerItemGroup() {
        ArtsByKevModMain.LOGGER.info("Registering item group for " + ArtsByKevModMain.MOD_ID);
    }

    private static void addEntries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        entries.add(ModItems.KEVOSAURUS_REX_SPAWN_EGG);
        entries.add(ModItems.KEVOCIRAPTOR_SPAWN_EGG);
        entries.add(ModItems.ARTS_BY_KEV_SPAWN_EGG);
        entries.add(ModItems.KEV_INGOT);
        entries.add(ModBlocks.KEV_BLOCK);
    }
}
