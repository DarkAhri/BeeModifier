package com.DarkAhri.DarkModifier.mixin;

import ic2.core.crop.TileEntityCrop;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(TileEntityCrop.class)
public class MixinTileEntityCrop {

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 256), remap = false)
    private static int modifyTickRateInStaticInit(int original) {
        return 16; // 改成你想要的数值，比如64（原版速度的4倍）
    }
}
