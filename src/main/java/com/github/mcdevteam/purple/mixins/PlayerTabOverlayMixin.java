package com.github.mcdevteam.purple.mixins;

import com.github.mcdevteam.purple.PurpleCarpetSettings;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerTabOverlay.class)
public abstract class PlayerTabOverlayMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;isEntityUpsideDown(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    boolean render(LivingEntity livingEntity) {
        return PurpleCarpetSettings.upsideDownEntities;
    }
}
