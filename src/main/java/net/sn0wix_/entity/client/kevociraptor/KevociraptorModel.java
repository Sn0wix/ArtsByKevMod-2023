package net.sn0wix_.entity.client.kevociraptor;

import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.custom.KevociraptorEntity;
import software.bernie.geckolib.model.GeoModel;

public class KevociraptorModel extends GeoModel<KevociraptorEntity> {

    public static final Identifier modelResource = new Identifier(ArtsByKevModMain.MOD_ID, "geo/kevociraptor.geo.json");
    public static final Identifier textureResource = new Identifier(ArtsByKevModMain.MOD_ID, "textures/entity/kevociraptor/kevociraptor.png");
    public static final Identifier animationResource = new Identifier(ArtsByKevModMain.MOD_ID, "animations/kevociraptor.animation.json");

    @Override
    public Identifier getModelResource(KevociraptorEntity animatable) {
        return modelResource;
    }

    @Override
    public Identifier getTextureResource(KevociraptorEntity animatable) {
        return textureResource;
    }

    @Override
    public Identifier getAnimationResource(KevociraptorEntity animatable) {
        return animationResource;
    }
}
