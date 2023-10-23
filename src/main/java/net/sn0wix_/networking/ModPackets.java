package net.sn0wix_.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.sn0wix_.ArtsByKevModMain;
import net.sn0wix_.networking.packets.KevosaurusRexParticlesS2CPacket;

public class ModPackets {
    public static final Identifier KEVOSAURUS_REX_PARTICLES = new Identifier(ArtsByKevModMain.MOD_ID, "kevosaurus_rex_particles");

    public static void registerC2SPackets(){

    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(KEVOSAURUS_REX_PARTICLES, KevosaurusRexParticlesS2CPacket::receive);
    }
}
