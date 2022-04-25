package com.github.mcdevteam.purple;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetExpression;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public class PurpleCarpetExtension implements ModInitializer, CarpetExtension {
    public static final String MODID = "purple-carpet";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void scarpetApi(CarpetExpression expression) {
        PurpleCarpetScarpet.apply(expression.getExpr());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(PurpleCarpetSettings.class);
        // CarpetServer.settingsManager.printAllRulesToLog(PurpleCarpetSettings.PURPLE);
    }

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(this);

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("carpet", "noise"), (server, player, handler, buf, responseSender) -> {
            double noise = buf.readDouble();
            if (!player.level.isClientSide
                    && PurpleCarpetSettings.microphoneEmitVibration >= 0
                    && noise > PurpleCarpetSettings.microphoneEmitVibration) {
                server.execute(() -> {
                    Vec3 pos = player.position().add(0, player.getEyeHeight(), 0);
                    player.level.gameEvent(player, GameEvent.ENTITY_SHAKE, pos);
                });
            }
            if(PurpleCarpetSettings.microphoneNoiseLogger) {
                String message = String.format("Microphone Noise: %.02f", noise);
                PurpleCarpetExtension.LOGGER.log(Level.DEBUG, message);
                player.displayClientMessage(new TextComponent(message), true);
            }
        });

        LOGGER.info("Purple Carpet Extension loaded successfully");
    }
}
