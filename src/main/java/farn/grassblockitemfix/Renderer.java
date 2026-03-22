package farn.grassblockitemfix;

import net.minecraft.src.Block;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class Renderer {

    public static void RenderGrassBlockItem(RenderBlocks renderer, Block block, float brightness) {
        Tessellator tess = Tessellator.instance;

        //b1.8 inventory render grass color
        int grassColor = Config.useColorFromBiome ? ColorizerGrass.getGrassColor(Config.humid, Config.temp) : Config.grassColor;
        float gr = (float)(grassColor >> 16 & 255) / 255.0F;
        float gg = (float)(grassColor >> 8 & 255) / 255.0F;
        float gb = (float)(grassColor & 255) / 255.0F;

        block.setBlockBoundsForItemRender();

        //bottom texture
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, 2);
        tess.draw();

        //colored top texture
        GL11.glColor3f(gr * brightness, gg * brightness, gb * brightness);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, 0);
        tess.draw();

        //side texture
        GL11.glColor3f(brightness, brightness, brightness);
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

        //tinted grass side texture
        GL11.glColor3f(gr * brightness, gg * brightness, gb * brightness);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, 38);
        tess.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
