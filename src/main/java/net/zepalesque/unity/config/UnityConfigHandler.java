package net.zepalesque.unity.config;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.zepalesque.unity.Unity;

import java.nio.file.Files;

// See https://github.com/TelepathicGrunt/Bumblezone/blob/6bbcb628672a77cfa7a2648be9b4d2428d1eeeb7/neoforge/src/main/java/com/telepathicgrunt/the_bumblezone/configs/neoforge/BzConfigHandler.java#L26
public class UnityConfigHandler {

    public static void setup(ModContainer mod, IEventBus bus) {

        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(Unity.MODID));
            mod.registerConfig(ModConfig.Type.CLIENT, UnityConfig.CLIENT_SPEC, Unity.MODID + "/client.toml");
            mod.registerConfig(ModConfig.Type.COMMON, UnityConfig.COMMON_SPEC, Unity.MODID + "/common.toml");
            mod.registerConfig(ModConfig.Type.SERVER, UnityConfig.SERVER_SPEC, Unity.MODID + "/server.toml");
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create Aether: Unity config files: ", e);
        }
    }
}
