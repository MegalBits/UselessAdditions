package net.megal.sound;

import net.megal.UAdd;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class USounds {
    public static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, new Identifier(UAdd.ID, id), SoundEvent.of(new Identifier(UAdd.ID, id)));
    }

    public static void loadStuff() {}
}
