package jobicade.toolsdoneright.item;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import jobicade.toolsdoneright.Items;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ItemSwordEnd extends ItemSword {
    private static final String CAPTURE_KEY = "Capture";
    private static Method getExperiencePoints;

    public ItemSwordEnd() {
        super(Items.END);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
        if(hasCapture(stack)) {
            EntityLivingBase capture = getCapture(stack, world);
            tooltip.add(I18n.format("toolsdoneright.captured", capture.getDisplayName().getFormattedText()));
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        double x = pos.getX() + hitX;
        double y = pos.getY() + hitY;
        double z = pos.getZ() + hitZ;

        if(hasCapture(stack)) {
            EntityLivingBase capture = getCapture(stack, world);
            capture.setPosition(x, y, z);

            if(canPlaceEntityAt(world, capture, x, y, z)) {
                if(!world.isRemote) {
                    capture.setVelocity(0, 0, 0);
                    world.spawnEntity(capture);
                }
                clearCapture(stack);

                particleEffect(capture, EnumParticleTypes.PORTAL);
                world.playSound(player, x, y, z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return EnumActionResult.SUCCESS;
        } else {
            particleEffect(world, player.getRNG(), x - 0.5, y - 0.5, z - 0.5, 1.0, 1.0, 1.0, EnumParticleTypes.SMOKE_NORMAL);
            return EnumActionResult.PASS;
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        // Modify even in creative mode
        stack = player.getHeldItem(hand);

        if(!hasCapture(stack)) {
            setCapture(stack, target);
            target.world.removeEntity(target);
            damageItemExp(stack, target, player);

            particleEffect(target, EnumParticleTypes.PORTAL);
            target.world.playSound(player, target.posX, target.posY, target.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return true;
        } else {
            particleEffect(target, EnumParticleTypes.SMOKE_NORMAL);
            return false;
        }
    }

    /**
     * Deals damage to the item proportional to the amount of experience dropped by the target.
     */
    private void damageItemExp(ItemStack stack, EntityLivingBase target, EntityPlayer player) {
        if(getExperiencePoints == null) {
            getExperiencePoints = ReflectionHelper.findMethod(EntityLivingBase.class,
                    "getExperiencePoints", "func_70693_a", EntityPlayer.class);
        }

        int experiencePoints;
        try {
            experiencePoints = (int)getExperiencePoints.invoke(target, player);
        } catch(IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        experiencePoints = ForgeEventFactory.getExperienceDrop(target, player, experiencePoints);

        stack.damageItem(experiencePoints, player);
    }

    /**
     * Checks if an entity can fit fully inside an empty space.
     *
     * @see Entity#isEntityInsideOpaqueBlock()
     */
    private static boolean canPlaceEntityAt(World world, Entity entity, double x, double y, double z) {
        double hw = entity.width / 2.0;

        BlockPos min = new BlockPos(Math.floor(x - hw), Math.floor(y), Math.floor(z - hw));
        BlockPos max = new BlockPos(Math.floor(x + hw), Math.floor(y + entity.height), Math.floor(z + hw));

        for(BlockPos pos : BlockPos.getAllInBox(min, max)) {
            if(world.getBlockState(pos).causesSuffocation()) {
                return false;
            }
        }
        return true;
    }

    private static void particleEffect(EntityLivingBase entity, EnumParticleTypes particleType) {
        particleEffect(entity.world, entity.getRNG(),
                entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2,
                entity.width, entity.height, entity.width, particleType);
    }

    private static void particleEffect(World world, Random random,
            double minX, double minY, double minZ, double width, double height, double depth,
            EnumParticleTypes particleType) {
        for(int i = 0; i < 16; i++) {
            double x = minX + random.nextDouble() * width;
            double y = minY + random.nextDouble() * height;
            double z = minZ + random.nextDouble() * depth;

            double dx = (random.nextDouble() - 0.5) * 0.2;
            double dy = (random.nextDouble() - 0.5) * 0.2;
            double dz = (random.nextDouble() - 0.5) * 0.2;

            world.spawnParticle(particleType, x, y, z, dx, dy, dz);
        }
    }

    // NBT utility methods

    public static boolean hasCapture(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(CAPTURE_KEY);
    }

    public static EntityLivingBase getCapture(ItemStack stack, World world) {
        return (EntityLivingBase)EntityList.createEntityFromNBT(
                stack.getTagCompound().getCompoundTag(CAPTURE_KEY), world);
    }

    public static void setCapture(ItemStack stack, EntityLivingBase capture) {
        NBTTagCompound rootTag = putTagIfAbsent(stack);
        rootTag.setTag(CAPTURE_KEY, capture.serializeNBT());
    }

    public static void clearCapture(ItemStack stack) {
        stack.getTagCompound().removeTag(CAPTURE_KEY);
    }

    public static NBTTagCompound putTagIfAbsent(ItemStack stack) {
        if(stack.hasTagCompound()) {
            return stack.getTagCompound();
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            stack.setTagCompound(tag);
            return tag;
        }
    }
}
