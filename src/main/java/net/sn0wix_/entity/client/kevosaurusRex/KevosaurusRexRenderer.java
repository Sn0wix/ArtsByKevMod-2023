package net.sn0wix_.entity.client.kevosaurusRex;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sn0wix_.entity.custom.KevosaurusRexEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KevosaurusRexRenderer extends GeoEntityRenderer<KevosaurusRexEntity> {
    public KevosaurusRexRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new KevosaurusRexModel());
    }

    @Override
    public Identifier getTextureLocation(KevosaurusRexEntity animatable) {
        return KevosaurusRexModel.textureResource;
    }
}
