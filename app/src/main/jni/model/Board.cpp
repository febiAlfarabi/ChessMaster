/*
 * Board.cpp
 *
 *  Created on: Oct 2, 2014
 *      Author: DSI-1
 */

#include "Board.h"

Board::Board() {
	// TODO Auto-generated constructor stub

}

Board::~Board() {
	// TODO Auto-generated destructor stub
	this->columns = (map<string, bool>*)malloc(sizeof(columns));
	this->pieces = (map<long, Piece>)malloc(sizeof(pieces));
	this->pieceInColumns = (map<string, long>)malloc(sizeof(pieceInColumns));
}

map getPieces(){
	return Board::pieces;
}
void setPieces(map<long, Piece> pieces){
	this->Board::pieces = pieces;
}

map getColumns(){
	return Board::columns ;
}
void setColumns(map<string, bool> columns){
	this->Board::columns = columns;
}

map getPieceInColumns(){
	return Board::pieceInColumns;
}
void setPieceInColumns(map<string, long> pieceInColumns){
	this->Board::pieceInColumns = pieceInColumns;
}

