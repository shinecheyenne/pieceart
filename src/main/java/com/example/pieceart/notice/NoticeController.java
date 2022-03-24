package com.example.pieceart.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/notices")
public class NoticeController {
    private final NoticeService service;
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getNotices(@RequestParam(value="page", required = false, defaultValue = "1") int page){
        int totalCount = service.findAll().size();
        List<NoticeDTO> notices = service.findByPage(page);

        Map<String, Object> map = new HashMap<>();
        if(notices.isEmpty()){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("count", notices.size());
        map.put("totalCount", totalCount);
        map.put("data", notices);

        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getNoticeById(@PathVariable("id") Long id){
        NoticeDTO notice = service.findById(id);
        Map<String, Object> map = new HashMap<>();

        if(notice == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("data", notice);

        return ResponseEntity.ok().body(map);
//        return ResponseEntity.of(notice); //valid body with 200 ok or no body with 404 not found
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createNotice(@Valid @RequestBody NoticeDTO noticeDTO){
        NoticeDTO created = service.create(noticeDTO);
        Map<String, Object> map = new HashMap<>();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        map.put("data", created);
        return ResponseEntity.created(location).body(map);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateNotice(@PathVariable("id") Long id, @Valid @RequestBody NoticeDTO updated){
        NoticeDTO updatedNotice = service.update(id, updated);
        Map<String, Object> map = new HashMap<>();
        if(updatedNotice == null){
            return createNotice(updated);
        } else{
            map.put("data", updatedNotice);
            return ResponseEntity.ok().body(map);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNotice(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        if(service.findById(id) == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidExceptions(MethodArgumentNotValidException e){
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        Map<String, Object> map = new HashMap<>(errors.size());
        errors.forEach((error)->{
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return ResponseEntity.badRequest().body(map);
    }
}
