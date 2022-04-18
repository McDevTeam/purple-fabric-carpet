package com.github.mcdevteam.purple;


import carpet.script.CarpetContext;
import carpet.script.Expression;
import carpet.script.exception.InternalExpressionException;
import carpet.script.value.EntityValue;
import carpet.script.value.Value;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.GameEvent;

public class PurpleCarpetScarpet {
    public static void apply(Expression expression) {
        expression.addContextFunction("apply_shader", -1, (c, t, lv) ->
        {
            if (lv.size() < 2) throw new InternalExpressionException("'apply_shader' requires at least two arguments");
            Value playerValue = lv.get(0);
            ServerPlayer player = EntityValue.getPlayerByValue(((CarpetContext) c).s.getServer(), playerValue);
            if (player == null)
                throw new InternalExpressionException("'apply_shader' requires a valid online player as the first argument.");
            ResourceLocation shaderIdentifier = null;
            if (lv.size() == 2) {
                shaderIdentifier = new ResourceLocation("shaders/post/" + lv.get(1).getString() + ".json");
            } else if (lv.size() == 3) {
                shaderIdentifier = new ResourceLocation(lv.get(1).getString(), "shaders/post/" + lv.get(2).getString() + ".json");
            }
            player.gameEvent(GameEvent.ENTITY_SHAKE);
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeResourceLocation(shaderIdentifier);

            ServerPlayNetworking.send(player, new ResourceLocation("carpet", "shaders"), buf);
            return Value.NULL;
        });
    }
}
