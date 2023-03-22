package io.github.maheevil.nodoubleslab.mixin;

import io.github.maheevil.nodoubleslab.NoDoubleSlabPlacementMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Final public Options options;

    @Inject(
            method = "handleKeybinds",
            at = @At("RETURN")
    )
    private void handleKeybinds$NoDoubleSlabPlacement(CallbackInfo callbackInfo){
        while(((IOptionGetter) options).getNoSlabKey().consumeClick()){
            NoDoubleSlabPlacementMod.setToggleValue(
                    !NoDoubleSlabPlacementMod.getToggleValue())
            ;
        }
    }
}
