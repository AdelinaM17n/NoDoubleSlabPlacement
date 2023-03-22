package io.github.maheevil.nodoubleslab.mixin;

import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {
    @Shadow @Final private static Map<String, Integer> CATEGORY_SORT_ORDER;

    @Inject(at = @At("RETURN"), method = "<clinit>")
    private static void initKeyMappings(CallbackInfo ci) {
        CATEGORY_SORT_ORDER.put("NoDoubleSlabPlacement", CATEGORY_SORT_ORDER.size() + 1);
    }
}
