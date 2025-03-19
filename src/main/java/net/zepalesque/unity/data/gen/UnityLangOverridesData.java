package net.zepalesque.unity.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.zepalesque.unity.data.prov.ConcatenatedLanguageProvider;
import net.zepalesque.unity.data.prov.UnityLanguageProvider;

public class UnityLangOverridesData extends ConcatenatedLanguageProvider<UnityLanguageProvider> {
    public UnityLangOverridesData(PackOutput output, String baseModid) {
        super(output, baseModid, UnityLanguageProvider.UnityLanguagePackGen::new);
    }
    
    @Override
    public void addTranslations() {
        UnityLanguageProvider aether = this.get("aether");
        aether.addBlock(AetherBlocks.PILLAR, "Valkyrie Pillar");
        aether.addLore(AetherBlocks.PILLAR, "A pretty white pillar made from Valkyrie Clay. These spawn all around silver dungeons and are excellent for building.");
        aether.addBlock(AetherBlocks.PILLAR_TOP, "Carved Valkyrie Pillar");
        aether.addLore(AetherBlocks.PILLAR_TOP, "A decorative top piece for Valkyrie Pillars. They look excellent and are great for building.");
    }
}
