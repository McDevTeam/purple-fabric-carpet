package com.github.mcdevteam.purple.utils;

import carpet.script.value.NumericValue;
import carpet.script.value.Value;
import net.minecraft.client.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class ClientOptionsManager {
    public static final HashMap<ResourceLocation, Tuple<BiConsumer<Minecraft, FriendlyByteBuf>, BiConsumer<Value, FriendlyByteBuf>>> OPTIONS;

    static {
        OPTIONS = new HashMap<>();

        OPTIONS.put(new ResourceLocation("dark_mojang_studios_background"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.darkMojangStudiosBackground().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("hide_lightning_flash"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.hideLightningFlash().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("sensitivity"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.sensitivity().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("render_distance"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.renderDistance().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("simulation_distance"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.simulationDistance().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("entity_distance_scaling"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.entityDistanceScaling().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("framerate_limit"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.framerateLimit().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("cloud_status"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    CloudStatus value = switch (index) {
                        case 1 -> CloudStatus.FAST;
                        case 2 -> CloudStatus.FANCY;
                        default -> CloudStatus.OFF;
                    };
                    client.execute(() -> client.options.cloudStatus().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("graphics_mode"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    GraphicsStatus value = switch (index) {
                        case 1 -> GraphicsStatus.FANCY;
                        case 2 -> GraphicsStatus.FABULOUS;
                        default -> GraphicsStatus.FAST;
                    };
                    client.execute(() -> client.options.graphicsMode().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("ambient_occlusion"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    AmbientOcclusionStatus value = switch (index) {
                        case 1 -> AmbientOcclusionStatus.MIN;
                        case 2 -> AmbientOcclusionStatus.MAX;
                        default -> AmbientOcclusionStatus.OFF;
                    };
                    client.execute(() -> client.options.ambientOcclusion().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("prioritize_chunk_updates"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    PrioritizeChunkUpdates value = switch (index) {
                        case 1 -> PrioritizeChunkUpdates.PLAYER_AFFECTED;
                        case 2 -> PrioritizeChunkUpdates.NEARBY;
                        default -> PrioritizeChunkUpdates.NONE;
                    };
                    client.execute(() -> client.options.prioritizeChunkUpdates().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("chat_visibility"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    ChatVisiblity value = switch (index) {
                        case 1 -> ChatVisiblity.SYSTEM;
                        case 2 -> ChatVisiblity.HIDDEN;
                        default -> ChatVisiblity.FULL;
                    };
                    client.execute(() -> client.options.chatVisibility().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("chat_opacity"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatOpacity().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("chat_line_spacing"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatLineSpacing().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("text_background_opacity"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.textBackgroundOpacity().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("main_hand"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    HumanoidArm value = switch (index) {
                        case 1 -> HumanoidArm.RIGHT;
                        default -> HumanoidArm.LEFT;
                    };
                    client.execute(() -> client.options.mainHand().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("chat_scale"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatScale().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("chat_width"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatWidth().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("chat_height_unfocused"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatHeightUnfocused().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("chat_height_focused"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatHeightFocused().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("chat_delay"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.chatDelay().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("mipmap_levels"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.mipmapLevels().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("attack_indicator"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    AttackIndicatorStatus value = switch (index) {
                        case 1 -> AttackIndicatorStatus.CROSSHAIR;
                        case 2 -> AttackIndicatorStatus.HOTBAR;
                        default -> AttackIndicatorStatus.OFF;
                    };
                    client.execute(() -> client.options.attackIndicator().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("biome_blend_radius"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.biomeBlendRadius().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("mouse_wheel_sensitivity"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.mouseWheelSensitivity().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("raw_mouse_input"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.rawMouseInput().set(value));
                },
                (value, buf) -> {
                }
        ));

        OPTIONS.put(new ResourceLocation("auto_jump"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.autoJump().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("auto_suggestions"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.autoSuggestions().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("chat_colors"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.chatColors().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("chat_links"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.chatLinks().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("chat_links_prompt"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.chatLinksPrompt().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("enable_vsync"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.enableVsync().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("entity_shadows"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.entityShadows().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("force_unicode_font"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.forceUnicodeFont().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("invert_y_mouse"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.invertYMouse().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("discrete_mouse_scroll"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.discreteMouseScroll().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("realms_notifications"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.realmsNotifications().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("allow_server_listing"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.allowServerListing().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("reduced_debug_info"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.reducedDebugInfo().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("show_subtitles"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.showSubtitles().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("directional_audio"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.directionalAudio().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("background_for_chat_only"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.backgroundForChatOnly().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("touchscreen"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.touchscreen().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("fullscreen"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.fullscreen().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("bob_view"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.bobView().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("toggle_crouch"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.toggleCrouch().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("toggle_sprint"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.toggleSprint().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("hide_matched_names"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.hideMatchedNames().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("show_autosave_indicator"), new Tuple<>(
                (client, buf) -> {
                    boolean value = buf.readBoolean();
                    client.execute(() -> client.options.showAutosaveIndicator().set(value));
                },
                (value, buf) -> buf.writeBoolean(value.getBoolean())
        ));

        OPTIONS.put(new ResourceLocation("fov"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.fov().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("screen_effect_scale"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.screenEffectScale().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("fov_effect_scale"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.fovEffectScale().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("darkness_effect_scale"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.darknessEffectScale().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("gamma"), new Tuple<>(
                (client, buf) -> {
                    double value = buf.readDouble();
                    client.execute(() -> client.options.gamma().set(value));
                },
                (value, buf) -> buf.writeDouble(NumericValue.asNumber(value).getDouble())
        ));

        OPTIONS.put(new ResourceLocation("gui_scale"), new Tuple<>(
                (client, buf) -> {
                    int value = buf.readInt();
                    client.execute(() -> client.options.guiScale().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("particles"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    ParticleStatus value = switch (index) {
                        case 1 -> ParticleStatus.DECREASED;
                        case 2 -> ParticleStatus.MINIMAL;
                        default -> ParticleStatus.ALL;
                    };
                    client.execute(() -> client.options.particles().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("narrator"), new Tuple<>(
                (client, buf) -> {
                    int index = buf.readInt();
                    NarratorStatus value = switch (index) {
                        case 1 -> NarratorStatus.ALL;
                        case 2 -> NarratorStatus.CHAT;
                        case 3 -> NarratorStatus.SYSTEM;
                        default -> NarratorStatus.OFF;
                    };
                    client.execute(() -> client.options.narrator().set(value));
                },
                (value, buf) -> buf.writeInt(NumericValue.asNumber(value).getInt())
        ));

        OPTIONS.put(new ResourceLocation("sound_device"), new Tuple<>(
                (client, buf) -> {
                    String value = buf.readUtf(Short.MAX_VALUE);
                    client.execute(() -> client.options.soundDevice().set(value));
                },
                (value, buf) -> buf.writeUtf(value.getString())
        ));
    }
}
