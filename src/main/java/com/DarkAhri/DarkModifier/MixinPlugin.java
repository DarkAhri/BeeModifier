package com.DarkAhri.DarkModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class MixinPlugin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.darkmodifier.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();
        if (loadedMods.contains("Forestry")) {
            mixins.add("MixinQueenWorkTick");
        }
        if (loadedMods.contains("IC2")) {
            mixins.add("MixinTileEntityCrop");
        }
        if (loadedMods.contains("cropsnh")) {
            mixins.add("MixinTileEntityCropSticks");
        }
        return mixins;
    }
}
