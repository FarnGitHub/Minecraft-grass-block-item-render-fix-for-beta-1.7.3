package farn.grassblockitemfix.mixin;

import farn.grassblockitemfix.Config;
import farn.grassblockitemfix.Renderer;
import net.minecraft.src.Block;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderBlocks.class)
public class RenderBlockMixin {

    @Inject(method = "renderBlockOnInventory", at = @At("HEAD"), cancellable = true)
    public void grassblock_onBlockInventoryRender(Block block, int meta, float brightness, CallbackInfo callback) {
        //if it match with grass block id then it will use it own render
        if(block.blockID == Block.grass.blockID) {
            Renderer.RenderGrassBlockItem((RenderBlocks) (Object) (this), block, brightness, meta);
            callback.cancel();
        }
    }

    @Inject(method="<init>()V", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        if(!Config.init) Config.load();
    }

}
