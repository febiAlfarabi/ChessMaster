/*
 * Piece.h
 *
 *  Created on: Oct 2, 2014
 *      Author: DSI-1
 */

#ifndef PIECE_H_
#define PIECE_H_

using namespace std;

class Piece {

	long id ;
	char pTag ;
	string loc ;
	bool pMine ;

public:
	Piece();
	virtual ~Piece();
	long getId();
	void setId(long id);
	char getPTag();
	void setPTag(char pTag);
	string getLoc();
	void setLoc(string loc);
	bool isMine();
	void setMine(bool mine);
};

#endif /* PIECE_H_ */
