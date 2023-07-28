package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import java.awt.Color;

import net.minecraft.client.Minecraft;

public class mod_GrassBlockRenderFix extends BaseMod {
	public static int grassBlockRender;

	@MLProp(name="Fancy_grass", info="colored side texture of grass block when on inventory (slower)")
	public static boolean fancymode = true;

	public void RenderInvBlock(RenderBlocks renderer, Block block, int metadata, int modelID) {
		Tessellator nw1 = Tessellator.instance;
		if(modelID == grassBlockRender) {
			float grassred = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) >> 16 & 255) / 255.0F;
			float grassgreen = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) >> 8 & 255) / 255.0F;
			float grassblue = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) & 255) / 255.0F;
			block.setBlockBoundsForItemRender();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			nw1.startDrawingQuads();
			nw1.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, metadata));
			nw1.draw();

			//colored top texture
			GL11.glColor3f(grassred, grassgreen, grassblue);
			nw1.startDrawingQuads();
			nw1.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, metadata));
			nw1.draw();
			GL11.glColor3f(1, 1, 1);

			nw1.startDrawingQuads();
			nw1.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, metadata));
			nw1.draw();
			nw1.startDrawingQuads();
			nw1.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, metadata));
			nw1.draw();
			nw1.startDrawingQuads();
			nw1.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, metadata));
			nw1.draw();
			nw1.startDrawingQuads();
			nw1.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, metadata));
			nw1.draw();

			//fancy grass
			if(fancymode) {
				GL11.glColor3f(grassred, grassgreen, grassblue);
				nw1.startDrawingQuads();
				nw1.setNormal(0.0F, 0.0F, -1.0F);
				renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, 38);
				nw1.draw();
				GL11.glColor3f(1, 1, 1);
				GL11.glColor3f(grassred, grassgreen, grassblue);
				nw1.startDrawingQuads();
				nw1.setNormal(0.0F, 0.0F, 1.0F);
				renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, 38);
				nw1.draw();
				GL11.glColor3f(1, 1, 1);
				GL11.glColor3f(grassred, grassgreen, grassblue);
				nw1.startDrawingQuads();
				nw1.setNormal(-1.0F, 0.0F, 0.0F);
				renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, 38);
				nw1.draw();
				GL11.glColor3f(1, 1, 1);
				GL11.glColor3f(grassred, grassgreen, grassblue);
				nw1.startDrawingQuads();
				nw1.setNormal(1.0F, 0.0F, 0.0F);
				renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, 38);
				nw1.draw();
			}

			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	public boolean RenderWorldBlock(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block, int modelID) {
		return renderer.renderStandardBlock(block, x, y, z);
	}

	public String Version() {
		return "1.6";
	}

	public void ModsLoaded() {
		grassBlockRender = ModLoader.getUniqueBlockModelID(this, true);
		try {
			Block overridedBlock = null;
			for(int i2 = 0; i2 < Block.class.getDeclaredFields().length; ++i2) {
				try {
					if(((Block)(Block.class.getDeclaredFields()[i2]).get(null)) == Block.grass) {
						Block.blocksList[Block.grass.blockID] = null;
						ModLoader.setPrivateValue(Block.class, (Object)null, i2, overridedBlock = (new BlockGrassRenderFix()).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("grass"));
						this.addEffectiveTools(new Item[]{Item.shovelDiamond, Item.shovelGold, Item.shovelSteel, Item.shovelStone, Item.shovelWood} , new Block[]{overridedBlock});
						break;
					}
				} catch (Exception exception10) {
				}
			}


			System.gc();
		} catch (Exception exception11) {
			throw new RuntimeException(exception11);
		}
	}

	private void addEffectiveTools(Item[] effectiveTools, Block[] vulnerableBlocks) {
		try {
			java.lang.reflect.Field blocksEffectiveAgainstField = ItemTool.class.getDeclaredFields()[0];
			blocksEffectiveAgainstField.setAccessible(true);

			for (Item tool : effectiveTools) {
				Block[] blocksEffectiveAgainstOriginal = (Block[]) blocksEffectiveAgainstField.get(tool);
				Block[] blocksEffectiveAgainst = new Block[blocksEffectiveAgainstOriginal.length + vulnerableBlocks.length];
				System.arraycopy(blocksEffectiveAgainstOriginal, 0, blocksEffectiveAgainst, 0, blocksEffectiveAgainstOriginal.length);
				System.arraycopy(vulnerableBlocks, 0, blocksEffectiveAgainst, blocksEffectiveAgainstOriginal.length, vulnerableBlocks.length);
				blocksEffectiveAgainstField.set(tool, blocksEffectiveAgainst);
			}

		} catch (Exception e) {
			System.out.println("(Farn Annoyance Fix) Failed To Add Tool Effective: " + e);
		}
	}
}
