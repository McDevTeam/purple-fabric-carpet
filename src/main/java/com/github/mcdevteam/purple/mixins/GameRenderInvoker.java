package com.github.mcdevteam.purple.mixins;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface GameRenderInvoker {
	@Invoker(value = "loadEffect")
	void invokeLoadEffect(ResourceLocation resloc);
}