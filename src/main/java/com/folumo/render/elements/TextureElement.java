package com.folumo.render.elements;

import com.folumo.render.TextureLoader;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.surface.SDL_Surface;

import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.surface.SdlSurface.*;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;

public class TextureElement extends Element {
    private SDL_Texture texture;
    private int x, y, w, h;

    public TextureElement(SDL_Renderer renderer, String path, int x, int y) {
        this.x = x;
        this.y = y;
        SDL_Surface surface = TextureLoader.loadTexture(path);
        if (surface == null) {
            throw new IllegalStateException("Failed to load image: " + path + " — " + SDL_GetError());
        }

        texture = SDL_CreateTextureFromSurface(renderer, surface);
        this.w = surface.getW();
        this.h = surface.getH();

        SDL_FreeSurface(surface);
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_Rect srcRect = null; // full texture
        SDL_Rect dstRect = new SDL_Rect();
        dstRect.x = x;
        dstRect.y = y;
        dstRect.w = w;
        dstRect.h = h;

        SDL_RenderCopy(renderer, texture, srcRect, dstRect);
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {}

    public void destroy() {
        if (texture != null) SDL_DestroyTexture(texture);
    }
}