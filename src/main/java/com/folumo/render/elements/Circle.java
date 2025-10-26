package com.folumo.render.elements;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class Circle extends Element {
    private int cx, cy, radius;
    private byte r, g, b, a;

    public Circle(int cx, int cy, int radius, byte r, byte g, byte b, byte a) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_SetRenderDrawColor(renderer, r, g, b, a);
        int x = radius - 1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius << 1);

        while (x >= y) {
            SDL_RenderDrawPoint(renderer, cx + x, cy + y);
            SDL_RenderDrawPoint(renderer, cx + y, cy + x);
            SDL_RenderDrawPoint(renderer, cx - y, cy + x);
            SDL_RenderDrawPoint(renderer, cx - x, cy + y);
            SDL_RenderDrawPoint(renderer, cx - x, cy - y);
            SDL_RenderDrawPoint(renderer, cx - y, cy - x);
            SDL_RenderDrawPoint(renderer, cx + y, cy - x);
            SDL_RenderDrawPoint(renderer, cx + x, cy - y);

            if (err <= 0) {
                y++;
                err += dy;
                dy += 2;
            } else {
                x--;
                dx += 2;
                err += dx - (radius << 1);
            }
        }
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {
        // No event handling for now
    }
}