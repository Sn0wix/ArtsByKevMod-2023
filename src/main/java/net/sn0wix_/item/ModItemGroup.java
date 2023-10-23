package net.sn0wix_.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;

public class ModItemGroup {
    public static final ItemGroup ARTS_BY_KEV_ITEM_GROUP = FabricItemGroup.builder(new Identifier(ArtsByKevModMain.MOD_ID, "arts_by_kev_item_group")).icon(() -> new ItemStack(ModItems.KEVOSAURUS_REX_SPAWN_EGG)).build();
}
