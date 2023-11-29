package net.sn0wix_.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.net.URL;

public class ArtsByKevEntity extends PathAwareEntity {
    public static final TrackedData<Byte> VIOLENT = DataTracker.registerData(ArtsByKevEntity.class, TrackedDataHandlerRegistry.BYTE);

    ActiveTargetGoal<PlayerEntity> playerAttack;

    public ArtsByKevEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new WanderAroundGoal(this, 1f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1f));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, (float) getAttributeBaseValue(EntityAttributes.GENERIC_FOLLOW_RANGE)));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1f, false));

        this.goalSelector.add(1, new MeleeAttackGoal(this, 1f, false));
        this.targetSelector.add(2, new RevengeGoal(this, PlayerEntity.class));
        this.goalSelector.add(2, new ActiveTargetGoal<>(this, KevosaurusRexEntity.class, true));
        this.goalSelector.add(3, new ActiveTargetGoal<>(this, KevociraptorEntity.class, true));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VIOLENT, (byte) 0);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64);
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

    public Boolean isViolent() {
        return dataTracker.get(VIOLENT) == 2;
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl) {
            //hand swing bug
        }
        return bl;
    }

    @Override
    public void tick() {
        super.tick();
        if (dataTracker.get(VIOLENT) == 0) {
            dataTracker.set(VIOLENT, random.nextInt(10) == 0 ? (byte) 2 : (byte) 3);
        }

        if (isViolent()) {
            goalSelector.add(1, playerAttack == null ? playerAttack = new ActiveTargetGoal<>(this, PlayerEntity.class, true): playerAttack);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("IsViolent")) {
            dataTracker.set(VIOLENT, nbt.getByte("IsViolent"));
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("IsViolent", dataTracker.get(VIOLENT));
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public Text getDisplayName() {
        return Text.of("ArtsByKev");
    }

    @Override
    public boolean shouldRenderName() {
        return true;
    }

    @Override
    protected Text getDefaultName() {
        return Text.of("ArtsByKev");
    }

    @Nullable
    @Override
    public Text getCustomName() {
        return Text.of("ArtsByKev");
    }
}
