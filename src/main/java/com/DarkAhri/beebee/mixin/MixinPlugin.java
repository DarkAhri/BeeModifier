package com.DarkAhri.beebee.mixin;

import com.DarkAhri.beebee.Tags;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.Files.walk;

public class MixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getRefMapperConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unused")
    @Override

    public List<String> getMixins() {
        loadJarOf(MixinPlugin::hasTrait, "Forestry");
        List<String> mixins = new ArrayList<>();
        mixins.add("MixinQueenWorkTick");
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    public static boolean hasTrait(Path p) {
        // System.err.println(p);
        try (ZipInputStream zs = new ZipInputStream(Files.newInputStream(p))) {

            ZipEntry entry = null;
            while ((entry = zs.getNextEntry()) != null) {
                String entryName = entry.getName();

                /*
                 * if(p.toString().contains("NotEnoughEnergistics-1.4.2.jar")){
                 * System.err.println(p);
                 * }
                 */
                boolean bingo = false;
                if (entryName.contains("forestry/apiculture/BeekeepingLogic.class")) {
                    bingo = true;

                }

                zs.closeEntry();
                if (bingo) {
                    return bingo;
                }

            }

        } catch (Exception e) {
            return false;
        }
        return false;

    }

    @SuppressWarnings("deprecation")
    private boolean loadJarOf(final Predicate<Path> mod, String trace) {
        try {
            File jar = findJarOf(mod);
            if (jar == null) {
                LOG.info("Jar not found for " + trace);
                return false;
            }

            LOG.info("Attempting to add " + jar + " to the URL Class Path");
            if (!jar.exists()) {
                throw new FileNotFoundException(jar.toString());
            }
            MinecraftURLClassPath.addJar(jar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final Logger LOG = LogManager.getLogger(Tags.VERSION + "Mixin");
    private static final Path MODS_DIRECTORY_PATH = new File(Launch.minecraftHome, "mods/").toPath();

    public static File findJarOf(final Predicate<Path> mod) {
        try {
            return walk(MODS_DIRECTORY_PATH).filter(mod)
                .map(Path::toFile)
                .findFirst()
                .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
