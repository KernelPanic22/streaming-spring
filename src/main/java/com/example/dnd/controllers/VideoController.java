package com.example.dnd.controllers;


import com.example.dnd.services.VideoService;
import com.example.dnd.services.exceptions.VideoAlreadyExistsException;
import com.example.dnd.services.exceptions.VideoNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/video")
@AllArgsConstructor
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/save")
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException, VideoAlreadyExistsException {
        try {
            videoService.saveVideo(file, name);
            return ok().body("Video saved");
        }catch (VideoAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Video already exists");
        }
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new ByteArrayResource(videoService.getVideo(name).getData()));
        } catch (VideoNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideosNames() {
        return ok().body(videoService.getAllVideosNames());
    }
}

//    MIT License
//
//    Copyright (c) 2023 kernelpanic22
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
