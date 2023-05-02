package com.example.dnd.services.impl;

import com.example.dnd.entities.Video;
import com.example.dnd.repositories.VideoRepository;
import com.example.dnd.services.VideoService;
import com.example.dnd.services.exceptions.VideoAlreadyExistsException;
import com.example.dnd.services.exceptions.VideoNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video getVideo(String name) throws VideoNotFoundException {
        if (!videoRepository.existsByName(name)) {
            throw new VideoNotFoundException();
        }

        return videoRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return videoRepository.existsByName(name);
    }

    @Override
    public void saveVideo(MultipartFile video, String name) throws VideoAlreadyExistsException, IOException {
        if (videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException();
        }

        videoRepository.save(Video.builder().data(video.getBytes()).name(name).build());
    }

    @Override
    public void deleteVideoByName(String name) throws VideoNotFoundException {
        if (!videoRepository.existsByName(name)) {
            throw new VideoNotFoundException();
        }

        videoRepository.deleteByName(name);
    }

    @Override
    public List<String> getAllVideosNames() {
        return videoRepository.getAllEntryNames();
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