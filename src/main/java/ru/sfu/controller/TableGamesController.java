package ru.sfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sfu.dao.TableGameDAO;
import ru.sfu.model.TableGame;

import javax.validation.Valid;

@Controller
@RequestMapping("/table_games")
public class TableGamesController {
    private final TableGameDAO dao;

    @Autowired
    public TableGamesController(TableGameDAO dao) {
        this.dao = dao;
    }


    @GetMapping()
    public String menu(){
        return "table_games/menu";
    }

    @GetMapping("/show")
    public String showGames(Model model){
        model.addAttribute("games", dao.findAll());
        return "table_games/show";
    }

    @GetMapping("/add")
    public String newGame(Model model){
        model.addAttribute("game", new TableGame());
        return "table_games/add";
    }

    @PostMapping
    public String addGame(@ModelAttribute("game") @Valid TableGame game,
                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "table_games/add";
        }

        dao.insert(game);
        return "redirect:/table_games";
    }

    @GetMapping("/{id}")
    public String showGame(@PathVariable("id") int id, Model model){
        model.addAttribute("game", dao.get(id));
        return "table_games/showGame";
    }

    @GetMapping("/{id}/edit")
    public String editGame(@PathVariable("id") int id, Model model){
        model.addAttribute("game", dao.get(id));
        return "table_games/edit";
    }

    @PostMapping("edit/{id}")
    public String updateGame(@PathVariable("id") int id,
                             @ModelAttribute("game") @Valid TableGame game,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "table_games/edit";
        }
        game.setId(id);
        dao.edit(game);
        return "redirect:/table_games";
    }

    @GetMapping("findGame")
    public String findGame(@RequestParam("price") int maxPrice, Model model)
    {
        model.addAttribute("games", dao.findGameBelowPrice(maxPrice));
        return "/table_games/show";
    }

    @GetMapping("find")
    public String showFoundedGames(Model model){
        return "table_games/find";
    }

    @GetMapping("delete/{id}")
    public String deleteGame(@PathVariable("id") int id){
        dao.delete(id);
        return "redirect:/table_games";
    }


}
