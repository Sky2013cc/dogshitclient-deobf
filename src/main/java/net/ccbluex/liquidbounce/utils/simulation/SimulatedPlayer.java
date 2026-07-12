package net.ccbluex.liquidbounce.utils.simulation;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.features.module.modules.movement.NoJumpDelay;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeModContainer;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.fontbox.ttf.PostScriptTable;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: SimulatedPlayer.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018�� Ø\u00012\u00020\u0001:\u0002Ø\u0001BÍ\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u0013\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u000f\u0012\u0006\u0010\u001f\u001a\u00020\u000f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010$\u001a\u00020\u000f\u0012\b\u0010%\u001a\u0004\u0018\u00010\u001a\u0012\b\u0010&\u001a\u0004\u0018\u00010'\u0012\u0006\u0010(\u001a\u00020\u000f\u0012\u0006\u0010)\u001a\u00020\u0013\u0012\u0006\u0010*\u001a\u00020\u0013\u0012\u0006\u0010+\u001a\u00020\u000f\u0012\u0006\u0010,\u001a\u00020\t\u0012\u0006\u0010-\u001a\u00020\u0013\u0012\u0006\u0010.\u001a\u00020\u0013\u0012\u0006\u0010/\u001a\u00020\t\u0012\u0006\u00100\u001a\u00020\u0013\u0012\u0006\u00101\u001a\u00020\u0013\u0012\u0006\u00102\u001a\u00020\t\u0012\u0006\u00103\u001a\u00020\u000f\u0012\u0006\u00104\u001a\u00020\u000f\u0012\u0006\u00105\u001a\u00020\u000f\u0012\u0006\u00106\u001a\u000207¢\u0006\u0004\b8\u00109J\u0006\u0010j\u001a\u00020kJ\b\u0010l\u001a\u00020kH\u0002J\u0010\u0010m\u001a\u00020k2\u0006\u0010n\u001a\u00020\u000fH\u0002J\b\u0010o\u001a\u00020kH\u0002J\b\u0010p\u001a\u00020\u000fH\u0002J\b\u0010q\u001a\u00020kH\u0002J \u0010r\u001a\u00020k2\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020\u000bH\u0002J\u0010\u0010v\u001a\u00020k2\u0006\u0010w\u001a\u00020\u000fH\u0002J \u0010x\u001a\u00020\u000f2\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020\u000bH\u0002J\u0018\u0010y\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020z2\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u0010{\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020zH\u0002J\u0018\u0010|\u001a\u00020k2\u0006\u0010e\u001a\u00020\u00132\u0006\u0010d\u001a\u00020\u0013H\u0002J\u0018\u0010}\u001a\u00020k2\u0006\u0010~\u001a\u00020\u00132\u0006\u0010\u007f\u001a\u00020\u0013H\u0002J$\u0010\u0080\u0001\u001a\u00020k2\u0007\u0010\u0081\u0001\u001a\u00020\u000b2\u0007\u0010\u0082\u0001\u001a\u00020\u000b2\u0007\u0010\u0083\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u0084\u0001\u001a\u00020\u0005H\u0002J\u0011\u0010\u0085\u0001\u001a\u00020k2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\t\u0010\u0086\u0001\u001a\u00020kH\u0002J\u0012\u0010\u0087\u0001\u001a\u00020k2\u0007\u0010\u0088\u0001\u001a\u00020\tH\u0002J\t\u0010\u0089\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u008a\u0001\u001a\u00020kH\u0002J\u0019\u0010\u008b\u0001\u001a\u00020k2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\t\u0010\u008c\u0001\u001a\u00020\u000fH\u0002J\u001c\u0010\u008d\u0001\u001a\u00020\u000f2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0002JH\u0010\u0091\u0001\u001a\u00020\u000f2\u0007\u0010\u0092\u0001\u001a\u00020\t2\u0007\u0010\u0093\u0001\u001a\u00020\t2\u0007\u0010\u0094\u0001\u001a\u00020\t2\u0007\u0010\u0095\u0001\u001a\u00020\t2\u0007\u0010\u0096\u0001\u001a\u00020\t2\u0007\u0010\u0097\u0001\u001a\u00020\t2\u0007\u0010\u0098\u0001\u001a\u00020\u000fH\u0002J\u0013\u0010\u0099\u0001\u001a\u00020k2\b\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0002J\t\u0010\u009c\u0001\u001a\u00020\u000fH\u0002J\u0007\u0010\u009d\u0001\u001a\u00020\u000fJ$\u0010\u009e\u0001\u001a\u00020k2\u0007\u0010\u009f\u0001\u001a\u00020\u00132\u0007\u0010 \u0001\u001a\u00020\u00132\u0007\u0010¡\u0001\u001a\u00020\u0013H\u0002J\t\u0010¢\u0001\u001a\u00020kH\u0002J\b\u00105\u001a\u00020\u000fH\u0002J\u0011\u0010£\u0001\u001a\u00020\u000f2\b\u0010¤\u0001\u001a\u00030¥\u0001J\u0012\u0010¦\u0001\u001a\u00030§\u00012\b\u0010¤\u0001\u001a\u00030¥\u0001J\t\u0010¨\u0001\u001a\u00020\u0013H\u0002J\t\u0010©\u0001\u001a\u00020\u000fH\u0002J\t\u0010ª\u0001\u001a\u00020kH\u0002J\t\u0010«\u0001\u001a\u00020\u000fH\u0002J\t\u0010¬\u0001\u001a\u00020\u000fH\u0002J\u0007\u0010\u00ad\u0001\u001a\u00020\u000fJ\t\u0010®\u0001\u001a\u00020kH\u0002J!\u0010¯\u0001\u001a\u00020\u000f2\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020\u000bH\u0002J\u0011\u0010°\u0001\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0016\u0010±\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050²\u00012\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010³\u0001\u001a\u0005\u0018\u00010´\u00012\u0007\u0010µ\u0001\u001a\u00020zJ\u0013\u0010¶\u0001\u001a\u00030·\u00012\u0007\u0010µ\u0001\u001a\u00020zH\u0002J\u001a\u0010¸\u0001\u001a\u00030·\u00012\u0006\u0010s\u001a\u00020\t2\u0006\u0010u\u001a\u00020\tH\u0002J\u0011\u0010¹\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020zH\u0002J\t\u0010º\u0001\u001a\u00020!H\u0002J\u001b\u0010»\u0001\u001a\u00020\u000f2\u0007\u0010¼\u0001\u001a\u00020!2\u0007\u0010½\u0001\u001a\u00020\u000fH\u0002J\u0011\u0010¾\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020zH\u0002J\u001a\u0010¾\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020z2\u0007\u0010¿\u0001\u001a\u00020\u000fH\u0002J\"\u0010À\u0001\u001a\u00020\u000f2\u0006\u0010s\u001a\u00020\t2\u0006\u0010u\u001a\u00020\t2\u0007\u0010Á\u0001\u001a\u00020\u000fH\u0002J!\u0010Â\u0001\u001a\t\u0012\u0004\u0012\u00020\u001a0²\u00012\u0007\u0010Ã\u0001\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J8\u0010Ä\u0001\u001a\t\u0012\u0004\u0012\u00020\u001a0²\u00012\u0007\u0010Ã\u0001\u001a\u00020\u001a2\u0007\u0010Å\u0001\u001a\u00020\u00052\u0014\u0010Æ\u0001\u001a\u000f\u0012\b\b��\u0012\u0004\u0018\u00010\u001a\u0018\u00010Ç\u0001H\u0002J\u001c\u0010È\u0001\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u001a2\u0007\u0010Ã\u0001\u001a\u00020\u001aH\u0002J\t\u0010É\u0001\u001a\u00020\u0013H\u0002J\u0016\u0010Ê\u0001\u001a\u00030Ë\u00012\n\u0010Ì\u0001\u001a\u0005\u0018\u00010Í\u0001H\u0002J\t\u0010Î\u0001\u001a\u00020'H\u0002J2\u0010Ï\u0001\u001a\u00020\u000f2\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u00012\u0007\u0010Ð\u0001\u001a\u00020\u001d2\b\u0010`\u001a\u0004\u0018\u00010z2\b\u0010Ã\u0001\u001a\u00030Ñ\u0001H\u0002J\t\u0010Ò\u0001\u001a\u00020kH\u0002J\u0013\u0010Ó\u0001\u001a\u00020k2\b\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0002J\u0007\u0010Ô\u0001\u001a\u00020\u000fJ\u0011\u0010Õ\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020zH\u0002J\u0011\u0010Ö\u0001\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020zH\u0002J\t\u0010×\u0001\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bF\u0010C\"\u0004\bG\u0010ER\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bH\u0010C\"\u0004\bI\u0010ER\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010\u0010\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bN\u0010K\"\u0004\bO\u0010MR\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001a\u0010\u0014\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bT\u0010C\"\u0004\bU\u0010ER\u001a\u0010\u0015\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bV\u0010C\"\u0004\bW\u0010ER\u001a\u0010\u0016\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bX\u0010C\"\u0004\bY\u0010ER\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001b\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u001e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001e\u0010K\"\u0004\bZ\u0010MR\u001a\u0010\u001f\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010K\"\u0004\b[\u0010MR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010$\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010%\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010(\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010)\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\\\u0010Q\"\u0004\b]\u0010SR\u000e\u0010*\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010+\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b+\u0010K\"\u0004\b^\u0010MR\u000e\u0010,\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010-\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010.\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010/\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u000e\u00100\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n��R\u000e\u00101\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n��R\u000e\u00102\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u001a\u00103\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b3\u0010K\"\u0004\b_\u0010MR\u000e\u00104\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u000e\u00105\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010`\u001a\u00020a8F¢\u0006\u0006\u001a\u0004\bb\u0010cR\u000e\u0010d\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010f\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010g\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bh\u0010K\"\u0004\bi\u0010M¨\u0006Ù\u0001"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/simulation/SimulatedPlayer;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "player", "Lnet/minecraft/client/entity/EntityPlayerSP;", "box", "Lnet/minecraft/util/AxisAlignedBB;", "movementInput", "Lnet/minecraft/util/MovementInput;", "jumpTicks", "", "motionZ", "", "motionY", "motionX", "inWater", "", "onGround", "isAirBorne", "rotationYaw", "", "posX", "posY", "posZ", "capabilities", "Lnet/minecraft/entity/player/PlayerCapabilities;", "ridingEntity", "Lnet/minecraft/entity/Entity;", "jumpMovementFactor", "worldObj", "Lnet/minecraft/world/World;", "isCollidedHorizontally", "isCollidedVertically", "worldBorder", "Lnet/minecraft/world/border/WorldBorder;", "chunkProvider", "Lnet/minecraft/world/chunk/IChunkProvider;", "isOutsideBorder", "riddenByEntity", "attributeMap", "Lnet/minecraft/entity/ai/attributes/BaseAttributeMap;", "isSpectator", "fallDistance", "stepHeight", "isCollided", "fire", "distanceWalkedModified", "distanceWalkedOnStepModified", "nextStepDistance", "height", "width", "fireResistance", "isInWeb", "noClip", "isSprinting", "foodStats", "Lnet/minecraft/util/FoodStats;", Constants.CTOR, "(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/util/AxisAlignedBB;Lnet/minecraft/util/MovementInput;IDDDZZZFDDDLnet/minecraft/entity/player/PlayerCapabilities;Lnet/minecraft/entity/Entity;FLnet/minecraft/world/World;ZZLnet/minecraft/world/border/WorldBorder;Lnet/minecraft/world/chunk/IChunkProvider;ZLnet/minecraft/entity/Entity;Lnet/minecraft/entity/ai/attributes/BaseAttributeMap;ZFFZIFFIFFIZZZLnet/minecraft/util/FoodStats;)V", "getBox", "()Lnet/minecraft/util/AxisAlignedBB;", "setBox", "(Lnet/minecraft/util/AxisAlignedBB;)V", "getMovementInput", "()Lnet/minecraft/util/MovementInput;", "setMovementInput", "(Lnet/minecraft/util/MovementInput;)V", "getMotionZ", "()D", "setMotionZ", "(D)V", "getMotionY", "setMotionY", "getMotionX", "setMotionX", "getInWater", "()Z", "setInWater", "(Z)V", "getOnGround", "setOnGround", "getRotationYaw", "()F", "setRotationYaw", "(F)V", "getPosX", "setPosX", "getPosY", "setPosY", "getPosZ", "setPosZ", "setCollidedHorizontally", "setCollidedVertically", "getFallDistance", "setFallDistance", "setCollided", "setInWeb", "pos", "Lnet/minecraft/util/Vec3;", "getPos", "()Lnet/minecraft/util/Vec3;", "moveForward", "moveStrafing", "isJumping", "safeWalk", "getSafeWalk", "setSafeWalk", "tick", "", "clientPlayerLivingUpdate", "playerUpdate", PostScriptTable.TAG, "livingEntityUpdate", "onEntityUpdate", "clampPositionFromEntityPlayer", "setPosition", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "z", "setSprinting", "state", "pushOutOfBlocks", "isHeadspaceFree", "Lnet/minecraft/util/BlockPos;", "isOpenBlockSpace", "playerSideMoveEntityWithHeading", "livingEntitySideMoveEntityWithHeading", "strafing", "forwards", "moveEntity", "xMotion", "yMotion", "zMotion", "getEntityBoundingBox", "setEntityBoundingBox", "setOnFireFromLava", "setFire", "seconds", "isWet", "doBlockCollisions", "updateFallState", "handleWaterMovement", "handleMaterialAcceleration", "boundingBox", "material", "Lnet/minecraft/block/material/Material;", "isAreaLoaded", "minX", "minY", "minZ", "maxX", "maxY", "maxZ", "idfk", "onEntityCollidedWithBlock", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "Lnet/minecraft/block/Block;", "canTriggerWalking", "isOnLadder", "moveFlying", "strafe", "forward", "friction", "jump", "isPotionActive", "potion", "Lnet/minecraft/potion/Potion;", "getActivePotionEffect", "Lnet/minecraft/potion/PotionEffect;", "getJumpUpwardsMotion", "isInWater", "updateLivingEntityInput", "isServerWorld", "isMovementBlocked", "isInLava", "updateAITick", "isOffsetPositionInLiquid", "isLiquidPresentInAABB", "getCollidingBoundingBoxes", "", "getBlockState", "Lnet/minecraft/block/state/IBlockState;", "blockPos", "getChunkFromBlockCoords", "Lnet/minecraft/world/chunk/Chunk;", "getChunkFromChunkCoords", "isValid", "getWorldBorder", "isInsideBorder", "border", "insideBorder", "isBlockLoaded", "check2", "isChunkLoaded", "flag", "getEntitiesWithinAABBExcludingEntity", "entity", "getEntitiesInAABBexcluding", "bb", "predicate", "Lcom/google/common/base/Predicate;", "getCollisionBox", "getAIMoveSpeed", "getEntityAttribute", "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;", "iAttribute", "Lnet/minecraft/entity/ai/attributes/IAttribute;", "getAttributeMap", "isLivingOnLadder", "world", "Lnet/minecraft/entity/EntityLivingBase;", "resetPositionToBB", "onLanded", "isSneaking", "isRainingAt", "canSeeSky", "isPushedByWater", "Companion", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/simulation/SimulatedPlayer.class */
public final class SimulatedPlayer implements MinecraftInstance {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final EntityPlayerSP player;

