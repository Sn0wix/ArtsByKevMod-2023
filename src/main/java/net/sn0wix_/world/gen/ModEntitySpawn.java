package net.sn0wix_.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.Heightmap;
import net.sn0wix_.entity.ModEntities;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD), SpawnGroup.MONSTER, ModEntities.KEVOSAURUS_REX, 2, 1, 1);
        SpawnRestriction.register(ModEntities.KEVOSAURUS_REX, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, (type, world, spawnReason, pos, random) -> true);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD), SpawnGroup.MONSTER, ModEntities.KEVOCIRAPTOR, 15, 1, 4);
        SpawnRestriction.register(ModEntities.KEVOCIRAPTOR, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, (type, world, spawnReason, pos, random) -> true);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD), SpawnGroup.CREATURE, ModEntities.ARTS_BY_KEV, 10, 1, 4);
        SpawnRestriction.register(ModEntities.ARTS_BY_KEV, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, (type, world, spawnReason, pos, random) -> true);
    }
}
