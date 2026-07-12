package net.ccbluex.liquidbounce.injection.forge.mixins.block;

import java.util.List;
import net.ccbluex.liquidbounce.event.BlockBBEvent;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.features.module.modules.combat.Criticals;
import net.ccbluex.liquidbounce.features.module.modules.exploit.GhostHand;
import net.ccbluex.liquidbounce.features.module.modules.player.NoFall;
import net.ccbluex.liquidbounce.features.module.modules.render.XRay;
import net.ccbluex.liquidbounce.features.module.modules.world.NoSlowBreak;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Block.class})
@SideOnly(Side.CLIENT)
/* loaded from: target.jar:net/ccbluex/liquidbounce/injection/forge/mixins/block/MixinBlock.class */
public abstract class MixinBlock {

    @Shadow
    @Final
    protected BlockState field_176227_L;

    @Shadow
    public abstract AxisAlignedBB func_180640_a(World world, BlockPos blockPos, IBlockState iBlockState);

    @Shadow
    public abstract void func_149676_a(float f, float f2, float f3, float f4, float f5, float f6);

    @Shadow
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return null;
    }

    @Overwrite
    public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        BlockBBEvent blockBBEvent = new BlockBBEvent(pos, this.field_176227_L.func_177622_c(), func_180640_a(worldIn, pos, state));
        EventManager.INSTANCE.call(blockBBEvent);
        AxisAlignedBB axisalignedbb = blockBBEvent.getBoundingBox();
        if (axisalignedbb == null || !mask.func_72326_a(axisalignedbb)) {
            return;
        }
        list.add(axisalignedbb);
    }

    @Inject(method = {"shouldSideBeRendered"}, at = {@At("HEAD")}, cancellable = true)
    private void shouldSideBeRendered(IBlockAccess p_shouldSideBeRendered_1_, BlockPos p_shouldSideBeRendered_2_, EnumFacing p_shouldSideBeRendered_3_, CallbackInfoReturnable<Boolean> cir) {
        if (XRay.INSTANCE.handleEvents()) {
            cir.setReturnValue(Boolean.valueOf(XRay.INSTANCE.getXrayBlocks().contains((Block) this)));
        }
    }

    @Inject(method = {"isCollidable"}, at = {@At("HEAD")}, cancellable = true)
    private void isCollidable(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        GhostHand ghostHand = GhostHand.INSTANCE;
        if (ghostHand.handleEvents() && ghostHand.getBlock() != Block.func_149682_b((Block) this)) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }

    @Inject(method = {"getAmbientOcclusionLightValue"}, at = {@At("HEAD")}, cancellable = true)
    private void getAmbientOcclusionLightValue(CallbackInfoReturnable<Float> cir) {
        if (XRay.INSTANCE.handleEvents()) {
            cir.setReturnValue(Float.valueOf(1.0f));
        }
    }

    @Inject(method = {"getPlayerRelativeBlockHardness"}, at = {@At("RETURN")}, cancellable = true)
    public void modifyBreakSpeed(EntityPlayer playerIn, World worldIn, BlockPos pos, CallbackInfoReturnable<Float> callbackInfo) {
        float f = callbackInfo.getReturnValue().floatValue();
        NoSlowBreak noSlowBreak = NoSlowBreak.INSTANCE;
        if (noSlowBreak.handleEvents()) {
            if (noSlowBreak.getWater() && playerIn.func_70055_a(Material.field_151586_h) && !EnchantmentHelper.func_77510_g(playerIn)) {
                f *= 5.0f;
            }
            if (noSlowBreak.getAir() && !playerIn.field_70122_E) {
                f *= 5.0f;
            }
        } else if (playerIn.field_70122_E) {
            NoFall noFall = NoFall.INSTANCE;
            Criticals criticals = Criticals.INSTANCE;
            if ((noFall.handleEvents() && noFall.getMode().equals("NoGround")) || (criticals.handleEvents() && criticals.getMode().equals("NoGround"))) {
                f /= 5.0f;
            }
        }
        callbackInfo.setReturnValue(Float.valueOf(f));
    }
}
