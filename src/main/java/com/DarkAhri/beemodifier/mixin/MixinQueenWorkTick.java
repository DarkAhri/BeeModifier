package com.DarkAhri.beemodifier.mixin;

import com.DarkAhri.beemodifier.config.BeeModifierConfig;
import forestry.api.apiculture.IBee;
import forestry.apiculture.BeekeepingLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BeekeepingLogic.class, remap = false)
public class MixinQueenWorkTick {

    @Shadow
    private int queenWorkCycleThrottle;

    @Inject(method = "queenWorkTick", at = @At("TAIL"), remap = false)
    private void queenWorkTick(IBee queen, CallbackInfo ci) {
        queenWorkCycleThrottle += BeeModifierConfig.queenWorkCycleThrottleIncrement;
    }
}
