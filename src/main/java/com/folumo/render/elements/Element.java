package com.folumo.render.elements;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;

public abstract class Element {
    public abstract void render(SDL_Renderer renderer);
    public abstract boolean doesAcceptEvents();
    public abstract void event(SDL_Event event);
}
