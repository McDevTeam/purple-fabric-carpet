package com.github.mcdevteam.purple;


import carpet.script.CarpetContext;
import carpet.script.Expression;
import carpet.script.exception.InternalExpressionException;
import carpet.script.value.EntityValue;
import carpet.script.value.ListValue;
import carpet.script.value.StringValue;
import carpet.script.value.Value;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.stream.Collectors;

import static carpet.script.value.NBTSerializableValue.nameFromRegistryId;
import static com.github.mcdevteam.purple.utils.ClientOptionsManager.OPTIONS;

public class PurpleCarpetScarpet {
    public static void apply(Expression expression) {
        expression.addContextFunction("client_shader", -1, (c, t, lv) -> {
            if (lv.size() < 2) throw new InternalExpressionException("'client_shader' requires at least two arguments");
            if (lv.size() > 3) throw new InternalExpressionException("'client_shader' requires at most tree arguments");
            Value playerValue = lv.get(0);
            ServerPlayer player = EntityValue.getPlayerByValue(((CarpetContext) c).s.getServer(), playerValue);
            if (player == null)
                throw new InternalExpressionException("'client_shader' requires a valid online player as the first argument.");
            ResourceLocation shaderIdentifier = null;
            if (lv.size() == 2) {
                shaderIdentifier = new ResourceLocation("shaders/post/" + lv.get(1).getString() + ".json");
            } else if (lv.size() == 3) {
                shaderIdentifier = new ResourceLocation(lv.get(1).getString(), "shaders/post/" + lv.get(2).getString() + ".json");
            }
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeResourceLocation(shaderIdentifier);
            ServerPlayNetworking.send(player, new ResourceLocation("purple_carpet", "shaders"), buf);
            return StringValue.of(nameFromRegistryId(shaderIdentifier));
        });

        expression.addContextFunction("client_option", -1, (c, t, lv) -> {
            if (lv.size() == 0) {
                List<Value> client_options = OPTIONS
                        .keySet()
                        .stream()
                        .map(r -> StringValue.of(nameFromRegistryId(r)))
                        .collect(Collectors.toList());
                return ListValue.wrap(client_options);
            }
            if (lv.size() != 3)
                throw new InternalExpressionException("'client_option' requires none or tree arguments");
            Value playerValue = lv.get(0);
            ServerPlayer player = EntityValue.getPlayerByValue(((CarpetContext) c).s.getServer(), playerValue);
            if (player == null)
                throw new InternalExpressionException("'client_option' requires a valid online player as the first argument.");

            ResourceLocation option = new ResourceLocation(lv.get(1).getString());
            Value value = lv.get(2);

            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeResourceLocation(option);
            try {
                OPTIONS.get(option).getB().accept(value, buf);
            } catch (NullPointerException e) {
                throw new InternalExpressionException("'" + option + "' is not a valid option.");
            }
            ServerPlayNetworking.send(player, new ResourceLocation("purple_carpet", "options"), buf);
            return StringValue.of(nameFromRegistryId(option));
        });
    }
}
