package com.folumo.render.elements;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class Rect extends Element {
    private int x, y, w, h;
    private byte r, g, b, a;
    private boolean filled;

    public Rect(int x, int y, int w, int h, byte r, byte g, byte b, byte a, boolean filled) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.filled = filled;
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_SetRenderDrawColor(renderer, r, g, b, a);
        SDL_Rect rect = new SDL_Rect();
        rect.x = x;
        rect.y = y;
        rect.w = w;
        rect.h = h;

        if (filled) SDL_RenderFillRect(renderer, rect);
        else SDL_RenderDrawRect(renderer, rect);
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {}
}