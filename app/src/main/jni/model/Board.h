/*
 * Board.h
 *
 *  Created on: Oct 2, 2014
 *      Author: DSI-1
 */


#ifndef BOARD_H_
#define BOARD_H_

#include <model/Piece.h>
#include <stl/_map.h>

using namespace std;

class Board {
	map<long, Piece> pieces ;
	map<string, bool> columns ;
	map<string, long> pieceInColumns;

public:
	Board();
	virtual ~Board();
	map<long, Piece> getPieces();
	void setPieces(map<long, Piece> pieces);
	map<string, bool> getColumns();
	void setColumns(map<string, bool> columns);
	map<string, long> getPieceInColumns();
	void setPieceInColumns(map<string, long> pieceInColumns);
};

#endif /* BOARD_H_ */
