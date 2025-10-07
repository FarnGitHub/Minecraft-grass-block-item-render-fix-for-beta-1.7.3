package farn.grassblockitemfix;

import net.minecraft.src.Block;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class GrassBlockItemRenderer {

    public static void RenderGrassBlockItem(RenderBlocks renderer, Block block) {
        Tessellator tess = Tessellator.instance;
        float grassred = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) >> 16 & 255) / 255.0F;
        float grassgreen = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) >> 8 & 255) / 255.0F;
        float grassblue = (float)(ColorizerGrass.getGrassColor(0.96, 0.44) & 255) / 255.0F;
        block.setBlockBoundsForItemRender();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, 2);
        tess.draw();

        //colored top texture
        GL11.glColor3f(grassred, grassgreen, grassblue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, 0);
        tess.draw();
        GL11.glColor3f(1, 1, 1);

        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, 3);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, 3);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, 3);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, 3);
        tess.draw();

        //fancy grass
        GL11.glColor3f(grassred, grassgreen, grassblue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        GL11.glColor3f(1, 1, 1);
        GL11.glColor3f(grassred, grassgreen, grassblue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        GL11.glColor3f(1, 1, 1);
        GL11.glColor3f(grassred, grassgreen, grassblue);
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        GL11.glColor3f(1, 1, 1);
        GL11.glColor3f(grassred, grassgreen, grassblue);
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
