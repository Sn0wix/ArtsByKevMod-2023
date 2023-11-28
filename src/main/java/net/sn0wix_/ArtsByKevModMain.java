package net.sn0wix_;

import net.fabricmc.api.ModInitializer;

import net.sn0wix_.blocks.ModBlocks;
import net.sn0wix_.entity.ModEntities;
import net.sn0wix_.item.ModItems;
import net.sn0wix_.networking.ModPackets;
import net.sn0wix_.sounds.ModSounds;
import net.sn0wix_.util.ModRegisteries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class ArtsByKevModMain implements ModInitializer {
	public static final String MOD_ID = "artsbykevmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModRegisteries.registerModStuffs();
		ModSounds.registerModSounds();
		ModPackets.registerC2SPackets();
		ModEntities.registerModEntities();

		GeckoLib.initialize();
	}
}