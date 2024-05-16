package com.example.classdemo3.board.impl;



import com.example.classdemo3.board.AbstractBoard;
import com.example.classdemo3.utils.GameConf;
import com.example.classdemo3.view.Piece;

import java.util.ArrayList;
import java.util.List;


public class HardBoard extends AbstractBoard {
    @Override
    protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
        // 创建一个6x8的Piece
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if(i<3 || i>4) {
                    Piece piece = new Piece(i, j);
                    notNullPieces.add(piece);
                }

            }
        }
        return notNullPieces;
    }
}