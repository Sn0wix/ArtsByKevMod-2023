package net.sn0wix_.entity.client.artsByKev;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.entity.custom.ArtsByKevEntity;

public class ArtsByKevRenderer extends MobEntityRenderer<ArtsByKevEntity, EntityModel<ArtsByKevEntity>> {
    public static final Identifier TEXTURE = new Identifier(ArtsByKevModMain.MOD_ID, "textures/entity/arts_by_kev/arts_by_kev.png");
    public ArtsByKevRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(ArtsByKevEntity entity) {
        return TEXTURE;
    }
}
