package com.github.mcdevteam.purple.mixins;

import com.github.mcdevteam.purple.PurpleCarpetSettings;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "canCollideWith",at = @At("HEAD"),cancellable = true)
    void solidCollision(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if(PurpleCarpetSettings.solidCollisionEntity) cir.setReturnValue(true);
    }
    @Inject(method = "isPushable",at = @At("HEAD"),cancellable = true)
    void pushable(CallbackInfoReturnable<Boolean> cir){
        if(PurpleCarpetSettings.solidCollisionEntity) cir.setReturnValue(true);
    }
}
