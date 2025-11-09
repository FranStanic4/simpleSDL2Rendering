package com.folumo.render;

import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.video.SDL_Window;
import io.github.libsdl4j.api.video.SDL_WindowFlags;
import org.intellij.lang.annotations.MagicConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.libsdl4j.api.Sdl.SDL_Init;
import static io.github.libsdl4j.api.Sdl.SDL_Quit;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
import static io.github.libsdl4j.api.event.SDL_EventType.*;
import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_ACCELERATED;
import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
import static io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;

public class Window <T extends Screen<?>> {
    protected SDL_Window window;
    protected SDL_Renderer renderer;
    protected SDL_Event evt;
    protected boolean running;
    protected Window.WindowOptions options;
    protected Map<String, T> screens;
    protected String currentScreen;


    public Window(Window.WindowOptions opt, List<T> screens, String screen){
        options = opt;
        currentScreen = screen;
        this.screens = new HashMap<>();

        if (SDL_Init(SDL_INIT_EVERYTHING) != 0) {
            throw new IllegalStateException("SDL init failed");
        }

        window = SDL_CreateWindow(options.title, SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, options.w, options.h, options.SDL_FLAGS);
        if (window == null) {
            throw new IllegalStateException("Unable to create SDL window: " + SDL_GetError());
        }

        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);
        if (renderer == null) {
            throw new IllegalStateException("Unable to create SDL renderer: " + SDL_GetError());
        }

        for (T screenO : screens) {
            screenO.init(this);
            this.screens.put(screenO.getName(), screenO);
        }
        evt = new SDL_Event();
        running = true;
        while (running) {
            while (SDL_PollEvent(evt) != 0) {
                if (evt.type == SDL_QUIT) {
                    running = false;
                } else {
                    getScreen().event(evt);
                }
            }
            SDL_SetRenderDrawColor(renderer, (byte) 0, (byte) 0, (byte) 0, (byte) 255);
            SDL_RenderClear(renderer);

            getScreen().render(renderer);

            SDL_RenderPresent(renderer);

        }

        SDL_Quit();
    }

    public T getScreen(){
        return screens.get(currentScreen);
    }

    public static class WindowOptions {
        public int w = 400;
        public int h = 400;
        public int fps = 60;

        public String title = "Set title in WindowOptions.title";
        public @MagicConstant(flagsFromClass = SDL_WindowFlags.class) int SDL_FLAGS = SDL_WINDOW_SHOWN;
    }
}
