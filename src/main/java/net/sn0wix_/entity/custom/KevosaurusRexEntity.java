package net.sn0wix_.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.sn0wix_.entity.ModEntities;
import net.sn0wix_.entity.ai.MMEntityMoveHelper;
import net.sn0wix_.entity.ai.MMPathNavigateGround;
import net.sn0wix_.networking.ModPackets;
import net.sn0wix_.sounds.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Random;

public class KevosaurusRexEntity extends HostileEntity implements GeoEntity {

    public static final RawAnimation IDLE = RawAnimation.begin().then("kevosaurus_rex.animation.idle", Animation.LoopType.LOOP);
    public static final RawAnimation WALK = RawAnimation.begin().then("kevosaurus_rex.animation.walk", Animation.LoopType.LOOP);
    public static final RawAnimation RUN = RawAnimation.begin().then("kevosaurus_rex.animation.run", Animation.LoopType.LOOP);
    public static final RawAnimation ROAR = RawAnimation.begin().then("kevosaurus_rex.animation.roar", Animation.LoopType.PLAY_ONCE);

    public static final RawAnimation ATTACK_BITE = RawAnimation.begin().then("kevosaurus_rex.animation.attack.bite", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation ATTACK_TAIL = RawAnimation.begin().then("kevosaurus_rex.animation.attack.tail", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation ATTACK_STOMP = RawAnimation.begin().then("kevosaurus_rex.animation.attack.stomp", Animation.LoopType.PLAY_ONCE);

    private int attackTicksLeft = 0;
    private int lastAttackedType = 0;
    public int roaringTicksLeft = 0;

    private final ServerBossBar bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Random random = new Random();


    public KevosaurusRexEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        moveControl = new MMEntityMoveHelper(this, 90);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 2.2, false));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new RevengeGoal(this, KevociraptorEntity.class));
        this.goalSelector.add(0, new SwimGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, ArtsByKevEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PigEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, WanderingTraderEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 600.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 3.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate)
                .triggerableAnim("roar", ROAR).triggerableAnim("bite", ATTACK_BITE).triggerableAnim("stomp", ATTACK_STOMP)
                .triggerableAnim("tail", ATTACK_TAIL));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            return this.isAttacking() ? state.setAndContinue(RUN) : state.setAndContinue(WALK);
        }

        return state.setAndContinue(IDLE);
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (lastAttackedTicks == 0) {
            int i = random.nextInt(10);

            if (i <= 3) {
                this.triggerAnim("controller", "bite");
                this.playSound(ModSounds.ENTITY_KEVOSAURUS_REX_ATTACK_BITE, this.getSoundVolume(), this.getSoundPitch());
                return super.tryAttack(target);
            }

            if (i == 4 || i == 5) {
                this.triggerAnim("controller", "stomp");
                this.playSound(ModSounds.ENTITY_KEVOSAURUS_REX_ATTACK_STOMP, this.getSoundVolume(), this.getSoundPitch());
                attackTicksLeft = 6;
                lastAttackedType = 1;
            }

            if (i >= 6) {
                this.triggerAnim("controller", "tail");
                this.playSound(ModSounds.ENTITY_KEVOSAURUS_REX_ATTACK_TAIL, this.getSoundVolume(), this.getSoundPitch());
                attackTicksLeft = 6;
                lastAttackedType = 2;
            }
        }

        return false;
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            try {
                this.getLookControl().lookAt(this.getTarget());
            } catch (NullPointerException ignored) {
            }

            if (this.isAttacking() && random.nextInt(1000) == 69 && roaringTicksLeft == 0 && attackTicksLeft == 0) {
                startRoaring();
            }

            if (random.nextInt(2500) == 69 && roaringTicksLeft == 0 && attackTicksLeft == 0) {
                startRoaring();
            }

            if (roaringTicksLeft > 0) {
                this.getNavigation().stop();
                this.setTarget(null);
                roaringTicksLeft--;
                if (attackTicksLeft > 0) {
                    attackTicksLeft++;
                }
            }

            if (attackTicksLeft > 0) {
                this.getNavigation().stop();
                attackTicksLeft--;
                if (attackTicksLeft == 0) {
                    this.tryDelayedAttack();
                }
            }
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return random.nextInt(2) == 0 ? ModSounds.ENTITY_KEVOSAURUS_REX_AMBIENT_1 : ModSounds.ENTITY_KEVOSAURUS_REX_AMBIENT_2;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_KEVOSAURUS_REX_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.ENTITY_KEVOSAURUS_REX_ATTACK_STOMP, 40F, 1.0F);
    }

    public void tryDelayedAttack() {
        try {
            if (roaringTicksLeft == 0) {
                super.tryAttack(this.getTarget());
                if (lastAttackedType == 2) {
                    this.getTarget().setVelocity(this.getTarget().getVelocity().multiply(10, 2, 10));
                }

                if (lastAttackedType == 1) {
                    Box box = new Box(this.getBlockPos()).expand(1.5f);
                    this.getWorld().getOtherEntities(this, box).forEach(entity -> {
                                if (entity instanceof LivingEntity livingEntity) {
                                    livingEntity.damage(this.getDamageSources().generic(), 5f);
                                    livingEntity.setVelocity(this.getTarget().getVelocity().multiply(5, 5, 5));
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1, true, true, true));
                                }
                            }
                    );
                    spawnParticles();
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    public void spawnParticles() {
        //it's not what it looks like in case you wander...
        BlockPos pos = this.getBlockPos().down();

        BlockPos pos1 = pos.north();
        BlockPos pos2 = pos.east();
        BlockPos pos3 = pos.south();
        BlockPos pos4 = pos.west();

        BlockPos pos5 = pos1.east();
        BlockPos pos6 = pos2.south();
        BlockPos pos7 = pos3.west();
        BlockPos pos8 = pos4.north();

        BlockPos[] blocksPosArray = new BlockPos[]{pos, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8};

        for (BlockPos blockPos : blocksPosArray) {
            this.getWorld().playSound(null, pos, this.getWorld().getBlockState(blockPos).getSoundGroup().getBreakSound(), SoundCategory.BLOCKS);
        }

        if (!this.getWorld().isClient) {
            this.getWorld().getPlayers().forEach(player -> {

                PacketByteBuf buffer = PacketByteBufs.create();
                buffer.writeBlockPos(pos);
                ServerPlayNetworking.send((ServerPlayerEntity) player, ModPackets.KEVOSAURUS_REX_PARTICLES, buffer);
            });
        }
    }

    public void startRoaring() {
        int i = random.nextInt(4);

        if (i == 1) {
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
        } else if (i == 2) {
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
        } else if (i == 3) {
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
            ModEntities.KEVOCIRAPTOR.spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.TRIGGERED);
        }

        this.triggerAnim("controller", "roar");
        this.playSound(ModSounds.ENTITY_KEVOSAURUS_REX_ATTACK_ROAR, 40, 1f);
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 3, true, false, false));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 4, true, false, false));
        roaringTicksLeft = 121;
    }

    public boolean isRoaring() {
        return roaringTicksLeft > 0;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new MMPathNavigateGround(this, world);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        this.getWorld().createExplosion(this, getX(), getY(), getZ(), 4f, false, World.ExplosionSourceType.MOB);

        if (!getWorld().isClient()) {
            int kevs = (int) ((Math.random() + 1) * 40);

            for (int i = 0; i < kevs; i++) {
                try {
                    ModEntities.ARTS_BY_KEV.spawn((ServerWorld) getWorld(), getBlockPos(), SpawnReason.MOB_SUMMONED).setInvulnerableFor(5, true)
                            .setVelocity(Math.random(), Math.random() * 2, Math.random());
                } catch (NullPointerException ignored) {
                }
            }
        }

        super.onDeath(damageSource);
    }


}
