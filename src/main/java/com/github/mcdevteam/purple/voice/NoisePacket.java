package com.github.mcdevteam.purple.voice;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import static com.github.mcdevteam.purple.voice.Microphone.LAST_PEAK;

public class NoisePacket implements Runnable {
    ResourceLocation identifier = new ResourceLocation("carpet", "noise");
    @Override
    public void run() {
        try {
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeDouble(LAST_PEAK);
            ClientPlayNetworking.send(identifier, buf);
        } catch (Exception ignored){}
    }
}
