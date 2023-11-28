package net.sn0wix_.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.net.URL;

public class ArtsByKevEntity extends PathAwareEntity {
    public ArtsByKevEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new WanderAroundGoal(this, 1f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1f));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, (float) getAttributeBaseValue(EntityAttributes.GENERIC_FOLLOW_RANGE)));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64);
    }


    @Nullable
    @Override
    public Text getCustomName() {
        return Text.of("ArtsByKev");
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (world.isClient) {
            player.sendMessage(Text.literal("<ArtsByKev> ").append(Text.translatable("text.artsbykevmod.subscribe").styled(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("text.artsbykevmod.subscribe"))).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/@ArtsByKev")).withColor(Formatting.BLUE).withBold(true).withUnderline(true))));
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.youtube.com/@ArtsByKev").toURI());
                } catch (Exception ignored) {
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }
}
