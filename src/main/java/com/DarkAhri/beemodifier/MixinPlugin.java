package com.DarkAhri.beemodifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class MixinPlugin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.beemodifier.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();
        mixins.add("MixinQueenWorkTick");
        return mixins;
    }
}
