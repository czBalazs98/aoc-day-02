package hu.czmarkob;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CubeGamesInputResolver {

	private final Path inputPath;

	public List<CubeGame> resolve() {
		List<CubeGame> cubeGames = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(inputPath);
			for (String line : lines) {
				String[] parts = line.split(": ");
				int gameId = getId(parts[0]);
				List<CubeGameSet> sets = new ArrayList<>();
				CubeGame cubeGame = new CubeGame(gameId, sets);

				String[] gameParts = parts[1].split("; ");
				for (String gamePart : gameParts) {
					String[] pullParts = gamePart.split(", ");
					CubeGameSet cubeGameSet = new CubeGameSet();
					sets.add(cubeGameSet);
					for (String pullPart : pullParts) {
						String[] numAndColor = pullPart.split(" ");
						setNumberOfBall(cubeGameSet, numAndColor[1], Integer.parseInt(numAndColor[0]));
					}
				}
				cubeGames.add(cubeGame);
			}
			return cubeGames;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int calculateSumOfIds(List<CubeGame> cubeGames) {
		int sum = 0;
		for (CubeGame cubeGame : cubeGames) {
			if (cubeGame.isPossible()) {
				sum += cubeGame.getId();
			}
		}
		return sum;
	}

	public int calculateSumOfPowers(List<CubeGame> cubeGames) {
		int sum = 0;
		for (CubeGame cubeGame : cubeGames) {
			CubeGameSet minSet = getMinimumSetOfCubes(cubeGame);
			sum += minSet.getPower();
		}
		return sum;
	}

	private CubeGameSet getMinimumSetOfCubes(CubeGame cubeGame) {
		List<CubeGameSet> sets = cubeGame.getSets();
		int maxRed = sets.get(0).getNumberOfRed();
		int maxBlue = sets.get(0).getNumberOfBlue();
		int maxGreen = sets.get(0).getNumberOfGreen();

		for (CubeGameSet set : sets) {
			if (set.getNumberOfRed() > maxRed) {
				maxRed = set.getNumberOfRed();
			}

			if (set.getNumberOfBlue() > maxBlue) {
				maxBlue = set.getNumberOfBlue();
			}

			if (set.getNumberOfGreen() > maxGreen) {
				maxGreen = set.getNumberOfGreen();
			}
		}

		return new CubeGameSet(maxBlue, maxGreen, maxRed);
	}

	private int getId(String gameIdString) {
		String[] parts = gameIdString.split("Game ");
		return Integer.parseInt(parts[1]);
	}

	private void setNumberOfBall(CubeGameSet cubeGameSet, String color, int number) {
		switch (color) {
			case "blue":
				cubeGameSet.setNumberOfBlue(number);
				break;
			case "red":
				cubeGameSet.setNumberOfRed(number);
				break;
			case "green":
				cubeGameSet.setNumberOfGreen(number);
				break;
			default:
				break;
		}
	}
}
