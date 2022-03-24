package com.example.pieceart.wishlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/users/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getWishlist(Authentication authentication){
        String email = authentication.getPrincipal().toString();
        log.info(email);
        List<WishlistDTO> list = wishlistService.getWishList(email);
        Map<String, Object> map = new HashMap<>();
        map.put("wishlist", list);
        return ResponseEntity.ok().body(map);
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Map<String, Object>> deleteWishlist(@PathVariable("wishlistId") Long wishlistId, Authentication authentication){
        String email = authentication.getPrincipal().toString();
        Map<String, Object> map = new HashMap<>();
        if(wishlistService.getWishList(email).size()==0){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        wishlistService.deleteWishList(wishlistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/works/{worksId}")
    public ResponseEntity<Map<String, Object>> createWishlist(@PathVariable("worksId") Long worksId, Authentication authentication){
        String email = authentication.getPrincipal().toString();
        boolean created = wishlistService.createWishlist(email, worksId);
        Map<String, Object> map = new HashMap<>();
        map.put("data", created);
        return ResponseEntity.ok().body(map);
    }
}