    @NotNull
    private AxisAlignedBB box;

    @NotNull
    private MovementInput movementInput;
    private int jumpTicks;
    private double motionZ;
    private double motionY;
    private double motionX;
    private boolean inWater;
    private boolean onGround;
    private boolean isAirBorne;
    private float rotationYaw;
    private double posX;
    private double posY;
    private double posZ;

    @NotNull
    private final PlayerCapabilities capabilities;

    @Nullable
    private final Entity ridingEntity;
    private float jumpMovementFactor;

    @NotNull
    private final World worldObj;
    private boolean isCollidedHorizontally;
    private boolean isCollidedVertically;

    @NotNull
    private final WorldBorder worldBorder;

    @NotNull
    private final IChunkProvider chunkProvider;
    private boolean isOutsideBorder;

    @Nullable
    private Entity riddenByEntity;

    @Nullable
    private BaseAttributeMap attributeMap;
    private final boolean isSpectator;
    private float fallDistance;
    private final float stepHeight;
    private boolean isCollided;
    private int fire;
    private float distanceWalkedModified;
    private float distanceWalkedOnStepModified;
    private int nextStepDistance;
    private final float height;
    private final float width;
    private final int fireResistance;
    private boolean isInWeb;
    private boolean noClip;
    private boolean isSprinting;

