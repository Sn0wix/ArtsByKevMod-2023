package net.sn0wix_.entity.client.kevociraptor;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sn0wix_.entity.custom.KevociraptorEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KevociraptorRenderer extends GeoEntityRenderer<KevociraptorEntity> {
    public KevociraptorRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new KevociraptorModel());
    }

    @Override
    public Identifier getTextureLocation(KevociraptorEntity animatable) {
        return KevociraptorModel.textureResource;
    }
}
