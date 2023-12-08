package hu.czmarkob;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) throws URISyntaxException {
		Path path = Paths.get(ClassLoader.getSystemResource("input.txt").toURI());
		CubeGamesInputResolver cubeGamesInputResolver = new CubeGamesInputResolver(path);
		List<CubeGame> cubeGames = cubeGamesInputResolver.resolve();
		System.out.println(cubeGamesInputResolver.calculateSumOfIds(cubeGames));
		System.out.println(cubeGamesInputResolver.calculateSumOfPowers(cubeGames));
	}
}