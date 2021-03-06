package de.pfannekuchen.lotas.mixin.render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.pfannekuchen.lotas.core.utils.PotionRenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * This Mixin adds a potion to the hotbar
 * @author Pancake
 * @version v1.0
 * @since v1.0
 */
@Mixin(EntityRenderer.class)
public abstract class MixinPotionRenderer {

//	@org.spongepowered.asm.mixin.Shadow @org.spongepowered.asm.mixin.Final
//	public Minecraft mc;
//	@org.spongepowered.asm.mixin.Shadow
//	//#if MC>=10900
//	public abstract void rotateArm(float partialTicks);
//	@org.spongepowered.asm.mixin.Shadow protected abstract void renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded);
//	//#else
////$$ 	public abstract void rotateWithPlayerRotations(net.minecraft.client.entity.EntityPlayerSP entityplayerspIn, float partialTicks);
////$$ 	@org.spongepowered.asm.mixin.Shadow public abstract void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform);
//	//#endif
//	
//	//#if MC>=10900
//	@org.spongepowered.asm.mixin.injection.Redirect(method = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;rotateArm(F)V"))
//	private void cancelRotateArm(ItemRenderer renderer, float pff) {
//
//	}
//	//#endif
//	
//	//#if MC>=10900
//	@Inject(method = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V"))
//	//#else
////$$ 	@Inject(method = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(F)V", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V"))
//	//#endif
	
	@Inject(method = "renderHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V", shift = Shift.AFTER))
	public void drawPotionAfter(CallbackInfo ci) {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        ItemStack stack2 = PotionRenderingUtils.renderPotion();
        Minecraft.getMinecraft().getRenderItem().renderItem(stack2, ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}
	

//	//#if MC>=10900
//	@Inject(method = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V"))
//	public void rotateArmLater(CallbackInfo ci) {
//		rotateArm(mc.getRenderPartialTicks());
//	}
//	//#endif
	
}