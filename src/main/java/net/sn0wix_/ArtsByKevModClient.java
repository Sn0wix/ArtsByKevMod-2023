package net.sn0wix_;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sn0wix_.entity.ModEntities;
import net.sn0wix_.entity.client.artsByKev.ArtsByKevRenderer;
import net.sn0wix_.entity.client.kevociraptor.KevociraptorRenderer;
import net.sn0wix_.entity.client.kevosaurusRex.KevosaurusRexRenderer;
import net.sn0wix_.networking.ModPackets;

public class ArtsByKevModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModPackets.registerS2CPackets();
        EntityRendererRegistry.register(ModEntities.KEVOSAURUS_REX, KevosaurusRexRenderer::new);
        EntityRendererRegistry.register(ModEntities.KEVOCIRAPTOR, KevociraptorRenderer::new);
        EntityRendererRegistry.register(ModEntities.ARTS_BY_KEV, ArtsByKevRenderer::new);
    }
}
