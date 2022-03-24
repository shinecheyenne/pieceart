package com.example.pieceart.works;

import com.example.pieceart.entity.Works;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/works")
public class WorksController {
    private final WorksService worksService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllWorks(){
        List<WorksDTO> works = worksService.getAllWorks();
        Map<String, Object> map = new HashMap<>();
        map.put("count", works.size());
        map.put("works", works);

        return ResponseEntity.ok().body(map);
    }
}
