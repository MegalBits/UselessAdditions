package net.megal.uselessadditions.magic;

import net.megal.uselessadditions.UAdd;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Spell {
    private final NbtCompound nbt = new NbtCompound();
    protected final String UNKNOWN_KEY = "spell.unknown";

    public Spell(Instruction instruction, @Nullable String translationKey, @Nullable String name) {
        NbtList nbtList = new NbtList();
        nbtList.add(instruction.writeToNbt());
        this.nbt.put("Instructions", nbtList);
        if (translationKey != null) this.nbt.putString("TranslationKey", translationKey);
        if (name != null) this.nbt.putString("Name", name);
        if (name == null && translationKey == null) this.nbt.putString("TranslationKey", UNKNOWN_KEY);
    }

    public Spell(List<Instruction> instructions, @Nullable String translationKey, @Nullable String name) {
        NbtList nbtList = new NbtList();
        for (Instruction instruction : instructions) {
            nbtList.add(instruction.writeToNbt());
        }
        this.nbt.put("Instructions", nbtList);
        if (translationKey != null) this.nbt.putString("TranslationKey", translationKey);
        if (name != null) this.nbt.putString("Name", name);
        if (name == null && translationKey == null) this.nbt.putString("TranslationKey", UNKNOWN_KEY);
    }

    public void runRightClickInstructions(PlayerEntity player, @Nullable Entity other, World world) {
        if (!this.nbt.contains("Instructions")) return;
        for (NbtElement instructionNbt : this.nbt.getList("Instructions", NbtElement.COMPOUND_TYPE)) {
            if (other != null) Instruction.readFromNbt((NbtCompound) instructionNbt).runRightClick(player, other, world);
            else Instruction.readFromNbt((NbtCompound) instructionNbt).runRightClick(player, world);
        }
    }

    public NbtCompound writeToNbt() {
        return nbt;
    }

    public static class Instruction {
        //Damage
        private double damage;
        //Projectile(s)
        private boolean hasProjectile = false;
        private int projectileCount;
        private double projectileDamage;
        private double projectileVelocity;
        private double projectileInaccuracy;
        //Block(s)
        @Nullable
        private BlockState state;
        private Box box;
        private boolean replaceSolid;
        private boolean replaceNonSolid;
        public Instruction setDamage(double damage) {
            this.damage = damage;
            return this;
        }
        public Instruction setProjectile(int count, double damage, double velocity, double inaccuracy) {
            this.hasProjectile = true;
            this.projectileCount = count;
            this.projectileDamage = damage;
            this.projectileVelocity = velocity;
            this.projectileInaccuracy = inaccuracy;
            return this;
        }
        public Instruction setProjectile(int count, double damage) {
            return setProjectile(count, damage, 1.25d, 1);
        }
        public Instruction setProjectile(double damage) {
            return setProjectile(1, damage);
        }
        public Instruction setProjectile() {
            return setProjectile(1);
        }
        public Instruction setBlocks(BlockState state, Box box, boolean replaceSolid, boolean replaceNonSolid) {
            this.state = state;
            this.box = box;
            this.replaceSolid = replaceSolid;
            this.replaceNonSolid = replaceNonSolid;
            return this;
        }

        //
        public void runRightClick(PlayerEntity player, World world) {
            if (!world.isClient()) {
                if (this.hasProjectile) {
                    for (int i = 0; i < this.projectileCount; i++) {
                        ArrowEntity arrow = new ArrowEntity(world, player);
                        arrow.setVelocity(player, player.getPitch(), player.getYaw(), 0, (float) projectileVelocity, (float) projectileInaccuracy);
                        arrow.setDamage(projectileDamage);
                        arrow.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        world.spawnEntity(arrow);
                    }
                }
            }
        }

        public void runRightClick(PlayerEntity player, Entity other, World world) {
            if(!world.isClient()) {
                if (this.damage > 0) {
                    other.damage(player.getDamageSources().create(DamageTypes.MAGIC, player), (float) this.damage);
                }
            }
            runRightClick(player, world);
        }

        //Put into nbt so that the instruction can be saved onto an item
        public NbtCompound writeToNbt() {
            NbtCompound nbt = new NbtCompound();
            //Damage
            nbt.putDouble("Damage", this.damage);
            //Projectiles
            if (this.hasProjectile) {
                NbtCompound projectileData = new NbtCompound();
                projectileData.putInt("Count", this.projectileCount);
                projectileData.putDouble("Damage", this.projectileDamage);
                projectileData.putDouble("Velocity", this.projectileVelocity);
                projectileData.putDouble("Accuracy", this.projectileInaccuracy);

                nbt.put("Projectile", projectileData);
            }

            return nbt;
        }
        //Read the nbt, so we actually know what the instruction is
        public static Instruction readFromNbt(NbtCompound nbt) {
            Instruction instruction = new Instruction();
            instruction.setDamage(nbt.getDouble("Damage"));
            if (nbt.contains("Projectile")) {
                NbtCompound projectileNbt = nbt.getCompound("Projectile");
                instruction.setProjectile(
                        projectileNbt.getInt("Count"),
                        projectileNbt.getDouble("Damage"),
                        projectileNbt.getDouble("Velocity"),
                        projectileNbt.getDouble("Accuracy")
                );
            }
            return instruction;
        }
    }
}
