package tools;

import spelelementen.Level;

public class LevelQueue {
	public static int RANDOMLEVEL = -1;
	public static int REALGAME = 0;
	private int level;
	
	private boolean realgame = false;
	private boolean gamefinished = false;

	public LevelQueue(int level) {
		if(level == REALGAME) {
			realgame = true;
			this.level = 1;
		} 
		else 
			this.level = level;
	}

	public Level NextLevel() {
		Level output;
		if(level == -1)
			output = RandomLevel.GenerateRandomLevel();
		else {
			LevelBuilder levelbuilder = new LevelBuilder(level);
			level++;
			output = levelbuilder.Build();
		}
		if (level == 7) {
			level = 1;
			gamefinished = true;
		}
		return output;
	}
	
	public boolean NextIsWinPanel() {
		return gamefinished && realgame;
	}

	public int getCurrentLevel() {
		return level;
	}

	public boolean isPlay() {
		return realgame;
	}

}
