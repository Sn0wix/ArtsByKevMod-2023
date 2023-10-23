package net.sn0wix_.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;

public class ModSounds {
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_AMBIENT_1 = registerSoundEvent("entity.kevosaurus.rex.ambient.1");
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_AMBIENT_2 = registerSoundEvent("entity.kevosaurus_rex.ambient.2");
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_DEATH = registerSoundEvent("entity.kevosaurus_rex.death");

    public static final SoundEvent ENTITY_KEVOSAURUS_REX_ATTACK_BITE = registerSoundEvent("entity.kevosaurus_rex.attack.bite");
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_ATTACK_TAIL = registerSoundEvent("entity_kevosaurus_rex.attack.tail");
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_ATTACK_STOMP = registerSoundEvent("entity.kevosaurus_rex.attack.stomp");
    public static final SoundEvent ENTITY_KEVOSAURUS_REX_ATTACK_ROAR = registerSoundEvent("entity.kevosaurus_rex.attack.roar");


    public static final SoundEvent ENTITY_KEVOCIRAPTOR_AMBIENT_1 = registerSoundEvent("entity.kevociraptor.ambient.1");
    public static final SoundEvent ENTITY_KEVOCIRAPTOR_AMBIENT_2 = registerSoundEvent("entity.kevociraptor.ambient.2");

    public static final SoundEvent ENTITY_KEVOCIRAPTOR_ATTACK_1 = registerSoundEvent("entity.kevociraptor.attack.1");
    public static final SoundEvent ENTITY_KEVOCIRAPTOR_ATTACK_2 = registerSoundEvent("entity.kevociraptor.attack.2");
    public static final SoundEvent ENTITY_KEVOCIRAPTOR_DEATH = registerSoundEvent("entity.kevociraptor.death");
    public static final SoundEvent ENTITY_KEVOCIRAPTOR_HURT = registerSoundEvent("entity.kevociraptor.hurt");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ArtsByKevModMain.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds(){
        ArtsByKevModMain.LOGGER.info("Registering sounds for " + ArtsByKevModMain.MOD_ID);
    }
}
