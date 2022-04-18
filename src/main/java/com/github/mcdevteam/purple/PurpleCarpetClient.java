package com.github.mcdevteam.purple;

import com.github.mcdevteam.purple.mixins.GameRenderInvoker;
import com.github.mcdevteam.purple.voice.Microphone;
import com.github.mcdevteam.purple.voice.NoisePacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PurpleCarpetClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Microphone(), 0, 50, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(new NoisePacket(), 25, 50, TimeUnit.MILLISECONDS);

        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation("carpet", "shaders"),
                (client, handler, buf, responseSender) -> {
                    ResourceLocation shaderResLoc = buf.readResourceLocation();
                    client.execute(
                            () -> ((GameRenderInvoker) client.gameRenderer).invokeLoadEffect(shaderResLoc)
                    );
                }
        );
    }
}
