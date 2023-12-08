package hu.czmarkob;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CubeGame {

	private final int id;

	private final List<CubeGameSet> sets;

	public boolean isPossible() {
		for (CubeGameSet set : sets) {
			if (!set.isPossible()) {
				return false;
			}
		}
		return true;
	}
}
