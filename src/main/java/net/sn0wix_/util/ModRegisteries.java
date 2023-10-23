package net.sn0wix_.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sn0wix_.entity.ModEntities;
import net.sn0wix_.entity.custom.KevociraptorEntity;
import net.sn0wix_.entity.custom.KevosaurusRexEntity;

public class ModRegisteries {
    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.KEVOSAURUS_REX, KevosaurusRexEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.KEVOCIRAPTOR, KevociraptorEntity.setAttributes());
    }
}
