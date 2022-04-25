package com.github.mcdevteam.purple.networking;

import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import static com.github.mcdevteam.purple.utils.ClientOptionsManager.OPTIONS;

public class OptionsPlayChannelHandler implements ClientPlayNetworking.PlayChannelHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        ResourceLocation option = buf.readResourceLocation();
        var value = OPTIONS.get(option);
        if(value != null)
            value.getA().accept(client, buf);
        else
            LOGGER.warn("Failed to change option: {}", option);

    }
}
