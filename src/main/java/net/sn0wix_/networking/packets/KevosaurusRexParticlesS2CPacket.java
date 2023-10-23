package net.sn0wix_.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class KevosaurusRexParticlesS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        //it's not what it looks like in case you wander...
        BlockPos pos = buf.readBlockPos();

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
            try {
                client.world.addBlockBreakParticles(blockPos, client.world.getBlockState(blockPos));
            }catch (NullPointerException ignored){}
        }
    }
}
