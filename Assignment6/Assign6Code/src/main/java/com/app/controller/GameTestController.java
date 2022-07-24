/* TEST CLASS MADE FOR CONFIRMING SPRING REST WAS WORKING CORRECTLY AND I COULD MAKE GET AND POST REQUESTS VIA POSTMAN

package com.app.controller;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/app")
public class GameTestController {


    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String BeginGame() {
        return "Game Begun!";
    }


    @GetMapping("/game")
    public String GetGames() {
        return "GetAllGames";
    }





}

*/