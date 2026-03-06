package net.zepalesque.unity.attachment;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.zepalesque.unity.Unity;

public class UnityDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = Unity.reg(NeoForgeRegistries.ATTACHMENT_TYPES);

}
