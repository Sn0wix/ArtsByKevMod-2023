package net.sn0wix_.entity.client.kevosaurusRex;

import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.custom.KevosaurusRexEntity;
import software.bernie.geckolib.model.GeoModel;

public class KevosaurusRexModel extends GeoModel<KevosaurusRexEntity> {
    public static final Identifier modelResource = new Identifier(ArtsByKevModMain.MOD_ID, "geo/kevosaurus_rex.geo.json");
    public static final Identifier textureResource = new Identifier(ArtsByKevModMain.MOD_ID, "textures/entity/kevosaurus_rex/kevosaurus_rex.png");
    public static final Identifier animationResource = new Identifier(ArtsByKevModMain.MOD_ID, "animations/kevosaurus_rex.animation.json");

    @Override
    public Identifier getModelResource(KevosaurusRexEntity animatable) {
        return modelResource;
    }

    @Override
    public Identifier getTextureResource(KevosaurusRexEntity animatable) {
        return textureResource;
    }

    @Override
    public Identifier getAnimationResource(KevosaurusRexEntity animatable) {
        return animationResource;
    }
}
