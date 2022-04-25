package com.github.mcdevteam.purple.networking;

import com.github.mcdevteam.purple.mixins.GameRenderInvoker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ShadersPlayChannelHandler implements ClientPlayNetworking.PlayChannelHandler {
    @Override
    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        ResourceLocation shaderResLoc = buf.readResourceLocation();
        client.execute(
                () -> ((GameRenderInvoker) client.gameRenderer).invokeLoadEffect(shaderResLoc)
        );
    }
}
