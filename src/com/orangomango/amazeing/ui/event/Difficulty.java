package com.orangomango.amazeing.ui.event;

public enum Difficulty {
	SUPER_EASY(10, 20), EASY(20, 30), MEDIUM(30, 40), HARD(40, 50), EXTRA_HARD(50, 60), INSANE(60, 70);

	private int minSize;
	private int maxSize;

	private Difficulty(int min, int max){
		this.minSize = min;
		this.maxSize = max;
	}

	public int getMaxSize(){
		return this.maxSize;
	}

	public int getMinSize(){
		return this.minSize;
	}
}