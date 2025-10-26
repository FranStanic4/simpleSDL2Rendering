package com.folumo.render;

import com.sun.jna.Memory;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.surface.SDL_Surface;
import io.github.libsdl4j.api.pixels.SDL_PixelFormatEnum;
import io.github.libsdl4j.api.surface.SdlSurface;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import javax.imageio.ImageIO;


public class TextureLoader {

    public static SDL_Surface loadTexture(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));

            int width = img.getWidth();
            int height = img.getHeight();

            // Extract ARGB pixels from BufferedImage
            int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

            // Allocate native memory (4 bytes per pixel)
            Memory nativeMem = new Memory((long) width * height * 4);

            // Copy the int[] pixels into native memory
            for (int i = 0; i < pixels.length; i++) {
                nativeMem.setInt((long) i * 4, pixels[i]);
            }

            // Create the SDL surface
            return SdlSurface.SDL_CreateRGBSurfaceWithFormatFrom(
                    nativeMem,
                    width,
                    height,
                    32,
                    width * 4,
                    SDL_PixelFormatEnum.SDL_PIXELFORMAT_ARGB8888
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}