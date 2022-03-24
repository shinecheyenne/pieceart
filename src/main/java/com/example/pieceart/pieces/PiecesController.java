package com.example.pieceart.pieces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/pieces")
public class PiecesController {
    private final PiecesService piecesService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPieces(Authentication authentication){
        String email = authentication.getPrincipal().toString();
        List<PiecesDTO> list = piecesService.findPiecesByUser(email);
        Map<String, Object> map = new HashMap<>();
        map.put("pieces", list);
        return ResponseEntity.ok().body(map);
    }

    @DeleteMapping("/{piecesId}")
    public ResponseEntity<Map<String, Object>> cancelPieces(@PathVariable("piecesId") Long piecedId, Authentication authentication){
        String email = authentication.getPrincipal().toString();
        boolean result = piecesService.cancelPieces(email, piecedId);
        if(result) return ResponseEntity.noContent().build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> purchasePieces(@RequestBody PiecesDTO piecesDTO, Authentication authentication){
        String email = authentication.getPrincipal().toString();
        Long worksId = piecesDTO.getWorksId();
        int pieceNum = piecesDTO.getPiecesPurchased();

        boolean result = piecesService.purchasePieces(email, worksId, pieceNum);
        Map<String, Object> map= new HashMap<>();
        map.put("status", result);

        if(!result) return ResponseEntity.badRequest().body(map);
        return ResponseEntity.ok().body(map);
    }
}
