package tools;

import spelelementen.Level;

public class LevelQueue {
	public static int RANDOMLEVEL = -1;
	public static int PLAY = 1;
	private int level = 1;

	public LevelQueue(int level) {
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
		if (level == 7)
			level = 1;
		return output;
	}

	public int getCurrentLevel() {
		return level;
	}
}
