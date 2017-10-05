package automaton;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.sun.glass.ui.Window.Level;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * G³ówna klasa aplikacji, uruchamia GUI
 * @author arek_
 *
 */
public class Main extends Application {
	
	static int width=5;
	static int height=5;
	static int size=21;
	
	static CellStateFactory  stateFactory;
	
	public static void main(String[] args) {
		launch(args);
		//Main.runCLIOneDim();
	}
	
	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("mainState.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	/**
	 * Inicjalizacja mapy losowymi wartoœciami
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 * @param state stan
	 * @return mapa komórek indeksowana klas¹ CellCoordinates
	 */
	public static Map<CellCoordinates,CellState> randomStatesInitialisation(int width, int height, CellState state){
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		Random generator = new Random();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				int a = Math.abs(generator.nextInt() % 100);
				System.out.println(a);
				
				if (state instanceof BinaryState) {
					if(a>60)
						states.put(coords, BinaryState.ALIVE);
					else
						states.put(coords, BinaryState.DEAD);
				} else if (state instanceof QuadState) {
					if (a <= 15)
						states.put(coords, QuadState.RED);
					if (a > 15 && a <= 30)
						states.put(coords, QuadState.GREEN);
					if (a > 30 && a <= 45)
						states.put(coords, QuadState.BLUE);
					if (a > 45 && a <= 60)
						states.put(coords, QuadState.YELLOW);
					if (a > 60)
						states.put(coords, QuadState.DEAD);
				} //TODO: dodaæ dla innych states
			
			}
		}
		return states;
	}
	
	/**
	 * inicjaluzje mapê pustymi komórkami
	 * @param width szerokosc
	 * @param height wysokosc
	 * @param state stan (do ustalenia typu enum)
	 * @return mapa komórek
	 */
	public static Map<CellCoordinates,CellState> emptyBoardInitialisation(int width, int height, CellState state){
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		Random generator = new Random();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				int a = generator.nextInt();

				if (state instanceof BinaryState)
					states.put(coords, BinaryState.DEAD);
				else if (state instanceof QuadState)
					states.put(coords, QuadState.DEAD);
				else if (state instanceof WireElectronState)
					states.put(coords, WireElectronState.VOID);
				//TODO: dokonczyc dla innych jezeli jest taka potrzeba
			}
		}
		return states;
	}
	
	/**
	 * dodaje na mapê automatu WireWorld przyk³adow¹ strukturê - dwa oscylatory z bramk¹ logiczn¹
	 * @param width
	 * @param height
	 * @return
	 */
	public static Map<CellCoordinates,CellState> statesInitialisationWIRE(int width, int height){
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				states.put(new Coords2D(i,j), WireElectronState.VOID);
			}
		}
		
		states.put(new Coords2D(0,1), WireElectronState.WIRE);
		states.put(new Coords2D(0,7), WireElectronState.WIRE);
		
		for (int x = 1; x <=8; x++ )
			states.put(new Coords2D(x,0), WireElectronState.WIRE);
	
		for (int x = 1; x <=8; x++ )
			states.put(new Coords2D(x,2), WireElectronState.WIRE);
		
		for (int x = 1; x <=8; x++ )
			states.put(new Coords2D(x,6), WireElectronState.WIRE);
		
		for (int x = 1; x <=8; x++ )
			states.put(new Coords2D(x,8), WireElectronState.WIRE);
		
		for (int x = 9; x <=14; x++ )
			states.put(new Coords2D(x,1), WireElectronState.WIRE);
		
		for (int x = 9; x <=14; x++ )
			states.put(new Coords2D(x,7), WireElectronState.WIRE);
		
		states.put(new Coords2D(15,2), WireElectronState.WIRE);
		states.put(new Coords2D(15,3), WireElectronState.WIRE);
		states.put(new Coords2D(15,5), WireElectronState.WIRE);
		states.put(new Coords2D(15,6), WireElectronState.WIRE);
		
		states.put(new Coords2D(14,3), WireElectronState.WIRE);
		states.put(new Coords2D(14,4), WireElectronState.WIRE);
		states.put(new Coords2D(14,5), WireElectronState.WIRE);
		
		states.put(new Coords2D(16,3), WireElectronState.WIRE);
		states.put(new Coords2D(16,5), WireElectronState.WIRE);
		
		states.put(new Coords2D(17,3), WireElectronState.WIRE);
		states.put(new Coords2D(17,4), WireElectronState.WIRE);
		states.put(new Coords2D(17,5), WireElectronState.WIRE);
		
		states.put(new Coords2D(18,4), WireElectronState.WIRE);
		states.put(new Coords2D(19,4), WireElectronState.WIRE);
		states.put(new Coords2D(20,4), WireElectronState.WIRE);
		states.put(new Coords2D(21,4), WireElectronState.WIRE);
		
		states.put(new Coords2D(7,0), WireElectronState.ELECTRON_TAIL);
		states.put(new Coords2D(8,0), WireElectronState.ELECTRON_HEAD);
		
		states.put(new Coords2D(1,0), WireElectronState.ELECTRON_TAIL);
		states.put(new Coords2D(2,0), WireElectronState.ELECTRON_HEAD);
		
		states.put(new Coords2D(1,6), WireElectronState.ELECTRON_TAIL);
		states.put(new Coords2D(2,6), WireElectronState.ELECTRON_HEAD);
		
		states.put(new Coords2D(5,8), WireElectronState.ELECTRON_TAIL);
		states.put(new Coords2D(4,8), WireElectronState.ELECTRON_HEAD);
		
		return states;
	}
	

	//METODY POMOCNICZE NIEWYMAGANE DO DZIA£ANIA W TRYBIE GRAFICZNYM
	public static void displayMapOneDim(GeneralStateFactory gsf){
		for (int i = 0; i < size; i++){
			
				Coords1D coords = new Coords1D();
				coords.x = i;
				
				if(gsf.states.get(coords) == BinaryState.ALIVE)
					System.out.print("o");
				else
					System.out.print("x");
			}
			System.out.println("");
		
	}
	
	public static void displayMapCells(Map<CellCoordinates, CellState> cells){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				if(cells.get(coords) == BinaryState.ALIVE)
					System.out.print("o");
				else if(cells.get(coords) == BinaryState.DEAD)
					System.out.print("x");
				else if(cells.get(coords) == null)
					System.out.print("n");
				else
					System.out.print("z");
			}
			System.out.println("");
		}
	}
	
	public static Map<CellCoordinates,CellState> randomStatesInitialisationOneDim(int size){
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		Random generator = new Random();
		for (int i = 0; i < size; i++){
				Coords1D coords = new Coords1D();
				coords.x = i;
				
				if(i == 26)
					states.put(coords, BinaryState.ALIVE);
				else
					states.put(coords, BinaryState.DEAD);
			
		}
		return states;
	}
	
	
	

	
	public static void displayMapCellsWIRE(int width, int height, Map<CellCoordinates,CellState> cells){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				if(cells.get(coords) == WireElectronState.ELECTRON_HEAD)
					System.out.print("n");
				else if(cells.get(coords) == WireElectronState.VOID)
					System.out.print(" ");
				else if(cells.get(coords) == WireElectronState.WIRE)
					System.out.print("x");
				else if(cells.get(coords) == WireElectronState.ELECTRON_TAIL)
					System.out.print("r");
				else if(cells.get(coords) == null)
					System.out.print("a");
			}
			System.out.println("");
		}
	}

	
	public static void runCLIGameOfLife() {
		CellNeighborhood neighborStrategy = new MoorNeighbourhood(width, height);
		
		stateFactory = new GeneralStateFactory(randomStatesInitialisation(width, height,QuadState.DEAD));
		
		GameOfLife gof = new GameOfLife(neighborStrategy, width, height, true, new RulesFactory("23/3"));
	
		gof.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		gof.displayMapCells(width, height,QuadState.DEAD);

		for(int i=0;i<5;i++){
			
			System.out.println("");
			
			gof = (GameOfLife) gof.nextState();
			gof.displayMapCells(width, height,QuadState.DEAD);
		}
	}
	
	public static void runCLIOneDim() {
		OneDimNeighbourhood neighborStrategy = new OneDimNeighbourhood(size);
		OneDimGame oneDimGame = new OneDimGame(neighborStrategy, size);
		stateFactory = new GeneralStateFactory(randomStatesInitialisationOneDim(size));
		
		oneDimGame.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		oneDimGame.displayMapCellsOneDim(size);
		for(int i=0;i<10;i++){
			oneDimGame = (OneDimGame) oneDimGame.nextState();
			oneDimGame.displayMapCellsOneDim(size);
		}
	}
	
	public static void runCLIWireWorld() {
		CellNeighborhood neighborStrategy = new MoorNeighbourhood(8, 3);
		stateFactory = new GeneralStateFactory(statesInitialisationWIRE(8, 3));
		WireWorld wireWorld = new WireWorld((CellNeighborhood) stateFactory, 8, 3);
		wireWorld.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		wireWorld.displayMapCellsWIRE(8, 3);
		
		for(int i=0;i<10;i++){
			wireWorld = (WireWorld) wireWorld.nextState();
			wireWorld.displayMapCellsWIRE(8, 3);
		}
	}
	




}
