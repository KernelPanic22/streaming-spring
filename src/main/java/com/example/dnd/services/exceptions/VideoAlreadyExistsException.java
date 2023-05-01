package com.example.dnd.services.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Video already exists")
@AllArgsConstructor
public class VideoAlreadyExistsException extends Throwable {

}
