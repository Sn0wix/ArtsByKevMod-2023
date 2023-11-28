package net.sn0wix_.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.custom.ArtsByKevEntity;
import net.sn0wix_.entity.custom.KevociraptorEntity;
import net.sn0wix_.entity.custom.KevosaurusRexEntity;

public class ModEntities {
    public static final EntityType<KevosaurusRexEntity> KEVOSAURUS_REX = Registry.register(Registries.ENTITY_TYPE, new Identifier(ArtsByKevModMain.MOD_ID, "kevosaurus_rex"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, KevosaurusRexEntity::new).dimensions(EntityDimensions.fixed(3, 3)).build());

    public static final EntityType<KevociraptorEntity> KEVOCIRAPTOR = Registry.register(Registries.ENTITY_TYPE, new Identifier(ArtsByKevModMain.MOD_ID, "kevociraptor"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, KevociraptorEntity::new).dimensions(EntityDimensions.fixed(1.9f, 1.7f)).build());

    public static final EntityType<ArtsByKevEntity> ARTS_BY_KEV = Registry.register(Registries.ENTITY_TYPE, new Identifier(ArtsByKevModMain.MOD_ID, "arts_by_kev"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ArtsByKevEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());

    public static void registerModEntities() {
        ArtsByKevModMain.LOGGER.info("Registering entities for " + ArtsByKevModMain.MOD_ID);
    }
}
