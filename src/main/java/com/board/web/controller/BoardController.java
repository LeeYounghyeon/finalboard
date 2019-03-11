package com.board.web.controller;

import com.board.web.domain.Board;
import com.board.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable,Model model) {
        model.addAttribute("board_List",boardService.boardListFind(pageable));
        return "/board/list";
    }

    @GetMapping("/insert_form")
    public String insert_form(Model model){
        return "/board/insert_form";
    }

    @PostMapping("/insert")
    @ResponseBody
    public Boolean insert(@RequestBody Board board){
        boardService.boardSave(board);
        return true;
    }

    @GetMapping("/list_detail")
    public String list_detail(@RequestParam(value = "idx", defaultValue = "0")Long idx, Model model){
        model.addAttribute("board_list",boardService.listDetail(idx));
        return "/board/list_detail";
    }

    @PutMapping("/list_update")
    @ResponseBody
    public Boolean list_update(@RequestBody Board board){
        boardService.boardUpdate(board);
        return true;
    }

    @DeleteMapping("/list_delete")
    @ResponseBody
    public Boolean list_delete(@RequestBody @RequestParam(value = "idx", defaultValue = "0")Long idx){
        boardService.boardDelete(idx);
        return true;
    }

}
