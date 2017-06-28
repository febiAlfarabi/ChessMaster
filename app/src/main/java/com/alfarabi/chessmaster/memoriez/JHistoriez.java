package com.alfarabi.chessmaster.memoriez;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.alfarabi.chessmaster.model.History;

public class JHistoriez implements Serializable{

	private static final long serialVersionUID = 7024328206583186182L;
	private static JHistoriez instance ;
	private List<History> histories = new ArrayList<History>();
	
	static{
		instance = new JHistoriez();
	}
	
	public static JHistoriez getInstance() {
		return instance;
	}
	
	public List<History> getHistories() {
		return histories;
	}
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
	
	public static void addHistory(History history){
		instance.getHistories().add(0, history);
	}

}
