package com.board.web.service;

import com.board.web.domain.Board;
import com.board.web.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    /*public List<Board> boardFindAll(){
       return boardRepository.findAll();
    }*/

     public void boardSave(Board board){
        board.setCreatedDate(LocalDateTime.now());
        boardRepository.save(board);
     }

     public Page<Board> boardListFind(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
     }

     public Board listDetail(Long idx){
         return boardRepository.findById(idx).orElse(new Board());
     }

     public void boardUpdate(Board board){
         board.setUpdatedDate(LocalDateTime.now());
         boardRepository.save(board);
     }

     public void boardDelete(Long idx){
         boardRepository.deleteById(idx);
     }

}
