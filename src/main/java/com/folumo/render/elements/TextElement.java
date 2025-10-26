package com.folumo.render.elements;

import com.folumo.render.FontRenderer;
import com.sun.jna.ptr.IntByReference;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.surface.SDL_Surface;
import io.github.libsdl4j.api.pixels.SDL_Color;

import java.awt.*;

import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.surface.SdlSurface.*;

public class TextElement extends Element {
    private String text;
    private int x, y;
    private byte r, g, b, a;
    private FontRenderer font;
    private SDL_Texture texture;

    public TextElement(SDL_Renderer renderer, FontRenderer font, String text, int x, int y, byte r, byte g, byte b, byte a) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;


        updateTexture(renderer);
    }

    private void updateTexture(SDL_Renderer renderer) {

        SDL_Surface surface = font.renderText(text, new Color(r, g, b, a));
        texture = SDL_CreateTextureFromSurface(renderer, surface);
        SDL_FreeSurface(surface);
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_Rect dst = new SDL_Rect();
        dst.x = x;
        dst.y = y;
        dst.w = 0;
        dst.h = 0;

        SDL_QueryTexture(texture, null, null, new IntByReference(dst.w), new IntByReference(dst.h));
        SDL_RenderCopy(renderer, texture, null, dst);
    }

    @Override
    public boolean doesAcceptEvents() { return false; }

    @Override
    public void event(SDL_Event event) {}

    public void destroy() {
        if (texture != null) SDL_DestroyTexture(texture);
    }
}