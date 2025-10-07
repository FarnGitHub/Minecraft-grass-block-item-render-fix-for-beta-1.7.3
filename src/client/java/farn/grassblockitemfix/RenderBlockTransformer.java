package farn.grassblockitemfix;

import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.src.Block;
import net.minecraft.src.RenderBlocks;

@CTransformer(RenderBlocks.class)
public class RenderBlockTransformer {

    RenderBlocks self = (RenderBlocks) (Object) (this);

    @CInject(method = "renderBlockOnInventory", target = @CTarget("HEAD"), cancellable = true)
    public void grassblock_onBlockInventoryRender(Block block, int i, float f, InjectionCallback callback) {
        if(block == Block.grass) {
            if(self == null) {
                self = (RenderBlocks) (Object) (this);
            }
            GrassBlockItemRenderer.RenderGrassBlockItem(self, block);
            callback.setCancelled(true);
        }
    }


}
