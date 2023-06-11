package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import java.awt.Color;

import net.minecraft.client.Minecraft;
import overrideapi.OverrideAPI;

public class mod_GrassBlockRenderFix extends BaseMod {
	public static int grassBlockRender;
	private static int defaultGrassColor = ColorizerGrass.getGrassColor(0.5, 1);
	private static float grassred = (float)(defaultGrassColor >> 16 & 255) / 255.0F;
	private static float grassgreen = (float)(defaultGrassColor >> 8 & 255) / 255.0F;
	private static float grassblue = (float)(defaultGrassColor & 255) / 255.0F;

	@MLProp(name="Fancy_mode", info="colored side texture of grass block when on inventory (slower)")
	public static boolean fancymode = true;

	public void ModsLoaded() {
		Block grass = (OverrideAPI.overrideVanillaBlock(Block.grass, BlockGrassRenderFix.class)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("grass");	
	}

	public void RenderInvBlock(RenderBlocks renderer, Block block, int metadata, int modelID) {
		Tessellator nw1 = Tessellator.instance;
		if(modelID == grassBlockRender) {
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
		return "1.0";
	}

	{
		grassBlockRender = ModLoader.getUniqueBlockModelID(this, true);
	}
}
