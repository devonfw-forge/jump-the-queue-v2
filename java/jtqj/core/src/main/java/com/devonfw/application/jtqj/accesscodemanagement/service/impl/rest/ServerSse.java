package com.devonfw.application.jtqj.accesscodemanagement.service.impl.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ServerSse {

    public static final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());

    @RequestMapping(path = "/accesscode/current/stream", method = RequestMethod.GET)
    public SseEmitter stream() throws IOException {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }
}