
#include "Piece.h"

Piece::Piece() {

}

Piece::~Piece() {

}

long getId() const {
	return Piece::id;
}

void setId(long id) {
	this->Piece::id = id;
}

string getLoc() const {
	return Piece::loc;
}

void setLoc(string loc) {
	this->Piece::loc = loc;
}

bool isMine() const {
	return Piece::pMine;
}

void setMine(bool mine) {
	this->Piece::pMine = mine;
}

char getTag() const {
	return Piece::pTag;
}

void setTag(char tag) {
	this->Piece::pTag = tag;
}

