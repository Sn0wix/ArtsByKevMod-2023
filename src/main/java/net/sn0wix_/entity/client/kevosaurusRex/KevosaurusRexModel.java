package net.sn0wix_.entity.client.kevosaurusRex;

import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.custom.KevosaurusRexEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

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

    @Override
    public void setCustomAnimations(KevosaurusRexEntity animatable, long instanceId, AnimationState<KevosaurusRexEntity> animationState) {
        if (!animatable.isRoaring()){
            CoreGeoBone head = this.getAnimationProcessor().getBone("h_head");

            if (head != null) {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
                head.setRotX(entityData.headPitch() * ((float) Math.PI / 180F));
                head.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));
            }
        }
    }
}
