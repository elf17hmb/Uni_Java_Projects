package whiteboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
/**
 * Eine simple graphische Zeichenoberfl‰che
 * 
 * @author Eldiar
 *
 */
public class WhiteboardFX extends Application {
	/** Flag das angibt, ob Fenster geschlossen wurde */
	private boolean windowExited = false;
	Stage primaryStage;
	Canvas canvas;
	BorderPane root;
	Pane drawdesk;

	private List<Shape> shapes = Collections.synchronizedList(new ArrayList());

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		initFrame();
		demo();
		Button button = new Button("weg mit der Form");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (drawdesk.getChildren().size() > 0) {
					System.out.println(
							"Children>0: " + drawdesk.getChildren().size());
					wipeShape(drawdesk.getChildren()
							.get((drawdesk.getChildren().size() - 1)));
				}
			}
		});
		button.setPrefSize(300, 10);
		HBox buttonBox = new HBox();
		buttonBox.getChildren().add(button);
		buttonBox.setAlignment(Pos.CENTER);
		root.setBottom(buttonBox);

	}
	public void initFrame() {
		primaryStage.setTitle("WhiteBoard");
		root = new BorderPane();

		drawdesk = new Pane();

		ScrollPane sp = new ScrollPane(drawdesk);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		root.setCenter(sp);
		root.setPrefSize(800, 600);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			windowExited = true;
			System.out.println("Stage is closing");
			System.exit(0);
		});

		primaryStage.show();

	}

	public void demo() {
		drawLine(0, 0, 800, 1000);
		drawLine(0, 0, 800, 2000);
		drawArc(0, 0, 800, 1000, 100, Color.RED, false);
		drawPoint(400, 900, Color.GREEN);
		drawEllipse(90, 350, 50, 200);
		drawEllipse(200, 450, 50, 30, Color.AQUAMARINE, true, 10);
		drawCircle(40, 120, 100);
		drawCircle(400, 120, 100, Color.BLUEVIOLET, true);

		double[] x = {50, 120, 150, 300};
		double[] y = {50, 200, 120, 70};
		drawPolygon(x, y, Color.CRIMSON, true, 0.0);

		drawRectangle(156, 543, 100, 200, Color.CHOCOLATE, true, 50);

		drawRectangle(0, 540, 2000, 540);
	}

	/** Baut Windows wieder auf, falls der Frame geschlossen wurde. */
	private void rebuild() {
		if (windowExited) {
			windowExited = false;
			initFrame();
		}
	}

	public Object drawLine(double xfrom, double yfrom, double xto, double yto) {
		return drawLine(xfrom, yfrom, xto, yto, Color.BLACK);
	}

	public Object drawLine(double xfrom, double yfrom, double xto, double yto,
			Color color) {
		Line line = new Line();
		rebuild();
		line.setStartX(xfrom);
		line.setStartY(yfrom);
		line.setEndX(xto);
		line.setEndY(yto);
		line.setStroke(color);

		shapes.add(line);

		drawdesk.getChildren().add(line);
		return line;

	}

	public Object drawArc(double xfrom, double yfrom, double xto, double yto,
			double excentricity) {
		return drawArc(xfrom, yfrom, xto, yto, excentricity, Color.BLACK,
				false);
	}

	public Object drawArc(double xfrom, double yfrom, double xto, double yto,
			double excentricity, Color color,
			boolean solid) {
		double centerX, centerY, radiusX, radiusY, xs, ys, d, dx, dy, r, exx,
				exy;
		rebuild();
		centerX = (xfrom + xto) / 2;
		centerY = (yfrom + yto) / 2;
		dx = xto - xfrom;
		dy = yto - yfrom;
		d = Math.sqrt(dx * dx + dy * dy);
		r = excentricity;
		exx = dy * r / d;
		exy = dx * r / d;
		xs = centerX - 2 * exx;
		ys = centerY + 2 * exy;
		QuadCurve quad = new QuadCurve();
		quad.setStartX(xfrom);
		quad.setStartY(yfrom);
		quad.setEndX(xto);
		quad.setEndY(yto);
		quad.setControlX(xs);
		quad.setControlY(ys);
		quad.setStroke(Color.RED);
		quad.setFill(Color.TRANSPARENT);

		if (solid) {
			quad.setFill(color);
		}

		shapes.add(quad);

		drawdesk.getChildren().add(quad);
		return quad;

	}

	public Object drawPoint(double x, double y) {
		return drawPoint(x, y, Color.BLACK);
	}

	public Object drawPoint(double x, double y, Color color) {
		rebuild();
		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(x);
		ellipse.setCenterY(y);
		ellipse.setRadiusX(2);
		ellipse.setRadiusY(2);
		ellipse.setStroke(color);
		ellipse.setFill(color);
		drawdesk.getChildren().add(ellipse);
		return ellipse;
	}

	public Object drawEllipse(double x, double y, double hx, double hy) {
		return drawEllipse(x, y, hx, hy, Color.BLACK, false, 0.0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param hx
	 * @param hy
	 * @param color
	 * @param solid
	 * @param rotation
	 *            in Winkelmaﬂ!
	 * @return
	 */
	public Object drawEllipse(double x, double y, double hx, double hy,
			Color color, boolean solid, double rotation) {
		rebuild();
		Ellipse ellipse = new Ellipse();
		ellipse.setCenterX(x);
		ellipse.setCenterY(y);
		ellipse.setRadiusX(hx);
		ellipse.setRadiusY(hy);
		ellipse.setStroke(color);

		if (solid) {
			ellipse.setFill(color);
		} else {
			ellipse.setFill(Color.TRANSPARENT);
		}

		ellipse.setRotate(rotation);
		drawdesk.getChildren().add(ellipse);
		return ellipse;

	}
	public Object drawCircle(double x, double y, double radius, Color color,
			boolean solid) {
		return drawEllipse(x, y, radius, radius, color, solid, 0.0);
	}

	public Object drawCircle(double x, double y, double radius) {
		return drawEllipse(x, y, radius, radius, Color.BLACK, false, 0.0);
	}

	public Object drawPolygon(double[] x, double[] y) {
		return drawPolygon(x, y, Color.BLACK, false, 0.0);
	}

	public Object drawPolygon(double[] x, double y[], Color color,
			boolean solid, double rotation) {
		rebuild();
		Polygon polygon = new Polygon();
		Double[] xyPoints = new Double[x.length + y.length];
		List<Double> doulbeList = new ArrayList<>();
		int xIndex = 0;
		int yIndex = 0;
		for (int i = 0; i < (x.length + y.length); i++) {
			if (i % 2 == 0) {
				polygon.getPoints().add(x[xIndex]);
				xIndex++;
			} else {
				polygon.getPoints().add(y[yIndex]);
				yIndex++;
			}
		}
		polygon.setStroke(color);
		if (solid) {
			polygon.setFill(color);
		} else {
			polygon.setFill(Color.TRANSPARENT);
		}
		polygon.setRotate(rotation);

		drawdesk.getChildren().add(polygon);
		return polygon;

	}

	public Object drawRectangle(double x, double y, double hx, double hy) {
		return drawRectangle(x, y, hx, hy, Color.BLACK, false, 0.0);
	}

	/**
	 * 
	 * @param x
	 *            Linke obere Ecke
	 * @param y
	 * @param hx
	 * @param hy
	 * @param color
	 * @param solid
	 * @param rotation
	 * @return
	 */
	public Object drawRectangle(double x, double y, double hx, double hy,
			Color color, boolean solid, double rotation) {
		rebuild();
		Rectangle r = new Rectangle();
		r.setX(x);
		r.setY(y);
		r.setWidth(hx);
		r.setHeight(hy);
		r.setStroke(color);
		if (solid) {
			r.setFill(color);
		} else {
			r.setFill(Color.TRANSPARENT);
		}
		r.setRotate(rotation);

		drawdesk.getChildren().add(r);

		return r;
	}

	public void wipeShape(Object o) {
		drawdesk.getChildren().remove(o);
	}

}
