package net.zepalesque.unity.mixin.mixins.common.datagen;

import net.neoforged.neoforge.common.data.LanguageProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LanguageProvider.class)
public interface LangProvAccessor {
    
    @Accessor
    String getLocale();
}
