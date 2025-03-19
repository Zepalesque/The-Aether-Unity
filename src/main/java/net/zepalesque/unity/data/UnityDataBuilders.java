package net.zepalesque.unity.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.nio.file.Path;

public class UnityDataBuilders {
    
    protected static <T extends DataProvider> void createPack(GatherDataEvent event, PackType type, PackOutput output, FactoryWithID<?> factory, String packid, String modid) {
        Path path = output.getOutputFolder().resolve("packs").resolve(modid).resolve(type == PackType.CLIENT_RESOURCES ? "resource" : "data");
        DataGenerator generator = event.getGenerator();
        
        DataGenerator.PackGenerator pack = generator.new PackGenerator(event.includeServer(), packid, new PackOutput(path.resolve(packid)));
        pack.addProvider(factory.asFactory(modid));
    }
    
    protected interface FactoryWithID<T extends DataProvider> {
        
        T create(PackOutput out, String modid);
        
        default DataProvider.Factory<T> asFactory(String modid) {
            return output -> this.create(output, modid);
        }
    }
}
