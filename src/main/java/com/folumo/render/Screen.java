package com.folumo.render;

import com.folumo.render.elements.Element;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen <E extends Element> {
    protected List<E> elements = new ArrayList<>();


    public void event(SDL_Event evt){
        for (E element : elements) {
            if (element.doesAcceptEvents()){
                element.event(evt);
            }
        }
    }

    public void render(SDL_Renderer renderer){
        for (E element : elements) {
            element.render(renderer);
        }
    }

    public abstract String getName();

    public abstract void init(Window<?> window);
}