    @NotNull
    private final FoodStats foodStats;
    private float moveForward;
    private float moveStrafing;
    private boolean isJumping;
    private boolean safeWalk;
    private static final float SPEED_IN_AIR = 0.02f;

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    public SimulatedPlayer(@NotNull EntityPlayerSP player, @NotNull AxisAlignedBB box, @NotNull MovementInput movementInput, int jumpTicks, double motionZ, double motionY, double motionX, boolean inWater, boolean onGround, boolean isAirBorne, float rotationYaw, double posX, double posY, double posZ, @NotNull PlayerCapabilities capabilities, @Nullable Entity ridingEntity, float jumpMovementFactor, @NotNull World worldObj, boolean isCollidedHorizontally, boolean isCollidedVertically, @NotNull WorldBorder worldBorder, @NotNull IChunkProvider chunkProvider, boolean isOutsideBorder, @Nullable Entity riddenByEntity, @Nullable BaseAttributeMap attributeMap, boolean isSpectator, float fallDistance, float stepHeight, boolean isCollided, int fire, float distanceWalkedModified, float distanceWalkedOnStepModified, int nextStepDistance, float height, float width, int fireResistance, boolean isInWeb, boolean noClip, boolean isSprinting, @NotNull FoodStats foodStats) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(box, "box");
        Intrinsics.checkNotNullParameter(movementInput, "movementInput");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        Intrinsics.checkNotNullParameter(worldObj, "worldObj");
        Intrinsics.checkNotNullParameter(worldBorder, "worldBorder");
        Intrinsics.checkNotNullParameter(chunkProvider, "chunkProvider");
        Intrinsics.checkNotNullParameter(foodStats, "foodStats");
        this.player = player;
        this.box = box;
        this.movementInput = movementInput;
        this.jumpTicks = jumpTicks;
        this.motionZ = motionZ;
        this.motionY = motionY;
        this.motionX = motionX;
        this.inWater = inWater;
        this.onGround = onGround;
        this.isAirBorne = isAirBorne;
        this.rotationYaw = rotationYaw;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.capabilities = capabilities;
        this.ridingEntity = ridingEntity;
        this.jumpMovementFactor = jumpMovementFactor;
        this.worldObj = worldObj;
        this.isCollidedHorizontally = isCollidedHorizontally;
        this.isCollidedVertically = isCollidedVertically;
        this.worldBorder = worldBorder;
        this.chunkProvider = chunkProvider;
        this.isOutsideBorder = isOutsideBorder;
        this.riddenByEntity = riddenByEntity;
        this.attributeMap = attributeMap;
        this.isSpectator = isSpectator;
        this.fallDistance = fallDistance;
        this.stepHeight = stepHeight;
        this.isCollided = isCollided;
        this.fire = fire;
        this.distanceWalkedModified = distanceWalkedModified;
        this.distanceWalkedOnStepModified = distanceWalkedOnStepModified;
        this.nextStepDistance = nextStepDistance;
        this.height = height;
        this.width = width;
        this.fireResistance = fireResistance;
        this.isInWeb = isInWeb;
        this.noClip = noClip;
        this.isSprinting = isSprinting;
        this.foodStats = foodStats;
    }

    @NotNull
    public final AxisAlignedBB getBox() {
        return this.box;
    }

    public final void setBox(@NotNull AxisAlignedBB axisAlignedBB) {
        Intrinsics.checkNotNullParameter(axisAlignedBB, "<set-?>");
        this.box = axisAlignedBB;
    }

    @NotNull
    public final MovementInput getMovementInput() {
        return this.movementInput;
    }

    public final void setMovementInput(@NotNull MovementInput movementInput) {
        Intrinsics.checkNotNullParameter(movementInput, "<set-?>");
        this.movementInput = movementInput;
    }

    public final double getMotionZ() {
        return this.motionZ;
    }

    public final void setMotionZ(double d) {
        this.motionZ = d;
    }

    public final double getMotionY() {
        return this.motionY;
    }

    public final void setMotionY(double d) {
        this.motionY = d;
    }

    public final double getMotionX() {
        return this.motionX;
    }

    public final void setMotionX(double d) {
        this.motionX = d;
    }

    public final boolean getInWater() {
        return this.inWater;
    }

    public final void setInWater(boolean z) {
        this.inWater = z;
    }

    public final boolean getOnGround() {
        return this.onGround;
    }

    public final void setOnGround(boolean z) {
        this.onGround = z;
    }

    public final float getRotationYaw() {
        return this.rotationYaw;
    }

    public final void setRotationYaw(float f) {
        this.rotationYaw = f;
    }

    public final double getPosX() {
        return this.posX;
    }

    public final void setPosX(double d) {
        this.posX = d;
    }

    public final double getPosY() {
        return this.posY;
    }

    public final void setPosY(double d) {
        this.posY = d;
    }

    public final double getPosZ() {
        return this.posZ;
    }

    public final void setPosZ(double d) {
        this.posZ = d;
    }

    public final boolean isCollidedHorizontally() {
        return this.isCollidedHorizontally;
    }

    public final void setCollidedHorizontally(boolean z) {
        this.isCollidedHorizontally = z;
    }

    public final boolean isCollidedVertically() {
        return this.isCollidedVertically;
    }

    public final void setCollidedVertically(boolean z) {
        this.isCollidedVertically = z;
    }

    public final float getFallDistance() {
        return this.fallDistance;
    }

    public final void setFallDistance(float f) {
        this.fallDistance = f;
    }

    public final boolean isCollided() {
        return this.isCollided;
    }

    public final void setCollided(boolean z) {
        this.isCollided = z;
    }

    public final boolean isInWeb() {
        return this.isInWeb;
    }

    public final void setInWeb(boolean z) {
        this.isInWeb = z;
    }

    @NotNull
    public final Vec3 getPos() {
        return new Vec3(this.posX, this.posY, this.posZ);
    }

    public final boolean getSafeWalk() {
        return this.safeWalk;
    }

    public final void setSafeWalk(boolean z) {
        this.safeWalk = z;
    }

    /* compiled from: SimulatedPlayer.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0007\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/utils/simulation/SimulatedPlayer$Companion;", "", Constants.CTOR, "()V", "SPEED_IN_AIR", "", "fromClientPlayer", "Lnet/ccbluex/liquidbounce/utils/simulation/SimulatedPlayer;", com.sun.tools.internal.ws.wsdl.parser.Constants.TAG_INPUT, "Lnet/minecraft/util/MovementInput;", "createFoodStatsCopy", "Lnet/minecraft/util/FoodStats;", "player", "Lnet/minecraft/client/entity/EntityPlayerSP;", "createCapabilitiesCopy", "Lnet/minecraft/entity/player/PlayerCapabilities;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/utils/simulation/SimulatedPlayer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final SimulatedPlayer fromClientPlayer(@NotNull MovementInput input) {
            Intrinsics.checkNotNullParameter(input, "input");
            EntityPlayerSP player = MinecraftInstance.mc.field_71439_g;
            Intrinsics.checkNotNull(player);
            PlayerCapabilities capabilities = createCapabilitiesCopy(player);
            FoodStats foodStats = createFoodStatsCopy(player);
            MovementInput $this$fromClientPlayer_u24lambda_u240 = new MovementInput();
            $this$fromClientPlayer_u24lambda_u240.field_78901_c = input.field_78901_c;
            $this$fromClientPlayer_u24lambda_u240.field_78900_b = input.field_78900_b;
            $this$fromClientPlayer_u24lambda_u240.field_78902_a = input.field_78902_a;
            $this$fromClientPlayer_u24lambda_u240.field_78899_d = input.field_78899_d;
            AxisAlignedBB func_174813_aQ = player.func_174813_aQ();
            Intrinsics.checkNotNullExpressionValue(func_174813_aQ, "getEntityBoundingBox(...)");
            int i = player.field_70773_bE;
            double d = player.field_70179_y;
            double d2 = player.field_70181_x;
            double d3 = player.field_70159_w;
            boolean func_70090_H = player.func_70090_H();
            boolean z = player.field_70122_E;
            boolean z2 = player.field_70160_al;
            float f = player.field_70177_z;
            double d4 = player.field_70165_t;
            double d5 = player.field_70163_u;
            double d6 = player.field_70161_v;
            Entity entity = player.field_70154_o;
            float f2 = player.field_70747_aH;
            World worldObj = player.field_70170_p;
            Intrinsics.checkNotNullExpressionValue(worldObj, "worldObj");
            boolean z3 = player.field_70123_F;
            boolean z4 = player.field_70124_G;
            WorldBorder func_175723_af = player.field_70170_p.func_175723_af();
            Intrinsics.checkNotNullExpressionValue(func_175723_af, "getWorldBorder(...)");
            IChunkProvider func_72863_F = player.field_70170_p.func_72863_F();
            Intrinsics.checkNotNullExpressionValue(func_72863_F, "getChunkProvider(...)");
            return new SimulatedPlayer(player, func_174813_aQ, $this$fromClientPlayer_u24lambda_u240, i, d, d2, d3, func_70090_H, z, z2, f, d4, d5, d6, capabilities, entity, f2, worldObj, z3, z4, func_175723_af, func_72863_F, player.func_174832_aS(), player.field_70153_n, player.func_110140_aT(), player.func_175149_v(), player.field_70143_R, player.field_70138_W, player.field_70132_H, player.field_70151_c, player.field_70140_Q, player.field_82151_R, player.field_70150_b, player.field_70131_O, player.field_70130_N, player.field_70174_ab, player.field_70134_J, player.field_70145_X, player.func_70051_ag(), foodStats);
        }

        private final FoodStats createFoodStatsCopy(EntityPlayerSP player) {
            NBTTagCompound foodStatsNBT = new NBTTagCompound();
            FoodStats foodStats = new FoodStats();
            player.func_71024_bL().func_75117_b(foodStatsNBT);
            foodStats.func_75112_a(foodStatsNBT);
            return foodStats;
        }

        private final PlayerCapabilities createCapabilitiesCopy(EntityPlayerSP player) {
            NBTTagCompound capabilitiesNBT = new NBTTagCompound();
            PlayerCapabilities capabilities = new PlayerCapabilities();
            player.field_71075_bZ.func_75091_a(capabilitiesNBT);
            capabilities.func_75095_b(capabilitiesNBT);
            return capabilities;
        }
    }

    public final void tick() {
        if (!onEntityUpdate() || this.player.func_70115_ae()) {
            return;
        }
        playerUpdate(false);
        clientPlayerLivingUpdate();
        playerUpdate(true);
    }

    private final void clientPlayerLivingUpdate() {
        pushOutOfBlocks(this.posX - (this.width * 0.35d), getEntityBoundingBox().field_72338_b + 0.5d, this.posZ + (this.width * 0.35d));
        pushOutOfBlocks(this.posX - (this.width * 0.35d), getEntityBoundingBox().field_72338_b + 0.5d, this.posZ - (this.width * 0.35d));
        pushOutOfBlocks(this.posX + (this.width * 0.35d), getEntityBoundingBox().field_72338_b + 0.5d, this.posZ - (this.width * 0.35d));
        pushOutOfBlocks(this.posX + (this.width * 0.35d), getEntityBoundingBox().field_72338_b + 0.5d, this.posZ + (this.width * 0.35d));
        boolean flag3 = ((float) this.foodStats.func_75116_a()) > 6.0f || this.capabilities.field_75101_c;
        boolean shouldSprint = this.player.func_70051_ag();
        if (this.onGround && this.movementInput.field_78900_b >= 0.8d && !isSprinting() && flag3 && !this.player.func_71039_bw()) {
            Potion blindness = Potion.field_76440_q;
            Intrinsics.checkNotNullExpressionValue(blindness, "blindness");
            if (!isPotionActive(blindness) && shouldSprint) {
                setSprinting(true);
            }
        }
        if (!isSprinting() && this.movementInput.field_78900_b >= 0.8d && flag3 && !this.player.func_71039_bw()) {
            Potion blindness2 = Potion.field_76440_q;
            Intrinsics.checkNotNullExpressionValue(blindness2, "blindness");
            if (!isPotionActive(blindness2) && shouldSprint) {
                setSprinting(true);
            }
        }
        if (this.movementInput.field_78899_d) {
            setSprinting(false);
        }
        if (isSprinting() && (this.movementInput.field_78900_b < 0.8d || this.isCollidedHorizontally || !flag3)) {
            setSprinting(false);
        }
        if (this.capabilities.field_75101_c && getMc().field_71442_b.func_178887_k() && !this.capabilities.field_75100_b) {
            this.capabilities.field_75100_b = true;
        }
        if (this.capabilities.field_75100_b) {
            if (this.movementInput.field_78899_d) {
                this.motionY -= this.capabilities.func_75093_a() * 3.0f;
            }
            if (this.movementInput.field_78901_c) {
                this.motionY += this.capabilities.func_75093_a() * 3.0f;
            }
        }
        livingEntityUpdate();
    }

    private final void playerUpdate(boolean post) {
        if (!post) {
            this.noClip = this.isSpectator;
            if (this.isSpectator) {
                this.onGround = false;
                return;
            }
            return;
        }
        clampPositionFromEntityPlayer();
    }

    private final void livingEntityUpdate() {
        if (this.jumpTicks > 0) {
            this.jumpTicks--;
            int i = this.jumpTicks;
        }
        if (Math.abs(this.motionX) < 0.005d) {
            this.motionX = 0.0d;
        }
        if (Math.abs(this.motionY) < 0.005d) {
            this.motionY = 0.0d;
        }
        if (Math.abs(this.motionZ) < 0.005d) {
            this.motionZ = 0.0d;
        }
        if (isMovementBlocked()) {
            this.isJumping = false;
            this.moveStrafing = 0.0f;
            this.moveForward = 0.0f;
        } else if (isServerWorld()) {
            updateLivingEntityInput();
        }
        if (this.isJumping) {
            if (isInWater() || isInLava()) {
                updateAITick();
            } else if (this.onGround && this.jumpTicks == 0) {
                jump();
                if (NoJumpDelay.INSTANCE.handleEvents()) {
                    this.jumpTicks = 10;
                }
            }
        } else {
            this.jumpTicks = 0;
        }
        this.moveStrafing *= 0.98f;
        this.moveForward *= 0.98f;
        playerSideMoveEntityWithHeading(this.moveStrafing, this.moveForward);
        this.jumpMovementFactor = SPEED_IN_AIR;
        if (isSprinting()) {
            this.jumpMovementFactor = (float) (this.jumpMovementFactor + 0.005999999865889549d);
        }
        if (this.onGround && this.capabilities.field_75100_b && !this.isSpectator) {
            this.capabilities.field_75100_b = false;
        }
    }

    private final boolean onEntityUpdate() {
        handleWaterMovement();
        if (this.worldObj.field_72995_K) {
            this.fire = 0;
        } else if (this.fire > 0) {
            this.fire--;
            int i = this.fire;
        }
        if (isInLava()) {
            setOnFireFromLava();
            this.fallDistance *= 0.5f;
        }
        if (this.posY < -64.0d) {
            return false;
        }
        return true;
    }

    private final void clampPositionFromEntityPlayer() {
        double d3 = MathHelper.func_151237_a(this.posX, -2.9999999E7d, 2.9999999E7d);
        double d4 = MathHelper.func_151237_a(this.posZ, -2.9999999E7d, 2.9999999E7d);
        if (d3 == this.posX) {
            if (d4 == this.posZ) {
                return;
            }
        }
        setPosition(d3, this.posY, d4);
    }

    private final void setPosition(double x, double y, double z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        float f = this.width / 2.0f;
        float f1 = this.height;
        setEntityBoundingBox(new AxisAlignedBB(x - f, y, z - f, x + f, y + f1, z + f));
    }

    private final void setSprinting(boolean state) {
        this.isSprinting = state;
    }

    private final boolean pushOutOfBlocks(double x, double y, double z) {
        if (this.noClip) {
            return false;
        }
        BlockPos blockPos = new BlockPos(x, y, z);
        double d0 = x - blockPos.func_177958_n();
        double d1 = z - blockPos.func_177952_p();
        int entHeight = RangesKt.coerceAtLeast((int) Math.ceil(this.height), 1);
        boolean inTranslucentBlock = !isHeadspaceFree(blockPos, entHeight);
        if (inTranslucentBlock) {
            int i = -1;
            double d2 = 9999.0d;
            BlockPos func_177976_e = blockPos.func_177976_e();
            Intrinsics.checkNotNullExpressionValue(func_177976_e, "west(...)");
            if (isHeadspaceFree(func_177976_e, entHeight) && d0 < 9999.0d) {
                d2 = d0;
                i = 0;
            }
            BlockPos func_177974_f = blockPos.func_177974_f();
            Intrinsics.checkNotNullExpressionValue(func_177974_f, "east(...)");
            if (isHeadspaceFree(func_177974_f, entHeight) && 1.0d - d0 < d2) {
                d2 = 1.0d - d0;
                i = 1;
            }
            BlockPos func_177978_c = blockPos.func_177978_c();
            Intrinsics.checkNotNullExpressionValue(func_177978_c, "north(...)");
            if (isHeadspaceFree(func_177978_c, entHeight) && d1 < d2) {
                d2 = d1;
                i = 4;
            }
            BlockPos func_177968_d = blockPos.func_177968_d();
            Intrinsics.checkNotNullExpressionValue(func_177968_d, "south(...)");
            if (isHeadspaceFree(func_177968_d, entHeight) && 1.0d - d1 < d2) {
                i = 5;
            }
            if (i == 0) {
                this.motionX = -0.1f;
            }
            if (i == 1) {
                this.motionX = 0.1f;
            }
            if (i == 4) {
                this.motionZ = -0.1f;
            }
            if (i == 5) {
                this.motionZ = 0.1f;
            }
        }
        return false;
    }

    private final boolean isHeadspaceFree(BlockPos pos, int height) {
        for (int y = 0; y < height; y++) {
            BlockPos func_177982_a = pos.func_177982_a(0, y, 0);
            Intrinsics.checkNotNullExpressionValue(func_177982_a, "add(...)");
            if (!isOpenBlockSpace(func_177982_a)) {
                return false;
            }
        }
        return true;
    }

    private final boolean isOpenBlockSpace(BlockPos pos) {
        IBlockState blockState = getBlockState(pos);
        if (blockState != null) {
            Block func_177230_c = blockState.func_177230_c();
            return (func_177230_c == null || func_177230_c.func_149721_r()) ? false : true;
        }
        return false;
    }

    private final void playerSideMoveEntityWithHeading(float moveStrafing, float moveForward) {
        if (this.capabilities.field_75100_b && this.ridingEntity == null) {
            double d3 = this.motionY;
            float f = this.jumpMovementFactor;
            this.jumpMovementFactor = this.capabilities.func_75093_a() * (isSprinting() ? 2 : 1);
            livingEntitySideMoveEntityWithHeading(moveStrafing, moveForward);
            this.motionY = d3 * 0.6d;
            this.jumpMovementFactor = f;
            return;
        }
        livingEntitySideMoveEntityWithHeading(moveStrafing, moveForward);
    }

    private final void livingEntitySideMoveEntityWithHeading(float strafing, float forwards) {
        float f;
        double d;
        if (isServerWorld()) {
            if (!isInWater() || this.capabilities.field_75100_b) {
                if (isInLava() && !this.capabilities.field_75100_b) {
                    double d0 = this.posY;
                    moveFlying(strafing, forwards, SPEED_IN_AIR);
                    moveEntity(this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5d;
                    this.motionY *= 0.5d;
                    this.motionZ *= 0.5d;
                    this.motionY -= 0.02d;
                    if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, ((this.motionY + 0.6000000238418579d) - this.posY) + d0, this.motionZ)) {
                        this.motionY = 0.30000001192092896d;
                        return;
                    }
                    return;
                }
                float f4 = 0.91f;
                if (this.onGround) {
                    f4 = this.worldObj.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.posX), MathHelper.func_76128_c(getEntityBoundingBox().field_72338_b) - 1, MathHelper.func_76128_c(this.posZ))).func_177230_c().field_149765_K * 0.91f;
                }
                float f2 = 0.16277136f / ((f4 * f4) * f4);
                if (this.onGround) {
                    f = getAIMoveSpeed() * f2;
                } else {
                    f = this.jumpMovementFactor;
                }
                float f5 = f;
                moveFlying(strafing, forwards, f5);
                float f42 = 0.91f;
                if (this.onGround) {
                    f42 = this.worldObj.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.posX), MathHelper.func_76128_c(getEntityBoundingBox().field_72338_b) - 1, MathHelper.func_76128_c(this.posZ))).func_177230_c().field_149765_K * 0.91f;
                }
                if (isOnLadder()) {
                    this.motionX = MathHelper.func_151237_a(this.motionX, -0.15f, 0.15f);
                    this.motionZ = MathHelper.func_151237_a(this.motionZ, -0.15f, 0.15f);
                    this.fallDistance = 0.0f;
                    if (this.motionY < -0.15d) {
                        this.motionY = -0.15d;
                    }
                    boolean flag = isSneaking();
                    if (flag && this.motionY < 0.0d) {
                        this.motionY = 0.0d;
                    }
                }
                moveEntity(this.motionX, this.motionY, this.motionZ);
                if (this.isCollidedHorizontally && isOnLadder()) {
                    this.motionY = 0.2d;
                }
                if (this.worldObj.field_72995_K && (!this.worldObj.func_175667_e(new BlockPos((int) this.posX, 0, (int) this.posZ)) || !this.worldObj.func_175726_f(new BlockPos((int) this.posX, 0, (int) this.posZ)).func_177410_o())) {
                    if (this.posY > 0.0d) {
                        d = -0.1d;
                    } else {
                        d = 0.0d;
                    }
                    this.motionY = d;
                } else {
                    this.motionY -= 0.08d;
                }
                this.motionY *= 0.9800000190734863d;
                this.motionX *= f42;
                this.motionZ *= f42;
                return;
            }
            double d02 = this.posY;
            float f52 = 0.8f;
            float f6 = 0.02f;
            float f3 = EnchantmentHelper.func_180318_b(this.player);
            if (f3 > 3.0f) {
                f3 = 3.0f;
            }
            if (!this.onGround) {
                f3 *= 0.5f;
            }
            if (f3 > 0.0f) {
                f52 = 0.8f + (((0.54600006f - 0.8f) * f3) / 3.0f);
                f6 = SPEED_IN_AIR + ((((getAIMoveSpeed() * 1.0f) - SPEED_IN_AIR) * f3) / 3.0f);
            }
            moveFlying(strafing, forwards, f6);
            moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= f52;
            this.motionY *= 0.800000011920929d;
            this.motionZ *= f52;
            this.motionY -= 0.02d;
            if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, ((this.motionY + 0.6000000238418579d) - this.posY) + d02, this.motionZ)) {
                this.motionY = 0.30000001192092896d;
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(52:6|(1:8)|9|(1:201)(1:15)|16|(1:18)|19|(2:22|20)|23|24|(3:26|(1:28)(1:199)|(41:32|33|(2:36|34)|37|38|(2:41|39)|42|43|(28:47|(1:49)(1:98)|(2:51|(1:53)(1:55))|56|(2:59|57)|60|61|(2:64|62)|65|66|(2:69|67)|70|71|(2:74|72)|75|76|(2:79|77)|80|81|(2:84|82)|85|86|(1:88)(1:97)|89|(2:92|90)|93|94|(1:96))|99|(1:101)(1:198)|(3:103|(1:105)(1:196)|(27:107|108|(1:110)(1:195)|(1:112)(1:194)|113|(1:193)(1:117)|118|(1:192)(1:122)|123|(2:125|(1:131))|132|(1:134)(1:191)|(1:136)|137|(1:139)(1:190)|(1:141)|142|(1:144)(1:189)|(1:146)|147|(6:153|(1:155)|156|(1:160)|161|(1:165))|166|167|168|(2:170|(2:172|(1:174)))(2:183|(1:185))|175|(1:181)(2:179|180)))|197|108|(0)(0)|(0)(0)|113|(1:115)|193|118|(1:120)|192|123|(0)|132|(0)(0)|(0)|137|(0)(0)|(0)|142|(0)(0)|(0)|147|(8:149|151|153|(0)|156|(2:158|160)|161|(2:163|165))|166|167|168|(0)(0)|175|(2:177|181)(1:182)))|200|33|(1:34)|37|38|(1:39)|42|43|(29:45|47|(0)(0)|(0)|56|(1:57)|60|61|(1:62)|65|66|(1:67)|70|71|(1:72)|75|76|(1:77)|80|81|(1:82)|85|86|(0)(0)|89|(1:90)|93|94|(0))|99|(0)(0)|(0)|197|108|(0)(0)|(0)(0)|113|(0)|193|118|(0)|192|123|(0)|132|(0)(0)|(0)|137|(0)(0)|(0)|142|(0)(0)|(0)|147|(0)|166|167|168|(0)(0)|175|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0690, code lost:
    
        r40 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0692, code lost:
    
        r40.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x021e, code lost:
    
        if ((r66 == r21) == false) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x05ac  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x05c1  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x05df  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:182:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x017c A[LOOP:1: B:34:0x0172->B:36:0x017c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01c1 A[LOOP:2: B:39:0x01b7->B:41:0x01c1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0286 A[LOOP:3: B:57:0x027c->B:59:0x0286, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02c2 A[LOOP:4: B:62:0x02b8->B:64:0x02c2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02fe A[LOOP:5: B:67:0x02f4->B:69:0x02fe, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0340 A[LOOP:6: B:72:0x0336->B:74:0x0340, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x037c A[LOOP:7: B:77:0x0372->B:79:0x037c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03b8 A[LOOP:8: B:82:0x03ae->B:84:0x03b8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x043b A[LOOP:9: B:90:0x0431->B:92:0x043b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void moveEntity(double xMotion, double yMotion, double zMotion) {
        boolean z;
        boolean z2;
        Block block1;
        boolean flag2;
        double d11;
        double d8;
        double d20;
        double d10;
        double velocityX = xMotion;
        double velocityY = yMotion;
        double velocityZ = zMotion;
        if (this.noClip) {
            AxisAlignedBB func_72317_d = getEntityBoundingBox().func_72317_d(velocityX, velocityY, velocityZ);
            Intrinsics.checkNotNullExpressionValue(func_72317_d, "offset(...)");
            setEntityBoundingBox(func_72317_d);
            resetPositionToBB();
            return;
        }
        double d0 = this.posX;
        double d1 = this.posY;
        double d2 = this.posZ;
        if (this.isInWeb) {
            this.isInWeb = false;
            velocityX *= 0.25d;
            velocityY *= 0.05000000074505806d;
            velocityZ *= 0.25d;
            this.motionX = 0.0d;
            this.motionY = 0.0d;
            this.motionZ = 0.0d;
        }
        double d3 = velocityX;
        double d4 = velocityY;
        double d5 = velocityZ;
        boolean flag = this.onGround && (isSneaking() || this.safeWalk);
        if (flag) {
            Pair $this$moveEntity_u24lambda_u240 = new SimulatedPlayerJavaExtensions().checkForCollision(this, velocityX, velocityZ);
            d3 = ((Number) $this$moveEntity_u24lambda_u240.getLeft()).doubleValue();
            d5 = ((Number) $this$moveEntity_u24lambda_u240.getRight()).doubleValue();
        }
        List<AxisAlignedBB> list1 = this.worldObj.func_72945_a(this.player, getEntityBoundingBox().func_72321_a(velocityX, velocityY, velocityZ));
        AxisAlignedBB axisalignedbb = getEntityBoundingBox();
        for (AxisAlignedBB axisalignedbb1 : list1) {
            velocityY = axisalignedbb1.func_72323_b(getEntityBoundingBox(), velocityY);
        }
        AxisAlignedBB func_72317_d2 = getEntityBoundingBox().func_72317_d(0.0d, velocityY, 0.0d);
        Intrinsics.checkNotNullExpressionValue(func_72317_d2, "offset(...)");
        setEntityBoundingBox(func_72317_d2);
        if (!this.onGround) {
            if ((d4 == velocityY) || d4 >= 0.0d) {
                z = false;
                boolean flag1 = z;
                for (AxisAlignedBB axisalignedbb2 : list1) {
                    velocityX = axisalignedbb2.func_72316_a(getEntityBoundingBox(), velocityX);
                }
                AxisAlignedBB func_72317_d3 = getEntityBoundingBox().func_72317_d(velocityX, 0.0d, 0.0d);
                Intrinsics.checkNotNullExpressionValue(func_72317_d3, "offset(...)");
                setEntityBoundingBox(func_72317_d3);
                for (AxisAlignedBB axisalignedbb13 : list1) {
                    velocityZ = axisalignedbb13.func_72322_c(getEntityBoundingBox(), velocityZ);
                }
                AxisAlignedBB func_72317_d4 = getEntityBoundingBox().func_72317_d(0.0d, 0.0d, velocityZ);
                Intrinsics.checkNotNullExpressionValue(func_72317_d4, "offset(...)");
                setEntityBoundingBox(func_72317_d4);
                if (this.stepHeight > 0.0f && flag1) {
                    if (d3 != velocityX) {
                    }
                    d11 = velocityX;
                    double d7 = velocityY;
                    d8 = velocityZ;
                    AxisAlignedBB axisalignedbb3 = getEntityBoundingBox();
                    setEntityBoundingBox(axisalignedbb);
                    double velocityY2 = this.stepHeight;
                    List<AxisAlignedBB> list = this.worldObj.func_72945_a(this.player, getEntityBoundingBox().func_72321_a(d3, velocityY2, d5));
                    AxisAlignedBB axisalignedbb4 = getEntityBoundingBox();
                    AxisAlignedBB axisalignedbb5 = axisalignedbb4.func_72321_a(d3, 0.0d, d5);
                    double d9 = velocityY2;
                    for (AxisAlignedBB axisalignedbb6 : list) {
                        d9 = axisalignedbb6.func_72323_b(axisalignedbb5, d9);
                    }
                    AxisAlignedBB axisalignedbb42 = axisalignedbb4.func_72317_d(0.0d, d9, 0.0d);
                    double d15 = d3;
                    for (AxisAlignedBB axisalignedbb7 : list) {
                        d15 = axisalignedbb7.func_72316_a(axisalignedbb42, d15);
                    }
                    AxisAlignedBB axisalignedbb43 = axisalignedbb42.func_72317_d(d15, 0.0d, 0.0d);
                    double d16 = d5;
                    for (AxisAlignedBB axisalignedbb8 : list) {
                        d16 = axisalignedbb8.func_72322_c(axisalignedbb43, d16);
                    }
                    AxisAlignedBB axisalignedbb44 = axisalignedbb43.func_72317_d(0.0d, 0.0d, d16);
                    AxisAlignedBB axisalignedbb14 = getEntityBoundingBox();
                    double d17 = velocityY2;
                    for (AxisAlignedBB axisalignedbb9 : list) {
                        d17 = axisalignedbb9.func_72323_b(axisalignedbb14, d17);
                    }
                    AxisAlignedBB axisalignedbb142 = axisalignedbb14.func_72317_d(0.0d, d17, 0.0d);
                    double d18 = d3;
                    for (AxisAlignedBB axisalignedbb10 : list) {
                        d18 = axisalignedbb10.func_72316_a(axisalignedbb142, d18);
                    }
                    AxisAlignedBB axisalignedbb143 = axisalignedbb142.func_72317_d(d18, 0.0d, 0.0d);
                    double d19 = d5;
                    for (AxisAlignedBB axisalignedbb11 : list) {
                        d19 = axisalignedbb11.func_72322_c(axisalignedbb143, d19);
                    }
                    AxisAlignedBB axisalignedbb144 = axisalignedbb143.func_72317_d(0.0d, 0.0d, d19);
                    d20 = (d15 * d15) + (d16 * d16);
                    d10 = (d18 * d18) + (d19 * d19);
                    if (d20 <= d10) {
                        velocityX = d15;
                        velocityZ = d16;
                        velocityY = -d9;
                        setEntityBoundingBox(axisalignedbb44);
                    } else {
                        velocityX = d18;
                        velocityZ = d19;
                        velocityY = -d17;
                        setEntityBoundingBox(axisalignedbb144);
                    }
                    for (AxisAlignedBB axisalignedbb12 : list) {
                        velocityY = axisalignedbb12.func_72323_b(getEntityBoundingBox(), velocityY);
                    }
                    AxisAlignedBB func_72317_d5 = getEntityBoundingBox().func_72317_d(0.0d, velocityY, 0.0d);
                    Intrinsics.checkNotNullExpressionValue(func_72317_d5, "offset(...)");
                    setEntityBoundingBox(func_72317_d5);
                    if ((d11 * d11) + (d8 * d8) >= (velocityX * velocityX) + (velocityZ * velocityZ)) {
                        velocityX = d11;
                        velocityY = d7;
                        velocityZ = d8;
                        setEntityBoundingBox(axisalignedbb3);
                    }
                }
                resetPositionToBB();
                if (d3 != velocityX) {
                    if (d5 == velocityZ) {
                        z2 = false;
                        this.isCollidedHorizontally = z2;
                        this.isCollidedVertically = !((d4 > velocityY ? 1 : (d4 == velocityY ? 0 : -1)) == 0);
                        this.onGround = !this.isCollidedVertically && d4 < 0.0d;
                        this.isCollided = !this.isCollidedHorizontally || this.isCollidedVertically;
                        int i = MathHelper.func_76128_c(this.posX);
                        int j = MathHelper.func_76128_c(this.posY - 0.20000000298023224d);
                        int k = MathHelper.func_76128_c(this.posZ);
                        BlockPos blockPos = new BlockPos(i, j, k);
                        block1 = this.worldObj.func_180495_p(blockPos).func_177230_c();
                        if (block1.func_149688_o() == Material.field_151579_a) {
                            Block block = this.worldObj.func_180495_p(blockPos.func_177977_b()).func_177230_c();
                            if ((block instanceof BlockFence) || (block instanceof BlockWall) || (block instanceof BlockFenceGate)) {
                                block1 = block;
                            }
                        }
                        updateFallState(velocityY, this.onGround);
                        if (!(d3 == velocityX)) {
                            this.motionX = 0.0d;
                        }
                        if (!(d5 == velocityZ)) {
                            this.motionZ = 0.0d;
                        }
                        if (!(d4 == velocityY)) {
                            Block block2 = block1;
                            Intrinsics.checkNotNull(block2);
                            onLanded(block2);
                        }
                        if (canTriggerWalking() && !flag && this.ridingEntity == null) {
                            double d12 = this.posX - d0;
                            double d13 = this.posY - d1;
                            double d14 = this.posZ - d2;
                            if (block1 != Blocks.field_150468_ap) {
                                d13 = 0.0d;
                            }
                            if (block1 != null && this.onGround) {
                                onEntityCollidedWithBlock(block1);
                            }
                            this.distanceWalkedModified = (float) (this.distanceWalkedModified + (MathHelper.func_76133_a((d12 * d12) + (d14 * d14)) * 0.6d));
                            this.distanceWalkedOnStepModified = (float) (this.distanceWalkedOnStepModified + (MathHelper.func_76133_a((d12 * d12) + (d13 * d13) + (d14 * d14)) * 0.6d));
                            if (this.distanceWalkedOnStepModified > this.nextStepDistance && block1.func_149688_o() != Material.field_151579_a) {
                                this.nextStepDistance = ((int) this.distanceWalkedOnStepModified) + 1;
                            }
                        }
                        doBlockCollisions();
                        flag2 = isWet();
                        if (this.worldObj.func_147470_e(getEntityBoundingBox().func_72331_e(0.001d, 0.001d, 0.001d))) {
                            if (!flag2) {
                                this.fire++;
                                int i2 = this.fire;
                                if (this.fire == 0) {
                                    setFire(8);
                                }
                            }
                        } else if (this.fire <= 0) {
                            this.fire = -this.fireResistance;
                        }
                        if (!flag2 || this.fire <= 0) {
                            return;
                        }
                        this.fire = -this.fireResistance;
                        return;
                    }
                }
                z2 = true;
                this.isCollidedHorizontally = z2;
                this.isCollidedVertically = !((d4 > velocityY ? 1 : (d4 == velocityY ? 0 : -1)) == 0);
                this.onGround = !this.isCollidedVertically && d4 < 0.0d;
                this.isCollided = !this.isCollidedHorizontally || this.isCollidedVertically;
                int i3 = MathHelper.func_76128_c(this.posX);
                int j2 = MathHelper.func_76128_c(this.posY - 0.20000000298023224d);
                int k2 = MathHelper.func_76128_c(this.posZ);
                BlockPos blockPos2 = new BlockPos(i3, j2, k2);
                block1 = this.worldObj.func_180495_p(blockPos2).func_177230_c();
                if (block1.func_149688_o() == Material.field_151579_a) {
                }
                updateFallState(velocityY, this.onGround);
                if (!(d3 == velocityX)) {
                }
                if (!(d5 == velocityZ)) {
                }
                if (!(d4 == velocityY)) {
                }
                if (canTriggerWalking()) {
                    double d122 = this.posX - d0;
                    double d132 = this.posY - d1;
                    double d142 = this.posZ - d2;
                    if (block1 != Blocks.field_150468_ap) {
                    }
                    if (block1 != null) {
                        onEntityCollidedWithBlock(block1);
                    }
                    this.distanceWalkedModified = (float) (this.distanceWalkedModified + (MathHelper.func_76133_a((d122 * d122) + (d142 * d142)) * 0.6d));
                    this.distanceWalkedOnStepModified = (float) (this.distanceWalkedOnStepModified + (MathHelper.func_76133_a((d122 * d122) + (d132 * d132) + (d142 * d142)) * 0.6d));
                    if (this.distanceWalkedOnStepModified > this.nextStepDistance) {
                        this.nextStepDistance = ((int) this.distanceWalkedOnStepModified) + 1;
                    }
                }
                doBlockCollisions();
                flag2 = isWet();
                if (this.worldObj.func_147470_e(getEntityBoundingBox().func_72331_e(0.001d, 0.001d, 0.001d))) {
                }
                if (flag2) {
                    return;
                } else {
                    return;
                }
            }
        }
        z = true;
        boolean flag12 = z;
        while (r0.hasNext()) {
        }
        AxisAlignedBB func_72317_d32 = getEntityBoundingBox().func_72317_d(velocityX, 0.0d, 0.0d);
        Intrinsics.checkNotNullExpressionValue(func_72317_d32, "offset(...)");
        setEntityBoundingBox(func_72317_d32);
        while (r0.hasNext()) {
        }
        AxisAlignedBB func_72317_d42 = getEntityBoundingBox().func_72317_d(0.0d, 0.0d, velocityZ);
        Intrinsics.checkNotNullExpressionValue(func_72317_d42, "offset(...)");
        setEntityBoundingBox(func_72317_d42);
        if (this.stepHeight > 0.0f) {
            if (d3 != velocityX) {
            }
            d11 = velocityX;
            double d72 = velocityY;
            d8 = velocityZ;
            AxisAlignedBB axisalignedbb32 = getEntityBoundingBox();
            setEntityBoundingBox(axisalignedbb);
            double velocityY22 = this.stepHeight;
            List<AxisAlignedBB> list2 = this.worldObj.func_72945_a(this.player, getEntityBoundingBox().func_72321_a(d3, velocityY22, d5));
            AxisAlignedBB axisalignedbb45 = getEntityBoundingBox();
            AxisAlignedBB axisalignedbb52 = axisalignedbb45.func_72321_a(d3, 0.0d, d5);
            double d92 = velocityY22;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb422 = axisalignedbb45.func_72317_d(0.0d, d92, 0.0d);
            double d152 = d3;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb432 = axisalignedbb422.func_72317_d(d152, 0.0d, 0.0d);
            double d162 = d5;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb442 = axisalignedbb432.func_72317_d(0.0d, 0.0d, d162);
            AxisAlignedBB axisalignedbb145 = getEntityBoundingBox();
            double d172 = velocityY22;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb1422 = axisalignedbb145.func_72317_d(0.0d, d172, 0.0d);
            double d182 = d3;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb1432 = axisalignedbb1422.func_72317_d(d182, 0.0d, 0.0d);
            double d192 = d5;
            while (r0.hasNext()) {
            }
            AxisAlignedBB axisalignedbb1442 = axisalignedbb1432.func_72317_d(0.0d, 0.0d, d192);
            d20 = (d152 * d152) + (d162 * d162);
            d10 = (d182 * d182) + (d192 * d192);
            if (d20 <= d10) {
            }
            while (r0.hasNext()) {
            }
            AxisAlignedBB func_72317_d52 = getEntityBoundingBox().func_72317_d(0.0d, velocityY, 0.0d);
            Intrinsics.checkNotNullExpressionValue(func_72317_d52, "offset(...)");
            setEntityBoundingBox(func_72317_d52);
            if ((d11 * d11) + (d8 * d8) >= (velocityX * velocityX) + (velocityZ * velocityZ)) {
            }
        }
        resetPositionToBB();
        if (d3 != velocityX) {
        }
        z2 = true;
        this.isCollidedHorizontally = z2;
        this.isCollidedVertically = !((d4 > velocityY ? 1 : (d4 == velocityY ? 0 : -1)) == 0);
        this.onGround = !this.isCollidedVertically && d4 < 0.0d;
        this.isCollided = !this.isCollidedHorizontally || this.isCollidedVertically;
        int i32 = MathHelper.func_76128_c(this.posX);
        int j22 = MathHelper.func_76128_c(this.posY - 0.20000000298023224d);
        int k22 = MathHelper.func_76128_c(this.posZ);
        BlockPos blockPos22 = new BlockPos(i32, j22, k22);
        block1 = this.worldObj.func_180495_p(blockPos22).func_177230_c();
        if (block1.func_149688_o() == Material.field_151579_a) {
        }
        updateFallState(velocityY, this.onGround);
        if (!(d3 == velocityX)) {
        }
        if (!(d5 == velocityZ)) {
        }
        if (!(d4 == velocityY)) {
        }
        if (canTriggerWalking()) {
        }
        doBlockCollisions();
        flag2 = isWet();
        if (this.worldObj.func_147470_e(getEntityBoundingBox().func_72331_e(0.001d, 0.001d, 0.001d))) {
        }
        if (flag2) {
        }
    }

    private final AxisAlignedBB getEntityBoundingBox() {
        return this.box;
    }

    private final void setEntityBoundingBox(AxisAlignedBB box) {
        this.box = box;
    }

    private final void setOnFireFromLava() {
        setFire(15);
    }

    private final void setFire(int seconds) {
        int i = EnchantmentProtection.func_92093_a(this.player, seconds * 20);
        if (this.fire < i) {
            this.fire = i;
        }
    }

    private final boolean isWet() {
        return this.inWater || isRainingAt(new BlockPos(this.posX, this.posY, this.posZ)) || isRainingAt(new BlockPos(this.posX, this.posY + ((double) this.height), this.posZ));
    }

    private final void doBlockCollisions() {
        BlockPos blockpos = new BlockPos(getEntityBoundingBox().field_72340_a + 0.001d, getEntityBoundingBox().field_72338_b + 0.001d, getEntityBoundingBox().field_72339_c + 0.001d);
        BlockPos blockpos1 = new BlockPos(getEntityBoundingBox().field_72336_d - 0.001d, getEntityBoundingBox().field_72337_e - 0.001d, getEntityBoundingBox().field_72334_f - 0.001d);
        if (isAreaLoaded(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p(), blockpos1.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p(), true)) {
            int i = blockpos.func_177958_n();
            int func_177958_n = blockpos1.func_177958_n();
            if (i > func_177958_n) {
                return;
            }
            while (true) {
                int j = blockpos.func_177956_o();
                int func_177956_o = blockpos1.func_177956_o();
                if (j <= func_177956_o) {
                    while (true) {
                        int k = blockpos.func_177952_p();
                        int func_177952_p = blockpos1.func_177952_p();
                        if (k <= func_177952_p) {
                            while (true) {
                                BlockPos pos = new BlockPos(i, j, k);
                                IBlockState state = this.worldObj.func_180495_p(pos);
                                try {
                                    Block block = state.func_177230_c();
                                    if (block instanceof BlockWeb) {
                                        this.isInWeb = true;
                                    } else if (block instanceof BlockSoulSand) {
                                        this.motionX *= 0.4d;
                                        this.motionZ *= 0.4d;
                                    }
                                } catch (Throwable var11) {
                                    var11.printStackTrace();
                                }
                                if (k == func_177952_p) {
                                    break;
                                } else {
                                    k++;
                                }
                            }
                        }
                        if (j == func_177956_o) {
                            break;
                        } else {
                            j++;
                        }
                    }
                }
                if (i == func_177958_n) {
                    return;
                } else {
                    i++;
                }
            }
        }
    }

    private final void updateFallState(double motionY, boolean onGround) {
        if (!isInWater()) {
            handleWaterMovement();
        }
        if (onGround) {
            if (this.fallDistance > 0.0f) {
                this.fallDistance = 0.0f;
            }
        } else if (motionY < 0.0d) {
            this.fallDistance = (float) (this.fallDistance - motionY);
        }
    }

    private final boolean handleWaterMovement() {
        AxisAlignedBB func_72331_e = getEntityBoundingBox().func_72314_b(0.0d, -0.4000000059604645d, 0.0d).func_72331_e(0.001d, 0.001d, 0.001d);
        Intrinsics.checkNotNullExpressionValue(func_72331_e, "contract(...)");
        Material water = Material.field_151586_h;
        Intrinsics.checkNotNullExpressionValue(water, "water");
        if (handleMaterialAcceleration(func_72331_e, water)) {
            this.fallDistance = 0.0f;
            this.inWater = true;
            this.fire = 0;
        } else {
            this.inWater = false;
        }
        return this.inWater;
    }

    private final boolean handleMaterialAcceleration(AxisAlignedBB boundingBox, Material material) {
        Block block;
        int i = MathHelper.func_76128_c(boundingBox.field_72340_a);
        int j = MathHelper.func_76128_c(boundingBox.field_72336_d + 1.0d);
        int k = MathHelper.func_76128_c(boundingBox.field_72338_b);
        int l = MathHelper.func_76128_c(boundingBox.field_72337_e + 1.0d);
        int i1 = MathHelper.func_76128_c(boundingBox.field_72339_c);
        int j1 = MathHelper.func_76128_c(boundingBox.field_72334_f + 1.0d);
        if (!isAreaLoaded(i, k, i1, j, l, j1, true)) {
            return false;
        }
        boolean flag = false;
        Vec3 vec3 = new Vec3(0.0d, 0.0d, 0.0d);
        BlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int k1 = i; k1 < j; k1++) {
            for (int l1 = k; l1 < l; l1++) {
                for (int i2 = i1; i2 < j1; i2++) {
                    mutableBlockPos.func_181079_c(k1, l1, i2);
                    IBlockState state = getBlockState(mutableBlockPos);
                    if (state != null && (block = state.func_177230_c()) != null && block.func_149688_o() == material) {
                        Comparable func_177229_b = state.func_177229_b(BlockLiquid.field_176367_b);
                        Intrinsics.checkNotNull(func_177229_b, "null cannot be cast to non-null type kotlin.Int");
                        double d0 = (l1 + 1) - BlockLiquid.func_149801_b(((Integer) func_177229_b).intValue());
                        if (l >= d0) {
                            flag = true;
                            vec3 = block.func_176197_a(this.worldObj, mutableBlockPos, this.player, vec3);
                        }
                    }
                }
            }
        }
        if (vec3.func_72433_c() > 0.0d && isPushedByWater()) {
            Vec3 vec32 = vec3.func_72432_b();
            this.motionX += vec32.field_72450_a * 0.014d;
            this.motionY += vec32.field_72448_b * 0.014d;
            this.motionZ += vec32.field_72449_c * 0.014d;
        }
        return flag;
    }

    private final boolean isAreaLoaded(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean idfk) {
        if (maxY >= 0 && minY < 256) {
            int minX1 = minX >> 4;
            int minZ1 = minZ >> 4;
            int maxX1 = maxX >> 4;
            int maxZ1 = maxZ >> 4;
            int i = minX1;
            if (i <= maxX1) {
                while (true) {
                    int j = minZ1;
                    if (j <= maxZ1) {
                        while (isChunkLoaded(i, j, idfk)) {
                            if (j != maxZ1) {
                                j++;
                            }
                        }
                        return false;
                    }
                    if (i == maxX1) {
                        break;
                    }
                    i++;
                }
            }
            return true;
        }
        return false;
    }

    private final void onEntityCollidedWithBlock(Block block) {
        if ((block instanceof BlockSlime) && Math.abs(this.motionY) < 0.1d && !isSneaking()) {
            double motion = 0.4d + (Math.abs(this.motionY) * 0.2d);
            this.motionX *= motion;
            this.motionZ *= motion;
        }
    }

    private final boolean canTriggerWalking() {
        return !this.capabilities.field_75100_b;
    }

    public final boolean isOnLadder() {
        int i = MathHelper.func_76128_c(this.posX);
        int j = MathHelper.func_76128_c(this.box.field_72338_b);
        int k = MathHelper.func_76128_c(this.posZ);
        Block block = this.worldObj.func_180495_p(new BlockPos(i, j, k)).func_177230_c();
        return isLivingOnLadder(block, this.worldObj, new BlockPos(i, j, k), (EntityLivingBase) this.player);
    }

    private final void moveFlying(float strafe, float forward, float friction) {
        float f = (strafe * strafe) + (forward * forward);
        if (f >= 1.0E-4f) {
            float f2 = MathHelper.func_76129_c(f);
            if (f2 < 1.0f) {
                f2 = 1.0f;
            }
            float f3 = friction / f2;
            float newStrafe = strafe * f3;
            float newForward = forward * f3;
            float f1 = MathHelper.func_76126_a((this.rotationYaw * 3.1415927f) / 180.0f);
            float f22 = MathHelper.func_76134_b((this.rotationYaw * 3.1415927f) / 180.0f);
            this.motionX += (newStrafe * f22) - (newForward * f1);
            this.motionZ += (newForward * f22) + (newStrafe * f1);
        }
    }

    private final void jump() {
        this.motionY = getJumpUpwardsMotion();
        Potion jump = Potion.field_76430_j;
        Intrinsics.checkNotNullExpressionValue(jump, "jump");
        if (isPotionActive(jump)) {
            double d = this.motionY;
            Potion jump2 = Potion.field_76430_j;
            Intrinsics.checkNotNullExpressionValue(jump2, "jump");
            this.motionY = d + ((getActivePotionEffect(jump2).func_76458_c() + 1) * 0.1f);
        }
        if (isSprinting()) {
            float f = this.rotationYaw * 0.017453292f;
            this.motionX -= MathHelper.func_76126_a(f) * 0.2f;
            this.motionZ += MathHelper.func_76134_b(f) * 0.2f;
        }
        this.isAirBorne = true;
    }

    private final boolean isSprinting() {
        return this.isSprinting;
    }

    public final boolean isPotionActive(@NotNull Potion potion) {
        Intrinsics.checkNotNullParameter(potion, "potion");
        return this.player.func_70660_b(potion) != null;
    }

    @NotNull
    public final PotionEffect getActivePotionEffect(@NotNull Potion potion) {
        Intrinsics.checkNotNullParameter(potion, "potion");
        PotionEffect func_70660_b = this.player.func_70660_b(potion);
        Intrinsics.checkNotNullExpressionValue(func_70660_b, "getActivePotionEffect(...)");
        return func_70660_b;
    }

    private final float getJumpUpwardsMotion() {
        return 0.42f;
    }

    private final boolean isInWater() {
        return this.inWater;
    }

    private final void updateLivingEntityInput() {
        this.moveForward = this.movementInput.field_78900_b;
        this.moveStrafing = this.movementInput.field_78902_a;
        this.isJumping = this.movementInput.field_78901_c;
    }

    private final boolean isServerWorld() {
        return true;
    }

    private final boolean isMovementBlocked() {
        return this.player.func_110143_aJ() <= 0.0f || this.player.field_71083_bS;
    }

    public final boolean isInLava() {
        return this.worldObj.func_72875_a(getEntityBoundingBox().func_72314_b(-0.10000000149011612d, -0.4000000059604645d, -0.10000000149011612d), Material.field_151587_i);
    }

    private final void updateAITick() {
        this.motionY += 0.03999999910593033d;
    }

    private final boolean isOffsetPositionInLiquid(double x, double y, double z) {
        AxisAlignedBB box = getEntityBoundingBox().func_72317_d(x, y, z);
        Intrinsics.checkNotNull(box);
        return isLiquidPresentInAABB(box);
    }

    private final boolean isLiquidPresentInAABB(AxisAlignedBB box) {
        return this.worldObj.func_72945_a(this.player, box).isEmpty() && !this.worldObj.func_72953_d(box);
    }

    @NotNull
    public final List<AxisAlignedBB> getCollidingBoundingBoxes(@NotNull AxisAlignedBB box) {
        Intrinsics.checkNotNullParameter(box, "box");
        List newArrayList = Lists.newArrayList();
        Intrinsics.checkNotNullExpressionValue(newArrayList, "newArrayList(...)");
        List list = newArrayList;
        int i = MathHelper.func_76128_c(box.field_72340_a);
        int j = MathHelper.func_76128_c(box.field_72336_d + 1.0d);
        int k = MathHelper.func_76128_c(box.field_72338_b);
        int l = MathHelper.func_76128_c(box.field_72337_e + 1.0d);
        int i1 = MathHelper.func_76128_c(box.field_72339_c);
        int j1 = MathHelper.func_76128_c(box.field_72334_f + 1.0d);
        WorldBorder worldborder = getWorldBorder();
        boolean flag = this.isOutsideBorder;
        boolean flag1 = isInsideBorder(worldborder, flag);
        IBlockState iblockstate = Blocks.field_150348_b.func_176223_P();
        BlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int k1 = i; k1 < j; k1++) {
            for (int l1 = i1; l1 < j1; l1++) {
                BlockPos.MutableBlockPos func_181079_c = mutableBlockPos.func_181079_c(k1, 64, l1);
                Intrinsics.checkNotNullExpressionValue(func_181079_c, "set(...)");
                if (isBlockLoaded((BlockPos) func_181079_c)) {
                    for (int i2 = k - 1; i2 < l; i2++) {
                        mutableBlockPos.func_181079_c(k1, i2, l1);
                        if (flag && flag1) {
                            this.isOutsideBorder = false;
                        } else if (!flag && !flag1) {
                            this.isOutsideBorder = true;
                        }
                        IBlockState state = iblockstate;
                        if (worldborder.func_177746_a(mutableBlockPos) || !flag1) {
                            state = getBlockState(mutableBlockPos);
                        }
                        state.func_177230_c().func_180638_a(this.worldObj, mutableBlockPos, state, box, list, this.player);
                    }
                }
            }
        }
        Entity entity = (Entity) this.player;
        AxisAlignedBB func_72314_b = box.func_72314_b(0.25d, 0.25d, 0.25d);
        Intrinsics.checkNotNullExpressionValue(func_72314_b, "expand(...)");
        Entity entitiesWithinAABBExcludingEntity = getEntitiesWithinAABBExcludingEntity(entity, func_72314_b);
        int size = ((Collection) entitiesWithinAABBExcludingEntity).size();
        for (int size2 = 0; size2 < size; size2++) {
            if (this.riddenByEntity != entitiesWithinAABBExcludingEntity && this.ridingEntity != entitiesWithinAABBExcludingEntity) {
                AxisAlignedBB boundingBox = entitiesWithinAABBExcludingEntity.get(size2).func_70046_E();
                if (boundingBox != null && boundingBox.func_72326_a(box)) {
                    list.add(boundingBox);
                }
                AxisAlignedBB boundingBox2 = getCollisionBox((Entity) this.player, entitiesWithinAABBExcludingEntity.get(size2));
                if (boundingBox2 != null && boundingBox2.func_72326_a(box)) {
                    list.add(boundingBox2);
                }
            }
        }
        return list;
    }

    @Nullable
    public final IBlockState getBlockState(@NotNull BlockPos blockPos) {
        Intrinsics.checkNotNullParameter(blockPos, "blockPos");
        return this.worldObj.func_180495_p(blockPos);
    }

    private final Chunk getChunkFromBlockCoords(BlockPos blockPos) {
        return getChunkFromChunkCoords(blockPos.func_177958_n() >> 4, blockPos.func_177952_p() >> 4);
    }

    private final Chunk getChunkFromChunkCoords(int x, int z) {
        Chunk func_73154_d = this.chunkProvider.func_73154_d(x, z);
        Intrinsics.checkNotNullExpressionValue(func_73154_d, "provideChunk(...)");
        return func_73154_d;
    }

    private final boolean isValid(BlockPos pos) {
        return pos.func_177958_n() >= -30000000 && pos.func_177952_p() >= -30000000 && pos.func_177958_n() < 30000000 && pos.func_177952_p() < 30000000 && pos.func_177956_o() >= 0 && pos.func_177956_o() < 256;
    }

    private final WorldBorder getWorldBorder() {
        return this.worldBorder;
    }

    private final boolean isInsideBorder(WorldBorder border, boolean insideBorder) {
        double d0;
        double d1;
        double d2;
        double d3;
        double d02 = border.func_177726_b();
        double d12 = border.func_177736_c();
        double d22 = border.func_177728_d();
        double d32 = border.func_177733_e();
        if (insideBorder) {
            d0 = d02 + 1.0d;
            d1 = d12 + 1.0d;
            d2 = d22 - 1.0d;
            d3 = d32 - 1.0d;
        } else {
            d0 = d02 - 1.0d;
            d1 = d12 - 1.0d;
            d2 = d22 + 1.0d;
            d3 = d32 + 1.0d;
        }
        return this.posX > d0 && this.posX < d2 && this.posZ > d1 && this.posZ < d3;
    }

    private final boolean isBlockLoaded(BlockPos pos) {
        return isBlockLoaded(pos, true);
    }

    private final boolean isBlockLoaded(BlockPos pos, boolean check2) {
        if (isValid(pos)) {
            return isChunkLoaded(pos.func_177958_n() >> 4, pos.func_177952_p() >> 4, check2);
        }
        return false;
    }

    private final boolean isChunkLoaded(int x, int z, boolean flag) {
        return this.chunkProvider.func_73149_a(x, z) && (flag || !this.chunkProvider.func_73154_d(x, z).func_76621_g());
    }

    private final List<Entity> getEntitiesWithinAABBExcludingEntity(Entity entity, AxisAlignedBB box) {
        return getEntitiesInAABBexcluding(entity, box, EntitySelectors.field_180132_d);
    }

    private final List<Entity> getEntitiesInAABBexcluding(Entity entity, AxisAlignedBB bb, Predicate<? super Entity> predicate) {
        List newArrayList = Lists.newArrayList();
        Intrinsics.checkNotNullExpressionValue(newArrayList, "newArrayList(...)");
        List list = newArrayList;
        int i = MathHelper.func_76128_c((bb.field_72340_a - World.MAX_ENTITY_RADIUS) / 16.0d);
        int j = MathHelper.func_76128_c((bb.field_72336_d + World.MAX_ENTITY_RADIUS) / 16.0d);
        int k = MathHelper.func_76128_c((bb.field_72339_c - World.MAX_ENTITY_RADIUS) / 16.0d);
        int l = MathHelper.func_76128_c((bb.field_72334_f + World.MAX_ENTITY_RADIUS) / 16.0d);
        int i1 = i;
        if (i1 <= j) {
            while (true) {
                int j1 = k;
                if (j1 <= l) {
                    while (true) {
                        if (isChunkLoaded(i1, j1, true)) {
                            getChunkFromChunkCoords(i1, j1).func_177414_a(entity, bb, list, predicate);
                        }
                        if (j1 == l) {
                            break;
                        }
                        j1++;
                    }
                }
                if (i1 == j) {
                    break;
                }
                i1++;
            }
        }
        return list;
    }

    private final AxisAlignedBB getCollisionBox(Entity player, Entity entity) {
        if (entity instanceof EntityBoat) {
            return ((EntityBoat) entity).func_174813_aQ();
        }
        if (entity instanceof EntityMinecart) {
            return player.func_70114_g(entity);
        }
        return null;
    }

    private final float getAIMoveSpeed() {
        return (float) getEntityAttribute(SharedMonsterAttributes.field_111263_d).func_111126_e();
    }

    private final IAttributeInstance getEntityAttribute(IAttribute iAttribute) {
        IAttributeInstance func_111151_a = getAttributeMap().func_111151_a(iAttribute);
        Intrinsics.checkNotNullExpressionValue(func_111151_a, "getAttributeInstance(...)");
        return func_111151_a;
    }

    private final BaseAttributeMap getAttributeMap() {
        if (this.attributeMap == null) {
            this.attributeMap = new ServersideAttributeMap();
        }
        BaseAttributeMap baseAttributeMap = this.attributeMap;
        Intrinsics.checkNotNull(baseAttributeMap);
        return baseAttributeMap;
    }

    private final boolean isLivingOnLadder(Block block, World world, BlockPos pos, EntityLivingBase entity) {
        boolean isSpectator = this.isSpectator;
        if (isSpectator) {
            return false;
        }
        if (!ForgeModContainer.fullBoundingBoxLadders) {
            return block != null && block.isLadder((IBlockAccess) world, pos, entity);
        }
        AxisAlignedBB bb = this.box;
        int mX = MathHelper.func_76128_c(bb.field_72340_a);
        int mY = MathHelper.func_76128_c(bb.field_72338_b);
        int mZ = MathHelper.func_76128_c(bb.field_72339_c);
        for (int y2 = mY; y2 < bb.field_72337_e; y2++) {
            for (int x2 = mX; x2 < bb.field_72336_d; x2++) {
                for (int z2 = mZ; z2 < bb.field_72334_f; z2++) {
                    BlockPos tmp = new BlockPos(x2, y2, z2);
                    if (world.func_180495_p(tmp).func_177230_c().isLadder((IBlockAccess) world, tmp, entity)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final void resetPositionToBB() {
        this.posX = (getEntityBoundingBox().field_72340_a + getEntityBoundingBox().field_72336_d) / 2.0d;
        this.posY = getEntityBoundingBox().field_72338_b;
        this.posZ = (getEntityBoundingBox().field_72339_c + getEntityBoundingBox().field_72334_f) / 2.0d;
    }

    private final void onLanded(Block block) {
        if (block instanceof BlockSlime) {
            if (isSneaking()) {
                this.motionY = 0.0d;
                return;
            } else {
                if (this.motionY < 0.0d) {
                    this.motionY = -this.motionY;
                    return;
                }
                return;
            }
        }
        this.motionY = 0.0d;
    }

    public final boolean isSneaking() {
        return this.movementInput.field_78899_d && !this.player.field_71083_bS;
    }

    private final boolean isRainingAt(BlockPos pos) {
        if (this.worldObj.func_72867_j(1.0f) <= 0.2d || !canSeeSky(pos) || this.worldObj.func_175725_q(pos).func_177956_o() > pos.func_177956_o()) {
            return false;
        }
        BiomeGenBase base = this.worldObj.func_180494_b(pos);
        Intrinsics.checkNotNullExpressionValue(base, "getBiomeGenForCoords(...)");
        if (base.func_76746_c() || this.worldObj.func_175708_f(pos, false)) {
            return false;
        }
        return base.func_76738_d();
    }

    private final boolean canSeeSky(BlockPos pos) {
        return getChunkFromBlockCoords(pos).func_177444_d(pos);
    }

    private final boolean isPushedByWater() {
        return !this.capabilities.field_75100_b;
    }
}
