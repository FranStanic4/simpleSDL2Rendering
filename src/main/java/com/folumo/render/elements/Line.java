package com.folumo.render.elements;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class Line extends Element {
    private int x1, y1, x2, y2;
    private byte r, g, b, a;

    public Line(int x1, int y1, int x2, int y2, byte r, byte g, byte b, byte a) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_SetRenderDrawColor(renderer, r, g, b, a);
        SDL_RenderDrawLine(renderer, x1, y1, x2, y2);
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {}
}