package io.github.maheevil.nodoubleslab.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin implements IOptionGetter {
    @Shadow
    @Final
    @Mutable
    public KeyMapping[] keyMappings;

    public KeyMapping noDoubleSlabToggle;

    @SuppressWarnings("NoTranslation")
    @Inject(method = "<init>", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/client/Options;load()V"))
    private void initKeys(CallbackInfo ci) {
        noDoubleSlabToggle = new KeyMapping("NoDoubleSlabPlacement Toggle", GLFW.GLFW_KEY_U,"NoDoubleSlabPlacement");
        keyMappings = ArrayUtils.add(keyMappings,noDoubleSlabToggle);
    }

    @Override
    public KeyMapping getNoSlabKey(){
        return noDoubleSlabToggle;
    }
}
