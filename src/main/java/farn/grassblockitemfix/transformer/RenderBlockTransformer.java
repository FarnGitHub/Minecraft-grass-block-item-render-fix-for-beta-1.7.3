package farn.grassblockitemfix.transformer;

import farn.grassblockitemfix.Config;
import farn.grassblockitemfix.Renderer;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.src.Block;
import net.minecraft.src.RenderBlocks;

@SuppressWarnings("unused")
@CTransformer(RenderBlocks.class)
public class RenderBlockTransformer {

    @SuppressWarnings("all")
    @CInject(method = "renderBlockOnInventory", target = @CTarget("HEAD"), cancellable = true)
    public void grassblock_onBlockInventoryRender(Block block, int meta, float brightness, InjectionCallback callback) {
        //if it match with grass block id then it will use it own render
        if(block.blockID == Block.grass.blockID) {
            Renderer.RenderGrassBlockItem((RenderBlocks) (Object) (this), block, brightness);
            callback.setCancelled(true);
        }
    }

    @CInject(method="<init>()V", target = @CTarget("TAIL"))
    public void init(InjectionCallback callback) {
        if(!Config.init) Config.load();
    }

}
