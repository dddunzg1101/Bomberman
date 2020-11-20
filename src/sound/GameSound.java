package sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class GameSound {
	public static GameSound instance;

	public static final String MENU = "menu.wav";
	public static final String PLAYGAME = "play_game.wav";  //Da sua
	public static final String BOMB = "newbomb.wav";    // Da sua
	public static final String BOMBER_DIE = "bomber_die.wav";	//Da sua
	public static final String MONSTER_DIE = "monster_die.wav"; //Da sua
	public static final String BOMB_BANG = "bomb_bang.wav"; //Da sua
	public static final String ITEM = "item.wav";   // Da sua
	public static final String WIN = "win.wav";     //Da sua
	public static final String LOSE = "lose.wav";   //Da sua
	private HashMap<String, AudioClip> audioMap;

	public GameSound() {
		audioMap = new HashMap<>();
		loadAllAudio();
	}

	public static GameSound getInstance() {
		if (instance == null) {
			instance = new GameSound();
		}
		return instance;
	}

	public void loadAllAudio() {
		putAudio(MENU);
		putAudio(PLAYGAME);
		putAudio(BOMB);
		putAudio(MONSTER_DIE);
		putAudio(BOMBER_DIE);
		putAudio(BOMB_BANG);
		putAudio(ITEM);
		putAudio(WIN);
		putAudio(LOSE);
	}

	public void stop() {
		getAudio(MENU).stop();;
		getAudio(PLAYGAME).stop();
		getAudio(BOMB).stop();
		getAudio(BOMB_BANG).stop();
		getAudio(WIN).stop();
		getAudio(LOSE).stop();
	}

	public void putAudio(String name) {
		AudioClip auClip = Applet.newAudioClip(GameSound.class.getResource(name));
		audioMap.put(name, auClip);
	}

	public AudioClip getAudio(String name) {
		return audioMap.get(name);
	}
}
