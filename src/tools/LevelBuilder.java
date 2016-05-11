package tools;

public class LevelBuilder {
	private enum Levels{L1,L2,L3,L4,L5,L6,L7,L8,L9,L10};
	private Levels level;
	public LevelBuilder(Levels level){
		this.level = level;
	}
	public void Build(Levels level){
		
	}
	
	public Levels getLevel(){
		return level;
	}
	public void setLevel(Levels level){
		this.level = level;
	}
}
