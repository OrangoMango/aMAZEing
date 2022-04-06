package com.orangomango.amazeing.game;

public enum Direction {
	NESW, NONE, N, E, S, W, NE, ES, SW, NW, NES, ESW, NSW, NEW, EW, NS;

	public static Direction addConn(Direction d, Direction a){
		if (d == NESW){
			return NESW;
		}
		if (d == NONE){
			return a;
		}
		StringBuilder out = new StringBuilder();
		String[] options = new String[]{"N", "E", "S", "W"};
		for (String o : options){
			if (d.toString().contains(o) || a.toString().contains(o)){
				out.append(o);
			}
		}
		return valueOf(out.toString());
	}

	public static Direction removeConn(Direction d, Direction r){
		if (d == NONE){
			return d;
		}
		if (d == r){
			return NONE;
		}
		if (!d.toString().contains(r.toString())){
			return d;
		}
		StringBuilder o = new StringBuilder(d.toString());
		o.deleteCharAt(d.toString().indexOf(r.toString()));
		return valueOf(o.toString());
	}

	public static Direction getComplementar(Direction d){
		if (d == NONE){
			return NESW;
		}
		StringBuilder builder = new StringBuilder();
		String[] dirs = new String[]{"N", "E", "S", "W"};
		for (String dir : dirs){
			if (!d.toString().contains(dir)){
				builder.append(dir);
			}
		}
		String out = builder.toString();
		if (out.equals("")){
			return NONE;
		} else {
			return valueOf(out);
		}
	}
}