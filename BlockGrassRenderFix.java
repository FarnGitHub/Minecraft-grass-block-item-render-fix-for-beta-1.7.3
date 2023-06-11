package net.minecraft.src;

import java.util.Random;

public class BlockGrassRenderFix extends BlockGrass {
	public BlockGrassRenderFix() {
		super(Block.grass.blockID);
	}

	public int getBlockTextureFromSide(int i5) {
		if(i5 == 1) {
			return 0;
		} else if(i5 == 0) {
			return 2;
		} else {
			return this.blockIndexInTexture;
		}
	}

	public int getRenderType() {
		return mod_GrassBlockRenderFix.grassBlockRender;
	}
}
