package net.sn0wix_.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.sn0wix_.sounds.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Random;

public class KevociraptorEntity extends HostileEntity implements GeoEntity {

    public static final RawAnimation IDLE = RawAnimation.begin().then("kevraptor.animation.idle", Animation.LoopType.LOOP);
    public static final RawAnimation WALK = RawAnimation.begin().then("kevraptor.animation.walk", Animation.LoopType.LOOP);
    public static final RawAnimation ATTACK = RawAnimation.begin().then("kevraptor.animation.attack", Animation.LoopType.PLAY_ONCE);

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    Random random = new Random();

    public KevociraptorEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new RevengeGoal(this, KevosaurusRexEntity.class, KevociraptorEntity.class));
        this.goalSelector.add(0, new SwimGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PigEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, WanderingTraderEntity.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",2,this::predicate).triggerableAnim("attack", ATTACK));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30);
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()){
            return state.setAndContinue(WALK);
        }
        return state.setAndContinue(IDLE);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!this.getWorld().isClient) {
            this.playSound(random.nextInt(2) == 0 ? ModSounds.ENTITY_KEVOCIRAPTOR_ATTACK_1 : ModSounds.ENTITY_KEVOCIRAPTOR_ATTACK_2, this.getSoundVolume(), this.getSoundPitch());
        }
        this.triggerAnim("controller", "attack");
        return super.tryAttack(target);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return random.nextInt(2) == 0 ? ModSounds.ENTITY_KEVOCIRAPTOR_AMBIENT_1 : ModSounds.ENTITY_KEVOCIRAPTOR_AMBIENT_2;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_KEVOCIRAPTOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_KEVOCIRAPTOR_DEATH;
    }

    public static boolean isValidNaturalSpawn(EntityType<? extends HostileEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, net.minecraft.util.math.random.Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON);
    }
}
