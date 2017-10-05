package automaton;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller {
	@FXML
	private GridPane grid;
	
	@FXML
	private MenuItem runTimer;
	
	@FXML
	private TextField setWidth;
	
	@FXML
	private TextField setHeight;
	
	@FXML
	private Button setDim;
	
	@FXML
	private MenuItem stopTimer;
	
	@FXML
	private MenuItem faster;
	
	@FXML
	private MenuItem slower;
	
	@FXML
	private MenuItem speed0;
	
	@FXML
	private MenuItem speed1;
	
	@FXML
	private MenuItem speed2;
	
	@FXML
	private MenuItem speed3;
	
	@FXML
	private MenuItem speed4;
	
	@FXML
	private MenuItem speed5;
	
	@FXML
	private TitledPane boardcontrols;
	
	@FXML
	private TitledPane neighborcontrols;
	
	@FXML
	private TitledPane golcontrols;
	
	@FXML
	private TitledPane wirecontrols;
	
	@FXML
	private Button nextStateBtn;
	
	@FXML
	private Slider speedsld;
	
	@FXML
	private Slider radiusslider;
	
	@FXML
	private Button randombtn;
	
	@FXML
	private CheckBox boardwrapbox;
	
	@FXML
	private ChoiceBox<String> neighborhoodchoice;
	
	@FXML
	private ChoiceBox<String> structurebox;
	
	@FXML
	private TextField rulestring;
	
	@FXML
	private Button initState;
	
	@FXML
	private CheckBox quadstatebox;
	
	@FXML
	private Button addstrbtn;
	
	ObservableList<String> neighborhoods = FXCollections.observableArrayList("Von Neuman Neighbourhood","Moor Neighbourhood");
	ObservableList<String> structures = FXCollections.observableArrayList("Block","Beehive","Loaf","Boat","Blinker","Toad","Beacon","Pulsar","Pentadecathlon", "Glider","LWSS");

	Timeline timeline;
	
	int width = 52; //52x34  8x5
	int height = 34;
	
	int oneDimRow = 0;
	
	int speed = 500;

	Boolean quadLife = true;
	
	Coords2D lastClicked;
	
	LangtonCell langtonCell;
	Automaton automaton,automaton2;
	CellNeighborhood neighbors;
	RulesFactory rules;
	
	Map<Coords2D,Rectangle> cells = new HashMap<Coords2D, Rectangle>();
	
    @FXML
    public void initialize() {
    	initializeGUIBoard();
    	
    	initializeGameOfLife();
		
    	mapModelToGUI();
    	
    	initializeTimeline();

		lastClicked = new Coords2D(1,1);
    }
    
    public void initializeGameOfLife() {
       	CellStateFactory stateFactory = null;
    	
    	neighbors = new MoorNeighbourhood(width, height, true, 1);
    	automaton = new GameOfLife(neighbors, width, height);
    		
    	rules = new RulesFactory("23/3");
    	stateFactory = new GeneralStateFactory(Main.emptyBoardInitialisation(width, height, BinaryState.DEAD));
    	((GameOfLife) automaton).setRules(rules);
    	((GameOfLife) automaton).setQuadLife(quadLife);

    	automaton.setMapFromCellFactory((GeneralStateFactory) stateFactory);	
    	golcontrols.setVisible(true);
    	wirecontrols.setVisible(false);
    	mapModelToGUI();
    }
    
    public void initializeWireWorld() {
    	golcontrols.setVisible(false);
    	wirecontrols.setVisible(true);
    	neighbors = new MoorNeighbourhood(width, height, true, 1);
		automaton = new WireWorld(neighbors, width, height);
		
		CellStateFactory stateFactory = new GeneralStateFactory(Main.statesInitialisationWIRE(width, height));
		
		automaton.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		mapModelToGUI();
    }
    
    public void initializeOneDim() {
    	golcontrols.setVisible(false);
    	wirecontrols.setVisible(false);
    	oneDimRow = 0;
    	if (automaton != null) clearButtonHandle();
		neighbors =  new OneDimNeighbourhood(width);
		automaton = new OneDimGame(neighbors, width);
		CellStateFactory stateFactory = new GeneralStateFactory(Main.randomStatesInitialisationOneDim(width));
		automaton.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		mapModelToGUI();
    }
    
	public void initializeGUIBoard() {
    	for (int j = 0; j < height; j++) {
	    	for (int i = 0; i < width; i++) {
	    		Coords2D c = new Coords2D(i,j);
	    		Rectangle r = new Rectangle(19, 19, Color.web("0xdddbde"));
	    	
	    		r.setOnMouseClicked(new EventHandler<MouseEvent>()
	            {
	                @Override
	                public void handle(MouseEvent t) {
	                    automaton.changeStateLive(c);
	                    lastClicked = c;
	                    mapModelToGUI();
	                }
	            });
	    		
	    		grid.add(r, i, j);
	    		cells.put(c, r);
	    	}
    	}
    	
		neighborhoodchoice.setItems(neighborhoods);
		neighborhoodchoice.setValue("Moor Neighbourhood");
		
		structurebox.setItems(structures);
		structurebox.setValue("Block");
	}
	
	public void initializeTimeline() {
		timeline = new Timeline(new KeyFrame(
		        Duration.millis(500),
		        ae -> nextStepGUI()));
		timeline.setCycleCount(Animation.INDEFINITE);
	}
	
	public void nextStepGUI() {
	    automaton = automaton.nextState();
	    automaton.setCellNeighborhood(neighbors);
	    if (automaton instanceof GameOfLife) {
	    	((GameOfLife) automaton).setRules(rules);
	    	((GameOfLife) automaton).setQuadLife(quadLife);
	    }
    	mapModelToGUI();
	}
	
    public void setCell(Coords2D coords, CellState state) {
    	Rectangle rect = cells.get(coords);
    	if (state instanceof BinaryState) {
    		if (state == BinaryState.ALIVE)
    			rect.setFill(Color.web("0x9c9a9b"));
    		else if (state == BinaryState.DEAD)
    			rect.setFill(Color.web("0xdddbde"));
    	} else if (state instanceof QuadState) {
    		if (state == QuadState.DEAD)
    			rect.setFill(Color.web("0xdddbde"));
    		else if (state == QuadState.RED)
    			rect.setFill(Color.web("0xff9f94"));
    		else if (state == QuadState.GREEN)
    			rect.setFill(Color.web("0xd0ff7e"));
    		else if (state == QuadState.BLUE)
    			rect.setFill(Color.web("0x8b99ff"));
    		else if (state == QuadState.YELLOW)
    			rect.setFill(Color.web("0xffec81"));
    	}  else if (state instanceof WireElectronState) {
			if (state == WireElectronState.VOID)
				rect.setFill(Color.web("0xdddbde"));
			if (state == WireElectronState.WIRE)
				rect.setFill(Color.web("0xffd86e"));
			if (state == WireElectronState.ELECTRON_HEAD)
				rect.setFill(Color.web("0x6565fc"));
			if (state == WireElectronState.ELECTRON_TAIL)
				rect.setFill(Color.web("0xff615c"));   
    	}
    }
    
    public void mapModelToGUI() {
    	if (automaton instanceof OneDimGame) {
    		for (int i = 0; i < width; i++) {

    			setCell(new Coords2D(i, oneDimRow), automaton.getMap().get(new Coords1D(i)));
    			
    		}
    	} else {
        	for (int i = 0; i < width; i++) {
        		for (int j = 0; j < height; j++) {
        			Coords2D coords = new Coords2D(i,j);
        				setCell(coords,automaton.getMap().get(coords));
        		}
        	}
    	}
    	
    	oneDimRow = (oneDimRow + 1) % height;

    }
    
    public void clearGUI() {
    	for (int i = 0; i < width; i++) {
    		for (int j = 0; j < height; j++) {
    			Coords2D coords = new Coords2D(i,j);
    				setCell(coords,BinaryState.DEAD);
    				cells.get(coords).setVisible(false);
    		}
    	}
    }
    
	/*
	 * GUI Objects handles
	 */
    
    public void setDimHandle() {
    		clearGUI();
	    	this.width = Integer.parseInt(setWidth.getText());
			this.height = Integer.parseInt(setHeight.getText());
			cells = new HashMap<Coords2D, Rectangle>();
			initializeGUIBoard();
    	if (automaton instanceof GameOfLife)
    		initializeGameOfLife();
    	if (automaton instanceof WireWorld)
    		initializeWireWorld();
    	if (automaton instanceof OneDimGame)
    		initializeOneDim();	
    }
    
	
	public void runTimerHandle() {
			timeline.play();
			nextStateBtn.setDisable(true);
	}
	
	public void stopTimerHandle() {
		timeline.stop();
		nextStateBtn.setDisable(false);
	}
	
	public void setSpeedHandle(ActionEvent e) {
		int newSpeed = speed;
		if (e.getSource() == speed0)
			newSpeed = 20;
		if (e.getSource() == speed1)
			newSpeed = 100;
		if (e.getSource() == speed2)
			newSpeed = 200;
		if (e.getSource() == speed3)
			newSpeed = 500;
		if (e.getSource() == speed4)
			newSpeed = 1000;
		if (e.getSource() == speed5)
			newSpeed = 2000;
		if (e.getSource() == faster) {
			if (newSpeed > 50)
				newSpeed = newSpeed - 50;
		}
		if (e.getSource() == slower) {
			if (newSpeed < 2000)
				newSpeed = newSpeed + 100;
		}
		
		timeline.stop();
		
		this.timeline = new Timeline(new KeyFrame(
		        Duration.millis(newSpeed),
		        ae -> nextStepGUI()));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		if (nextStateBtn.isDisable()) timeline.play();
		
		this.speed = newSpeed;
	}
	
    public void addStructureButtonHandle() {
    	if (automaton instanceof GameOfLife) {
    		//System.out.println(lastClicked);
    	
    		((GameOfLife) automaton).addStructure(lastClicked, structurebox.getValue());
    		mapModelToGUI();
    	}
    }
    
    public void neighborhoodChoiceHandle() {
    	if (neighborhoodchoice.getValue() == "Moor Neighbourhood") {
    		neighbors = new MoorNeighbourhood(width, height);
    	}
    	
    	if (neighborhoodchoice.getValue() == "Von Neuman Neighbourhood") {
    		neighbors = new VonNeumanNeighbourhood(width, height);
    	}   	
    }
    
    public void structureChoiceHandle() {
	
    }
    
    public void initialStateHandle() {
    	CellStateFactory stateFactory = new GeneralStateFactory(Main.statesInitialisationWIRE(width, height));
		automaton.setMapFromCellFactory((GeneralStateFactory) stateFactory);
		mapModelToGUI();
    }
    
    public void boardWrappingBoxHandle() {
    	neighbors.setBoardWrapping(boardwrapbox.isSelected());
    	//System.out.println(boardwrapbox.isSelected());
    }
	
	public void radiusSliderHandle() {
		neighbors.setRadius((int)radiusslider.getValue());
	}
	
	public void stopTimerButtonHandle() {
		timeline.stop();
	}
	
    public void nextStateHandle(ActionEvent event) {
    	//System.out.println("nextState");
    	nextStepGUI();
    }
    
    public void clearButtonHandle() {
    	
    	automaton.setMapFromCellFactory(new GeneralStateFactory(Main.emptyBoardInitialisation(width, height,automaton.getMap().get(new Coords2D(0,0)))));
    	mapModelToGUI();
    	
    	
    	
    }
    
    public void randomButtonHandle(ActionEvent event) {
    	if (automaton instanceof GameOfLife) {
        	if (quadLife)
        		automaton.setMapFromCellFactory(new GeneralStateFactory(Main.randomStatesInitialisation(width, height,QuadState.DEAD)));
        	else
        		automaton.setMapFromCellFactory(new GeneralStateFactory(Main.randomStatesInitialisation(width, height,BinaryState.DEAD)));
    	} else if (automaton instanceof WireWorld)
    		automaton.setMapFromCellFactory(new GeneralStateFactory(Main.randomStatesInitialisation(width, height,WireElectronState.VOID)));
    	
    	mapModelToGUI();
    	
    }
    
    public void quadStateBoxHandle() {
    	if (quadstatebox.isSelected()) {
    		this.quadLife = true;
    		((GameOfLife) automaton).setQuadLife(true);
    		clearButtonHandle();
    		randomButtonHandle(new ActionEvent());
    	} else {
    		//System.out.println("disabled");
    		this.quadLife = false;
    		((GameOfLife) automaton).setQuadLife(false);
    		clearButtonHandle();
    		randomButtonHandle(new ActionEvent());
    	}
    }
    
    public void ruleStringHandle() {
    	rules = new RulesFactory(rulestring.getText());
    }
    


}	