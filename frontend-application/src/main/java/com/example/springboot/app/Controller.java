package com.example.springboot.app;

import com.example.springboot.components.ScoreBoard;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
	private final ScoreBoard scoreBoard;

	public Controller(ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	@GetMapping("/")
	public ModelAndView summary(){
		scoreBoard.startGame("nasi","vasi");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("games",scoreBoard.getSummary());
		modelAndView.setViewName("ScoreBoard");

		return modelAndView;
	}

	@GetMapping("/finish-game/{gameIndex}")
	public ModelAndView finishGame(@PathVariable int gameIndex){
		scoreBoard.finishGame(gameIndex);

		return new ModelAndView("redirect:/");
	}

}
