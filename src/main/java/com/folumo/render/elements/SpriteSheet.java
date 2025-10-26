package com.folumo.render.elements;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.rect.SDL_Rect;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class SpriteSheet extends Element {
    private SDL_Texture texture;
    private int srcX, srcY, frameW, frameH;
    private int x, y;
    private int currentFrame = 0;
    private int frameCount = 1;

    public SpriteSheet(SDL_Texture texture, int x, int y, int frameW, int frameH, int frameCount) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.frameW = frameW;
        this.frameH = frameH;
        this.frameCount = frameCount;
    }

    public void setFrame(int frame) {
        this.currentFrame = Math.max(0, Math.min(frame, frameCount - 1));
    }

    @Override
    public void render(SDL_Renderer renderer) {
        SDL_Rect src = new SDL_Rect();
        src.x = frameW * currentFrame;
        src.y = 0;
        src.w = frameW;
        src.h = frameH;

        SDL_Rect dst = new SDL_Rect();
        dst.x = x;
        dst.y = y;
        dst.w = frameW;
        dst.h = frameH;
        SDL_RenderCopy(renderer, texture, src, dst);
    }

    @Override
    public boolean doesAcceptEvents() { return false; }

    @Override
    public void event(SDL_Event event) {}
}