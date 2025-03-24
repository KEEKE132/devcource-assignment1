package me.feel5n.board;

import me.feel5n.customException.DuplicateBoardNameException;
import me.feel5n.customException.InvalidValueException;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
    private List<Board> boards = new ArrayList<>();

    public void add(Board board) throws InvalidValueException {
        if (get(board.getTitle()) != null) {
            throw new InvalidValueException("boardName", new DuplicateBoardNameException("boardName Duplicated"));
        }
        boards.add(board);
    }

    public Board get(Long id) {
        for (Board board : boards) {
            if (board.getId().equals(id)) return board;
        }
        return null;
    }

    public Board get(String name) {
        for (Board board : boards) {
            if (board.getTitle().equals(name)) return board;
        }
        return null;
    }

    public boolean remove(Long id) {
        return boards.removeIf(board -> board.getId().equals(id));
    }

    public boolean replace(Long id, Board board) {
        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).getId().equals(id)) {
                boards.set(i, board);
                return true;
            }
        }
        return false;
    }

}
