package com.github.mcdevteam.purple.mixins;

import com.github.mcdevteam.purple.PurpleCarpetSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Options;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {
    @Shadow @Final
    private Options options;

    /**
     * @author BisUmTo (and Gnottero)
     * @reason Carpet overrides the default keybinds
     */
    @Overwrite
    @Override
    public void tick(boolean slowDown, float f) {

        List<Boolean> lb = new ArrayList<>();
        lb.add(this.options.keyUp.isDown());
        lb.add(this.options.keyRight.isDown());
        lb.add(this.options.keyDown.isDown());
        lb.add(this.options.keyLeft.isDown());

        int i = 0;
        this.up = lb.get((PurpleCarpetSettings.invertControlsDirection + i++)%4);
        this.right = lb.get((PurpleCarpetSettings.invertControlsDirection + i++)%4);
        this.down = lb.get((PurpleCarpetSettings.invertControlsDirection + i++)%4);
        this.left = lb.get((PurpleCarpetSettings.invertControlsDirection + i)%4);

        this.forwardImpulse = this.up == this.down ? 0.0F : (this.up ? 1.0F : -1.0F);
        this.leftImpulse = this.left == this.right ? 0.0F : (this.left ? 1.0F : -1.0F);
        this.jumping = PurpleCarpetSettings.upsideDownEntities ? this.options.keyShift.isDown() : this.options.keyJump.isDown();
        this.shiftKeyDown = PurpleCarpetSettings.upsideDownEntities ? this.options.keyJump.isDown() : this.options.keyShift.isDown();
        if (slowDown) {
            this.leftImpulse = (float)((double)this.leftImpulse * 0.3D);
            this.forwardImpulse = (float)((double)this.forwardImpulse * 0.3D);
        }
    }
}
