package com.example.miniprojects.application.tictactoe;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/tictactoe")
public class GameController {

    private TicTacToe getGameFromSession(HttpSession session) {
        TicTacToe game = (TicTacToe) session.getAttribute("game");
        if (game == null) {
            game = new TicTacToe();
            session.setAttribute("game", game);
        }
        return game;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        TicTacToe game = getGameFromSession(session);
        model.addAttribute("game", game);
        return "tictactoe";
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam("position") int position, HttpSession session) {
        TicTacToe game = getGameFromSession(session);
        game.makeMove(position);
        return "redirect:/tictactoe/";
    }

    @PostMapping("/reset")
    public String resetGame(HttpSession session) {
        TicTacToe game = getGameFromSession(session);
        game.reset();
        return "redirect:/tictactoe/";
    }
}
