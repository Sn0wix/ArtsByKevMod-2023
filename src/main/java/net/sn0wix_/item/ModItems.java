package net.sn0wix_.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.ModEntities;

public class ModItems {

    public static final Item KEVOSAURUS_REX_SPAWN_EGG = registerItem("kevosaurus_rex_spawn_egg",
            new SpawnEggItem(ModEntities.KEVOSAURUS_REX, 7025684, 14861373, new FabricItemSettings()));

    public static final Item KEVOCIRAPTOR_SPAWN_EGG = registerItem("kevociraptor_spawn_egg",
            new SpawnEggItem(ModEntities.KEVOCIRAPTOR, 7025684, 5046016, new FabricItemSettings()));

    private static void addItemsToGroups(){
        addToItemGroup(KEVOSAURUS_REX_SPAWN_EGG);
        addToItemGroup(KEVOCIRAPTOR_SPAWN_EGG);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(ArtsByKevModMain.MOD_ID,name),item);
    }

    public static void registerModItems(){
        addItemsToGroups();
        ArtsByKevModMain.LOGGER.info("Registering Mod Items for " + ArtsByKevModMain.MOD_ID);
    }

    private static void addToItemGroup(Item item){
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ARTS_BY_KEV_ITEM_GROUP).register(entries -> entries.add(item));
    }
}
